package telinc.telicraft.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import telinc.telicraft.common.TelicraftMain;
import telinc.telicraft.common.reference.TextureReferences;

public class BlockAdamantBlk extends Block {
	public BlockAdamantBlk(int id, int texture, Material material) {
		super(id, texture, material);
		this.setCreativeTab(TelicraftMain.tabTelicraft);
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return TelicraftMain.adamant.shiftedIndex;
	}

	@Override
	public int quantityDropped(Random par1Random) {
		return 9;
	}

	/**
	 * Determines if this block can be used as the base of a beacon.
	 * 
	 * @param world
	 *            The current world
	 * @param x
	 *            X Position
	 * @param y
	 *            Y Position
	 * @param z
	 *            Z position
	 * @param beaconX
	 *            Beacons X Position
	 * @param beaconY
	 *            Beacons Y Position
	 * @param beaconZ
	 *            Beacons Z Position
	 * @return True, to support the beacon, and make it active with this block.
	 */
	@Override
	public boolean isBeaconBase(World worldObj, int x, int y, int z,
			int beaconX, int beaconY, int beaconZ) {
		return true;
	}

	@Override
	public String getTextureFile() {
		return TextureReferences.BLOCK_TEXTURE;
	}
}