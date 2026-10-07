package snapshotcycle.wintersquared.init;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import snapshotcycle.wintersquared.block.EncasedIceBlock;

public class ModBlocks {
    public static final Block ENCASED_ICE = WSRegistrar.registerBlock(
            "encased_ice",
            EncasedIceBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.ICE),
            true,
            CreativeModeTabs.FUNCTIONAL_BLOCKS);

    public static void init() {}
}
