package telinc.telicraft.block;

import net.minecraft.block.BlockOre;
import telinc.telicraft.TelicraftMain;
import telinc.telicraft.lib.TextureReferences;

public class BlockAdamantOre extends BlockOre {
	public BlockAdamantOre(int id, int texture) {
		super(id, texture);
		this.setCreativeTab(TelicraftMain.tabTelicraft);
	}
	
	@Override
	public String getTextureFile() {
		return TextureReferences.BLOCK_TEXTURE;
	}
}