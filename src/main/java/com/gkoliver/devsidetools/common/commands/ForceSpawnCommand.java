package com.gkoliver.devsidetools.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.command.arguments.PotionArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;

import java.util.Collection;
import java.util.Random;
import java.util.Set;

public class ForceSpawnCommand extends Command {
    private static Random RAND = new Random();
    @Override
    public void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("forcespawn").requires((p_198359_0_) -> {
            return p_198359_0_.hasPermissionLevel(2);
        }).then(Commands.argument("targets", EntityArgument.entity()).executes((context)->{
            return forceSpawn(context.getSource(), EntityArgument.getEntity(context, "targets"));
        })));
    }
    private static final int const_radius = 128;
    public static int forceSpawn(CommandSource source, Entity target) {
        System.out.println("Firing ForceSpawn");
        BlockPos position = new BlockPos(target.getPosX(), target.getPosY(), target.getPosZ());
        forceOneSpawn(1, target.getEntityWorld(), position, source);
        return 1;
    }
    public static void forceOneSpawn(int amt, World worldIn, BlockPos posIn, CommandSource source) {
        Biome biomeIn = worldIn.getBiome(posIn);
        MobSpawnInfo info = biomeIn.func_242433_b();
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
                    entity.setPosition(x, y, z);
                    entity.onAddedToWorld();
                    source.sendFeedback(new StringTextComponent("Summoned a ").func_230529_a_(entity.getName()), false);
                    flag = true;
                }

            }

        }
    }
}
