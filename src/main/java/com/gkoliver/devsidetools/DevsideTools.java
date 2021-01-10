package com.gkoliver.devsidetools;

import com.gkoliver.devsidetools.common.commands.*;
import com.gkoliver.devsidetools.common.network.SetItemStackPacket;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
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
import org.spongepowered.asm.mixin.Mixin;

import java.util.ArrayList;
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
    	String commandTest = "hello this is a test bruh";
    	System.out.println(Command.stripCommand(commandTest, 3));
    	IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	if (ModList.get().isLoaded("swampexpansion")) {
    		this.swampExpansion = true;
    	}
    	DevsideToolsItems.ITEMS.register(eventBus);
    	DevsideToolsEffects.EFFECTS.register(eventBus);
    	DevsideToolsEffects.POTIONS.register(eventBus);
    	eventBus.addListener(this::setup);
		this.makeCommands();
		DistExecutor.callWhenOn(Dist.CLIENT, ()->()->System.setProperty("java.awt.headless", "false"));
    }
    public void makeCommands() {
		new AggravateCommand();
		//new DeepEditorCommands();
		new EnchantNLCommand();
		new EndlessEffectCommand();
		new GetColorCodeCommand();
		new GetPotionIDCommand();
		new NameCommands.LoreRawCommands();
		new NameCommands.NameRawCommands();
		new PlayerHealCommands.HealCommand();
		new PlayerHealCommands.HungerCommand();
		new PlayerHealCommands.SaturateCommand();
		new PotionCommands.PotionCommand();
		new PotionCommands.SplashPotionCommand();
		new PotionCommands.LingeringPotionCommand();
		new SetCountCommand();
		new UnbreakableCommand();
	}
    public void setup(final FMLCommonSetupEvent event)
    {
   		handler.registerMessage(1, SetItemStackPacket.class, SetItemStackPacket::write, SetItemStackPacket::read, SetItemStackPacket::work);
    }
    public static ArrayList<Command> commands = Lists.newArrayList();
    @SubscribeEvent
   	public static void onServerStartingEvent(RegisterCommandsEvent event) {
		/*EnchantNLCommand.register(event.getDispatcher());
		GetPotionIDCommand.register(event.getDispatcher());
		UnbreakableCommand.register(event.getDispatcher());
		EndlessEffectCommand.register(event.getDispatcher());
		NameCommands.NameRawCommands.register(event.getDispatcher());
		NameCommands.LoreRawCommands.register(event.getDispatcher());
		PotionCommands.PotionCommand.register(event.getDispatcher());
		PotionCommands.LingeringPotionCommand.register(event.getDispatcher());
		PotionCommands.SplashPotionCommand.register(event.getDispatcher());
		AggravateCommand.register(event.getDispatcher());
		SetCountCommand.register(event.getDispatcher());
		PlayerHealCommands.HealCommand.register(event.getDispatcher());
		PlayerHealCommands.HungerCommand.register(event.getDispatcher());
		PlayerHealCommands.SaturateCommand.register(event.getDispatcher());
		GetColorCodeCommand.register(event.getDispatcher());
		//DeepEditorCommands.register(event.getDispatcher());*/
		for (Command commandIn : commands) {commandIn.register(event.getDispatcher());}
   	}
}
