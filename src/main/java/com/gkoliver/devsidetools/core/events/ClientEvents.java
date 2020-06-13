package com.gkoliver.devsidetools.core.events;


import com.gkoliver.devsidetools.DevsideTools;
import com.gkoliver.devsidetools.common.item.ItemNBTSpawnEgg;
import com.gkoliver.devsidetools.core.registry.DevsideToolsItems;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
@Mod.EventBusSubscriber(modid=DevsideTools.MODID, bus=Bus.MOD, value=Dist.CLIENT)
public class ClientEvents {
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
	}
	@SubscribeEvent
	public static void registerBlockColors(ColorHandlerEvent.Item event) {
		for (ItemNBTSpawnEgg item : DevsideToolsItems.NBT_SPAWN_EGGS) {
			event.getItemColors().register((p_198141_1_, p_198141_2_) -> {
	            return item.getColor(p_198141_2_);
	         }, item);
		}
	}

}
