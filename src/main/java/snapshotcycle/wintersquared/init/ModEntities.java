package snapshotcycle.wintersquared.init;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import snapshotcycle.wintersquared.WinterDropSquared;
import snapshotcycle.wintersquared.entity.ShiverEntity;

public class ModEntities {
    public static final ResourceKey<EntityType<?>> SHIVER_KEY = ResourceKey.create(Registries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(WinterDropSquared.MOD_ID, "shiver"));

    public static final EntityType<ShiverEntity> SHIVER = Registry.register(BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(WinterDropSquared.MOD_ID, "shiver"),
            EntityType.Builder.of(ShiverEntity::new, MobCategory.CREATURE).sized(0.75f, 0.5f).build(SHIVER_KEY));


    public static void init() {
        WinterDropSquared.LOGGER.info("There now you can't say I didn't do anything :)");
    }
}
