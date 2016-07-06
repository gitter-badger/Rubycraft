package com.flatworks.rubycraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * @author sjx233
 */
public class ItemRuby extends Item {
    public ItemRuby() {
        this.setUnlocalizedName("ruby");
        this.setMaxStackSize(64);
        this.setCreativeTab(CreativeTabs.MATERIALS);
    }
}
