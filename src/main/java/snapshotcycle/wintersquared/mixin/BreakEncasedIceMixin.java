package snapshotcycle.wintersquared.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import snapshotcycle.wintersquared.block.EncasedIceBlock;
import snapshotcycle.wintersquared.block.entity.EncasedIceBlockEntity;

@Mixin(Level.class)
public abstract class BreakEncasedIceMixin {
	@Shadow
	public abstract BlockState getBlockState(BlockPos pos);

	@Shadow
	@Nullable
	public abstract BlockEntity getBlockEntity(BlockPos pos);

	@Shadow
	@Final
	private boolean isClientSide;

	@WrapMethod(method = "setBlock")
	private boolean breakEncased(BlockPos pos, BlockState blockState, int updateFlags, int updateLimit, Operation<Boolean> original) {
		if (!isClientSide) {
			if (getBlockState(pos).getBlock() instanceof EncasedIceBlock) {
				EncasedIceBlockEntity blockEntity = (EncasedIceBlockEntity) getBlockEntity(pos);
				boolean og = original.call(pos, blockState, updateFlags, updateLimit);
				EncasedIceBlock.chainDestroy(((Level) (Object) this), blockEntity, pos);
				return og;
			}
		}
		return original.call(pos,blockState,updateFlags,updateLimit);
	}
}