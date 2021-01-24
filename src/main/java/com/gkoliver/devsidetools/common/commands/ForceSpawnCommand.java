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
        dispatcher.register(Commands.literal("effectinfinity").requires((p_198359_0_) -> {
            return p_198359_0_.hasPermissionLevel(2);
        }).then(Commands.argument("targets", EntityArgument.entity()).executes((context)->{
            return forceSpawn(context.getSource(), EntityArgument.getEntity(context, "targets"));
        });
    }
    private static final int const_radius = 128;
    public static int forceSpawn(CommandSource source, Entity target) {
        BlockPos position = new BlockPos(target.getPosX(), target.getPosY(), target.getPosZ());
        forceOneSpawn(1, target.getEntityWorld(), position);
        return 1;
    }
    public static void forceOneSpawn(int amt, World worldIn, BlockPos posIn) {
        Biome biomeIn = worldIn.getBiome(posIn);
        MobSpawnInfo info = biomeIn.func_242433_b();
        Set<EntityType<?>> entities = info.getEntityTypes();
        for (EntityType<?> entityType : entities) {
            boolean flag = false;
            while (!flag) {
                int x = posIn.getX() + RAND.nextInt(const_radius);
                int y = posIn.getY() + RAND.nextInt(const_radius);
                int z = posIn.getZ() + RAND.nextInt(const_radius);
                BlockPos spawnPos = new BlockPos(x, y, z);
                if (worldIn.getBlockState(spawnPos).canEntitySpawn(worldIn, spawnPos, entityType)) {
                    Entity entity = entityType.create(worldIn);
                    entity.setPosition(x, y, z);
                    entity.onAddedToWorld();
                    flag = true;
                }

            }

        }
    }
}
