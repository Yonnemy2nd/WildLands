package superlord.wildlands.common;

import com.google.common.collect.ImmutableMap;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.FireBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import superlord.wildlands.WildLands;
import superlord.wildlands.init.WLBlocks;
import superlord.wildlands.init.WLItems;

@Mod.EventBusSubscriber(modid = WildLands.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonEvents {

	@SubscribeEvent
	public static void init(final FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			compostibleBlockss();
			AxeItem.STRIPPABLES = new ImmutableMap.Builder<Block, Block>().putAll(AxeItem.STRIPPABLES)
					.put(WLBlocks.COCONUT_LOG.get(), WLBlocks.STRIPPED_COCONUT_LOG.get())
					.put(WLBlocks.CHARRED_LOG.get(), WLBlocks.STRIPPED_CHARRED_LOG.get())
					.put(WLBlocks.CYPRESS_LOG.get(), WLBlocks.STRIPPED_CYPRESS_LOG.get()).build();
			registerFlammables();
		});
	}
		
    private static void compostibleBlockss() {
    	compostibleBlocks(0.3F, WLBlocks.COCONUT_LEAVES.get());
		compostibleBlocks(0.3F, WLBlocks.CYPRESS_LEAVES.get());
		compostibleBlocks(0.3F, WLBlocks.COCONUT_SAPLING.get());
		compostibleBlocks(0.3F, WLBlocks.CYPRESS_SAPLING.get());
		compostibleBlocks(0.3F, WLBlocks.DUCKWEED.get());
		compostibleBlocks(0.3F, WLItems.COCONUT.get());
		compostibleBlocks(0.3F, WLItems.CRACKED_COCONUT.get());
		compostibleBlocks(0.5F, WLBlocks.PALMETTO.get());
		compostibleBlocks(0.5F, WLBlocks.CATTAIL.get());
		compostibleBlocks(0.5F, WLItems.BEARD_MOSS.get());
		compostibleBlocks(0.85F, WLItems.SEAWEED_BLOCK.get());
    }
    
    private static void compostibleBlocks(float chance, ItemLike item) {
        ComposterBlock.COMPOSTABLES.put(item.asItem(), chance);
    }
    
    public static void registerFlammables() {
		registerFlammable(WLBlocks.CYPRESS_PLANKS.get(), 5, 20);
		registerFlammable(WLBlocks.COCONUT_PLANKS.get(), 5, 20);
		registerFlammable(WLBlocks.CHARRED_PLANKS.get(), 3, 10);
		registerFlammable(WLBlocks.CYPRESS_SLAB.get(), 5, 20);
		registerFlammable(WLBlocks.COCONUT_SLAB.get(), 5, 20);
		registerFlammable(WLBlocks.CHARRED_SLAB.get(), 3, 10);
		registerFlammable(WLBlocks.CYPRESS_FENCE_GATE.get(), 5, 20);
		registerFlammable(WLBlocks.COCONUT_FENCE_GATE.get(), 5, 20);
		registerFlammable(WLBlocks.CHARRED_FENCE_GATE.get(), 3, 10);
    	registerFlammable(WLBlocks.CYPRESS_FENCE.get(), 5, 20);
		registerFlammable(WLBlocks.COCONUT_FENCE.get(), 5, 20);
    	registerFlammable(WLBlocks.CHARRED_FENCE.get(), 3, 10);
		registerFlammable(WLBlocks.CYPRESS_STAIRS.get(), 5, 20);
		registerFlammable(WLBlocks.COCONUT_STAIRS.get(), 5, 20);
		registerFlammable(WLBlocks.CHARRED_STAIRS.get(), 3, 10);
		registerFlammable(WLBlocks.COCONUT_LOG.get(), 5, 5);
		registerFlammable(WLBlocks.CYPRESS_LOG.get(), 5, 5);
		registerFlammable(WLBlocks.CHARRED_LOG.get(), 3, 3);
		registerFlammable(WLBlocks.STRIPPED_COCONUT_LOG.get(), 5, 5);
		registerFlammable(WLBlocks.STRIPPED_CYPRESS_LOG.get(), 5, 5);
		registerFlammable(WLBlocks.STRIPPED_CHARRED_LOG.get(), 3, 3);
		registerFlammable(WLBlocks.STRIPPED_CYPRESS_WOOD.get(), 5, 5);
		registerFlammable(WLBlocks.STRIPPED_COCONUT_WOOD.get(), 5, 5);
		registerFlammable(WLBlocks.STRIPPED_CHARRED_WOOD.get(), 3, 3);
		registerFlammable(WLBlocks.CYPRESS_WOOD.get(), 5, 5);
		registerFlammable(WLBlocks.COCONUT_WOOD.get(), 5, 5);
		registerFlammable(WLBlocks.CHARRED_WOOD.get(), 3, 3);
		registerFlammable(WLBlocks.SMOLDERING_LOG.get(), 1, 3);
		registerFlammable(WLBlocks.COCONUT_LEAVES.get(), 30, 60);
		registerFlammable(WLBlocks.CYPRESS_LEAVES.get(), 30, 60);
		registerFlammable(WLBlocks.PALMETTO.get(), 60, 100);
		registerFlammable(WLBlocks.CATTAIL.get(), 60, 100);
		registerFlammable(WLBlocks.CHARRED_TALL_GRASS.get(), 30, 50);
		registerFlammable(WLBlocks.CHARRED_BUSH.get(), 30, 50);
		registerFlammable(WLBlocks.SEAWEED_BLOCK.get(), 60, 20);
		registerFlammable(WLBlocks.BEARD_MOSS.get(), 15, 60);
		registerFlammable(WLBlocks.BEARD_MOSS_TOP.get(), 15, 60);
    }
    
    public static void registerFlammable(Block block, int flameOdds, int burnOdds) {
        FireBlock fire = (FireBlock) Blocks.FIRE;
        fire.setFlammable(block, flameOdds, burnOdds);
    }

}
