package superlord.wildlands.world.decorator;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.mojang.serialization.Codec;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.WorldDecoratingHelper;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;

public class AtOceanFloorWithExtra extends Placement<AtSurfaceWithExtraConfig>{
	
	public AtOceanFloorWithExtra(Codec<AtSurfaceWithExtraConfig> config) {
		super(config);
	}
	
	public Stream<BlockPos> getPositions(WorldDecoratingHelper decoratorContext, Random random, AtSurfaceWithExtraConfig config, BlockPos pos) {
		int i = config.count;
		if (random.nextFloat() < config.extraChance) {
			i += config.extraCount;
		}
		return IntStream.range(0, i).mapToObj((streamedInt) -> {
			int x = random.nextInt(16) + pos.getX();
			int z = random.nextInt(16) + pos.getZ();
			int y = decoratorContext.func_242893_a(Heightmap.Type.OCEAN_FLOOR_WG, x, z);
			return new BlockPos(x, y, z);
		});
	}

}
