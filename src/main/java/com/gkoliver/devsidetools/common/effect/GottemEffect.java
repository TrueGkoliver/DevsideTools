package com.gkoliver.devsidetools.common.effect;

import java.util.Collection;
import java.util.List;

import com.gkoliver.devsidetools.common.commands.AggravateCommand;

import net.minecraft.command.CommandSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.InstantEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class GottemEffect extends InstantEffect {

	public GottemEffect() {
		super(EffectType.NEUTRAL, 0x00fff7);
	}
	
	@Override
	public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
		BlockPos position = new BlockPos(entityLivingBaseIn.getPosX(), entityLivingBaseIn.getPosY(), entityLivingBaseIn.getPosZ());
		List<Entity> entities = entityLivingBaseIn.getEntityWorld().getEntitiesWithinAABBExcludingEntity(entityLivingBaseIn, new AxisAlignedBB(position).grow(2000D));
		aggravate(entities, entityLivingBaseIn, false);
	}
	public static int aggravate(Collection<? extends Entity> aggressor, Entity aggression, boolean doBoth) {
		if (aggression instanceof LivingEntity) {
			for (Entity entity : aggressor) {
				if (entity instanceof LivingEntity) {
					LivingEntity livingAggressor = (LivingEntity)entity;
					LivingEntity livingVictim = (LivingEntity)aggression;
					livingAggressor.setRevengeTarget(livingVictim);
					if (doBoth) {
						livingVictim.setRevengeTarget(livingAggressor);
					}
					
				}
			}
		}
		return 1;
	}
}
