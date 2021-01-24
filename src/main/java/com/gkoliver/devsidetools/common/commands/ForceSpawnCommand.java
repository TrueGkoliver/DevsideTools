package com.gkoliver.devsidetools.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;

import java.util.Random;
import java.util.Set;

public class ForceSpawnCommand extends Command {
    private static Random RAND = new Random();
    @Override
    public void register(CommandDispatcher<CommandSource> dispatcher) {

    }
    private static final int const_radius = 128;

    public void forceOneSpawn(int amt, World worldIn, BlockPos posIn) {
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
