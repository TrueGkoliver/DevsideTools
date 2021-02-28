package com.gkoliver.devsidetools.core.registry;

import java.util.ArrayList;
import java.util.HashMap;

import com.gkoliver.devsidetools.DevsideTools;
import com.gkoliver.devsidetools.common.item.ItemNBTSpawnEgg;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item.Properties;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DevsideToolsItems {
	public static RegistryObject<ItemNBTSpawnEgg> genSlabEgg(int id, int colorbase, int colorsides) {
		if (DevsideTools.swampExpansion) {
			CompoundNBT nbtTag = SLC.get(id);
			RegistryObject<ItemNBTSpawnEgg> tbr = ITEMS.register("slabfish_spawn_egg_"+Integer.toString(id), ()->new ItemNBTSpawnEgg(colorbase, colorsides, new Item.Properties().group(ItemGroup.SEARCH), nbtTag));
			SLABFISH_EGGS.add(tbr);
			return tbr;
		}
		else {
			return null;
		}
	}
	public static RegistryObject<ItemNBTSpawnEgg> genVillager(String type, String name, int colorBase, int colorSide, String modname) {
		if (modname!=null) {
			if (!ModList.get().isLoaded(modname)) return null;
		}
		CompoundNBT nbtTag = new CompoundNBT();
		CompoundNBT villager = new CompoundNBT();
		villager.putString("type", type);
		nbtTag.put("VillagerData", villager);
		RegistryObject<ItemNBTSpawnEgg> tbr = ITEMS.register("villager_spawn_egg_"+name, ()->new ItemNBTSpawnEgg(EntityType.VILLAGER, colorBase, colorSide, new Item.Properties().group(ItemGroup.SEARCH), nbtTag));
		return tbr;
	}
	public static RegistryObject<ItemNBTSpawnEgg> genVillager(String type, String name, int colorBase, int colorSide) {
		return genVillager(type, name, colorBase, colorSide, null);
	}
	public static RegistryObject<ItemNBTSpawnEgg> genCatEgg(int id, int colorbase, int colorside) {
		CompoundNBT nbtTag = new CompoundNBT();
		nbtTag.putInt("CatType", id);
		RegistryObject<ItemNBTSpawnEgg> tbr = ITEMS.register("cat_spawn_egg_"+Integer.toString(id), ()->new ItemNBTSpawnEgg(EntityType.CAT, colorbase, colorside, new Item.Properties().group(ItemGroup.SEARCH), nbtTag));
		return tbr;
	}
	
	public static final ArrayList<ItemNBTSpawnEgg> NBT_SPAWN_EGGS = new ArrayList<ItemNBTSpawnEgg>();
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DevsideTools.MODID);
	
	
	public static final CompoundNBT BROWN_PANDA = new CompoundNBT();
	public static final CompoundNBT BROWN_MOOSHROOM = new CompoundNBT();
	public static final CompoundNBT ARCTIC_FOX = new CompoundNBT();
	
	public static final HashMap<Integer, CompoundNBT> SLC = new HashMap<Integer,CompoundNBT>();
	static {
		registerNBTs();
	}
	public static final ArrayList<RegistryObject<ItemNBTSpawnEgg>> SLABFISH_EGGS = new ArrayList<RegistryObject<ItemNBTSpawnEgg>>();
	
	//Cats: All of them
	public static final RegistryObject<ItemNBTSpawnEgg> TABBY_SPAWN_EGG = genCatEgg(0, 0x725239, 0x2A1A0E);
	public static final RegistryObject<ItemNBTSpawnEgg> TUXEDO_SPAWN_EGG = genCatEgg(1, 0x2A1A0E, 0xECECEC);
	public static final RegistryObject<ItemNBTSpawnEgg> RED_SPAWN_EGG = genCatEgg(2, 0xEBC987, 0xE08B2C);
	public static final RegistryObject<ItemNBTSpawnEgg> SIAMESE_SPAWN_EGG = genCatEgg(3, 0xDFD4C2, 0x534A37);
	public static final RegistryObject<ItemNBTSpawnEgg> BRITISH_SHORTHAIR_SPAWN_EGG = genCatEgg(4, 0xBBBBBB, 0x515151);
	public static final RegistryObject<ItemNBTSpawnEgg> CALICO_SPAWN_EGG = genCatEgg(5, 0x674619, 0x4A443A);
	public static final RegistryObject<ItemNBTSpawnEgg> PERSIAN_SPAWN_EGG = genCatEgg(6, 0xFCE8CA, 0x675134);
	public static final RegistryObject<ItemNBTSpawnEgg> RAGDOLL_SPAWN_EGG = genCatEgg(7, 0xF4F4F4, 0x726258);
	public static final RegistryObject<ItemNBTSpawnEgg> WHITE_SPAWN_EGG = genCatEgg(8, 0xFAF6F8, 0x7D7B7C);
	public static final RegistryObject<ItemNBTSpawnEgg> JELLIE_SPAWN_EGG = genCatEgg(9, 0x403D3D, 0xE4E1E2);
	public static final RegistryObject<ItemNBTSpawnEgg> BLACK_SPAWN_EGG = genCatEgg(10, 0x0E0D1F, 0x03030A);
	
	
	
	
	
	public static final Item.Properties SPAWN_EGG_PROPS = new Item.Properties().group(ItemGroup.SEARCH); 
	public static final RegistryObject<Item> BROWN_MOOSHROOM_SPAWN_EGG = ITEMS.register("brown_mooshroom_spawn_egg", ()->new ItemNBTSpawnEgg(EntityType.MOOSHROOM, 0x6C5148, 0x4B362C, new Item.Properties().group(ItemGroup.SEARCH), BROWN_MOOSHROOM));
	public static final RegistryObject<Item> BROWN_PANDA_SPAWN_EGG = ITEMS.register("brown_panda_spawn_egg", ()->new ItemNBTSpawnEgg(EntityType.PANDA, 0xA5A5A4, 0x523D30, new Item.Properties().group(ItemGroup.SEARCH), BROWN_PANDA));
	public static final RegistryObject<Item> ARCTIC_FOX_SPAWN_EGG = ITEMS.register("arctic_fox_spawn_egg", ()->new ItemNBTSpawnEgg(EntityType.FOX, 0xFEFDF6, 0xA2BDB7, SPAWN_EGG_PROPS, ARCTIC_FOX));
	
	
	
	


	public static final RegistryObject<ItemNBTSpawnEgg> VILLAGER_PLAINS = genVillager("minecraft:plains", "plains", 0x73554E, 0xBF896D);
	public static final RegistryObject<ItemNBTSpawnEgg> VILLAGER_DESERT = genVillager("minecraft:desert", "desert", 0xF3CF9D, 0xE36E08);
	public static final RegistryObject<ItemNBTSpawnEgg> VILLAGER_JUNGLE = genVillager("minecraft:jungle", "jungle", 0x356517, 0xC59235);
	public static final RegistryObject<ItemNBTSpawnEgg> VILLAGER_SAVANNA = genVillager("minecraft:savanna", "savanna", 0xAA2A2A, 0x413B0F);
	public static final RegistryObject<ItemNBTSpawnEgg> VILLAGER_SNOWY = genVillager("minecraft:snow", "snowy", 0x5C8C81, 0xDED2B1);
	public static final RegistryObject<ItemNBTSpawnEgg> VILLAGER_SWAMP = genVillager("minecraft:swamp", "swamp", 0x4F3A62, 0x63723B);
	public static final RegistryObject<ItemNBTSpawnEgg> VILLAGER_TAIGA = genVillager("minecraft:taiga", "taiga", 0x1A1A1A, 0x70654D);

	public static final RegistryObject<ItemNBTSpawnEgg> VILLAGER_BLOSSOM = genVillager("environmental:blossom", "blossom", 0xF7ACC4, 0x535B32, "environmental");
	public static final RegistryObject<ItemNBTSpawnEgg> VILLAGER_FOREST = genVillager("environmental:forest", "forest", 0x535B32, 0x5A3C2B, "environmental");
	public static final RegistryObject<ItemNBTSpawnEgg> VILLAGER_FLOWER_FOREST = genVillager("environmental:flower_forest", "flower_forest", 0x716099, 0x6C4C3A, "environmental");
	public static final RegistryObject<ItemNBTSpawnEgg> VILLAGER_ICE_SPIKES = genVillager("environmental:ice_spikes", "ice_spikes", 0x44322E, 0x92B9FE, "environmental");
	public static void registerNBTs() {
		BROWN_MOOSHROOM.putString("Type", "brown");
		BROWN_PANDA.putString("MainGene", "brown");
		BROWN_PANDA.putString("HiddenGene", "brown");
		ARCTIC_FOX.putString("Type", "snow");
		for (int i=0; i<43; i++) {
			System.out.println(i);
			CompoundNBT compound = new CompoundNBT();
			compound.putInt("SlabfishType", i);
			SLC.put(i, compound);
		} 
	}
	public static void registerSlabfishTypes() {
	}

}
