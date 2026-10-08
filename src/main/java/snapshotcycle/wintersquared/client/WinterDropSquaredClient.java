package snapshotcycle.wintersquared.client;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import snapshotcycle.wintersquared.block.entity.client.EncasedIceBlockEntityRenderer;
import snapshotcycle.wintersquared.init.ModBlockEntities;

public class WinterDropSquaredClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRenderers.register(ModBlockEntities.ENCASED_ICE_BLOCK_ENTITY_TYPE, EncasedIceBlockEntityRenderer::new);
    }
}
