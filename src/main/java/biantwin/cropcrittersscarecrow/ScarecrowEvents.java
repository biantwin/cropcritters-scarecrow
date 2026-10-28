package biantwin.cropcrittersscarecrow;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.level.ExplosionEvent;
import net.minecraftforge.event.level.PistonEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public final class ScarecrowEvents {

    private ScarecrowEvents() {
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (event.getLevel() instanceof ServerLevel level) {
            handleChange(level, event.getPos());
        }
    }

    @SubscribeEvent
    public static void onNeighborNotify(BlockEvent.NeighborNotifyEvent event) {
        if (event.getLevel() instanceof ServerLevel level) {
            tryRegister(level, event.getPos());
            handleChange(level, event.getPos());
        }
    }

    @SubscribeEvent
    public static void onExplosionDetonate(ExplosionEvent.Detonate event) {
        if (event.getLevel() instanceof ServerLevel level) {
            for (BlockPos pos : event.getAffectedBlocks()) {
                handleChange(level, pos);
            }
        }
    }

    @SubscribeEvent
    public static void onPistonPre(PistonEvent.Pre event) {
        if (event.getLevel() instanceof ServerLevel level) {
            handleChange(level, event.getPos());
        }
    }

    @SubscribeEvent
    public static void onEntityPlace(BlockEvent.EntityPlaceEvent event) {
        if (event.getLevel() instanceof ServerLevel level) {
            tryRegister(level, event.getPos());
        }
    }

    private static void tryRegister(ServerLevel level, BlockPos placedPos) {
        BlockPos head = ScarecrowStructure.headOf(level, placedPos);
        if (head != null) {
            ScarecrowRegistry.get(level).add(head);
        }
    }

    private static void handleChange(ServerLevel level, BlockPos pos) {
        ScarecrowRegistry registry = ScarecrowRegistry.get(level);
        for (int dy = -2; dy <= 2; dy++) {
            BlockPos candidate = pos.above(dy);
            if (level.isLoaded(candidate) && !ScarecrowStructure.matches(level, candidate)) {
                registry.remove(candidate);
            }
        }
    }
}
