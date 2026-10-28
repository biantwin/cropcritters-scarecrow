package biantwin.cropcrittersscarecrow.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import biantwin.cropcrittersscarecrow.CropTickContext;
import biantwin.cropcrittersscarecrow.ScarecrowRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(Level.class)
public class FarmlandDegradationMixin {

    @Inject(method = "setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z",
            at = @At("HEAD"), cancellable = true)
    private void scarecrow$blockCropMaturityDegradation(BlockPos pos, BlockState newState, int flags,
                                                        CallbackInfoReturnable<Boolean> cir) {
        if (!CropTickContext.inCropTick()) {
            return;
        }
        if (newState.getBlock() instanceof FarmBlock) {
            return;
        }
        if (!((Object) this instanceof ServerLevel level)) {
            return;
        }
        if (!(level.getBlockState(pos).getBlock() instanceof FarmBlock)) {
            return;
        }
        if (ScarecrowRegistry.get(level).isSuppressed(level, pos)) {
            cir.setReturnValue(false);
        }
    }
}
