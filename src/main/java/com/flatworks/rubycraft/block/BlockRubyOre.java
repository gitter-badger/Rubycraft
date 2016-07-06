package com.flatworks.rubycraft.block;

import java.util.Random;

import com.flatworks.rubycraft.RubycraftMod;

import net.minecraft.block.BlockOre;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

/**
 * @author sjx233
 */
public class BlockRubyOre extends BlockOre {
    public BlockRubyOre() {
        super();
        this.setHardness(3.0F);
        this.setResistance(5.0F);
        this.setHarvestLevel("pickaxe", 2);
        this.setUnlocalizedName("oreRuby");
        this.setSoundType(SoundType.STONE);
    }
    
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return RubycraftMod.RUBY;
    }
    
    @Override
    public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos,
            int fortune) {
        return MathHelper.getRandomIntegerInRange(
                world instanceof World ? ((World) world).rand : new Random(), 3, 7);
    }
}
