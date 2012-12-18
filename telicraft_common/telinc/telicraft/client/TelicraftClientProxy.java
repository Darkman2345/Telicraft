package telinc.telicraft.client;

import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import telinc.telicraft.TelicraftMain;
import telinc.telicraft.client.entities.RenderMeteorBomb;
import telinc.telicraft.client.entities.RenderPetrifyClassic;
import telinc.telicraft.client.entities.RenderPetrifyNew;
import telinc.telicraft.entity.EntityMeteorBombPrimed;
import telinc.telicraft.entity.EntityPetrify;
import telinc.telicraft.handler.TelicraftSoundHandler;
import telinc.telicraft.lib.TextureReferences;
import telinc.telicraft.proxy.TelicraftCommonProxy;
import cpw.mods.fml.client.registry.RenderingRegistry;

/**
 * The client side proxy.
 * @author Telinc1
 * */
public class TelicraftClientProxy extends TelicraftCommonProxy {
	
	@Override
	public void registerRenderThings() {
		MinecraftForgeClient.preloadTexture(TextureReferences.BLOCK_TEXTURE);
		MinecraftForgeClient.preloadTexture(TextureReferences.ITEM_TEXTURE);
		
		if(TelicraftMain.classicPetrifyModel){
			RenderingRegistry.registerEntityRenderingHandler(EntityPetrify.class, new RenderPetrifyClassic(TelicraftMain.potionCustom.getIconFromDamage(2)));
		}else{
			RenderingRegistry.registerEntityRenderingHandler(EntityPetrify.class, new RenderPetrifyNew());
		}
		
		RenderingRegistry.registerEntityRenderingHandler(EntityMeteorBombPrimed.class, new RenderMeteorBomb());
	}
	
	@Override
	public void registerSoundHandler(){
		MinecraftForge.EVENT_BUS.register(new TelicraftSoundHandler());
	}
}