package telinc.telicraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import telinc.telicraft.TelicraftMain;
import telinc.telicraft.lib.TextureReferences;

public class BlockACT extends Block {
    public BlockACT(int par1) {
        super(par1, 0, Material.wood);
        this.setCreativeTab(TelicraftMain.tabTelicraft);
    }

    @Override
    public int getBlockTextureFromSide(int par1) {
        switch(par1){
        case 0:
            return 10; // Bottom
        case 1:
            return 11; // Top
        default:
            return 12; // Sides
        }
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World par1World, int par2, int par3,
            int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
            float par8, float par9) {
        if(par1World.isRemote){
            return true;
        }else{
            if(par5EntityPlayer.isSneaking()){
                return false;
            }

            par5EntityPlayer.openGui(TelicraftMain.instance, 2, par1World,
                    par2, par3, par4);
            return true;
        }
    }

    @Override
    public String getTextureFile() {
        return TextureReferences.BLOCK_TEXTURE;
    }
}
