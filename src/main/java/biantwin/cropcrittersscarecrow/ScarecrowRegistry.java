package biantwin.cropcrittersscarecrow;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.LongArrayTag;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

public class ScarecrowRegistry extends SavedData {

    private static final String KEY_HEADS = "Heads";

    private final Set<BlockPos> heads = new HashSet<>();

    public static ScarecrowRegistry get(ServerLevel level) {
        ResourceLocation id = level.dimension().location();
        return level.getServer().overworld().getDataStorage().computeIfAbsent(
                tag -> read(tag),
                ScarecrowRegistry::new,
                "cropcritters_scarecrow/" + id.getNamespace() + "/" + id.getPath());
    }

    public static ScarecrowRegistry read(CompoundTag tag) {
        ScarecrowRegistry data = new ScarecrowRegistry();
        for (long packed : tag.getLongArray(KEY_HEADS)) {
            data.heads.add(BlockPos.of(packed));
        }
        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        long[] packed = new long[this.heads.size()];
        int i = 0;
        for (BlockPos pos : this.heads) {
            packed[i++] = pos.asLong();
        }
        tag.put(KEY_HEADS, new LongArrayTag(packed));
        return tag;
    }

    public void add(BlockPos head) {
        if (this.heads.add(head.immutable())) {
            this.setDirty();
        }
    }

    public void remove(BlockPos head) {
        if (this.heads.remove(head)) {
            this.setDirty();
        }
    }

    public boolean isSuppressed(ServerLevel level, BlockPos target) {
        if (this.heads.isEmpty()) {
            return false;
        }
        BlockPos stale = null;
        boolean covered = false;
        for (BlockPos head : this.heads) {
            if (!ScarecrowStructure.inRange(head, target) || !level.isLoaded(head)) {
                continue;
            }
            if (ScarecrowStructure.matches(level, head)) {
                covered = true;
                break;
            }
            stale = head;
        }
        if (stale != null) {
            this.heads.remove(stale);
            this.setDirty();
        }
        return covered;
    }
}
