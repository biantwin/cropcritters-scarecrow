package biantwin.cropcrittersscarecrow.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import biantwin.cropcrittersscarecrow.CropTickContext;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(CropBlock.class)
public class CropTickMarkerMixin {

    @Inject(method = "randomTick", at = @At("HEAD"))
    private void scarecrow$enterCropTick(BlockState state, ServerLevel level, BlockPos pos,
                                         RandomSource random, CallbackInfo ci) {
        CropTickContext.enter();
    }

    @Inject(method = "randomTick", at = @At("RETURN"))
    private void scarecrow$exitCropTick(BlockState state, ServerLevel level, BlockPos pos,
                                        RandomSource random, CallbackInfo ci) {
        CropTickContext.exit();
    }
}
