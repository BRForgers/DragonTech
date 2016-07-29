package cf.brforgers.mods.DragonTech.client;

import cf.brforgers.core.lib.client.Armor3DRenderer;
import cf.brforgers.mods.DragonTech.client.models.ModelDragonChestplate;
import cf.brforgers.mods.DragonTech.client.renderers.ItemTileEntityRenderer;
import cf.brforgers.mods.DragonTech.client.renderers.TileCauldronConstructRenderer;
import cf.brforgers.mods.DragonTech.client.renderers.TileCrystalRenderer;
import cf.brforgers.mods.DragonTech.common.CommonProxy;
import cf.brforgers.mods.DragonTech.common.DSEXManager;
import cf.brforgers.mods.DragonTech.common.general.blocks.tile.TileCauldronConstruct;
import cf.brforgers.mods.DragonTech.common.general.blocks.tile.TileCrystal;
import net.minecraft.client.model.ModelBiped;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	private static final ModelBiped dragonChestplate = new ModelDragonChestplate(1.0f);
	private static final ModelBiped dragonLeggings = new ModelBiped(0.45f);
	private static final ModelBiped dragonBoots = new ModelBiped(0.9f);
	
	private static int cauldronRenderType = -1;
	
	public void preInit(){
		super.preInit();
	}
	
	public void registerRenderThings(){
		//Register TileEntity Renderers
		ClientRegistry.bindTileEntitySpecialRenderer(TileCrystal.class, new TileCrystalRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileCauldronConstruct.class, new TileCauldronConstructRenderer());
		
		
		//Tweak to Render at Inventory
        ItemTileEntityRenderer.newItemTileRenderer(DSEXManager.dragonCrystal);
        ItemTileEntityRenderer.newItemTileRenderer(DSEXManager.cauldronConstruct);

        //Register Cauldron Renderer
		cauldronRenderType = RenderingRegistry.getNextAvailableRenderId();
		ISimpleBlockRenderingHandler cauldronRenderer = new BlockModCauldronRenderer();
		RenderingRegistry.registerBlockHandler(cauldronRenderType, cauldronRenderer);
		
		//Register the Armor
        Armor3DRenderer.register(DSEXManager.scalesBoots);
        Armor3DRenderer.register(DSEXManager.scalesChestplate);
        Armor3DRenderer.register(DSEXManager.scalesHelm);
        Armor3DRenderer.register(DSEXManager.scalesLeggings);

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
}