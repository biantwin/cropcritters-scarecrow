package biantwin.cropcrittersscarecrow;

import net.minecraftforge.common.ForgeConfigSpec;

public final class ScarecrowConfig {

    public static final int DEFAULT_RADIUS = 32;
    public static final int MIN_RADIUS = 1;
    public static final int MAX_RADIUS = 64;

    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.IntValue RADIUS;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment(
                "CropCritters Scarecrow settings.",
                "A scarecrow is: any fence + hay block + carved pumpkin (bottom to top)."
        ).push("scarecrow");

        RADIUS = builder
                .comment(
                        "Square effect radius in blocks, measured from the carved pumpkin.",
                        "Within this range no crop critters spawn, no weeds grow and",
                        "farmland does not degrade when a crop matures.",
                        "X, Z and Y are each limited to +/- this value.",
                        "Range: " + MIN_RADIUS + " - " + MAX_RADIUS + ", default: " + DEFAULT_RADIUS
                )
                .defineInRange("radius", DEFAULT_RADIUS, MIN_RADIUS, MAX_RADIUS);

        builder.pop();
        SPEC = builder.build();
    }

    private ScarecrowConfig() {
    }

    public static int radius() {
        return RADIUS.get();
    }
}
