package telinc.telicraft.item;

import net.minecraft.item.ItemSeeds;
import telinc.telicraft.TelicraftMain;
import telinc.telicraft.lib.TextureReferences;

public class ItemTomato extends ItemSeeds {
	public ItemTomato(int i, int j, int k) {
		super(i, j, k);
		maxStackSize = 16;
		this.setCreativeTab(TelicraftMain.tabTelicraft);
	}
	
	@Override
	public String getTextureFile() {
		return TextureReferences.ITEM_TEXTURE;
	}
}