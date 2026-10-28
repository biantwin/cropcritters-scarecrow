package biantwin.cropcrittersscarecrow.mixin;

import java.util.List;
import java.util.Set;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

public class ScarecrowMixinPlugin implements IMixinConfigPlugin {

    private static Boolean cropcrittersLoaded;

    @Override
    public void onLoad(String mixinPackage) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return cropcrittersLoaded();
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    private static boolean cropcrittersLoaded() {
        if (cropcrittersLoaded == null) {
            cropcrittersLoaded = hasClass("com.yelf42.cropcritters.config.CritterHelper")
                    && hasClass("com.yelf42.cropcritters.config.WeedHelper");
        }
        return cropcrittersLoaded;
    }

    private static boolean hasClass(String name) {
        String path = name.replace('.', '/') + ".class";
        ClassLoader loader = ScarecrowMixinPlugin.class.getClassLoader();
        return loader != null && loader.getResource(path) != null;
    }
}
