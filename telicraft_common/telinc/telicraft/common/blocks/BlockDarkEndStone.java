package telinc.telicraft.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import telinc.telicraft.common.TelicraftMain;
import telinc.telicraft.common.core.TelicraftDamageSources;
import telinc.telicraft.common.reference.TextureReferences;

public class BlockDarkEndStone extends Block {
	public BlockDarkEndStone(int id, int texture, Material material) {
		super(id, texture, material);
		this.setCreativeTab(TelicraftMain.tabTelicraft);
		
		if(TelicraftMain.doEndstoneDamage){
			this.setTickRandomly(true);
		}
	}
	
	@Override
	public void randomDisplayTick(World par1World, int par2, int par3,
			int par4, Random par5Random) {
		super.randomDisplayTick(par1World, par2, par3, par4, par5Random);
		
		if (par5Random.nextInt(50) == 0) {
			par1World.spawnParticle("cloud", (double)((float)par2 + par5Random.nextFloat()), (double)((float)par3 + 1.1F), (double)((float)par4 + par5Random.nextFloat()), 0.0D, 0.0D, 0.0D);
		}
	}
	
	@Override
	public void onEntityWalking(World par1World, int par2, int par3, int par4, Entity par5Entity) {
		if(TelicraftMain.doEndstoneDamage){
			par5Entity.attackEntityFrom(TelicraftDamageSources.stone, 1);
		}
	}
	
    /**
     * Determines if this block is destroyed when a ender dragon tries to fly through it.
     * The block will be set to 0, nothing will drop.
     * 
     * @param world The current world
     * @param x X Position
     * @param y Y Position
     * @param z Z position
     * @return True to allow the ender dragon to destroy this block
     */
	@Override
    public boolean canDragonDestroy(World world, int x, int y, int z){
        return false;
    }
	
	@Override
	public String getTextureFile() {
		return TextureReferences.BLOCK_TEXTURE;
	}
}