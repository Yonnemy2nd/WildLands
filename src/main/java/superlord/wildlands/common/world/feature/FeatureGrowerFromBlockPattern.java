package superlord.wildlands.common.world.feature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;

import com.mojang.datafixers.util.Pair;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import superlord.wildlands.common.block.sapling.GrowingPatterns;

public interface FeatureGrowerFromBlockPattern {

    List<Supplier<? extends FeatureGrowerFromBlockPattern>> ENTRIES = new ArrayList<>();

    void load();


    static void serializePatterns(ResourceLocation key, List<Pair<List<BlockPos>, SimpleWeightedRandomList<GrowingPatterns.FeatureSpawner>>> patternsToSpawner) {
        patternsToSpawner.clear();
        Map<ResourceLocation, List<GrowingPatterns.GrowingPatternEntry>> patterns = GrowingPatterns.getConfig().patternsForBlock();
        if (patterns.containsKey(key)) {
            List<GrowingPatterns.GrowingPatternEntry> growingPatternEntries = new ArrayList<>(patterns.get(key));
            growingPatternEntries.sort(Comparator.comparingInt(p -> {
                int saplingCount = 0;
                for (String s : p.pattern()) {
                    char[] chars = s.toCharArray();
                    for (char aChar : chars) {
                        if (aChar == 'x' || aChar == 'X') {
                            saplingCount++;
                        }
                    }
                }
                return saplingCount;
            }));

            List<Pair<List<BlockPos>, SimpleWeightedRandomList<GrowingPatterns.FeatureSpawner>>> patternsToSpawnerMapped = new ArrayList<>();
            for (GrowingPatterns.GrowingPatternEntry growingPatternEntry : growingPatternEntries) {
                List<String> pattern = growingPatternEntry.pattern();
                SimpleWeightedRandomList<GrowingPatterns.FeatureSpawner> spawner = growingPatternEntry.spawners();
                Pair<List<BlockPos>, SimpleWeightedRandomList<GrowingPatterns.FeatureSpawner>> newEntry = Pair.of(new ArrayList<>(), spawner);
                patternsToSpawnerMapped.add(newEntry);
                int patternLoopSize = Math.min(pattern.size(), GrowingPatterns.MAX_PATTERN_SIZE);

                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < patternLoopSize; i++) {
                    String s = pattern.get(i);
                    String substring = s.substring(0, Math.min(s.length(), GrowingPatterns.MAX_PATTERN_SIZE));
                    builder.append(substring);
                }

                String patternCombined = builder.toString();

                String trimmed = patternCombined.trim();
                if (trimmed.length() > 1) {
                    int zOffset = -patternLoopSize / 2;
                    for (int i = 0; i < patternLoopSize; i++) {
                        String s = pattern.get(i);
                        int range = Math.min(s.length(), GrowingPatterns.MAX_PATTERN_SIZE);
                        String substring = s.substring(0, range);
                        int xOffset = -range / 2;
                        char[] charArray = substring.toCharArray();
                        for (char c : charArray) {
                            if (c == 'x' || c == 'X') {
                                newEntry.getFirst().add(new BlockPos(xOffset, 0, zOffset));
                            }
                            xOffset++;
                        }
                        zOffset++;
                    }
                } else {
                    newEntry.getFirst().add(new BlockPos(0, 0, 0));
                }
            }
            patternsToSpawner.addAll(patternsToSpawnerMapped);
            Collections.reverse(patternsToSpawner);
        }
    }

    static boolean growFeature(Block block, ServerLevel world, BlockPos pos, Random rand, List<Pair<List<BlockPos>, SimpleWeightedRandomList<GrowingPatterns.FeatureSpawner>>> patternsToSpawner) {
        int range = (GrowingPatterns.MAX_PATTERN_SIZE - 1) / 2;
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos().set(pos);
        for (Pair<List<BlockPos>, SimpleWeightedRandomList<GrowingPatterns.FeatureSpawner>> entry : patternsToSpawner) {
            for (int xMove = -range; xMove <= range; xMove++) {
                for (int zMove = -range; zMove <= range; zMove++) {
                    boolean matchedPattern = true;
                    BlockPos.MutableBlockPos mutableBlockPos1 = new BlockPos.MutableBlockPos().set(mutableBlockPos.set(pos).move(xMove, 0, zMove));


                    List<BlockPos> offsets = entry.getFirst();
                    SimpleWeightedRandomList<GrowingPatterns.FeatureSpawner> treePicker = entry.getSecond();
                    for (BlockPos offset : offsets) {
                        BlockPos.MutableBlockPos movedPos = mutableBlockPos1.set(mutableBlockPos).move(offset);
                        BlockState offsetState = world.getBlockState(movedPos);
                        if (offsetState.getBlock() != block) {
                            matchedPattern = false;
                            break;
                        }
                    }
                    if (matchedPattern) {
                        // Set tree
                        Optional<Registry<ConfiguredFeature<?, ?>>> configuredFeaturesOptionalRegistry = world.registryAccess().ownedRegistry(Registry.CONFIGURED_FEATURE_REGISTRY);
                        if (configuredFeaturesOptionalRegistry.isPresent()) {
                            Registry<ConfiguredFeature<?, ?>> configuredFeaturesRegistry = configuredFeaturesOptionalRegistry.get();
                            Optional<GrowingPatterns.FeatureSpawner> randomValue = treePicker.getRandomValue(rand);
                            if (randomValue.isPresent()) {
                                GrowingPatterns.FeatureSpawner featureSpawner = randomValue.get();
                                ConfiguredFeature<?, ?> configuredFeature = configuredFeaturesRegistry.get(featureSpawner.spawnerID());
                                if (configuredFeature != null) {
                                    BlockPos spawnOffset = featureSpawner.spawnOffset();
                                    BlockPos growthPos = mutableBlockPos1.offset(spawnOffset);
                                    if (configuredFeature.place(world, world.getChunkSource().getGenerator(), rand, growthPos)) {
                                        // Clear saplings
                                        for (BlockPos offset : offsets) {
                                            BlockPos.MutableBlockPos movedPos = mutableBlockPos1.set(mutableBlockPos).move(offset);
                                            BlockState offsetState = world.getBlockState(movedPos);
                                            if (offsetState.getBlock() == block) {
                                                world.removeBlock(movedPos, false);
                                            }
                                        }
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

}