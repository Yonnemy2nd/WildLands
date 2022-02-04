package superlord.wildlands.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import superlord.wildlands.init.WildLandsEffects;

public class JellyBlock extends Block {

	protected static final VoxelShape COLLISION_SHAPE = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D);

	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return COLLISION_SHAPE;
	}

	public JellyBlock(Properties properties) {
		super(properties);
	}

	public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
		if (entityIn.isSuppressingBounce()) {
			super.onFallenUpon(worldIn, pos, entityIn, fallDistance);
		} else {
			entityIn.onLivingFall(fallDistance, 0.0F);
		}
	}

	public void onLanded(IBlockReader worldIn, Entity entityIn) {
		if (entityIn.isSuppressingBounce()) {
			super.onLanded(worldIn, entityIn);
		} else {
			this.bounceEntity(entityIn);
		}
	}

	private void bounceEntity(Entity entity) {
		Vector3d vector3d = entity.getMotion();
		if (vector3d.y < 0.0D) {
			double d0 = entity instanceof LivingEntity ? 1.0D : 0.8D;
			entity.setMotion(vector3d.x, -vector3d.y * d0, vector3d.z);
		}
	}

	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		double d0 = Math.abs(entityIn.getMotion().y);
		if (d0 < 0.1D && !entityIn.isSteppingCarefully()) {
			double d1 = 0.4D + d0 * 0.2D;
			entityIn.setMotion(entityIn.getMotion().mul(d1, 1.0D, d1));
		}
		super.onEntityWalk(worldIn, pos, entityIn);
	}

	@Override
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		if (entityIn instanceof LivingEntity) {
			((LivingEntity) entityIn).addPotionEffect(new EffectInstance(WildLandsEffects.STING.get(), 100));
		}
	}

}