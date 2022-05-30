package com.gkoliver.devsidetools.common.commands;

import java.util.Collection;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.ItemEnchantmentArgument;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;

public class EnchantNLCommand extends Command {
		private static final DynamicCommandExceptionType NONLIVING_ENTITY_EXCEPTION = new DynamicCommandExceptionType((p_208839_0_) -> {
	      return new TranslatableComponent("commands.enchant.failed.entity", p_208839_0_);
	   });
	   private static final DynamicCommandExceptionType ITEMLESS_EXCEPTION = new DynamicCommandExceptionType((p_208835_0_) -> {
	      return new TranslatableComponent("commands.enchant.failed.itemless", p_208835_0_);
	   });
	   private static final SimpleCommandExceptionType FAILED_EXCEPTION = new SimpleCommandExceptionType(new TranslatableComponent("commands.enchant.failed"));

	   public void register(CommandDispatcher<CommandSourceStack> dispatcher) {
	      dispatcher.register(Commands.literal("enchantur").requires((cmdsource) -> {
	         return cmdsource.hasPermission(2);
	      }).then(Commands.argument("targets", EntityArgument.entities()).then(Commands.argument("enchantment", ItemEnchantmentArgument.enchantment()).executes((cmdsource) -> {
	         return enchant(cmdsource.getSource(), EntityArgument.getEntities(cmdsource, "targets"), ItemEnchantmentArgument.getEnchantment(cmdsource, "enchantment"), 1);
	      }).then(Commands.argument("level", IntegerArgumentType.integer(-100000, 100000)).executes((cmdsource) -> {
	         return enchant(cmdsource.getSource(), EntityArgument.getEntities(cmdsource, "targets"), ItemEnchantmentArgument.getEnchantment(cmdsource, "enchantment"), IntegerArgumentType.getInteger(cmdsource, "level"));
	      })))));
	   }
	   @SuppressWarnings("deprecation")
	public static void addEnchantment(ItemStack stack, Enchantment ench, int level) {
		      stack.getOrCreateTag();
		      if (!stack.getTag().contains("Enchantments", 9)) {
		    	  stack.getTag().put("Enchantments", new ListTag());
		      }

		      ListTag ListTag = stack.getTag().getList("Enchantments", 10);
		      CompoundTag compoundnbt = new CompoundTag();
		      compoundnbt.putString("id", String.valueOf((Object) Registry.ENCHANTMENT.getKey(ench)));
		      compoundnbt.putInt("lvl", (short)(level));
		      ListTag.add(compoundnbt);
		   }
	   private static int enchant(CommandSourceStack source, Collection<? extends Entity> targets, Enchantment enchantmentIn, int level) throws CommandSyntaxException {
         int i = 1;

         for(Entity entity : targets) {
            if (entity instanceof LivingEntity) {
               LivingEntity livingentity = (LivingEntity)entity;
               ItemStack itemstack = livingentity.getItemInHand(InteractionHand.MAIN_HAND);
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
               source.sendSuccess(new TranslatableComponent("commands.enchant.success.single", enchantmentIn.getFullname(level), targets.iterator().next().getDisplayName()), true);
            } else {
               source.sendSuccess(new TranslatableComponent("commands.enchant.success.multiple", enchantmentIn.getFullname(level), targets.size()), true);
            }
			if (level>255) {
				source.sendSuccess(new TranslatableComponent("commands.enchantur.warning").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC), true);
			}

            return i;
         }
	      
	   }

}
