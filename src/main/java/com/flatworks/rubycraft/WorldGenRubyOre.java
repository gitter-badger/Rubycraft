package com.flatworks.rubycraft;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

/**
 * @author sjx233
 */
public class WorldGenRubyOre implements IWorldGenerator {
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world,
            IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        int minY = Math.min(RubycraftMod.spawnMinY, RubycraftMod.spawnMaxY);
        int maxY = Math.max(RubycraftMod.spawnMinY, RubycraftMod.spawnMaxY);
        
        for (int i = 0; i < RubycraftMod.spawnTrials; i++) {
            int x = chunkX * 16 + random.nextInt(16);
            int y = MathHelper.getRandomIntegerInRange(random, minY, maxY);
            int z = chunkZ * 16 + random.nextInt(16);
            
            new WorldGenMinable(RubycraftMod.RUBY_ORE.getDefaultState(), RubycraftMod.spawnCount)
                    .generate(world, random, new BlockPos(x, y, z));
        }
    }
}
