package cf.adriantodt.mods.DragonScales.client;

import cf.adriantodt.mods.DragonScales.client.models.ModelDragonChestplate;
import cf.adriantodt.mods.DragonScales.client.models.ModelModDragon;
import cf.adriantodt.mods.DragonScales.client.models.RenderModDragon;
import cf.adriantodt.mods.DragonScales.client.renderers.BlockModCauldronRenderer;
import cf.adriantodt.mods.DragonScales.client.renderers.TileCauldronConstructRenderer;
import cf.adriantodt.mods.DragonScales.client.renderers.TileCombinerRenderer;
import cf.adriantodt.mods.DragonScales.client.renderers.TileCrystalRenderer;
import cf.adriantodt.mods.DragonScales.common.CommonProxy;
import cf.adriantodt.mods.DragonScales.common.DragonScalesHandler;
import cf.adriantodt.mods.DragonScales.common.blocks.tile.TileCauldronConstruct;
import cf.adriantodt.mods.DragonScales.common.blocks.tile.TileCombiner;
import cf.adriantodt.mods.DragonScales.common.blocks.tile.TileCrystal;
import cf.adriantodt.mods.DragonScales.common.events.KeyBindings;
import cf.adriantodt.mods.DragonScales.common.events.EventHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.item.ItemStack;

public class ClientProxy extends CommonProxy {
	private static final ModelBiped dragonChestplate = new ModelDragonChestplate(1.0f);
	private static final ModelBiped dragonLeggings = new ModelBiped(0.45f);
	private static final ModelBiped dragonBoots = new ModelBiped(0.9f);
	
	private static int cauldronRenderType = -1;
	
	public void preInit(){
		super.preInit();
		
		//Tweak to Remove the Cauldron from NEI
		if (Loader.isModLoaded("NotEnoughItems"));
		codechicken.nei.api.API.hideItem(new ItemStack(DragonScalesHandler.modCauldron));
	}
	
	public void registerRenderThings(){
		//Register TileEntity Renderers
		ClientRegistry.bindTileEntitySpecialRenderer(TileCrystal.class, new TileCrystalRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileCombiner.class, new TileCombinerRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileCauldronConstruct.class, new TileCauldronConstructRenderer());
		
		
		//Register Cauldron Renderer
		cauldronRenderType = RenderingRegistry.getNextAvailableRenderId();
		ISimpleBlockRenderingHandler cauldronRenderer = new BlockModCauldronRenderer();
		RenderingRegistry.registerBlockHandler(cauldronRenderType, cauldronRenderer);
		
		//Register Dragon Renderer
		//RenderingRegistry.registerEntityRenderingHandler(EntityModDragon.class, new RenderModDragon(new ModelModDragon(), 0.5F));
	}
	
	@Override
	public ModelBiped getArmorModel(int id){
		switch (id) {
		case 0:
			return dragonBoots;
		case 1:
			return dragonChestplate;
		case 2:
			return dragonLeggings;
		case 3:
			return dragonBoots;
		}
		return dragonLeggings;
	}
	
	@Override
	public int getRenderType(String name) {
		if (name.equals("modCauldron"))
			return cauldronRenderType;
		
		return 0;
	}
}
