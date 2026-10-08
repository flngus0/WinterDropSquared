package snapshotcycle.wintersquared.entity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EnergySwirlLayer;
import net.minecraft.resources.Identifier;

@Environment(EnvType.CLIENT)
public class ShiverPowerLayer extends EnergySwirlLayer<ShiverRenderState, ShiverModel> {
    private static final Identifier POWER_LOCATION = Identifier.withDefaultNamespace("textures/entity/creeper/creeper_armor.png");
    private final ShiverModel model;

    public ShiverPowerLayer(final RenderLayerParent<ShiverRenderState, ShiverModel> renderer, final EntityModelSet modelSet) {
        super(renderer);
        this.model = new ShiverModel(modelSet.bakeLayer(ModelLayers.CREEPER_ARMOR));
    }

    protected boolean isPowered(final ShiverRenderState state) {
        return state.isPowered;
    }

    @Override
    protected float xOffset(final float t) {
        return t * 0.01F;
    }

    @Override
    protected Identifier getTextureLocation() {
        return POWER_LOCATION;
    }

    protected ShiverModel model() {
        return this.model;
    }
}
