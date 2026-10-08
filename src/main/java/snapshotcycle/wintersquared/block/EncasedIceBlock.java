package snapshotcycle.wintersquared.block;

import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.TypedEntityData;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.level.storage.loot.LootParams;
import org.jspecify.annotations.Nullable;
import snapshotcycle.wintersquared.block.entity.EncasedIceBlockEntity;
import snapshotcycle.wintersquared.init.ModBlocks;

import java.util.List;
import java.util.Optional;

public class EncasedIceBlock extends BaseEntityBlock {

    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;

    public EncasedIceBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new EncasedIceBlockEntity(worldPosition,blockState);
    }

    public static int getBlockHeight(EntityType<?> type) {
        return (int) Math.ceil(type.getHeight());
    }
    public static int getBlockWidth(EntityType<?> type) {
        return (int) Math.ceil(type.getWidth());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING});
    }

    @Override
    public @org.jetbrains.annotations.Nullable BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        Direction direction = blockPlaceContext.getHorizontalDirection();
        if (blockPlaceContext.getItemInHand().has(DataComponents.BLOCK_ENTITY_DATA)) {
            Optional<TypedEntityData<EntityType<?>>> typedData = blockPlaceContext.getItemInHand().get(DataComponents.BLOCK_ENTITY_DATA).copyTagWithoutId().read("entityData", TypedEntityData.codec(EntityType.CODEC));
            if (typedData.isPresent()) {
                Level level = blockPlaceContext.getLevel();
                EntityType<?> type = typedData.get().type();
                for (int x = 0; x < getBlockWidth(type); x++) {
                    for (int y = 0; y < getBlockHeight(type); y++) {
                        for (int z = 0; z < EncasedIceBlock.getBlockHeight(type); z++) {
                            BlockPos pos = blockPlaceContext.getClickedPos().relative(direction, x).relative(direction, z).above(y);
                            if (level.getBlockState(pos).canBeReplaced(blockPlaceContext) && level.getWorldBorder().isWithinBounds(pos)) {
                                continue;
                            } else {
                                return null;
                            }
                        }
                    }
                }
            }
        }
        return super.getStateForPlacement(blockPlaceContext).setValue(FACING, direction);
    }

    public static void chainDestroy(LevelAccessor level, EncasedIceBlockEntity entity, BlockPos pos) {
        destroyIfSimilar(level,(EncasedIceBlockEntity) entity,pos.above());
        destroyIfSimilar(level,(EncasedIceBlockEntity) entity,pos.below());
        destroyIfSimilar(level,(EncasedIceBlockEntity) entity,pos.north());
        destroyIfSimilar(level,(EncasedIceBlockEntity) entity,pos.south());
        destroyIfSimilar(level,(EncasedIceBlockEntity) entity,pos.east());
        destroyIfSimilar(level,(EncasedIceBlockEntity) entity,pos.west());
    }

    public static void destroyIfSimilar(LevelAccessor level, EncasedIceBlockEntity entity, BlockPos pos1) {
        if (level.getBlockState(pos1).is(ModBlocks.ENCASED_ICE)) {
            if (((EncasedIceBlockEntity) level.getBlockEntity(pos1)) == null) return;
            if (entity.sameAs(((EncasedIceBlockEntity) level.getBlockEntity(pos1)))) {
                level.destroyBlock(pos1, false);
                chainDestroy(level,entity,pos1);
            }
        }
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity by, ItemStack itemStack) {
        super.setPlacedBy(level, pos, state, by, itemStack);
        if (itemStack.has(DataComponents.BLOCK_ENTITY_DATA)) {
            Optional<TypedEntityData<EntityType<?>>> typedData = itemStack.get(DataComponents.BLOCK_ENTITY_DATA).copyTagWithoutId().read("entityData", TypedEntityData.codec(EntityType.CODEC));
            if (typedData.isPresent()) {
                EntityType<?> type = typedData.get().type();
                for (int x = 0; x < getBlockWidth(type); x++) {
                    for (int y = 0; y < getBlockHeight(type); y++) {
                        for (int z = 0; z < getBlockWidth(type); z++) {
                            BlockPos newPos = pos.relative(state.getValue(FACING), x).relative(state.getValue(FACING).getClockWise(), z).above(y);
                            level.setBlock(newPos, state, 3);
                            ((EncasedIceBlockEntity) level.getBlockEntity(newPos)).setEntityData(((EncasedIceBlockEntity) level.getBlockEntity(pos)).entityData);
                            state.updateNeighbourShapes(level, pos, 3);
                        }
                    }
                }
            }
            ((EncasedIceBlockEntity) level.getBlockEntity(pos)).isParent = true;
        }
    }
}
