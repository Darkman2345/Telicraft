package telinc.telicraft.item;

import net.minecraft.item.Item;
import telinc.telicraft.TelicraftMain;
import telinc.telicraft.lib.TextureReferences;

public class ItemSharpTool extends Item {
	
	public ItemSharpTool(int id) {
		super(id);
		maxStackSize = 1;
		this.setCreativeTab(TelicraftMain.tabTelicraft);
		this.setMaxDamage(500); //250
	}
	
	@Override
	public boolean isFull3D(){
		return true;
	}
	
	@Override
	public String getTextureFile() {
		return TextureReferences.ITEM_TEXTURE;
	}
}