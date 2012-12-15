package telinc.telicraft.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import telinc.telicraft.common.TelicraftMain;
import telinc.telicraft.common.reference.TextureReferences;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public class BlockClearGlass extends Block {
	public BlockClearGlass(int id, int texture, Material material) {
		super(id, texture, material);
		this.setCreativeTab(TelicraftMain.tabTelicraft);
	}

	@Override
	public int quantityDropped(Random par1Random) {
		return 0;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderBlockPass() {
		return 0;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	protected boolean canSilkHarvest() {
		return true;
	}

	@Override
	public String getTextureFile() {
		return TextureReferences.BLOCK_TEXTURE;
	}
}