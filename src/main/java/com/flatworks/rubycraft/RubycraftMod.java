package com.flatworks.rubycraft;

import com.flatworks.rubycraft.items.BlockRubyBlock;
import com.flatworks.rubycraft.items.BlockRubyOre;
import com.flatworks.rubycraft.items.ItemRuby;
import com.flatworks.rubycraft.items.ItemRubyWand;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

/**
 * @author sjx233
 */
@Mod(modid = RubycraftMod.MODID, name = RubycraftMod.NAME, version = RubycraftMod.VERSION)
public class RubycraftMod {
    public static final String MODID = "rubycraft";
    public static final String NAME = "Rubycraft";
    public static final String VERSION = "2.0.0";
    
    @Instance(MODID)
    public static RubycraftMod instance;
    public static Configuration modConfig;
    
    public static int spawnTrials = 4;
    public static int spawnCount = 8;
    public static int spawnMinY = 16;
    public static int spawnMaxY = 32;
    
    public static final ToolMaterial MATERIAL_RUBY =
            EnumHelper.addToolMaterial("RUBY", 0, 800, 0, 3, 18);
    public static final DamageSource DAMAGE_RUBY_MAGIC =
            new DamageSource("rubyMagic").setDamageBypassesArmor();
    
    public static final Item RUBY = new ItemRuby();
    public static final Item RUBY_WAND = new ItemRubyWand();
    public static final Block RUBY_ORE = new BlockRubyOre();
    public static final Block RUBY_BLOCK = new BlockRubyBlock();
    
    public static final Achievement ACHIEVEMENT_MAKE_LIGHTING =
            new Achievement("achievement.rubycraft.makeLighting", "rubycraft.makeLighting", 8, 1,
                    RUBY_WAND, AchievementList.BUILD_BETTER_PICKAXE).registerStat();
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        modConfig = new Configuration(event.getSuggestedConfigurationFile());
        modConfig.load();
        
        spawnTrials = modConfig.getInt("ruby spawn trials", Configuration.CATEGORY_GENERAL, 4, 1,
                16, "How many spawn trials per chunk");
        spawnCount = modConfig.getInt("ruby spawn count", Configuration.CATEGORY_GENERAL, 8, 1, 16,
                "How many ruby ores per trial");
        spawnMinY = modConfig.getInt("ruby spawn min height", Configuration.CATEGORY_GENERAL, 16, 0,
                255, "");
        spawnMaxY = modConfig.getInt("ruby spawn max height", Configuration.CATEGORY_GENERAL, 32, 0,
                255, "");
        
        modConfig.save();
        
        GameRegistry.register(RUBY.setRegistryName("ruby"));
        GameRegistry.register(RUBY_WAND.setRegistryName("ruby_wand"));
        GameRegistry.register(RUBY_ORE.setRegistryName("ruby_ore"));
        GameRegistry.register(new ItemBlock(RUBY_ORE).setRegistryName("ruby_ore"));
        GameRegistry.register(RUBY_BLOCK.setRegistryName("ruby_block"));
        GameRegistry.register(new ItemBlock(RUBY_BLOCK).setRegistryName("ruby_block"));
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
        GameRegistry.addShapedRecipe(new ItemStack(RUBY_BLOCK), "###", "###", "###", '#', RUBY);
        GameRegistry.addShapedRecipe(new ItemStack(RUBY, 9), "#", '#', RUBY_BLOCK);
        GameRegistry.addShapedRecipe(new ItemStack(RUBY_WAND), "  #", " | ", "|  ", '#', RUBY, '|',
                Items.STICK);
        
        GameRegistry.registerWorldGenerator(new WorldGenRubyOre(), 20);
        
        if (event.getSide().equals(Side.CLIENT)) {
            ItemModelMesher models = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
            models.register(RUBY, 0, invModel("ruby"));
            models.register(RUBY_WAND, 0, invModel("ruby_wand"));
            models.register(item(RUBY_ORE), 0, invModel("ruby_ore"));
            models.register(item(RUBY_BLOCK), 0, invModel("ruby_block"));
        }
    }
    
    private static ModelResourceLocation invModel(String itemId) {
        return new ModelResourceLocation(MODID + ":" + itemId, "inventory");
    }
    
    private static Item item(Block block) {
        return Item.getItemFromBlock(block);
    }
}
