package telinc.telicraft.common.blocks;

import net.minecraft.src.BlockOre;

import telinc.telicraft.common.TelicraftMain;
import telinc.telicraft.common.reference.TextureReferences;

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