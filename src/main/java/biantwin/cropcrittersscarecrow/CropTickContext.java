package biantwin.cropcrittersscarecrow;

public final class CropTickContext {

    private static final ThreadLocal<Integer> DEPTH = ThreadLocal.withInitial(() -> 0);

    private CropTickContext() {
    }

    public static void enter() {
        DEPTH.set(DEPTH.get() + 1);
    }

    public static void exit() {
        int depth = DEPTH.get() - 1;
        if (depth <= 0) {
            DEPTH.remove();
        } else {
            DEPTH.set(depth);
        }
    }

    public static boolean inCropTick() {
        return DEPTH.get() > 0;
    }
}
