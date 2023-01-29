package superlord.wildlands.init;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import superlord.wildlands.WildLands;
import superlord.wildlands.common.entity.WLBoat;
import superlord.wildlands.common.item.CoconutItem;
import superlord.wildlands.common.item.DuckweedItem;
import superlord.wildlands.common.item.JellyItem;
import superlord.wildlands.common.item.WLBoatItem;
import superlord.wildlands.common.item.WLBucketItem;
import superlord.wildlands.common.item.WLChestBoatItem;
import superlord.wildlands.common.item.WLSpawnEggItem;

public class WLItems {

	public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, WildLands.MOD_ID);

	public static final RegistryObject<Item> OLIVINE = REGISTER.register("olivine", () -> new Item(new Item.Properties().tab(WildLands.ITEM_GROUP)));
	//public static final RegistryObject<Item> PEARL = REGISTER.register("pearl", () -> new Item(new Item.Properties().tab(WildLands.ITEM_GROUP)));
	public static final RegistryObject<BlockItem> MUD = REGISTER.register("mud", () -> new BlockItem(WLBlocks.MUD.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> CYPRESS_LOG = REGISTER.register("cypress_log", () -> new BlockItem(WLBlocks.CYPRESS_LOG.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> STRIPPED_CYPRESS_LOG = REGISTER.register("stripped_cypress_log", () -> new BlockItem(WLBlocks.STRIPPED_CYPRESS_LOG.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> COCONUT_LOG = REGISTER.register("coconut_log", () -> new BlockItem(WLBlocks.COCONUT_LOG.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> STRIPPED_COCONUT_LOG = REGISTER.register("stripped_coconut_log", () -> new BlockItem(WLBlocks.STRIPPED_COCONUT_LOG.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> CHARRED_LOG = REGISTER.register("charred_log", () -> new BlockItem(WLBlocks.CHARRED_LOG.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> STRIPPED_CHARRED_LOG = REGISTER.register("stripped_charred_log", () -> new BlockItem(WLBlocks.STRIPPED_CHARRED_LOG.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> CYPRESS_WOOD = REGISTER.register("cypress_wood", () -> new BlockItem(WLBlocks.CYPRESS_WOOD.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> STRIPPED_CYPRESS_WOOD = REGISTER.register("stripped_cypress_wood", () -> new BlockItem(WLBlocks.STRIPPED_CYPRESS_WOOD.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> COCONUT_WOOD = REGISTER.register("coconut_wood", () -> new BlockItem(WLBlocks.COCONUT_WOOD.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> STRIPPED_COCONUT_WOOD = REGISTER.register("stripped_coconut_wood", () -> new BlockItem(WLBlocks.STRIPPED_COCONUT_WOOD.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> CHARRED_WOOD = REGISTER.register("charred_wood", () -> new BlockItem(WLBlocks.CHARRED_WOOD.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> STRIPPED_CHARRED_WOOD = REGISTER.register("stripped_charred_wood", () -> new BlockItem(WLBlocks.STRIPPED_CHARRED_WOOD.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> SMOLDERING_LOG = REGISTER.register("smoldering_log", () -> new BlockItem(WLBlocks.SMOLDERING_LOG.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> CYPRESS_PLANKS = REGISTER.register("cypress_planks", () -> new BlockItem(WLBlocks.CYPRESS_PLANKS.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> COCONUT_PLANKS = REGISTER.register("coconut_planks", () -> new BlockItem(WLBlocks.COCONUT_PLANKS.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> CHARRED_PLANKS = REGISTER.register("charred_planks", () -> new BlockItem(WLBlocks.CHARRED_PLANKS.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> CYPRESS_DOOR = REGISTER.register("cypress_door", () -> new BlockItem(WLBlocks.CYPRESS_DOOR.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> COCONUT_DOOR = REGISTER.register("coconut_door", () -> new BlockItem(WLBlocks.COCONUT_DOOR.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> CHARRED_DOOR = REGISTER.register("charred_door", () -> new BlockItem(WLBlocks.CHARRED_DOOR.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> CYPRESS_LEAVES = REGISTER.register("cypress_leaves", () -> new BlockItem(WLBlocks.CYPRESS_LEAVES.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> COCONUT_LEAVES = REGISTER.register("coconut_leaves", () -> new BlockItem(WLBlocks.COCONUT_LEAVES.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> CYPRESS_STAIRS = REGISTER.register("cypress_stairs", () -> new BlockItem(WLBlocks.CYPRESS_STAIRS.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> COCONUT_STAIRS = REGISTER.register("coconut_stairs", () -> new BlockItem(WLBlocks.COCONUT_STAIRS.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> CHARRED_STAIRS = REGISTER.register("charred_stairs", () -> new BlockItem(WLBlocks.CHARRED_STAIRS.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> CYPRESS_PRESSURE_PLATE = REGISTER.register("cypress_pressure_plate", () -> new BlockItem(WLBlocks.CYPRESS_PRESSURE_PLATE.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> COCONUT_PRESSURE_PLATE = REGISTER.register("coconut_pressure_plate", () -> new BlockItem(WLBlocks.COCONUT_PRESSURE_PLATE.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> CHARRED_PRESSURE_PLATE = REGISTER.register("charred_pressure_plate", () -> new BlockItem(WLBlocks.CHARRED_PRESSURE_PLATE.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> CYPRESS_BUTTON = REGISTER.register("cypress_button", () -> new BlockItem(WLBlocks.CYPRESS_BUTTON.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> COCONUT_BUTTON = REGISTER.register("coconut_button", () -> new BlockItem(WLBlocks.COCONUT_BUTTON.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> CHARRED_BUTTON = REGISTER.register("charred_button", () -> new BlockItem(WLBlocks.CHARRED_BUTTON.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> CYPRESS_FENCE = REGISTER.register("cypress_fence", () -> new BlockItem(WLBlocks.CYPRESS_FENCE.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> COCONUT_FENCE = REGISTER.register("coconut_fence", () -> new BlockItem(WLBlocks.COCONUT_FENCE.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> CHARRED_FENCE = REGISTER.register("charred_fence", () -> new BlockItem(WLBlocks.CHARRED_FENCE.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> CYPRESS_TRAPDOOR = REGISTER.register("cypress_trapdoor", () -> new BlockItem(WLBlocks.CYPRESS_TRAPDOOR.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> COCONUT_TRAPDOOR = REGISTER.register("coconut_trapdoor", () -> new BlockItem(WLBlocks.COCONUT_TRAPDOOR.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> CHARRED_TRAPDOOR = REGISTER.register("charred_trapdoor", () -> new BlockItem(WLBlocks.CHARRED_TRAPDOOR.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> CYPRESS_FENCE_GATE = REGISTER.register("cypress_fence_gate", () -> new BlockItem(WLBlocks.CYPRESS_FENCE_GATE.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> COCONUT_FENCE_GATE = REGISTER.register("coconut_fence_gate", () -> new BlockItem(WLBlocks.COCONUT_FENCE_GATE.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> CHARRED_FENCE_GATE = REGISTER.register("charred_fence_gate", () -> new BlockItem(WLBlocks.CHARRED_FENCE_GATE.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> CYPRESS_SLAB = REGISTER.register("cypress_slab", () -> new BlockItem(WLBlocks.CYPRESS_SLAB.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> COCONUT_SLAB = REGISTER.register("coconut_slab", () -> new BlockItem(WLBlocks.COCONUT_SLAB.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> CHARRED_SLAB = REGISTER.register("charred_slab", () -> new BlockItem(WLBlocks.CHARRED_SLAB.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> CYPRESS_SAPLING = REGISTER.register("cypress_sapling", () -> new BlockItem(WLBlocks.CYPRESS_SAPLING.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> COCONUT_SAPLING = REGISTER.register("coconut_sapling", () -> new BlockItem(WLBlocks.COCONUT_SAPLING.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> PALMETTO = REGISTER.register("palmetto", () -> new BlockItem(WLBlocks.PALMETTO.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> CATTAIL = REGISTER.register("cattail", () -> new BlockItem(WLBlocks.CATTAIL.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> DUCKWEED = REGISTER.register("duckweed", () -> new DuckweedItem(WLBlocks.DUCKWEED.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> BEARD_MOSS = REGISTER.register("beard_moss", () -> new BlockItem(WLBlocks.BEARD_MOSS_TOP.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<Item> RAW_CATFISH = REGISTER.register("raw_catfish", () -> new Item(new Item.Properties().tab(WildLands.ITEM_GROUP).food(new FoodProperties.Builder().nutrition(3).saturationMod(0.1F).meat().build())));
	public static final RegistryObject<Item> ANCHOVY = REGISTER.register("anchovy", () -> new Item(new Item.Properties().tab(WildLands.ITEM_GROUP).food(new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).fast().meat().build())));
	public static final RegistryObject<Item> CRAB_CLAW = REGISTER.register("crab_claw", () -> new Item(new Item.Properties().tab(WildLands.ITEM_GROUP).food(new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).meat().build())));
	public static final RegistryObject<Item> COOKED_CATFISH = REGISTER.register("cooked_catfish", () -> new Item(new Item.Properties().tab(WildLands.ITEM_GROUP).food(new FoodProperties.Builder().nutrition(8).saturationMod(0.8F).meat().build())));
	public static final RegistryObject<Item> COOKED_ANCHOVY = REGISTER.register("cooked_anchovy", () -> new Item(new Item.Properties().tab(WildLands.ITEM_GROUP).food(new FoodProperties.Builder().nutrition(5).saturationMod(0.6F).fast().meat().build())));
	public static final RegistryObject<Item> COOKED_CRAB_CLAW = REGISTER.register("cooked_crab_claw", () -> new Item(new Item.Properties().tab(WildLands.ITEM_GROUP).food(new FoodProperties.Builder().nutrition(6).saturationMod(0.6F).meat().build())));
	public static final RegistryObject<WLBucketItem> CATFISH_BUCKET = REGISTER.register("catfish_bucket", () -> new WLBucketItem(WLEntities.CATFISH, () -> Fluids.WATER, new Item.Properties().tab(WildLands.ITEM_GROUP).stacksTo(1)));
	public static final RegistryObject<WLBucketItem> ALLIGATOR_BUCKET = REGISTER.register("alligator_bucket", () -> new WLBucketItem(WLEntities.ALLIGATOR, () -> Fluids.WATER, new Item.Properties().tab(WildLands.ITEM_GROUP).stacksTo(1)));
	public static final RegistryObject<WLBucketItem> ANCHOVY_BUCKET = REGISTER.register("anchovy_bucket", () -> new WLBucketItem(WLEntities.ANCHOVY, () -> Fluids.WATER, new Item.Properties().tab(WildLands.ITEM_GROUP).stacksTo(1)));
	public static final RegistryObject<Item> CATFISH_SPAWN_EGG = REGISTER.register("catfish_spawn_egg", () -> new WLSpawnEggItem(WLEntities.CATFISH, 0xC9A764, 0x7E774F, new Item.Properties().tab(WildLands.SPAWN_EGG_GROUP)));
	public static final RegistryObject<Item> ALLIGATOR_SPAWN_EGG = REGISTER.register("alligator_spawn_egg", () -> new WLSpawnEggItem(WLEntities.ALLIGATOR, 0x5A6C43, 0x89915A, new Item.Properties().tab(WildLands.SPAWN_EGG_GROUP)));
	public static final RegistryObject<Item> CRAB_SPAWN_EGG = REGISTER.register("crab_spawn_egg", () -> new WLSpawnEggItem(WLEntities.CRAB, 0x872B2F, 0xE74D3C, new Item.Properties().tab(WildLands.SPAWN_EGG_GROUP)));
	public static final RegistryObject<Item> SEA_LION_SPAWN_EGG = REGISTER.register("sea_lion_spawn_egg", () -> new WLSpawnEggItem(WLEntities.SEA_LION, 0x865726, 0x251711, new Item.Properties().tab(WildLands.SPAWN_EGG_GROUP)));
	public static final RegistryObject<BlockItem> CONGLOMERATE = REGISTER.register("conglomerate", () -> new BlockItem(WLBlocks.CONGLOMERATE.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> CONGLOMERATE_SLAB = REGISTER.register("conglomerate_slab", () -> new BlockItem(WLBlocks.CONGLOMERATE_SLAB.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> CONGLOMERATE_STAIRS = REGISTER.register("conglomerate_stairs", () -> new BlockItem(WLBlocks.CONGLOMERATE_STAIRS.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> CONGLOMERATE_BRICKS = REGISTER.register("conglomerate_bricks", () -> new BlockItem(WLBlocks.CONGLOMERATE_BRICKS.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> CONGLOMERATE_BRICK_SLAB = REGISTER.register("conglomerate_brick_slab", () -> new BlockItem(WLBlocks.CONGLOMERATE_BRICK_SLAB.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> CONGLOMERATE_BRICK_STAIRS = REGISTER.register("conglomerate_brick_stairs", () -> new BlockItem(WLBlocks.CONGLOMERATE_BRICK_STAIRS.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> CONGLOMERATE_BRICK_WALL = REGISTER.register("conglomerate_brick_wall", () -> new BlockItem(WLBlocks.CONGLOMERATE_BRICK_WALL.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> GABBRO = REGISTER.register("gabbro", () -> new BlockItem(WLBlocks.GABBRO.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> OLIVINE_GABBRO = REGISTER.register("olivine_gabbro", () -> new BlockItem(WLBlocks.OLIVINE_GABBRO.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> GABBRO_SLAB = REGISTER.register("gabbro_slab", () -> new BlockItem(WLBlocks.GABBRO_SLAB.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> GABBRO_STAIRS = REGISTER.register("gabbro_stairs", () -> new BlockItem(WLBlocks.GABBRO_STAIRS.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> GABBRO_WALL = REGISTER.register("gabbro_wall", () -> new BlockItem(WLBlocks.GABBRO_WALL.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> POLISHED_GABBRO = REGISTER.register("polished_gabbro", () -> new BlockItem(WLBlocks.POLISHED_GABBRO.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> POLISHED_GABBRO_SLAB = REGISTER.register("polished_gabbro_slab", () -> new BlockItem(WLBlocks.POLISHED_GABBRO_SLAB.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> POLISHED_GABBRO_STAIRS = REGISTER.register("polished_gabbro_stairs", () -> new BlockItem(WLBlocks.POLISHED_GABBRO_STAIRS.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> DOLERITE = REGISTER.register("dolerite", () -> new BlockItem(WLBlocks.DOLERITE.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> DOLERITE_SLAB = REGISTER.register("dolerite_slab", () -> new BlockItem(WLBlocks.DOLERITE_SLAB.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> DOLERITE_STAIRS = REGISTER.register("dolerite_stairs", () -> new BlockItem(WLBlocks.DOLERITE_STAIRS.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> DOLERITE_WALL = REGISTER.register("dolerite_wall", () -> new BlockItem(WLBlocks.DOLERITE_WALL.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> POLISHED_DOLERITE = REGISTER.register("polished_dolerite", () -> new BlockItem(WLBlocks.POLISHED_DOLERITE.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> POLISHED_DOLERITE_SLAB = REGISTER.register("polished_dolerite_slab", () -> new BlockItem(WLBlocks.POLISHED_DOLERITE_SLAB.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> POLISHED_DOLERITE_STAIRS = REGISTER.register("polished_dolerite_stairs", () -> new BlockItem(WLBlocks.POLISHED_DOLERITE_STAIRS.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> FINE_SAND = REGISTER.register("fine_sand", () -> new BlockItem(WLBlocks.FINE_SAND.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> FINE_SANDSTONE = REGISTER.register("fine_sandstone", () -> new BlockItem(WLBlocks.FINE_SANDSTONE.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> FINE_SANDSTONE_SLAB = REGISTER.register("fine_sandstone_slab", () -> new BlockItem(WLBlocks.FINE_SANDSTONE_SLAB.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> FINE_SANDSTONE_STAIRS = REGISTER.register("fine_sandstone_stairs", () -> new BlockItem(WLBlocks.FINE_SANDSTONE_STAIRS.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> FINE_SANDSTONE_WALL = REGISTER.register("fine_sandstone_wall", () -> new BlockItem(WLBlocks.FINE_SANDSTONE_WALL.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> SMOOTH_FINE_SANDSTONE = REGISTER.register("smooth_fine_sandstone", () -> new BlockItem(WLBlocks.SMOOTH_FINE_SANDSTONE.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> SMOOTH_FINE_SANDSTONE_SLAB = REGISTER.register("smooth_fine_sandstone_slab", () -> new BlockItem(WLBlocks.SMOOTH_FINE_SANDSTONE_SLAB.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> SMOOTH_FINE_SANDSTONE_STAIRS = REGISTER.register("smooth_fine_sandstone_stairs", () -> new BlockItem(WLBlocks.SMOOTH_FINE_SANDSTONE_STAIRS.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> CUT_FINE_SANDSTONE = REGISTER.register("cut_fine_sandstone", () -> new BlockItem(WLBlocks.CUT_FINE_SANDSTONE.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> CUT_FINE_SANDSTONE_SLAB = REGISTER.register("cut_fine_sandstone_slab", () -> new BlockItem(WLBlocks.CUT_FINE_SANDSTONE_SLAB.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> OLIVINE_BLOCK = REGISTER.register("olivine_block", () -> new BlockItem(WLBlocks.OLIVINE.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> OLIVINE_LAMP = REGISTER.register("olivine_lamp", () -> new BlockItem(WLBlocks.OLIVINE_LAMP.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> OLIVINE_PRESSURE_PLATE = REGISTER.register("olivine_pressure_plate", () -> new BlockItem(WLBlocks.OLIVINE_PRESSURE_PLATE.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	//public static final RegistryObject<BlockItem> PEARL_BLOCK = REGISTER.register("pearl_block", () -> new BlockItem(WLBlocks.PEARL.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	//public static final RegistryObject<BlockItem> PEARL_TILE_BLOCK = REGISTER.register("pearl_tile_block", () -> new BlockItem(WLBlocks.PEARL_TILES.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<CoconutItem> COCONUT = REGISTER.register("coconut", () -> new CoconutItem(WLBlocks.COCONUT.get(), WLBlocks.WALL_COCONUT.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP).stacksTo(16)));
	public static final RegistryObject<Item> CRACKED_COCONUT = REGISTER.register("cracked_coconut", () -> new Item(new Item.Properties().tab(WildLands.ITEM_GROUP).food(new FoodProperties.Builder().saturationMod(0.3F).nutrition(2).build())));
	public static final RegistryObject<JellyItem> JELLY_BALL = REGISTER.register("jelly_ball", () -> new JellyItem(new Item.Properties().tab(WildLands.ITEM_GROUP)));
	public static final RegistryObject<BlockItem> JELLY_BLOCK = REGISTER.register("jelly_block", () -> new BlockItem(WLBlocks.JELLY_BLOCK.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> BLUE_STARFISH = REGISTER.register("blue_starfish", () -> new BlockItem(WLBlocks.BLUE_STARFISH.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP).stacksTo(1)));
	public static final RegistryObject<BlockItem> MAGENTA_STARFISH = REGISTER.register("magenta_starfish", () -> new BlockItem(WLBlocks.MAGENTA_STARFISH.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP).stacksTo(1)));
	public static final RegistryObject<BlockItem> ORANGE_STARFISH = REGISTER.register("orange_starfish", () -> new BlockItem(WLBlocks.ORANGE_STARFISH.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP).stacksTo(1)));
	public static final RegistryObject<BlockItem> PINK_STARFISH = REGISTER.register("pink_starfish", () -> new BlockItem(WLBlocks.PINK_STARFISH.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP).stacksTo(1)));
	public static final RegistryObject<BlockItem> RED_STARFISH = REGISTER.register("red_starfish", () -> new BlockItem(WLBlocks.RED_STARFISH.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP).stacksTo(1)));
	public static final RegistryObject<BlockItem> URCHIN = REGISTER.register("urchin", () -> new BlockItem(WLBlocks.URCHIN.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> SEAWEED_BLOCK = REGISTER.register("seaweed_block", () -> new BlockItem(WLBlocks.SEAWEED_BLOCK.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> CHARRED_GRASS = REGISTER.register("charred_grass", () -> new BlockItem(WLBlocks.CHARRED_GRASS.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> CHARRED_TALL_GRASS = REGISTER.register("charred_tall_grass", () -> new BlockItem(WLBlocks.CHARRED_TALL_GRASS.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	public static final RegistryObject<BlockItem> CHARRED_BUSH = REGISTER.register("charred_bush", () -> new BlockItem(WLBlocks.CHARRED_BUSH.get(), new Item.Properties().tab(WildLands.BLOCK_GROUP)));
	
	public static final RegistryObject<WLBoatItem> CYPRESS_BOAT = REGISTER.register("bald_cypress_boat", () -> new WLBoatItem(WLBoat.WLBoatTypes.CYPRESS, (new Item.Properties().stacksTo(1).tab(WildLands.ITEM_GROUP))));
	public static final RegistryObject<WLBoatItem> COCONUT_BOAT = REGISTER.register("coconut_boat", () -> new WLBoatItem(WLBoat.WLBoatTypes.COCONUT, (new Item.Properties().stacksTo(1).tab(WildLands.ITEM_GROUP))));
	public static final RegistryObject<WLBoatItem> CHARRED_BOAT = REGISTER.register("charred_boat", () -> new WLBoatItem(WLBoat.WLBoatTypes.CHARRED, (new Item.Properties().stacksTo(1).tab(WildLands.ITEM_GROUP))));
	public static final RegistryObject<WLChestBoatItem> CYPRESS_CHEST_BOAT = REGISTER.register("bald_cypress_chest_boat", () -> new WLChestBoatItem(WLBoat.WLBoatTypes.CYPRESS, (new Item.Properties().stacksTo(1).tab(WildLands.ITEM_GROUP))));
	public static final RegistryObject<WLChestBoatItem> COCONUT_CHEST_BOAT = REGISTER.register("coconut_chest_boat", () -> new WLChestBoatItem(WLBoat.WLBoatTypes.COCONUT, (new Item.Properties().stacksTo(1).tab(WildLands.ITEM_GROUP))));
	public static final RegistryObject<WLChestBoatItem> CHARRED_CHEST_BOAT = REGISTER.register("charred_chest_boat", () -> new WLChestBoatItem(WLBoat.WLBoatTypes.CHARRED, (new Item.Properties().stacksTo(1).tab(WildLands.ITEM_GROUP))));

	//public static final RegistryObject<Item> CLAM_SPAWN_EGG = REGISTER.register("clam_spawn_egg", () -> new WLSpawnEggItem(WLEntities.CLAM, 0x674F57, 0xE2C19D, new Item.Properties().tab(WildLands.SPAWN_EGG_GROUP)));
	public static final RegistryObject<Item> HAMMERHEAD_SPAWN_EGG = REGISTER.register("hammerhead_spawn_egg", () -> new WLSpawnEggItem(WLEntities.HAMMERHEAD, 0x6F594C, 0xDBCCBC, new Item.Properties().tab(WildLands.SPAWN_EGG_GROUP)));
	public static final RegistryObject<Item> ANCHOVY_SPAWN_EGG = REGISTER.register("anchovy_spawn_egg", () -> new WLSpawnEggItem(WLEntities.ANCHOVY, 0xD6D6D6, 0x454B5D, new Item.Properties().tab(WildLands.SPAWN_EGG_GROUP)));
	public static final RegistryObject<Item> OCTOPUS_SPAWN_EGG = REGISTER.register("octopus_spawn_egg", () -> new WLSpawnEggItem(WLEntities.OCTOPUS, 0x6E2354, 0xEC4DA7, new Item.Properties().tab(WildLands.SPAWN_EGG_GROUP))); 
	public static final RegistryObject<Item> JELLYFISH_SPAWN_EGG = REGISTER.register("jellyfish_spawn_egg", () -> new WLSpawnEggItem(WLEntities.JELLYFISH, 0xFB87A7, 0xE06595, new Item.Properties().tab(WildLands.SPAWN_EGG_GROUP)));
	public static final RegistryObject<Item> GRIZZLY_BEAR_SPAWN_EGG = REGISTER.register("grizzly_bear_spawn_egg", () -> new WLSpawnEggItem(WLEntities.GRIZZLY, 0x491F10, 0x8C572B, new Item.Properties().tab(WildLands.SPAWN_EGG_GROUP)));
	public static final RegistryObject<Item> CYPRESS_SIGN = REGISTER.register("cypress_sign", () -> new SignItem(new Item.Properties().stacksTo(16).tab(WildLands.BLOCK_GROUP), WLBlocks.CYPRESS_SIGN.get(), WLBlocks.CYPRESS_WALL_SIGN.get()));
	public static final RegistryObject<Item> COCONUT_SIGN = REGISTER.register("coconut_sign", () -> new SignItem(new Item.Properties().stacksTo(16).tab(WildLands.BLOCK_GROUP), WLBlocks.COCONUT_SIGN.get(), WLBlocks.COCONUT_WALL_SIGN.get()));
	public static final RegistryObject<Item> CHARRED_SIGN = REGISTER.register("charred_sign", () -> new SignItem(new Item.Properties().stacksTo(16).tab(WildLands.BLOCK_GROUP), WLBlocks.CHARRED_SIGN.get(), WLBlocks.CHARRED_WALL_SIGN.get()));
}
