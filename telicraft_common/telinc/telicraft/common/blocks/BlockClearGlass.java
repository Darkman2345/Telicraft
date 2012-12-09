package telinc.telicraft.common.blocks;

import java.util.Random;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import net.minecraft.src.Block;
import net.minecraft.src.Material;

import telinc.telicraft.common.TelicraftMain;
import telinc.telicraft.common.reference.TextureReferences;

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