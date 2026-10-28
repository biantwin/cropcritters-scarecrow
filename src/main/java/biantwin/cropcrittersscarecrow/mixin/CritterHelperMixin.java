package biantwin.cropcrittersscarecrow.mixin;

import com.yelf42.cropcritters.config.CritterHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import biantwin.cropcrittersscarecrow.ScarecrowRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(value = CritterHelper.class, remap = false)
public class CritterHelperMixin {

    @Inject(method = "spawnCritter", at = @At("HEAD"), cancellable = true, remap = false)
    private static void scarecrow$blockSpawn(ServerLevel level, BlockState state,
                                             RandomSource random, BlockPos pos,
                                             CallbackInfoReturnable<Boolean> cir) {
        if (ScarecrowRegistry.get(level).isSuppressed(level, pos)) {
            cir.setReturnValue(false);
        }
    }
}
