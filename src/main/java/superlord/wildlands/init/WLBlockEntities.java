package superlord.wildlands.init;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import superlord.wildlands.WildLands;
import superlord.wildlands.common.entity.block.WLHangingSignBlockEntity;
import superlord.wildlands.common.entity.block.WLSignBlockEntity;

public class WLBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, WildLands.MOD_ID);
	
    public static final RegistryObject<BlockEntityType<WLSignBlockEntity>> SIGN = REGISTER.register("sign", () -> BlockEntityType.Builder.of(WLSignBlockEntity::new,
    		WLBlocks.COCONUT_SIGN.get(), WLBlocks.COCONUT_WALL_SIGN.get(),
    		WLBlocks.CYPRESS_SIGN.get(), WLBlocks.CYPRESS_WALL_SIGN.get(),
    		WLBlocks.CHARRED_SIGN.get(), WLBlocks.CHARRED_WALL_SIGN.get()
    	).build(null));
    public static final RegistryObject<BlockEntityType<WLHangingSignBlockEntity>> HANGING_SIGN = REGISTER.register("hanging_sign", () -> BlockEntityType.Builder.of(WLHangingSignBlockEntity::new, 
    		WLBlocks.COCONUT_HANGING_SIGN.get(), WLBlocks.COCONUT_HANGING_WALL_SIGN.get(),
    		WLBlocks.CYPRESS_HANGING_SIGN.get(), WLBlocks.CYPRESS_HANGING_WALL_SIGN.get(),
    		WLBlocks.CHARRED_HANGING_SIGN.get(), WLBlocks.CHARRED_HANGING_WALL_SIGN.get()
    		).build(null));

}
