package telinc.telicraft.common.blocks;

import net.minecraft.src.Block;
import net.minecraft.src.Material;
import net.minecraft.src.World;

import telinc.telicraft.common.TelicraftMain;
import telinc.telicraft.common.reference.TextureReferences;

public class BlockRedstone extends Block {
	public BlockRedstone(int par1, int par2){
		super(par1, par2, Material.iron);
		this.setCreativeTab(TelicraftMain.tabTelicraft);
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
	public String getTextureFile(){
		return TextureReferences.BLOCK_TEXTURE;
	}
}