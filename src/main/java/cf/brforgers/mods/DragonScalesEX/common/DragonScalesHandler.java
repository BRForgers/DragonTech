package cf.brforgers.mods.DragonScalesEX.common;

import cf.brforgers.api.DragonScalesEX.DragonScalesAPI;
import cf.brforgers.api.DragonScalesEX.DragonScalesAPI.CauldronRecipe;
import cf.brforgers.core.lib.FastFactory;
import cf.brforgers.core.lib.ItemHelper;
import cf.brforgers.mods.DragonScalesEX.DragonScalesEX;
import cf.brforgers.mods.DragonScalesEX.Lib;
import cf.brforgers.mods.DragonScalesEX.common.blocks.BlockCauldronConstruct;
import cf.brforgers.mods.DragonScalesEX.common.blocks.BlockCombiner;
import cf.brforgers.mods.DragonScalesEX.common.blocks.BlockDragonCrystal;
import cf.brforgers.mods.DragonScalesEX.common.blocks.BlockModCauldron;
import cf.brforgers.mods.DragonScalesEX.common.blocks.tile.TileCauldronConstruct;
import cf.brforgers.mods.DragonScalesEX.common.blocks.tile.TileCombiner;
import cf.brforgers.mods.DragonScalesEX.common.blocks.tile.TileCrystal;
import cf.brforgers.mods.DragonScalesEX.common.blocks.world.*;
import cf.brforgers.mods.DragonScalesEX.common.items.*;
import cf.brforgers.mods.DragonScalesEX.common.utils.GridSystem;
import cf.brforgers.mods.DragonScalesEX.common.utils.RegisterHelper;
import cf.brforgers.mods.DragonScalesEX.common.world.DraconyVirus;
import cf.brforgers.mods.DragonScalesEX.common.world.EnumVirusState;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Dragon Scales Main Handler
 */
public class DragonScalesHandler {
	public static final ToolMaterial DRAGONALLOY_TOOL_MATERIAL =
			EnumHelper.addToolMaterial("DRAGONSCALE", 10, 2000, 50.0F, 16.0F, 35);
	public static final ArmorMaterial DRAGONSCALES_ARMOR_MATERIAL =
			EnumHelper.addArmorMaterial("DRAGONSCALE", 50, new int[] { 5, 16, 12, 6 }, 35);
	public static GridSystem<EnumVirusState>
	public static FastFactory factory;
	public static RegisterHelper register;
	// All Items
	public static Item dragonScale, dragonEssenceShard, dragonMetal, dragonEssenceBottle, infusedStick,
		dragonSword, dragonMultiTool, dragonScepter;
	
	public static ItemArmor scalesHelm, scalesChestplate, scalesLeggings, scalesBoots;
	
	// All Blocks
	public static Block modCauldron, cauldronConstruct, essenceCombiner, dragonBricks, dragonChest, dragonScaleBlock,
		dragonCrystal, dragonGrass, dragonDirt, dragonStone, draconyLeaves, draconyLog, draconySapling, draconyPlanks,
		dragonEssenceBlock;

	public static void registerHelpers()
	{
		factory = FastFactory.newFactory(DragonScalesEX.tabDragonScales, Lib.TEXTURE_PATH, Material.ROCK);
		register = RegisterHelper.fromMod(Lib.MODID);
	}
	
	private static void registerMaterialHandling() {
		DRAGONALLOY_TOOL_MATERIAL.setRepairItem(ItemHelper.toStack(dragonMetal));
		DRAGONSCALES_ARMOR_MATERIAL.customCraftingMaterial = dragonScale;
	}

