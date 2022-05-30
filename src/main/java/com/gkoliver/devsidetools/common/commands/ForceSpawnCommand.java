package com.gkoliver.devsidetools.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;

import java.util.Collection;
import java.util.Random;
import java.util.Set;

public class ForceSpawnCommand extends Command {
    private static Random RAND = new Random();
    @Override
    public void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("forcespawn").requires((p_198359_0_) -> {
            return p_198359_0_.hasPermission(2);
        }).then(Commands.argument("targets", EntityArgument.entity()).executes((context)->{
            return forceSpawn(context.getSource(), EntityArgument.getEntity(context, "targets"));
        })));
    }
    private static final int const_radius = 128;
    public static int forceSpawn(CommandSourceStack source, Entity target) {
        System.out.println("Firing ForceSpawn");
        BlockPos position = new BlockPos(target.getX(), target.getY(), target.getZ());
        forceOneSpawn(1, target.getLevel(), position, source);
        return 1;
    }
    public static void forceOneSpawn(int amt, Level worldIn, BlockPos posIn, CommandSourceStack source) {
        Biome biomeIn = worldIn.getBiomeManager().m_204214_(posIn).m_203334_();

        MobSpawnSettings info = biomeIn.getMobSettings();
        Set<EntityType<?>> entities = info.getEntityTypes();
        System.out.println(entities);
        for (EntityType<?> entityType : entities) {
            System.out.println(entityType.getRegistryName().toString());
            boolean flag = false;
            while (!flag) {
                int x = posIn.getX() + RAND.nextInt(const_radius);
                int y = posIn.getY() + RAND.nextInt(const_radius);
                int z = posIn.getZ() + RAND.nextInt(const_radius);
                BlockPos spawnPos = new BlockPos(x, y, z);
                //boolean flag2 = worldIn.getBlockState(spawnPos).canEntitySpawn(worldIn, spawnPos, entityType);
                boolean flag2 = worldIn.getBlockState(spawnPos).isAir();
                if (flag2) {
                    Entity entity = entityType.create(worldIn);
                    entity.setPos(x, y, z);
                    entity.onAddedToWorld();
                    source.sendSuccess(new TextComponent("Summoned a ").append(entity.getName()), false);
                    flag = true;
                }

            }

        }
    }
}
