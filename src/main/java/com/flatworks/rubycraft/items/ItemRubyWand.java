package com.flatworks.rubycraft.items;

import java.util.HashSet;

import com.flatworks.rubycraft.RubycraftMod;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * @author sjx233
 */
public class ItemRubyWand extends ItemTool {
    public ItemRubyWand() {
        super(RubycraftMod.MATERIAL_RUBY, new HashSet<Block>());
        this.setUnlocalizedName("rubyWand");
        this.setCreativeTab(CreativeTabs.TOOLS);
    }
    
    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        if (target != null) {
            target.attackEntityFrom(RubycraftMod.DAMAGE_RUBY_MAGIC, 20);
            this.lighting(attacker, target.getEntityWorld(), target.posX, target.posY, target.posZ);
        }
        return super.hitEntity(stack, target, attacker);
    }
    
    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn,
            BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        switch (facing) {
            case EAST:
                pos = pos.east();
                break;
            case NORTH:
                pos = pos.north();
                break;
            case SOUTH:
                pos = pos.south();
                break;
            case UP:
            case DOWN:
                pos = pos.up();
                break;
            case WEST:
                pos = pos.west();
                break;
            default:
                break;
        }
        this.lighting(playerIn, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return EnumActionResult.SUCCESS;
    }
    
    private void lighting(EntityLivingBase entity, World world, double x, double y, double z) {
        if (!world.isRemote) {
            world.addWeatherEffect(new EntityLightningBolt(world, x, y, z, false));
            if (entity != null && entity instanceof EntityPlayer) {
                ((EntityPlayer) entity).addStat(RubycraftMod.ACHIEVEMENT_MAKE_LIGHTING);
            }
        }
    }
}
