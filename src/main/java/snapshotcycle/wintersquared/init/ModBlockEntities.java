package snapshotcycle.wintersquared.init;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import snapshotcycle.wintersquared.WinterDropSquared;
import snapshotcycle.wintersquared.block.entity.EncasedIceBlockEntity;

public class ModBlockEntities {
    public static final BlockEntityType<EncasedIceBlockEntity> ENCASED_ICE_BLOCK_ENTITY_TYPE =
            register("encased_ice", EncasedIceBlockEntity::new, ModBlocks.ENCASED_ICE);

    private static <T extends BlockEntity> BlockEntityType<T> register(
            String name,
            FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory,
            Block... blocks
    ) {
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, WinterDropSquared.id(name), FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build());
    }

    public static void init() {}
}
