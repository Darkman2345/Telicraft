package telinc.telicraft.client;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import telinc.telicraft.proxy.TelicraftCommonEngine;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class TelicraftClientEngine extends TelicraftCommonEngine {
	
	@Override
	public int addArmor(String name) {
		return RenderingRegistry.addNewArmourRendererPrefix(name);
	}
	
	@Override
	public EntityPlayer getPlayerInstance(){
		return Minecraft.getMinecraft().thePlayer;
	}
	
}