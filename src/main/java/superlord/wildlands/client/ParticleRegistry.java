package superlord.wildlands.client;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.particles.ParticleType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import superlord.wildlands.WildLands;

@Mod.EventBusSubscriber(modid = WildLands.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ParticleRegistry {
	
	public static final List<ParticleType<?>> PARTICLE_TYPES = Lists.newArrayList();
	
	public static <T extends ParticleType<?>> T registerParticle(String name, T particle) {
		particle.setRegistryName(new ResourceLocation(WildLands.MOD_ID, name));
		PARTICLE_TYPES.add(particle);
		return particle;
	}
	
	@SubscribeEvent
	public static void registerParticles(RegistryEvent.Register<ParticleType<?>> event) {
		IForgeRegistry<ParticleType<?>> registry = event.getRegistry();
		for (ParticleType<?> particle : PARTICLE_TYPES) {
			registry.register(particle);
		}
	}

}
