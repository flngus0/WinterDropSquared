package snapshotcycle.wintersquared.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import snapshotcycle.wintersquared.WinterDropSquared;
import snapshotcycle.wintersquared.entity.ShiverEntity;

@Environment(EnvType.CLIENT)
public class ShiverRenderer extends MobRenderer<ShiverEntity, ShiverRenderState, ShiverModel> {
    private static final Identifier CREEPER_LOCATION = Identifier.fromNamespaceAndPath(WinterDropSquared.MOD_ID, "textures/entity/shiver/shiver.png");

    public ShiverRenderer(final EntityRendererProvider.Context context) {
        super(context, new ShiverModel(context.bakeLayer(ModelLayers.CREEPER)), 0.5F);
        this.addLayer(new ShiverPowerLayer(this, context.getModelSet()));
    }

    protected void scale(final ShiverRenderState state, final PoseStack poseStack) {
        float g = state.swelling;
        float wobble = 1.0F + Mth.sin(g * 100.0F) * g * 0.01F;
        g = Math.clamp(g, 0.0F, 1.0F);
        g *= g;
        g *= g;
        float s = (1.0F + g * 0.4F) * wobble;
        float hs = (1.0F + g * 0.1F) / wobble;
        poseStack.scale(s, hs, s);
    }

    protected float getWhiteOverlayProgress(final ShiverRenderState state) {
        float step = state.swelling;
        return (int)(step * 10.0F) % 2 == 0 ? 0.0F : Math.clamp(step, 0.5F, 1.0F);
    }

    public Identifier getTextureLocation(final ShiverRenderState state) {
        return CREEPER_LOCATION;
    }

    public ShiverRenderState createRenderState() {
        return new ShiverRenderState();
    }

    public void extractRenderState(final ShiverEntity entity, final ShiverRenderState state, final float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.swelling = entity.getSwelling(partialTicks);
        state.isPowered = entity.isPowered();
    }
}
