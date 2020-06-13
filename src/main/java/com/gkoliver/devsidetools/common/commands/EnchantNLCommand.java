package com.gkoliver.devsidetools.common.commands;

import java.util.Collection;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EnchantmentArgument;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.TranslationTextComponent;

public class EnchantNLCommand {
		private static final DynamicCommandExceptionType NONLIVING_ENTITY_EXCEPTION = new DynamicCommandExceptionType((p_208839_0_) -> {
	      return new TranslationTextComponent("commands.enchant.failed.entity", p_208839_0_);
	   });
	   private static final DynamicCommandExceptionType ITEMLESS_EXCEPTION = new DynamicCommandExceptionType((p_208835_0_) -> {
	      return new TranslationTextComponent("commands.enchant.failed.itemless", p_208835_0_);
	   });
	   private static final SimpleCommandExceptionType FAILED_EXCEPTION = new SimpleCommandExceptionType(new TranslationTextComponent("commands.enchant.failed"));

	   public static void register(CommandDispatcher<CommandSource> dispatcher) {
	      dispatcher.register(Commands.literal("enchantur").requires((cmdsource) -> {
	         return cmdsource.hasPermissionLevel(2);
	      }).then(Commands.argument("targets", EntityArgument.entities()).then(Commands.argument("enchantment", EnchantmentArgument.enchantment()).executes((cmdsource) -> {
	         return enchant(cmdsource.getSource(), EntityArgument.getEntities(cmdsource, "targets"), EnchantmentArgument.getEnchantment(cmdsource, "enchantment"), 1);
	      }).then(Commands.argument("level", IntegerArgumentType.integer(-100000, 100000)).executes((cmdsource) -> {
	         return enchant(cmdsource.getSource(), EntityArgument.getEntities(cmdsource, "targets"), EnchantmentArgument.getEnchantment(cmdsource, "enchantment"), IntegerArgumentType.getInteger(cmdsource, "level"));
	      })))));
	   }
	   @SuppressWarnings("deprecation")
	public static void addEnchantment(ItemStack stack, Enchantment ench, int level) {
		      stack.getOrCreateTag();
		      if (!stack.getTag().contains("Enchantments", 9)) {
		    	  stack.getTag().put("Enchantments", new ListNBT());
		      }

		      ListNBT listnbt = stack.getTag().getList("Enchantments", 10);
		      CompoundNBT compoundnbt = new CompoundNBT();
		      compoundnbt.putString("id", String.valueOf((Object)Registry.ENCHANTMENT.getKey(ench)));
		      compoundnbt.putInt("lvl", (short)(level));
		      listnbt.add(compoundnbt);
		   }
	   private static int enchant(CommandSource source, Collection<? extends Entity> targets, Enchantment enchantmentIn, int level) throws CommandSyntaxException {
         int i = 1;

         for(Entity entity : targets) {
            if (entity instanceof LivingEntity) {
               LivingEntity livingentity = (LivingEntity)entity;
               ItemStack itemstack = livingentity.getHeldItemMainhand();
               if (!itemstack.isEmpty()) {
            	  addEnchantment(itemstack, enchantmentIn, level);
               } else if (targets.size() == 1) {
                  throw ITEMLESS_EXCEPTION.create(livingentity.getName().getString());
               }
            } else if (targets.size() == 1) {
               throw NONLIVING_ENTITY_EXCEPTION.create(entity.getName().getString());
            }
         }

         if (i == 0) {
            throw FAILED_EXCEPTION.create();
         } else {
            if (targets.size() == 1) {
               source.sendFeedback(new TranslationTextComponent("commands.enchant.success.single", enchantmentIn.getDisplayName(level), targets.iterator().next().getDisplayName()), true);
            } else {
               source.sendFeedback(new TranslationTextComponent("commands.enchant.success.multiple", enchantmentIn.getDisplayName(level), targets.size()), true);
            }

            return i;
         }
	      
	   }

}
