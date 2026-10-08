package snapshotcycle.wintersquared.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.item.component.TypedEntityData;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;
import snapshotcycle.wintersquared.init.ModBlockEntities;

import java.util.Optional;
import java.util.UUID;

public class EncasedIceBlockEntity extends BlockEntity {

    public boolean isParent = false;
    public TypedEntityData<EntityType<?>> entityData = TypedEntityData.of(EntityTypes.CHICKEN, new CompoundTag());

    public EncasedIceBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.ENCASED_ICE_BLOCK_ENTITY_TYPE, worldPosition, blockState);
    }

    public boolean sameAs(EncasedIceBlockEntity entity) {
        return entityData.equals(entity.entityData);
    }
    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putBoolean("isParent", isParent);
        output.store("entityData", TypedEntityData.codec(EntityType.CODEC), entityData);
    }

    public void setEntityData(TypedEntityData<EntityType<?>> entityData) {
        this.entityData = entityData;
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        isParent = input.getBooleanOr("isParent", false);
        Optional<TypedEntityData<EntityType<?>>> optional = input.read("entityData", TypedEntityData.codec(EntityType.CODEC));
        if (optional.isPresent())
            entityData = optional.get();
    }
}
