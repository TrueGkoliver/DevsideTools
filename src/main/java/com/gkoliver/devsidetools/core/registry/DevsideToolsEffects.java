package com.gkoliver.devsidetools.core.registry;

import com.gkoliver.devsidetools.DevsideTools;
import com.gkoliver.devsidetools.common.effect.GottemEffect;

import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DevsideToolsEffects {
	public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, DevsideTools.MODID);
	public static final RegistryObject<MobEffect> VIBECHECK = EFFECTS.register("vibecheck", ()->new GottemEffect());
	
	public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, DevsideTools.MODID);
	
	public static final RegistryObject<Potion> BOTTLE_OF_VIBECHECK = POTIONS.register("vibecheck", ()->new Potion("vibecheck", new MobEffectInstance(VIBECHECK.get())));

}
