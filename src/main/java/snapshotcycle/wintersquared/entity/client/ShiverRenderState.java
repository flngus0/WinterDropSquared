package snapshotcycle.wintersquared.entity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

@Environment(EnvType.CLIENT)
public class ShiverRenderState extends LivingEntityRenderState {
    public float swelling;
    public boolean isPowered;
}
