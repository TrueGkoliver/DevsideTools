package com.gkoliver.devsidetools.common.effect;

import java.util.Collection;
import java.util.List;

import com.gkoliver.devsidetools.common.commands.AggravateCommand;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.phys.AABB;

public class GottemEffect extends InstantenousMobEffect {

	public GottemEffect() {
		super(MobEffectCategory.NEUTRAL, 0x00fff7);
	}
	

	public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
		BlockPos position = new BlockPos(entityLivingBaseIn.getX(), entityLivingBaseIn.getY(), entityLivingBaseIn.getZ());
		List<Entity> entities = entityLivingBaseIn.getLevel().getEntities(entityLivingBaseIn, new AABB(position).inflate(2000D), (e)->{return e instanceof LivingEntity;});
				//.getNearbyEntities(Entity.class, TargetingConditions.forCombat(), entityLivingBaseIn, new AABB(position).inflate(2000D));
		aggravate(entities, entityLivingBaseIn, false);
	}
	public static int aggravate(Collection<? extends Entity> aggressor, Entity aggression, boolean doBoth) {
		if (aggression instanceof LivingEntity) {
			for (Entity entity : aggressor) {
				if (entity instanceof LivingEntity) {
					LivingEntity livingAggressor = (LivingEntity)entity;
					LivingEntity livingVictim = (LivingEntity)aggression;
					livingAggressor.setLastHurtByMob(livingVictim);
					if (doBoth) {
						livingVictim.setLastHurtByMob(livingAggressor);
					}
					
				}
			}
		}
		return 1;
	}
}
