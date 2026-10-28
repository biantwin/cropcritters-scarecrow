package biantwin.cropcrittersscarecrow.mixin;

import com.yelf42.cropcritters.config.WeedHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import biantwin.cropcrittersscarecrow.ScarecrowRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(value = WeedHelper.class, remap = false)
public class WeedHelperMixin {

    @Inject(method = "generateWeed", at = @At("HEAD"), cancellable = true, remap = false)
    private static void scarecrow$blockWeed(BlockState state, ServerLevel level, BlockPos pos,
                                            RandomSource random, boolean nether,
                                            CallbackInfo ci) {
        if (ScarecrowRegistry.get(level).isSuppressed(level, pos)) {
            ci.cancel();
        }
    }
}
