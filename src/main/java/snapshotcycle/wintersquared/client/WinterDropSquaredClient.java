package snapshotcycle.wintersquared.client;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import snapshotcycle.wintersquared.block.entity.client.EncasedIceBlockEntityRenderer;
import snapshotcycle.wintersquared.entity.client.ShiverRenderer;
import snapshotcycle.wintersquared.init.ModBlockEntities;
import snapshotcycle.wintersquared.init.ModEntities;

public class WinterDropSquaredClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRenderers.register(ModBlockEntities.ENCASED_ICE_BLOCK_ENTITY_TYPE, EncasedIceBlockEntityRenderer::new);
        EntityRenderers.register(ModEntities.SHIVER, ShiverRenderer::new);

    }
}
