package superlord.wildlands.common.world.biome;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.Nullable;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.WeightedList;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import superlord.wildlands.common.util.BiomeData;

@SuppressWarnings("deprecation")
public class WLBiome {

	public static final List<WLBiome> WL_BIOMES = new ArrayList<>();
	private final Biome biome;

	public static List<BiomeData> biomeData = new ArrayList<>();

	public WLBiome(Biome.Climate climate, Biome.Category category, float depth, float scale, BiomeAmbience effects, BiomeGenerationSettings biomeGenerationSettings, MobSpawnInfo mobSpawnInfo) {
		biome = new Biome(climate, category, depth, scale, effects, biomeGenerationSettings, mobSpawnInfo);
		WL_BIOMES.add(this);
	}

	public WLBiome(Biome.Builder builder) {
		this.biome = builder.build();
		WL_BIOMES.add(this);
	}

	public WLBiome(Biome biome) {
		this.biome = biome;
		WL_BIOMES.add(this);
	}

	public Biome getBiome() {
		return this.biome;
	}

	public Biome getRiver() {
		return WorldGenRegistries.BIOME.getOrThrow(Biomes.RIVER);
	}

	@Nullable
	public WeightedList<Biome> getHills() {
		return null;
	}

	@Nullable
	public Biome getEdge() {
		return null;
	}

	@Nullable
	public Biome getBeach() {
		return WorldGenRegistries.BIOME.getOrThrow(Biomes.BEACH);
	}
	
	public BiomeDictionary.Type[] getBiomeDictionary() {
		return new BiomeDictionary.Type[] {BiomeDictionary.Type.OVERWORLD};
	}
	
	public BiomeManager.BiomeType getBiomeType() {
		return BiomeManager.BiomeType.WARM;
	}
	
	public int getWeight() {
		return 5;
	}
	
	public RegistryKey<Biome> getKey() {
		return RegistryKey.getOrCreateKey(Registry.BIOME_KEY, Objects.requireNonNull(WorldGenRegistries.BIOME.getKey(this.biome)));
	}

}