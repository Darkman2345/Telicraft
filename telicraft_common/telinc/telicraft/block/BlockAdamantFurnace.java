package telinc.telicraft.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import telinc.telicraft.TelicraftMain;
import telinc.telicraft.lib.MainReferences;
import telinc.telicraft.lib.TextureReferences;
import telinc.telicraft.tileentity.TileAdamantFurnace;

public class BlockAdamantFurnace extends BlockContainer {

    /**
     * Is the random generator used by furnace to drop the inventory contents in
     * random directions.
     */
    private Random rand = new Random();

    /**
     * True if this is an active furnace, false if idle
     * */
    private final boolean isActive;

    /**
     * This flag is used to prevent the furnace inventory to be dropped upon
     * block removal, is used internally when the furnace block changes from
     * idle to active and vice-versa.
     */
    private static boolean keepFurnaceInventory = false;

    public BlockAdamantFurnace(int par1, boolean par2) {
        super(par1, 0, Material.iron);
        this.isActive = par2;
        this.setCreativeTab(TelicraftMain.tabTelicraft);
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    @Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4) {
        super.onBlockAdded(par1World, par2, par3, par4);

        if(par1World.isBlockIndirectlyGettingPowered(par2, par3, par4)){
            this.setHeat(par1World, par2, par3, par4, true);
        }else{
            this.setHeat(par1World, par2, par3, par4, false);
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which
     * neighbor changed (coordinates passed are their own) Args: x, y, z,
     * neighbor blockID
     */
    @Override
    public void onNeighborBlockChange(World par1World, int par2, int par3,
            int par4, int par5) {
        if(par5 > 0 && Block.blocksList[par5].canProvidePower()
                && par1World.isBlockIndirectlyGettingPowered(par2, par3, par4)){
            this.setHeat(par1World, par2, par3, par4, true);
        }else{
            this.setHeat(par1World, par2, par3, par4, false);
        }
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3) {
        return TelicraftMain.adamantFurnace.blockID;
    }

    /**
     * Update which block ID the furnace is using depending on whether or not it
     * is burning
     */
    public static void updateFurnaceBlockState(boolean par0, World par1World,
            int par2, int par3, int par4) {
        TileEntity var6 = par1World.getBlockTileEntity(par2, par3, par4);
        keepFurnaceInventory = true;

        if(par0){
            par1World.setBlockWithNotify(par2, par3, par4,
                    TelicraftMain.adamantFurnaceActive.blockID);
        }else{
            par1World.setBlockWithNotify(par2, par3, par4,
                    TelicraftMain.adamantFurnace.blockID);
        }

        keepFurnaceInventory = false;

        if(var6 != null){
            var6.validate();
            par1World.setBlockTileEntity(par2, par3, par4, var6);
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z,
            EntityPlayer player, int i, float a, float b, float c) {
        if(player.getCurrentEquippedItem() != null
                && player.getCurrentEquippedItem().itemID == Item.flintAndSteel.shiftedIndex){
            // Block right-clicked while holding Flint and Steel, ignore GUI and
            // explode.
            this.onBlockDestroyedByPlayer(world, x, y, z, 1);
            world.setBlockWithNotify(x, y, z, 0);

            world.createExplosion((Entity)null, x, y, z,
                    MainReferences.MACHINE_EXPLOSION_STRENGTH, true);
        }else{
            // Block right-clicked without holding Flint and Steel, open GUI.

            TileEntity tile_entity = world.getBlockTileEntity(x, y, z);

            if(tile_entity == null || player.isSneaking()){
                return false;
            }

            player.openGui(TelicraftMain.instance, 1, world, x, y, z);
        }

        return true;
    }

    @Override
    public int getBlockTextureFromSide(int i) {
        switch(i){
        case 0:
            return 24; // Bottom
        case 1:
            return 25; // Top
        default:
            if(this.isActive){
                return 31; // Sides, active
            }else{
                return 26; // Sides, inactive
            }
        }
    }

    @Override
    public void onBlockDestroyedByExplosion(World par1World, int par2,
            int par3, int par4) {
        par1World.createExplosion((Entity)null, par2, par3, par4,
                MainReferences.MACHINE_EXPLOSION_STRENGTH, true);
    }

    @Override
    public TileEntity createNewTileEntity(World par1World) {
        return new TileAdamantFurnace();
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int i, int j) {
        this.dropItems(world, x, y, z);
        super.breakBlock(world, x, y, z, i, j);
    }

    private void dropItems(World par1World, int par2, int par3, int par4) {
        if(!keepFurnaceInventory){
            TileAdamantFurnace var7 = (TileAdamantFurnace)par1World
                    .getBlockTileEntity(par2, par3, par4);

            if(var7 != null){
                for(int var8 = 0; var8 < var7.getSizeInventory(); ++var8){
                    ItemStack var9 = var7.getStackInSlot(var8);

                    if(var9 != null){
                        float var10 = this.rand.nextFloat() * 0.8F + 0.1F;
                        float var11 = this.rand.nextFloat() * 0.8F + 0.1F;
                        float var12 = this.rand.nextFloat() * 0.8F + 0.1F;

                        while(var9.stackSize > 0){
                            int var13 = this.rand.nextInt(21) + 10;

                            if(var13 > var9.stackSize){
                                var13 = var9.stackSize;
                            }

                            var9.stackSize -= var13;
                            EntityItem var14 = new EntityItem(par1World,
                                    (double)((float)par2 + var10),
                                    (double)((float)par3 + var11),
                                    (double)((float)par4 + var12),
                                    new ItemStack(var9.itemID, var13,
                                            var9.getItemDamage()));

                            if(var9.hasTagCompound()){
                            	var14.func_92014_d().setTagCompound((NBTTagCompound)var9
                                        .getTagCompound().copy());
                            }

                            float var15 = 0.05F;
                            var14.motionX = (double)((float)this.rand
                                    .nextGaussian() * var15);
                            var14.motionY = (double)((float)this.rand
                                    .nextGaussian() * var15 + 0.2F);
                            var14.motionZ = (double)((float)this.rand
                                    .nextGaussian() * var15);
                            par1World.spawnEntityInWorld(var14);
                        }
                    }
                }
            }
        }
    }

    private void setHeat(World world, int x, int y, int z, boolean value) {
        TileAdamantFurnace tile_entity = (TileAdamantFurnace)world
                .getBlockTileEntity(x, y, z);

        if(tile_entity != null){
            tile_entity.setHeatIncrease(value);
        }
    }

    @Override
    public String getTextureFile() {
        return TextureReferences.BLOCK_TEXTURE;
    }
}