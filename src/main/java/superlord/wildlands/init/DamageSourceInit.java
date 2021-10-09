package superlord.wildlands.init;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class DamageSourceInit {
	
	public static final DamageSource CLAM = new DamageCustomDeathMessage("clam");
	
	static class DamageCustomDeathMessage extends DamageSource {
		
		public DamageCustomDeathMessage(String damageTypeIn) {
			super(damageTypeIn);
		}
		
		public ITextComponent getDeathMessage(LivingEntity entityLivingBaseIn) {
			LivingEntity livingEntity = entityLivingBaseIn.getAttackingEntity();
			String s = "death.attack." + this.damageType;
			int index = entityLivingBaseIn.getRNG().nextInt(3);
			String s1 = s + "." + index;
			String s2 = s + ".attacker_" + index;
			return livingEntity != null ? new TranslationTextComponent(s2, entityLivingBaseIn.getDisplayName(), livingEntity.getDisplayName()) : new TranslationTextComponent(s1, entityLivingBaseIn.getDisplayName());
		}
		
	}

}
