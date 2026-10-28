package biantwin.cropcrittersscarecrow;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(CropCrittersScarecrow.MOD_ID)
public class CropCrittersScarecrow {

    public static final String MOD_ID = "cropcritters_scarecrow";

    public CropCrittersScarecrow() {
        MinecraftForge.EVENT_BUS.register(ScarecrowEvents.class);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ScarecrowConfig.SPEC);
    }
}
