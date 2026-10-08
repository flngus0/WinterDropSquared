package snapshotcycle.wintersquared.block.entity.client;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.entity.state.EntityRenderState;

public class EncasedIceBlockRenderState extends BlockEntityRenderState {
    boolean shouldRenderEntity = false;
    EntityRenderState entityRenderState = new EntityRenderState();
}
