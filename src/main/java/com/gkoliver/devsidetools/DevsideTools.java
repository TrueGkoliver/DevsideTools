package com.gkoliver.devsidetools;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gkoliver.devsidetools.common.commands.AggravateCommand;
import com.gkoliver.devsidetools.common.commands.EnchantNLCommand;
import com.gkoliver.devsidetools.common.commands.EndlessEffectCommand;
import com.gkoliver.devsidetools.common.commands.GetColorCodeCommand;
import com.gkoliver.devsidetools.common.commands.GetPotionIDCommand;
import com.gkoliver.devsidetools.common.commands.NameCommands;
import com.gkoliver.devsidetools.common.commands.PlayerHealCommands;
import com.gkoliver.devsidetools.common.commands.PotionCommands;
import com.gkoliver.devsidetools.common.commands.SetCountCommand;
import com.gkoliver.devsidetools.common.commands.UnbreakableCommand;
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
    public DevsideTools() {
    	IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	if (ModList.get().isLoaded("swampexpansion")) {
    		this.swampExpansion = true;
    	}
    	DevsideToolsItems.ITEMS.register(eventBus);
    	DevsideToolsEffects.EFFECTS.register(eventBus);
    	DevsideToolsEffects.POTIONS.register(eventBus);
    }
    @SubscribeEvent
    public static void setup(final FMLCommonSetupEvent event)
    {
   
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
   }
}
