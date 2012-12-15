package telinc.telicraft.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import telinc.telicraft.common.TelicraftMain;
import telinc.telicraft.common.reference.TextureReferences;

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