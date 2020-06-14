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
	public static RegistryObject<ItemNBTSpawnEgg> genCatEgg(int id, int colorbase, int colorside) {
		CompoundNBT nbtTag = new CompoundNBT();
		nbtTag.putInt("CatType", id);
		RegistryObject<ItemNBTSpawnEgg> tbr = ITEMS.register("cat_spawn_egg_"+Integer.toString(id), ()->new ItemNBTSpawnEgg(EntityType.CAT, colorbase, colorside, new Item.Properties().group(ItemGroup.SEARCH), nbtTag));
		return tbr;
	}
	
	public static final ArrayList<ItemNBTSpawnEgg> NBT_SPAWN_EGGS = new ArrayList<ItemNBTSpawnEgg>();
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<Item>(ForgeRegistries.ITEMS, DevsideTools.MODID);
	
	
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
	
	
	
	//Slabfish
	public static final RegistryObject<ItemNBTSpawnEgg> SLABFISH_0 = genSlabEgg(0, 0x42662D, 0x25512C);
	public static final RegistryObject<ItemNBTSpawnEgg> SLABFISH_1 = genSlabEgg(1, 0x386080, 0x284257);
	public static final RegistryObject<ItemNBTSpawnEgg> SLABFISH_2 = genSlabEgg(2, 0x363A17, 0x191E11);
	public static final RegistryObject<ItemNBTSpawnEgg> SLABFISH_3 = genSlabEgg(3, 0x3F392F, 0x8B7B60);
	public static final RegistryObject<ItemNBTSpawnEgg> SLABFISH_4 = genSlabEgg(4, 0x585952, 0xD8D7D5);
	public static final RegistryObject<ItemNBTSpawnEgg> SLABFISH_5 = genSlabEgg(5, 0x5E6E23, 0x7A4F35);
	public static final RegistryObject<ItemNBTSpawnEgg> SLABFISH_6 = genSlabEgg(6, 0x533F18, 0xDBD0A4);
	public static final RegistryObject<ItemNBTSpawnEgg> SLABFISH_7 = genSlabEgg(7, 0x443916, 0x5C4D2D);
	public static final RegistryObject<ItemNBTSpawnEgg> SLABFISH_8 = genSlabEgg(8, 0x6E402F, 0x39211A);
	public static final RegistryObject<ItemNBTSpawnEgg> SLABFISH_9 = genSlabEgg(9, 0x2D4D5B, 0x775837);
	public static final RegistryObject<ItemNBTSpawnEgg> SLABFISH_10 = genSlabEgg(10, 0xFFFA97, 0x2FA55B);
	public static final RegistryObject<ItemNBTSpawnEgg> SLABFISH_11 = genSlabEgg(11, 0x37634B, 0x735535);
	public static final RegistryObject<ItemNBTSpawnEgg> SLABFISH_12 = genSlabEgg(12, 0x3C5E29, 0x4A4030);
	public static final RegistryObject<ItemNBTSpawnEgg> SLABFISH_13 = genSlabEgg(13, 0x3C5D29, 0x283E1C);
	public static final RegistryObject<ItemNBTSpawnEgg> SLABFISH_14 = genSlabEgg(14, 0x9C9FA2, 0x464646);
	public static final RegistryObject<ItemNBTSpawnEgg> SLABFISH_15 = genSlabEgg(15, 0x252525, 0x0A0A0A);
	public static final RegistryObject<ItemNBTSpawnEgg> SLABFISH_16 = genSlabEgg(16, 0x4C5235, 0x31361D);
	public static final RegistryObject<ItemNBTSpawnEgg> SLABFISH_17 = genSlabEgg(17, 0x8D8B39, 0x806232);
	public static final RegistryObject<ItemNBTSpawnEgg> SLABFISH_18 = genSlabEgg(18, 0x4F5A26, 0x614258);
	public static final RegistryObject<ItemNBTSpawnEgg> SLABFISH_19 = genSlabEgg(19, 0x423B22, 0x2A2815);
	public static final RegistryObject<ItemNBTSpawnEgg> SLABFISH_20 = genSlabEgg(20, 0x29350E, 0x5A693E);
	public static final RegistryObject<ItemNBTSpawnEgg> SLABFISH_21 = genSlabEgg(21, 0x769485, 0x617F80);
	public static final RegistryObject<ItemNBTSpawnEgg> SLABFISH_22 = genSlabEgg(22, 0x757F7F, 0x212E2E);
	public static final RegistryObject<ItemNBTSpawnEgg> SLABFISH_23 = genSlabEgg(23, 0x2B2F1E, 0x4C1D0D);
	public static final RegistryObject<ItemNBTSpawnEgg> SLABFISH_24 = genSlabEgg(24, 0x1F1F1F, 0x80007E);
	public static final RegistryObject<ItemNBTSpawnEgg> SLABFISH_25 = genSlabEgg(25, 0x331B33, 0x676044);
	public static final RegistryObject<ItemNBTSpawnEgg> SLABFISH_26 = genSlabEgg(26, 0x807F7B, 0x515557);
	/*public static final RegistryObject<ItemNBTSpawnEgg> SLABFISH_27 = genSlabEgg(27, 0, 0);
	public static final RegistryObject<ItemNBTSpawnEgg> SLABFISH_28 = genSlabEgg(28, 0, 0);*/
	public static final RegistryObject<ItemNBTSpawnEgg> SLABFISH_29 = genSlabEgg(29, 0x631A1A, 0x2C0909);
	//public static final RegistryObject<ItemNBTSpawnEgg> SLABFISH_30 = genSlabEgg(30, 0, 0);
	public static final RegistryObject<ItemNBTSpawnEgg> SLABFISH_31 = genSlabEgg(31, 0x387A5F, 0x195200);
	public static final RegistryObject<ItemNBTSpawnEgg> SLABFISH_32 = genSlabEgg(32, 0x2D4A57, 0x51869C);
	public static final RegistryObject<ItemNBTSpawnEgg> SLABFISH_33 = genSlabEgg(33, 0x2C4B64, 0x844638);
	public static final RegistryObject<ItemNBTSpawnEgg> SLABFISH_34 = genSlabEgg(34, 0x39684F, 0x28463C);
	public static final RegistryObject<ItemNBTSpawnEgg> SLABFISH_35 = genSlabEgg(35, 0x9F3131, 0x9FA6AD);
	public static final RegistryObject<ItemNBTSpawnEgg> SLABFISH_36 = genSlabEgg(36, 0x434D21, 0x202816);
	public static final RegistryObject<ItemNBTSpawnEgg> SLABFISH_37 = genSlabEgg(37, 0xB1A574, 0x572F58);
	public static final RegistryObject<ItemNBTSpawnEgg> SLABFISH_38 = genSlabEgg(38, 0x27311B, 0x192015);
	public static final RegistryObject<ItemNBTSpawnEgg> SLABFISH_39 = genSlabEgg(39, 0x288B44, 0x7A648C);
	public static final RegistryObject<ItemNBTSpawnEgg> SLABFISH_40 = genSlabEgg(40, 0x37707E, 0x1B343A);
	public static final RegistryObject<ItemNBTSpawnEgg> SLABFISH_41 = genSlabEgg(41, 0x1F3947, 0x626262);
	public static final RegistryObject<ItemNBTSpawnEgg> SLABFISH_42 = genSlabEgg(42, 0x564119, 0x282415);
	
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