	public static void registerBlocks() {
		dragonBricks = factory.newBlock("dragonBricks").setHardness(2.0F).setResistance(10.0F);//setStepSound(dragonBricks.soundTypePiston);

		GameRegistry.registerBlock(dragonBricks, "dragonBricks");
		
		dragonScaleBlock = factory.newBlock("dragonScaleBlock").setHardness(0.8F).setStepSound(dragonScaleBlock.soundTypeCloth);
		GameRegistry.registerBlock(dragonScaleBlock, "dragonScaleBlock");
		
		//dragonEssenceOre = ModBlock.process(new BlockDragonEssenceOre().setHardness(3.0F).setResistance(5.0F).setStepSound(dragonBricks.soundTypePiston), "dragonEssenceOre");
		//GameRegistry.registerBlock(dragonEssenceOre, "dragonEssenceOre");
		
		modCauldron = new BlockModCauldron();
		GameRegistry.registerBlock(modCauldron, "modCauldron");
		
		cauldronConstruct = new BlockCauldronConstruct();
		GameRegistry.registerBlock(cauldronConstruct, "cauldronConstruct");
		GameRegistry.registerTileEntity(TileCauldronConstruct.class, "Tile"+Lib.MODID+"ModelCauldronConstruct");
		
		essenceCombiner = new BlockCombiner();
		GameRegistry.registerBlock(essenceCombiner, "essenceCombiner");
		GameRegistry.registerTileEntity(TileCombiner.class, "Tile"+Lib.MODID+"Combiner");
		
		//dragonChest = ModBlock.process(new BlockDragonChest(), "dragonChest");
		//GameRegistry.registerBlock(dragonChest, ItemBlock.class, "dragonChest");
		//GameRegistry.registerTileEntity(TileEntityDragonChest.class, "dragonchestTileEntity");
		
		dragonCrystal = factory.processBlock(new BlockDragonCrystal(), "dragonCrystal");
		GameRegistry.registerBlock(dragonCrystal, "dragonCrystal");
		GameRegistry.registerTileEntity(TileCrystal.class, "Tile"+Lib.MODID+"DragonCrystal");
		
		dragonStone = factory.processBlock(new BlockVirusBase(Material.rock), "dragonStone");
		GameRegistry.registerBlock(dragonStone, "dragonStone");
		
		dragonDirt = factory.processBlock(new BlockVirusBase(Material.ground).setHardness(0.6F).setHardness(0.5F).setStepSound(modCauldron.soundTypeGravel), "dragonDirt");
		GameRegistry.registerBlock(dragonDirt, "dragonDirt");
		
		dragonGrass = factory.processBlock(new DragonGrass(), "dragonGrass");
		GameRegistry.registerBlock(dragonGrass, "dragonGrass");
		
		draconyLeaves = factory.processBlock(new DraconyLeaves(), "draconyLeaves");
		GameRegistry.registerBlock(draconyLeaves, "draconyLeaves");
		
		draconyLog = factory.processBlock(new DraconyLog(), "draconyLog");
		GameRegistry.registerBlock(draconyLog, "draconyLog");
		
		draconySapling = factory.processBlock(new DraconySapling(), "draconySapling");
		GameRegistry.registerBlock(draconySapling, "draconySapling");

		draconyPlanks = factory.processBlock(new BlockVirusBase(Material.wood), "draconyPlanks");
		draconyPlanks.setHardness(2.0F).setResistance(5.0F).setStepSound(draconyPlanks.soundTypeWood);
		GameRegistry.registerBlock(draconyPlanks, "draconyPlanks");
		
		dragonEssenceBlock = factory.processBlock(new BlockVirusBase(Material.iron), "dragonEssenceBlock");
		dragonEssenceBlock.setHardness(5.0F).setResistance(10.0F).setStepSound(dragonEssenceBlock.soundTypeMetal);
		GameRegistry.registerBlock(dragonEssenceBlock, "dragonEssenceBlock");
	}

	public static void registerItems()
	{
		dragonScale = factory.processItem(new ItemDragonScale(new ItemStack(Items.leather)), "dragonScale");
		GameRegistry.registerItem(dragonScale, "dragonScale");
		dragonEssenceShard = factory.processItem(new ItemDragonScale(null),"dragonEssenceShard");
		GameRegistry.registerItem(dragonEssenceShard, "dragonEssenceShard");
		dragonMetal = factory.processItem(new ItemDragonScale(new ItemStack(Items.iron_ingot)),"dragonMetal");
		GameRegistry.registerItem(dragonMetal, "dragonMetal");
		dragonEssenceBottle = factory.processItem(new ItemEssenceBottle(new ItemStack(Items.glass_bottle)),"dragonEssenceBottle");
		GameRegistry.registerItem(dragonEssenceBottle, "dragonEssenceBottle");
		infusedStick = factory.newItem("infusedStick");
		GameRegistry.registerItem(infusedStick, "infusedStick");
		
		dragonSword = factory.processItem(new ItemDragonSword(DRAGONALLOY_TOOL_MATERIAL), "dragonSword");
		GameRegistry.registerItem(dragonSword, "dragonSword");
		dragonMultiTool = factory.processItem(new ItemDragonMulti(DRAGONALLOY_TOOL_MATERIAL), "dragonMultiTool");
		GameRegistry.registerItem(dragonMultiTool, "dragonMultiTool");

		DragonScalesAPI.setCustomSpeed(Blocks.obsidian, 10f);
		
		dragonScepter = factory.processItem(new ItemDragonScepter(DRAGONALLOY_TOOL_MATERIAL), "dragonScepter");
		GameRegistry.registerItem(dragonScepter, "dragonScepter");
		
		scalesHelm = factory.processItem(new ItemDragonArmor(DRAGONSCALES_ARMOR_MATERIAL, 0, "scalesHelm"), "scalesHelm");
		GameRegistry.registerItem(scalesHelm, "scalesHelm");
		scalesChestplate = factory.processItem(new ItemDragonArmor(DRAGONSCALES_ARMOR_MATERIAL, 1, "scalesChestplate"), "scalesChestplate");
		GameRegistry.registerItem(scalesChestplate, "scalesChestplate");
		scalesLeggings = factory.processItem(new ItemDragonArmor(DRAGONSCALES_ARMOR_MATERIAL, 2, "scalesLeggings"), "scalesLeggings");
		GameRegistry.registerItem(scalesLeggings, "scalesLeggings");
		scalesBoots = factory.processItem(new ItemDragonArmor(DRAGONSCALES_ARMOR_MATERIAL, 3, "scalesBoots"), "scalesBoots");
		GameRegistry.registerItem(scalesBoots, "scalesBoots");
	}
	
