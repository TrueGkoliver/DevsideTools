package com.gkoliver.devsidetools.core.registry;

import com.gkoliver.devsidetools.DevsideTools;
import com.gkoliver.devsidetools.common.effect.GottemEffect;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DevsideToolsEffects {
	public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, DevsideTools.MODID);
	public static final RegistryObject<Effect> VIBECHECK = EFFECTS.register("vibecheck", ()->new GottemEffect());
	
	public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTION_TYPES, DevsideTools.MODID);
	
	public static final RegistryObject<Potion> BOTTLE_OF_VIBECHECK = POTIONS.register("vibecheck", ()->new Potion("vibecheck", new EffectInstance(VIBECHECK.get())));

}
