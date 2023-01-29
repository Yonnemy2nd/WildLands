package superlord.wildlands.common.block;

import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.NetherVines;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import superlord.wildlands.init.WLBlocks;

public class BeardMossTopBlock extends GrowingPlantHeadBlock {
   protected static final VoxelShape SHAPE = Block.box(4.0D, 9.0D, 4.0D, 12.0D, 16.0D, 12.0D);

   public BeardMossTopBlock(Properties properties) {
      super(properties, Direction.DOWN, SHAPE, false, 0.1D);
   }

   /**
    * Used to determine how much to grow the plant when using bonemeal. Kelp always returns 1, where as the nether vines
    * return a random value at least 1.
    */
   protected int getBlocksToGrowWhenBonemealed(RandomSource rand) {
      return NetherVines.getBlocksToGrowWhenBonemealed(rand);
   }

   protected Block getBodyBlock() {
      return WLBlocks.BEARD_MOSS.get();
   }

   protected boolean canGrowInto(BlockState state) {
      return NetherVines.isValidGrowthState(state);
   }

}
