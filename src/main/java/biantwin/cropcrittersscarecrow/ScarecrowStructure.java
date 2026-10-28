package biantwin.cropcrittersscarecrow;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public final class ScarecrowStructure {

    private ScarecrowStructure() {
    }

    public static boolean isHead(BlockState state) {
        return state.is(Blocks.CARVED_PUMPKIN);
    }

    public static boolean matches(BlockGetter level, BlockPos headPos) {
        if (!isHead(level.getBlockState(headPos))) {
            return false;
        }
        if (!level.getBlockState(headPos.below()).is(Blocks.HAY_BLOCK)) {
            return false;
        }
        return level.getBlockState(headPos.below(2)).is(BlockTags.FENCES);
    }

    public static BlockPos headOf(BlockGetter level, BlockPos anyPart) {
        for (int dy = 0; dy <= 2; dy++) {
            BlockPos candidate = anyPart.above(dy);
            if (matches(level, candidate)) {
                return candidate;
            }
        }
        for (int dy = 1; dy <= 2; dy++) {
            BlockPos candidate = anyPart.below(dy);
            if (matches(level, candidate)) {
                return candidate;
            }
        }
        return null;
    }

    public static boolean inRange(BlockPos head, BlockPos target) {
        int radius = ScarecrowConfig.radius();
        return Math.abs(target.getX() - head.getX()) <= radius
                && Math.abs(target.getZ() - head.getZ()) <= radius
                && Math.abs(target.getY() - head.getY()) <= radius;
    }
}
