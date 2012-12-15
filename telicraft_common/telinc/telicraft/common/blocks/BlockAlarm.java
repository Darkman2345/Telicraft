package telinc.telicraft.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import telinc.telicraft.common.TelicraftMain;
import telinc.telicraft.common.reference.TextureReferences;
import telinc.telicraft.common.tileEntities.TileEntityAlarm;

public class BlockAlarm extends BlockContainer {
	
	public BlockAlarm(int par1) {
		super(par1, 0, Material.circuits);
		this.setCreativeTab(TelicraftMain.tabTelicraft);
    }
    
	/**
	 * Called whenever the block is added into the world. Args: world, x, y, z
	 */
	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4) {
		super.onBlockAdded(par1World, par2, par3, par4);

		if (par1World.isBlockIndirectlyGettingPowered(par2, par3, par4)) {
			this.setActive(par1World, par2, par3, par4, true);
		}else{
			this.setActive(par1World, par2, par3, par4, false);
		}
	}
	
	@Override
	public int getBlockTextureFromSide(int par1){
		switch(par1){
		case 0:
			return 27;	//Bottom
		case 1:
			return 28;	//Top
		default:
			return 29;	//Side
		}
	}
	
	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
	 * their own) Args: x, y, z, neighbor blockID
	 */
	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3,
			int par4, int par5) {
		if (par5 > 0 && Block.blocksList[par5].canProvidePower()
				&& par1World.isBlockIndirectlyGettingPowered(par2, par3, par4)) {
			this.setActive(par1World, par2, par3, par4, true);
		}else{
			this.setActive(par1World, par2, par3, par4, false);
		}
	}
	
	private void setActive(World world, int x, int y, int z, boolean value){
		TileEntityAlarm tile_entity = (TileEntityAlarm) world.getBlockTileEntity(x, y, z);
		
		if(tile_entity != null){
			tile_entity.active = value;
		}
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityAlarm();
	}
	
	@Override
	public String getTextureFile() {
		return TextureReferences.BLOCK_TEXTURE;
	}
}