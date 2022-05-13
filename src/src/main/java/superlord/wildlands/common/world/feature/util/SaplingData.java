package superlord.wildlands.common.world.feature.util;

import java.util.Set;

import net.minecraft.core.BlockPos;

public class SaplingData {
	private final Set<BlockPos> trunkOffsetCoordinates;
    private final int searchRange;

    public SaplingData(Set<BlockPos> trunkOffsetCoordinates, int searchRange) {
        this.trunkOffsetCoordinates = trunkOffsetCoordinates;
        this.searchRange = searchRange;
    }


    public int getSearchRange() {
        return searchRange;
    }

    public Set<BlockPos> getTrunkOffsetCoordinates() {
        return trunkOffsetCoordinates;
    }
}
