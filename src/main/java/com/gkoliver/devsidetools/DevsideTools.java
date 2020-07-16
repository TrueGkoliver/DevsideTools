package com.gkoliver.devsidetools;

import com.gkoliver.devsidetools.common.commands.*;
import com.gkoliver.devsidetools.common.network.SetItemStackPacket;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gkoliver.devsidetools.core.registry.DevsideToolsEffects;
import com.gkoliver.devsidetools.core.registry.DevsideToolsItems;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(DevsideTools.MODID)
@Mod.EventBusSubscriber(modid=DevsideTools.MODID)
public class DevsideTools
{
	public static final String MODID = "devsidetools";
    private static final Logger LOGGER = LogManager.getLogger();
    public static boolean swampExpansion = false;
    public static SimpleChannel handler = NetworkRegistry.newSimpleChannel(new ResourceLocation(MODID, "deep_editor_handler"), ()->"1.16.1", predicate -> true, predicate->true);
    public DevsideTools() {
    	IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	if (ModList.get().isLoaded("swampexpansion")) {
    		this.swampExpansion = true;
    	}
    	DevsideToolsItems.ITEMS.register(eventBus);
    	DevsideToolsEffects.EFFECTS.register(eventBus);
    	DevsideToolsEffects.POTIONS.register(eventBus);
    	eventBus.addListener(this::setup);
		DistExecutor.callWhenOn(Dist.CLIENT, ()->()->System.setProperty("java.awt.headless", "false"));
    }
    public void setup(final FMLCommonSetupEvent event)
    {
    	System.out.println("WHAT DO YOU MENA INVALID HJ DJF DYOU EDASTUU GUDOUBM KWFY ");
   		handler.registerMessage(1, SetItemStackPacket.class, SetItemStackPacket::write, SetItemStackPacket::read, SetItemStackPacket::work);
    }
    @SubscribeEvent
   public static void onServerStartingEvent(FMLServerStartingEvent event) {
	   EnchantNLCommand.register(event.getCommandDispatcher());
	   GetPotionIDCommand.register(event.getCommandDispatcher());
	   UnbreakableCommand.register(event.getCommandDispatcher());
	   EndlessEffectCommand.register(event.getCommandDispatcher());
	   NameCommands.NameRawCommands.register(event.getCommandDispatcher());
	   NameCommands.LoreRawCommands.register(event.getCommandDispatcher());
	   PotionCommands.PotionCommand.register(event.getCommandDispatcher());
	   PotionCommands.LingeringPotionCommand.register(event.getCommandDispatcher());
	   PotionCommands.SplashPotionCommand.register(event.getCommandDispatcher());
	   AggravateCommand.register(event.getCommandDispatcher());
	   SetCountCommand.register(event.getCommandDispatcher());
	   PlayerHealCommands.HealCommand.register(event.getCommandDispatcher());
	   PlayerHealCommands.HungerCommand.register(event.getCommandDispatcher());
	   PlayerHealCommands.SaturateCommand.register(event.getCommandDispatcher());
	   GetColorCodeCommand.register(event.getCommandDispatcher());
		DeepEditorCommands.register(event.getCommandDispatcher());
   }
}
