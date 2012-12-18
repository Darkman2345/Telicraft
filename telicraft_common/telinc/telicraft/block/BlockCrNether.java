package telinc.telicraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import telinc.telicraft.TelicraftMain;
import telinc.telicraft.lib.TextureReferences;

public class BlockCrNether extends Block {
	public BlockCrNether(int id, int texture, Material material) {
		super(id, texture, material);
		this.setCreativeTab(TelicraftMain.tabTelicraft);
	}

	@Override
	public String getTextureFile() {
		return TextureReferences.BLOCK_TEXTURE;
	}
}