package telinc.telicraft.common.items;

import net.minecraft.item.ItemSeeds;
import telinc.telicraft.common.TelicraftMain;
import telinc.telicraft.common.reference.TextureReferences;

public class ItemPepper extends ItemSeeds {
	public ItemPepper(int i, int j, int k) {
		super(i, j, k);
		maxStackSize = 16;
		this.setCreativeTab(TelicraftMain.tabTelicraft);
	}
	
	@Override
	public String getTextureFile() {
		return TextureReferences.ITEM_TEXTURE;
	}
}