	private static void registerEntities()
	{
		//int entityID = EntityRegistry.findGlobalUniqueEntityId();
		
		//EntityRegistry.registerGlobalEntityID(EntityModDragon.class, "ModDragon", entityID);
		//EntityRegistry.registerModEntity(EntityModDragon.class, "ModDragon", entityID, Lib.MODID, 1, 1, 1);
		//EntityRegistry.addSpawn(EntityModDragon.class, 2, 1, 2, EnumCreatureType.creature, BiomeGenBase.extremeHills);
		//EntityList.entityEggs.put(Integer.valueOf(entityID), new EntityList.EntityEggInfo(entityID, 0x000000, 0x000000));
	}

	private static ItemStack toWildcardStack(Block block) {
		return new ItemStack(block, 1, OreDictionary.WILDCARD_VALUE);
	}

	private static ItemStack toWildcardStack(Item item) {
		return new ItemStack(item, 1, OreDictionary.WILDCARD_VALUE);
	}

	public static void registerOredict() {
		OreDictionary.registerOre("stone", toWildcardStack(dragonStone));
		OreDictionary.registerOre("cobblestone", toWildcardStack(dragonStone));
		OreDictionary.registerOre("dirt", toWildcardStack(dragonDirt));
		OreDictionary.registerOre("grass", toWildcardStack(dragonGrass));
		OreDictionary.registerOre("stickWood",toWildcardStack(infusedStick));
		OreDictionary.registerOre("plankWood",toWildcardStack(draconyPlanks));
		OreDictionary.registerOre("logWood", toWildcardStack(draconyLog));
		OreDictionary.registerOre("treeSapling",toWildcardStack(draconySapling));
		OreDictionary.registerOre("treeLeaves", toWildcardStack(draconyLeaves));
		OreDictionary.registerOre("leather", toWildcardStack(dragonScale));

	}
	
