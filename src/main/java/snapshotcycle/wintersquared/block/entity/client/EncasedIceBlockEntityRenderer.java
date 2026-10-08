package snapshotcycle.wintersquared.block.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntitySpawnRequest;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;
import snapshotcycle.wintersquared.block.entity.EncasedIceBlockEntity;

public class EncasedIceBlockEntityRenderer implements BlockEntityRenderer<EncasedIceBlockEntity, EncasedIceBlockRenderState> {


    private final EntityRenderDispatcher entityRenderer;

    public EncasedIceBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        entityRenderer = context.entityRenderer();
    }

    @Override
    public void extractRenderState(EncasedIceBlockEntity blockEntity, EncasedIceBlockRenderState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);
        if (blockEntity.getLevel() == null) return;
        state.entityRenderState = entityRenderer.extractEntity(EntityType.loadEntityRecursive(blockEntity.entityData.copyTagWithoutId(), blockEntity.getLevel(), new EntitySpawnRequest(EntitySpawnReason.SPAWNER, true), BaseSpawner.SET_DISPLAY_ENTITY_ID),partialTicks);
        state.shouldRenderEntity = blockEntity.isParent;
    }

    @Override
    public EncasedIceBlockRenderState createRenderState() {
        return new EncasedIceBlockRenderState();
    }

    @Override
    public void submit(EncasedIceBlockRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        if (state.shouldRenderEntity) {
            entityRenderer.submit(state.entityRenderState, camera, 0, 0, 0, poseStack, submitNodeCollector);
        }
    }
}
