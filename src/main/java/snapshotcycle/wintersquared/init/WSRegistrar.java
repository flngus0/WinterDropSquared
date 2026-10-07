package snapshotcycle.wintersquared.init;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import snapshotcycle.wintersquared.WinterDropSquared;

import java.util.function.Function;

public class WSRegistrar {

    public static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties, boolean shouldRegisterItem, ResourceKey<CreativeModeTab> tab) {

        Block block = blockFactory.apply(properties.setId(keyOfBlock(name)));

        if (shouldRegisterItem) {
            registerItem(name, (b)->{return new BlockItem(block, b);}, new Item.Properties().setId(keyOfItem(name)).useBlockDescriptionPrefix(), tab);
        }

        return Registry.register(BuiltInRegistries.BLOCK, WinterDropSquared.id(name), block);
    }

    public static <T extends Item> T registerItem(String name, Function<Item.Properties, T> itemFactory, Item.Properties settings,
                                                  //? if >1.19
                                                  ResourceKey<CreativeModeTab> tab
                                                  //? if <=1.19
                                                  //CreativeModeTab tab
    ) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, WinterDropSquared.id(name));

        T item = itemFactory.apply(settings.setId(itemKey));

        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        CreativeModeTabEvents.modifyOutputEvent(tab)
                .register((creativeTab) -> {
                    creativeTab.accept(item);
                });

        return item;
    }

    private static ResourceKey<Block> keyOfBlock(String name) {
        return ResourceKey.create(Registries.BLOCK, WinterDropSquared.id(name));
    }

    private static ResourceKey<Item> keyOfItem(String name) {
        return ResourceKey.create(Registries.ITEM, WinterDropSquared.id(name));
    }
}
