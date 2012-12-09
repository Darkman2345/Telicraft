package telinc.telicraft.common.blocks;

import net.minecraft.src.Block;
import net.minecraft.src.Material;
import net.minecraft.src.World;

import telinc.telicraft.common.TelicraftMain;
import telinc.telicraft.common.reference.TextureReferences;

public class BlockMegaAdamant extends Block {
	public BlockMegaAdamant(int id, int texture, Material material) {
		super(id, texture, material);
		this.setCreativeTab(TelicraftMain.tabTelicraft);
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