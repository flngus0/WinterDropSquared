package snapshotcycle.wintersquared;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.component.TypedEntityData;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.TagValueInput;
import net.minecraft.world.level.storage.TagValueOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import snapshotcycle.wintersquared.block.EncasedIceBlock;
import snapshotcycle.wintersquared.block.entity.EncasedIceBlockEntity;
import snapshotcycle.wintersquared.init.ModBlockEntities;
import snapshotcycle.wintersquared.init.ModBlocks;
import snapshotcycle.wintersquared.init.ModEntities;
import snapshotcycle.wintersquared.init.ModItems;

public class WinterDropSquared implements ModInitializer {
	public static final String MOD_ID = "winter-squared";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("lets get cold bruh");

		ModBlocks.init();
		ModBlockEntities.init();
		ModEntities.init();
		ModItems.init();

		ServerLivingEntityEvents.AFTER_DEATH.register((le,damageSource)->{
			encaseEntity(le, (ServerLevel) le.level());
		});

	}

	public static void encaseEntity(Entity entity, ServerLevel level) {
		EntityType<?> type = entity.getType();
		BlockPos pos = entity.blockPosition();
		BlockState state = ModBlocks.ENCASED_ICE.defaultBlockState();
		TagValueOutput tag = TagValueOutput.createWithContext(ProblemReporter.DISCARDING, level.registryAccess());
		entity.save(tag);
		for (int x = 0; x < EncasedIceBlock.getBlockWidth(type); x++) {
			for (int y = 0; y < EncasedIceBlock.getBlockHeight(type); y++) {
				for (int z = 0; z < EncasedIceBlock.getBlockHeight(type); z++) {
					BlockPos newPos = pos.relative(state.getValue(EncasedIceBlock.FACING), x).relative(state.getValue(EncasedIceBlock.FACING).getClockWise(), z).above(y);
					level.setBlock(newPos, state, 3);
					state.updateNeighbourShapes(level, newPos, 3);

					EncasedIceBlockEntity blockEntity = new EncasedIceBlockEntity(newPos, state);
					blockEntity.setEntityData(TypedEntityData.of(type, tag.buildResult()));
					level.setBlockEntity(blockEntity);
				}
			}
		}
		((EncasedIceBlockEntity) level.getBlockEntity(pos)).isParent = true;
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
