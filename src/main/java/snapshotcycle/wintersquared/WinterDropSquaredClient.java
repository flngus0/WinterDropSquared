package snapshotcycle.wintersquared;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.renderer.entity.EntityRenderers;
import snapshotcycle.wintersquared.entity.client.ModModelLayerLocations;
import snapshotcycle.wintersquared.entity.client.ShiverModel;
import snapshotcycle.wintersquared.entity.client.ShiverRenderer;
import snapshotcycle.wintersquared.init.ModEntities;

public class WinterDropSquaredClient implements ClientModInitializer {

    @Override
    public void onInitializeClient(){
        //ModelLayerRegistry.registerModelLayer(ModModelLayerLocations.SHIVER, ShiverModel::createBodyLayer(CubeDeformation.NONE));
        EntityRenderers.register(ModEntities.SHIVER, ShiverRenderer::new);
    }
}
