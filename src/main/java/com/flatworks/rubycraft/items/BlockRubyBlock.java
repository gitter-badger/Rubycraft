package com.flatworks.rubycraft.items;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

/**
 * @author sjx233
 */
public class BlockRubyBlock extends Block {
    public BlockRubyBlock() {
        super(Material.IRON, MapColor.RED);
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setHarvestLevel("pickaxe", 2);
        this.setUnlocalizedName("blockRuby");
        this.setSoundType(SoundType.METAL);
        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    }
}
