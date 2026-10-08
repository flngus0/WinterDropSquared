package snapshotcycle.wintersquared.init;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import snapshotcycle.wintersquared.WinterDropSquared;

import java.util.function.Function;

import static snapshotcycle.wintersquared.init.WSRegistrar.registerItem;

public class ModItems {
    public static final Item SHIVER_SPAWN_EGG = registerItem("shiver_spawn_egg",
            properties -> new SpawnEggItem(properties.spawnEgg(ModEntities.SHIVER)));


    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(WinterDropSquared.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(WinterDropSquared.MOD_ID, name)))));
    }

    public static void init(){
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.SPAWN_EGGS).register(output -> {
            output.accept(SHIVER_SPAWN_EGG);
        });
    }
}
