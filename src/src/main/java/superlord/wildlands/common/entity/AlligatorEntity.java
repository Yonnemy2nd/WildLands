package superlord.wildlands.common.entity;

import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.BreathAirGoal;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.pathfinder.AmphibiousNodeEvaluator;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.phys.HitResult;
import superlord.wildlands.init.WildLandsBlocks;
import superlord.wildlands.init.WildLandsEntities;
import superlord.wildlands.init.WildLandsItems;
import superlord.wildlands.init.WildLandsSounds;

public class AlligatorEntity extends Animal {

	private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(AlligatorEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Boolean> WARNING = SynchedEntityData.defineId(AlligatorEntity.class, EntityDataSerializers.BOOLEAN);
	public static final Predicate<LivingEntity> NO_LIKEY = (p_213498_0_) -> {
		return !(p_213498_0_ instanceof AlligatorEntity || p_213498_0_ instanceof Creeper);
	};
	private int warningSoundTicks;

	@SuppressWarnings("deprecation")
	public AlligatorEntity(EntityType<? extends AlligatorEntity> type, Level levelIn) {
		super(type, levelIn);
		this.moveControl = new AlligatorEntity.MoveHelperController(this);
		this.maxUpStep = 1.0F;
	}

	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new BreathAirGoal(this));
		this.goalSelector.addGoal(1, new RandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new AlligatorEntity.SwimGoal(this));
		this.goalSelector.addGoal(2, new AlligatorEntity.HissAtPlayerGoal());
		this.goalSelector.addGoal(3, new AlligatorEntity.AttackPlayerGoal());
		this.goalSelector.addGoal(2, new AlligatorEntity.HurtByTargetGoal());
		this.goalSelector.addGoal(1, new AlligatorEntity.MeleeAttackGoal());
		this.goalSelector.addGoal(2, new AlligatorEntity.PanicGoal());
		this.goalSelector.addGoal(5, new AlligatorEntity.GoToWaterGoal(this, 1.2));
		this.targetSelector.addGoal(2, new AlligatorEntity.AttackCatfishGoal());
	}

	protected SoundEvent getAmbientSound() {
		return WildLandsSounds.ALLIGATOR_IDLE;
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return WildLandsSounds.ALLIGATOR_HURT;
	}

	@Override
	public ItemStack getPickedResult(HitResult target) {
		return new ItemStack(WildLandsItems.ALLIGATOR_SPAWN_EGG.get());
	}

	protected SoundEvent getDeathSound() {
		return WildLandsSounds.ALLIGATOR_DEATH;
	}

	public int getVariant() {
		return this.entityData.get(VARIANT);
	}

	private void setVariant(int variant) {
		this.entityData.set(VARIANT, variant);
	}

	public boolean isWarning() {
		return this.entityData.get(WARNING);
	}

	private void setWarning(boolean isWarning) {
		this.entityData.set(WARNING, isWarning);
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
		AlligatorEntity entity = new AlligatorEntity(WildLandsEntities.ALLIGATOR.get(), this.level);
		entity.finalizeSpawn(level, this.level.getCurrentDifficultyAt(new BlockPos(entity.getEyePosition())), MobSpawnType.BREEDING, (SpawnGroupData)null, (CompoundTag)null);
		return entity;
	}

	public boolean isBreedingItem(ItemStack stack) {
		return stack.getItem() == WildLandsItems.RAW_CATFISH.get();
	}

	public static boolean canAlligatorSpawn(EntityType<? extends Animal> animal, LevelAccessor levelIn, MobSpawnType reason, BlockPos pos, Random random) {
		return (levelIn.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK) || levelIn.getBlockState(pos.below()).is(WildLandsBlocks.MUD.get())) && isBrightEnoughToSpawn(levelIn, pos);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 45.0D).add(Attributes.FOLLOW_RANGE, 20.0D).add(Attributes.MOVEMENT_SPEED, 0.23D).add(Attributes.ATTACK_DAMAGE, 7.0D);
	}

	protected PathNavigation createNavigation(Level levelIn) {
		return new AlligatorEntity.WalkAndSwimPathNavigator(this, levelIn);
	}

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack heldItem = player.getItemInHand(hand);
		if (heldItem.getItem() == Items.WATER_BUCKET && this.isAlive() && this.isBaby()) {
			playSound(SoundEvents.BUCKET_FILL_FISH, 1.0F, 1.0F);
			heldItem.shrink(1);
			ItemStack itemstack1 = new ItemStack(WildLandsItems.ALLIGATOR_BUCKET.get());
			this.setBucketData(itemstack1);
			if (!this.level.isClientSide) {
				CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer) player, itemstack1);
			}
			if(heldItem.isEmpty()) {
				player.setItemInHand(hand, itemstack1);
			} else if (!player.getInventory().add(itemstack1)) {
				player.drop(itemstack1, false);
			}
			this.discard();
			return InteractionResult.SUCCESS;
		}
		return super.mobInteract(player, hand);
	}

	private void setBucketData(ItemStack bucket) {
		CompoundTag nbt = bucket.getOrCreateTag();
		nbt.putInt("Variant", this.getVariant());
		nbt.putInt("GrowingAge", this.getAge());
		if (this.hasCustomName()) {
			bucket.setHoverName(this.getCustomName());
		}
	}

	class WalkAndSwimPathNavigator extends WaterBoundPathNavigation {
		WalkAndSwimPathNavigator(AlligatorEntity alligator, Level levelIn) {
			super(alligator, levelIn);
		}
		protected boolean canUpdatePath() {
			return true;
		}

		protected PathFinder createPathFinder(int p_179679_1_) {
			this.nodeEvaluator = new AmphibiousNodeEvaluator(true);
			return new PathFinder(this.nodeEvaluator, p_179679_1_);
		}

		public boolean isStableDestination(BlockPos pos) {
			return !this.level.getBlockState(pos.below()).isAir();
		}
	}

	public int getMaxAir() {
		return 4800;
	}

	protected int determineNextAir(int currentAir) {
		return this.getMaxAir();
	}

	public boolean doHurtTarget(Entity entityIn) {
		boolean flag = entityIn.hurt(DamageSource.mobAttack(this), (float)((int)this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
		if (flag) {
			this.doEnchantDamageEffects(this, entityIn);
		}

		return flag;
	}

	protected float getWaterSlowDown() {
		return 0.98F;
	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(VARIANT, 0);
		this.entityData.define(WARNING, false);
	}

	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.setWarning(compound.getBoolean("Warning"));
		this.setVariant(compound.getInt("Variant"));
	}

	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("Variant", this.getVariant());
		compound.putBoolean("Warning", this.isWarning());
	}

	public SpawnGroupData finalizeSpawn(ServerLevelAccessor levelIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
		spawnDataIn = super.finalizeSpawn(levelIn, difficultyIn, reason, spawnDataIn, dataTag);
		if (dataTag == null) {
			int variant = random.nextInt(99);
			if (variant < 33) {
				setVariant(0);
			} else if (variant > 32 && variant < 66) {
				setVariant(1);
			} else if (variant > 65 && variant < 98) {
				setVariant(2);
			} else {
				setVariant(3);
			}
		}
		return spawnDataIn;
	}

	static class MoveHelperController extends MoveControl {
		private final AlligatorEntity alligator;

		MoveHelperController(AlligatorEntity alligator) {
			super(alligator);
			this.alligator = alligator;
		}

		public void tick() {

			if (this.alligator.isInWater()) {
				this.alligator.setDeltaMovement(this.alligator.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
			}

			if (this.operation == MoveControl.Operation.MOVE_TO && !this.alligator.getNavigation().isDone()) {
				float f = (float)(this.speedModifier * this.alligator.getAttributeValue(Attributes.MOVEMENT_SPEED));
				this.alligator.setSpeed(Mth.lerp(0.125F, this.alligator.getSpeed(), f));
				double d0 = this.wantedX - this.alligator.getX();
				double d1 = this.wantedY - this.alligator.getY();
				double d2 = this.wantedZ - this.alligator.getZ();
				if (d1 != 0.0D) {
					double d3 = (double)Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
					this.alligator.setDeltaMovement(this.alligator.getDeltaMovement().add(0.0D, (double)this.alligator.getSpeed() * (d1 / d3) * 0.1D, 0.0D));
				}

				if (d0 != 0.0D || d2 != 0.0D) {
					float f1 = (float)(Mth.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
					this.alligator.yRotO = this.rotlerp(this.alligator.getYRot(), f1, 90.0F);
					this.alligator.yBodyRot = this.alligator.getYRot();
				}

			} else {
				this.alligator.setSpeed(0.0F);
			}
		}
	}

	public void tick() {
		super.tick();
		if (this.warningSoundTicks > 0) {
			--this.warningSoundTicks;
		}
	}

	protected boolean func_212800_dy() {
		return true;
	}

	static class SwimGoal extends RandomSwimmingGoal {
		private final AlligatorEntity alligator;

		public SwimGoal(AlligatorEntity alligator) {
			super(alligator, 1.0D, 40);
			this.alligator = alligator;
		}

		public boolean canUse() {
			return this.alligator.func_212800_dy() && super.canUse();
		}
	}

	class HurtByTargetGoal extends net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal {
		public HurtByTargetGoal() {
			super(AlligatorEntity.this);
		}

		public void start() {
			super.start();
			if (AlligatorEntity.this.isBaby()) {
				this.alertOthers();
				this.stop();
			}

		}

		protected void alertOther(Mob mobIn, LivingEntity targetIn) {
			if (mobIn instanceof AlligatorEntity && !mobIn.isBaby()) {
				super.alertOther(mobIn, targetIn);
			}

		}
	}

	class MeleeAttackGoal extends net.minecraft.world.entity.ai.goal.MeleeAttackGoal {
		public MeleeAttackGoal() {
			super(AlligatorEntity.this, 1.25D, true);
		}

		protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
			double d0 = this.getAttackReachSqr(enemy);
			if (distToEnemySqr <= d0 && this.isTimeToAttack()) {
				this.resetAttackCooldown();
				this.mob.doHurtTarget(enemy);
				AlligatorEntity.this.setWarning(false);
			} else if (distToEnemySqr <= d0 * 2.0D) {
				if (this.isTimeToAttack()) {
					AlligatorEntity.this.setWarning(false);
					this.resetAttackCooldown();
				}

				if (this.getTicksUntilNextAttack() <= 10) {
					AlligatorEntity.this.setWarning(true);
				}
			} else {
				this.resetAttackCooldown();
				AlligatorEntity.this.setWarning(false);
			}

		}

		public void stop() {
			AlligatorEntity.this.setWarning(false);
			super.stop();
		}

		protected double getAttackReachSqr(LivingEntity attackTarget) {
			return (double)(7.0F + attackTarget.getBbWidth());
		}
	}

	class PanicGoal extends net.minecraft.world.entity.ai.goal.PanicGoal {
		public PanicGoal() {
			super(AlligatorEntity.this, 1.2D);
		}

		public boolean shouldExecute() {
			if (AlligatorEntity.this.isBaby() && !AlligatorEntity.this.isInWater() && !AlligatorEntity.this.isOnFire() && AlligatorEntity.this.getTarget() != null) {
				return true;
			} else {
				return false;
			}
		}
	}

	static class GoToWaterGoal extends net.minecraft.world.entity.ai.goal.MoveToBlockGoal {
		private final AlligatorEntity alligator;

		private GoToWaterGoal(AlligatorEntity alligator, double speedIn) {
			super(alligator, alligator.isBaby() ? 2.0D : speedIn, 24);
			this.alligator = alligator;
			this.verticalSearchStart = -1;
		}

		public boolean canContinueToUse() {
			return !this.alligator.isInWater() && this.tryTicks <= 1200 && this.isValidTarget(this.alligator.level, this.blockPos);
		}

		public boolean canUse() {
			List<Player> players = alligator.level.getEntitiesOfClass(Player.class, alligator.getBoundingBox().inflate(10, 5, 10));
			if (this.alligator.isBaby() && !this.alligator.isInWater() && !players.isEmpty() && super.canUse() && alligator.getTarget() != null) {
				return true;
			} else {
				return false;
			}
		}

		public boolean shouldRecalculatePath() {
			return this.tryTicks % 160 == 0;
		}

		protected boolean isValidTarget(LevelReader levelIn, BlockPos pos) {
			return levelIn.getBlockState(pos).is(Blocks.WATER);
		}
	}

	class AttackPlayerGoal extends NearestAttackableTargetGoal<LivingEntity> {
		public AttackPlayerGoal() {
			super(AlligatorEntity.this, LivingEntity.class, 20, true, true, AlligatorEntity.NO_LIKEY);
		}

		public boolean canUse() {
			if (AlligatorEntity.this.isBaby()) {
				if (super.canUse() && AlligatorEntity.this.isInWater() && AlligatorEntity.this.getTarget() != null) {
					return true;
				} else {
					return false;
				}
			} else if (super.canUse()) {
				List<LivingEntity> living = AlligatorEntity.this.level.getEntitiesOfClass(LivingEntity.class, AlligatorEntity.this.getBoundingBox().inflate(5, 5, 5), AlligatorEntity.NO_LIKEY);
				if (!living.isEmpty()) {
					for (LivingEntity alive : living) {
						if (AlligatorEntity.NO_LIKEY.test(alive)) {
							return true;
						} else return false;
					}
				} else {
					return false;
				}
			}
			return false;
		}

		protected double getFollowDistance() {
			return super.getFollowDistance() * 0.5D;
		}
	}

	class HissAtPlayerGoal extends net.minecraft.world.entity.ai.goal.Goal {

		@Override
		public boolean canUse() {
			if (AlligatorEntity.this.isBaby()) {
				return false;
			} else {
				List<Player> players = AlligatorEntity.this.level.getEntitiesOfClass(Player.class, AlligatorEntity.this.getBoundingBox().inflate(5, 5, 5));
				List<LivingEntity> living = AlligatorEntity.this.level.getEntitiesOfClass(LivingEntity.class, AlligatorEntity.this.getBoundingBox().inflate(5, 5, 5), AlligatorEntity.NO_LIKEY);
				for (Player player : players) {
					if (!players.isEmpty() && !player.isCreative()) {
						return true;
					} else {
						return false;
					}
				}
				if (!living.isEmpty()) {
					return true;
				} else {
					return false;
				}
			}
		}

		public boolean canContinueToUse() {
			List<Player> players = AlligatorEntity.this.level.getEntitiesOfClass(Player.class, AlligatorEntity.this.getBoundingBox().inflate(5, 5, 5));
			if (players.isEmpty()) {
				return true;
			} else return false;
		}

		public void tick() {
			super.tick();
			List<Player> players = AlligatorEntity.this.level.getEntitiesOfClass(Player.class, AlligatorEntity.this.getBoundingBox().inflate(5, 5, 5));
			if (!players.isEmpty()) {
				AlligatorEntity.this.setWarning(true);
				AlligatorEntity.this.warningSoundTicks++;
			} else {
				AlligatorEntity.this.setWarning(false);
				this.stop();
			}
		}

		public void stop() {
			super.stop();
			AlligatorEntity.this.warningSoundTicks = 0;
		}

	}

	class AttackCatfishGoal extends net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal<CatfishEntity> {
		public AttackCatfishGoal() {
			super(AlligatorEntity.this, CatfishEntity.class, 20, true, true, (Predicate<LivingEntity>)null);
		}

		@SuppressWarnings("unused")
		public boolean canUse() {
			if (AlligatorEntity.this.isBaby()) {
				return false;
			} else {
				if (super.canUse()) {
					for(AlligatorEntity alligatorEntity : AlligatorEntity.this.level.getEntitiesOfClass(AlligatorEntity.class, AlligatorEntity.this.getBoundingBox().inflate(8.0D, 4.0D, 8.0D))) {
						return true;
					}
				}

				return false;
			}
		}

		protected double getFollowDistance() {
			return super.getFollowDistance() * 0.5D;
		}
	}

}