	public static void registerRecipes()
	{
		DragonScalesAPI.cauldronRecipes.add(new CauldronRecipe(new ItemStack(Items.leather), 3, new ItemStack(dragonScale)).registerDefaultDispenserBehaviour());
		DragonScalesAPI.cauldronRecipes.add(new CauldronRecipe(new ItemStack(Items.gold_ingot), 3, new ItemStack(dragonMetal)).registerDefaultDispenserBehaviour());
		DragonScalesAPI.cauldronRecipes.add(new CauldronRecipe(new ItemStack(Items.emerald), 3, new ItemStack(dragonCrystal)).registerDefaultDispenserBehaviour());
		DragonScalesAPI.cauldronRecipes.add(new CauldronRecipe(new ItemStack(Items.glass_bottle), 1, new ItemStack(dragonEssenceBottle)).registerDefaultDispenserBehaviour());
		DragonScalesAPI.cauldronRecipes.add(new CauldronRecipe(new ItemStack(Items.stick), 0, new ItemStack(infusedStick)).registerDefaultDispenserBehaviour());
		DragonScalesAPI.cauldronRecipes.add(
			new CauldronRecipe(new ItemStack(Blocks.brick_block), 1, new ItemStack(dragonBricks)) {
				public ItemStack getOutput(ItemStack input, int essentiaLevel, World world, int x, int y, int z, EntityPlayer player) {
					ItemStack output = super.getOutput(input, essentiaLevel, world, z, z, z, player);
					output.stackSize = input.stackSize;
					return output;
				}
				
				public int getEssentiaCost(ItemStack input, int essentiaLevel, World world, int x, int y, int z, EntityPlayer player)
				{
					return MathHelper.clamp_int((int)((float)(input.stackSize / 64) * 3)+1, 1, 3);
				}
				
				public int getItemCost(ItemStack input, int essentiaLevel, World world, int x, int y, int z, EntityPlayer player)
				{
					return input.stackSize;
				}
			}.registerDefaultDispenserBehaviour()
		);
		DragonScalesAPI.cauldronRecipes.add(
				new CauldronRecipe(new ItemStack(Blocks.soul_sand), 1, new ItemStack(Blocks.end_stone)) {
					public ItemStack getOutput(ItemStack input, int essentiaLevel, World world, int x, int y, int z, EntityPlayer player) {
						ItemStack output = super.getOutput(input, essentiaLevel, world, z, z, z, player);
						output.stackSize = MathHelper.clamp_int(input.stackSize,0,16);
						return output;
					}
					
					public int getEssentiaCost(ItemStack input, int essentiaLevel, World world, int x, int y, int z, EntityPlayer player)
					{
						return MathHelper.clamp_int((int)((float)(input.stackSize / 16) * 3)+1, 1, 3);
					}
					
					public int getItemCost(ItemStack input, int essentiaLevel, World world, int x, int y, int z, EntityPlayer player)
					{
						return MathHelper.clamp_int(input.stackSize,0,16);
					}
				}.registerDefaultDispenserBehaviour()
		);

		DragonScalesAPI.cauldronRecipes.add(
				new CauldronRecipe(new ItemStack(dragonEssenceBlock), 3, new ItemStack(dragonGrass)) {
					public ItemStack getOutput(ItemStack input, int essentiaLevel, World world, int x, int y, int z, EntityPlayer player) {
						DraconyVirus.InfectBiomeAsync(world, x, y-1, z, (7 + world.rand.nextInt(10)));
						return null;
					}
				}.registerDefaultDispenserBehaviour()
		);
		
		GameRegistry.addShapelessRecipe(new ItemStack(dragonEssenceBottle), new ItemStack(Items.potionitem,1,0), new ItemStack(dragonEssenceShard));
		
		GameRegistry.addShapedRecipe(
				new ItemStack(dragonScaleBlock,1),
				"DDD","DDD","DDD",
				'D', dragonScale
		);
		
		GameRegistry.addShapelessRecipe(new ItemStack(dragonScale,9), dragonScaleBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(dragonEssenceShard,9), dragonEssenceBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(draconyPlanks, 6),  draconyLog);
		GameRegistry.addShapedRecipe(new ItemStack(infusedStick, 4),
				"P","P",
				'P', draconyPlanks
		); 
		
		GameRegistry.addShapedRecipe(
				new ItemStack(dragonMultiTool,1),
				"MMM","MSM","DSD",
				'M', dragonMetal,
				'S', infusedStick,
				'D', dragonScale
		);
		
		GameRegistry.addShapedRecipe(
				new ItemStack(dragonSword,1), 
				" M "," MD","DS ",
				'M', dragonMetal,
				'S', infusedStick,
				'D', dragonScale
		);
		
		GameRegistry.addShapedRecipe(
				new ItemStack(scalesHelm,1), 
				"DDD","DED","   ",
				'D', dragonScale,
				'E', dragonEssenceBottle.setContainerItem(Items.glass_bottle)
		);
		
		GameRegistry.addShapedRecipe(
				new ItemStack(scalesChestplate,1), 
				"DED","DDD","DDD",
				'D', dragonScale,
				'E', dragonEssenceBottle.setContainerItem(Items.glass_bottle)
		);
		
		GameRegistry.addShapedRecipe(
				new ItemStack(scalesLeggings,1), 
				"DDD","DED","D D",
				'D', dragonScale,
				'E', dragonEssenceBottle.setContainerItem(Items.glass_bottle)
		);
		
		GameRegistry.addShapedRecipe(
				new ItemStack(scalesBoots,1), 
				"E E","D D","D D",
				'D', dragonScale,
				'E', dragonEssenceBottle.setContainerItem(Items.glass_bottle)
		);
		
		GameRegistry.addShapedRecipe(
				new ItemStack(dragonScepter,1), 
				"MMD"," SD","DSE",
				'D', dragonScale,
				'M', dragonMetal,
				'S', infusedStick,
				'E', dragonEssenceBottle.setContainerItem(Items.glass_bottle)
		);
	}
}