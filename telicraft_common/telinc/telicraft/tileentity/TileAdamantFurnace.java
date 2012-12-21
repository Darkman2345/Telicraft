package telinc.telicraft.tileentity;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.ISidedInventory;
import telinc.telicraft.block.BlockAdamantFurnace;
import telinc.telicraft.lib.MainReferences;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileAdamantFurnace extends TileEntity implements IInventory,
        ISidedInventory {

    /**
     * The speed of the furnace.
     */
    private static int furnaceSpeed = 30;

    /**
     * The ItemStacks that hold the items currently being used in the furnace
     */
    private ItemStack[] furnaceItemStacks = new ItemStack[4];

    private Random furnaceRand = new Random();

    /** The number of ticks that the furnace will keep burning */
    public int furnaceBurnTime = 0;

    /**
     * The number of ticks that a fresh copy of the currently-burning item would
     * keep the furnace burning for
     */
    public int currentItemBurnTime = 0;

    /** The number of ticks that the current item has been cooking for */
    public int furnaceCookTime = 0;

    /**
     * Determines if the heat value should increase.
     */
    private static boolean heatIncrease = false;

    /**
     * If true, the furnace will start increasing the heat next time
     * furnaceCookTime is 0.
     */
    public static boolean shouldStartIncreasing = false;

    /**
     * The current heat.
     */
    public int heat = 0;

    /**
     * Returns the number of slots in the inventory.
     */
    @Override
    public int getSizeInventory() {
        return this.furnaceItemStacks.length;
    }

    /**
     * Returns the stack in slot i
     */
    @Override
    public ItemStack getStackInSlot(int par1) {
        return this.furnaceItemStacks[par1];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number
     * (second arg) of items and returns them in a new stack.
     */
    @Override
    public ItemStack decrStackSize(int par1, int par2) {
        if(this.furnaceItemStacks[par1] != null){
            ItemStack var3;

            if(this.furnaceItemStacks[par1].stackSize <= par2){
                var3 = this.furnaceItemStacks[par1];
                this.furnaceItemStacks[par1] = null;
                return var3;
            }else{
                var3 = this.furnaceItemStacks[par1].splitStack(par2);

                if(this.furnaceItemStacks[par1].stackSize == 0){
                    this.furnaceItemStacks[par1] = null;
                }

                return var3;
            }
        }else{
            return null;
        }
    }

    /**
     * When some containers are closed they call this on each slot, then drop
     * whatever it returns as an EntityItem - like when you close a workbench
     * GUI.
     */
    @Override
    public ItemStack getStackInSlotOnClosing(int par1) {
        if(this.furnaceItemStacks[par1] != null){
            ItemStack var2 = this.furnaceItemStacks[par1];
            this.furnaceItemStacks[par1] = null;
            return var2;
        }else{
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be
     * crafting or armor sections).
     */
    @Override
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
        this.furnaceItemStacks[par1] = par2ItemStack;

        if(par2ItemStack != null
                && par2ItemStack.stackSize > this.getInventoryStackLimit()){
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    /**
     * Returns the name of the inventory.
     */
    @Override
    public String getInvName() {
        return "container.furnace.adamant";
    }

    /**
     * Reads a tile entity from NBT.
     */
    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList var2 = par1NBTTagCompound.getTagList("Items");
        this.furnaceItemStacks = new ItemStack[this.getSizeInventory()];

        for(int var3 = 0; var3 < var2.tagCount(); ++var3){
            NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
            byte var5 = var4.getByte("Slot");

            if(var5 >= 0 && var5 < this.furnaceItemStacks.length){
                this.furnaceItemStacks[var5] = ItemStack
                        .loadItemStackFromNBT(var4);
            }
        }

        this.heat = par1NBTTagCompound.getShort("Heat");
        this.furnaceBurnTime = par1NBTTagCompound.getShort("BurnTime");
        this.furnaceCookTime = par1NBTTagCompound.getShort("CookTime");
        this.heatIncrease = par1NBTTagCompound.getBoolean("HeatIncrease");
        this.currentItemBurnTime = getItemBurnTime(this.furnaceItemStacks[1]);
        this.shouldStartIncreasing = par1NBTTagCompound
                .getBoolean("ShouldIncrease");
    }

    /**
     * Writes a tile entity to NBT.
     */
    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setShort("Heat", (short)this.heat);
        par1NBTTagCompound.setBoolean("HeatIncrease", this.heatIncrease);
        par1NBTTagCompound.setShort("BurnTime", (short)this.furnaceBurnTime);
        par1NBTTagCompound.setShort("CookTime", (short)this.furnaceCookTime);
        par1NBTTagCompound.setBoolean("ShouldIncrease",
                this.shouldStartIncreasing);

        NBTTagList var2 = new NBTTagList();

        for(int var3 = 0; var3 < this.furnaceItemStacks.length; ++var3){
            if(this.furnaceItemStacks[var3] != null){
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte)var3);
                this.furnaceItemStacks[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }
        }

        par1NBTTagCompound.setTag("Items", var2);
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be
     * 64, possibly will be extended. *Isn't this more of a set than a get?*
     */
    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @SideOnly(Side.CLIENT)
    /**
     * Returns an integer between 0 and the passed value representing how close the current item is to being completely
     * cooked
     */
    public int getCookProgressScaled(int par1) {
        return this.furnaceCookTime * par1 / furnaceSpeed;
    }

    @SideOnly(Side.CLIENT)
    /**
     * Returns an integer between 0 and the passed value representing how much burn time is left on the current fuel
     * item, where 0 means that the item is exhausted and the passed value means that the item is fresh
     */
    public int getBurnTimeRemainingScaled(int par1) {
        if(this.currentItemBurnTime == 0){
            this.currentItemBurnTime = 200;
        }

        return this.furnaceBurnTime * par1 / this.currentItemBurnTime;
    }

    /**
     * Returns true if the furnace is currently burning
     */
    public boolean isBurning() {
        return this.furnaceBurnTime > 0;
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses,
     * e.g. the mob spawner uses this to count ticks and creates a new spawn
     * inside its implementation.
     */
    public void updateEntity() {
        boolean var1 = this.furnaceBurnTime > 0;
        boolean var2 = false;

        if(this.furnaceBurnTime > 0){
            --this.furnaceBurnTime;
        }

        if(!this.worldObj.isRemote){
            if(this.furnaceBurnTime == 0 && this.canSmelt()){
                this.currentItemBurnTime = this.furnaceBurnTime = getItemBurnTime(this.furnaceItemStacks[1]);

                if(this.furnaceBurnTime > 0){
                    var2 = true;

                    if(this.furnaceItemStacks[1] != null){
                        --this.furnaceItemStacks[1].stackSize;

                        if(this.furnaceItemStacks[1].stackSize == 0){
                            this.furnaceItemStacks[1] = this.furnaceItemStacks[1]
                                    .getItem().getContainerItemStack(
                                            furnaceItemStacks[1]);
                        }
                    }
                }
            }

            if(this.isBurning() && this.canSmelt()){
                ++this.furnaceCookTime;

                if(this.furnaceCookTime == furnaceSpeed){
                    this.furnaceCookTime = 0;
                    this.smeltItem();
                    var2 = true;
                }
            }else{
                this.furnaceCookTime = 0;
            }

            if(var1 != this.furnaceBurnTime > 0){
                var2 = true;
                BlockAdamantFurnace.updateFurnaceBlockState(
                        this.furnaceBurnTime > 0, this.worldObj, this.xCoord,
                        this.yCoord, this.zCoord);
            }

            if(furnaceItemStacks[0] == null && furnaceItemStacks[3] != null){
                furnaceItemStacks[0] = furnaceItemStacks[3];
                furnaceItemStacks[3] = null;

                var2 = true;
            }else{
                if(furnaceItemStacks[0] != null && furnaceItemStacks[3] != null){
                    Item var3 = furnaceItemStacks[0].getItem();
                    Item var4 = furnaceItemStacks[3].getItem();

                    if(var3 == var4){
                        int var5 = furnaceItemStacks[0].stackSize
                                + furnaceItemStacks[3].stackSize;

                        if(var5 > 64){
                            int var6 = 64 - furnaceItemStacks[0].stackSize;

                            furnaceItemStacks[0].stackSize = 64;
                            furnaceItemStacks[3].stackSize = furnaceItemStacks[3].stackSize
                                    - var6;

                            var2 = true;
                        }else{
                            furnaceItemStacks[0].stackSize = var5;
                            furnaceItemStacks[3] = null;
                            var2 = true;
                        }
                    }
                }
            }

        }

        if(var2){
            this.onInventoryChanged();
        }

        /* -- CUSTOM CODE START -- */

        if(this.shouldStartIncreasing){
            if(this.furnaceCookTime == 0){
                this.heatIncrease = true;
            }
        }else{
            this.heatIncrease = false;
        }

        if(this.heat > 499){
            this.worldObj.setBlockWithNotify(this.xCoord, this.yCoord,
                    this.zCoord, 0);
            this.worldObj.createExplosion((Entity)null, this.xCoord,
                    this.yCoord, this.zCoord,
                    MainReferences.MACHINE_EXPLOSION_STRENGTH, true);
        }

        if(this.heatIncrease){
            int var3 = this.furnaceRand.nextInt(100);
            int precent = 3;

            if(this.furnaceCookTime > 0){
                precent = 11;
            }else{
                precent = 3;
            }

            if(var3 < precent){
                this.heat += 5;
            }

            this.furnaceSpeed = 10;
        }else{
            int var4 = this.furnaceRand.nextInt(200);

            if(var4 < 51 && this.heat > 0){
                if(this.heat - 5 < 0){
                    this.heat = 0;
                }else{
                    this.heat -= 5;
                }
            }

            this.furnaceSpeed = 30;
        }

        /* -- CUSTOM CODE END -- */
    }

    /**
     * Returns true if the furnace can smelt an item, i.e. has a source item,
     * destination stack isn't full, etc.
     */
    private boolean canSmelt() {
        if(this.furnaceItemStacks[0] == null){
            return false;
        }else{
            ItemStack var1 = FurnaceRecipes.smelting().getSmeltingResult(
                    this.furnaceItemStacks[0]);

            if(var1 == null) return false;
            if(this.furnaceItemStacks[2] == null) return true;
            if(!this.furnaceItemStacks[2].isItemEqual(var1)) return false;
            int result = furnaceItemStacks[2].stackSize + var1.stackSize;

            return (result <= getInventoryStackLimit() && result <= var1
                    .getMaxStackSize());
        }
    }

    /**
     * Turn one item from the furnace source stack into the appropriate smelted
     * item in the furnace result stack
     */
    public void smeltItem() {
        if(this.canSmelt()){
            ItemStack var1 = FurnaceRecipes.smelting().getSmeltingResult(
                    this.furnaceItemStacks[0]);

            if(this.furnaceItemStacks[2] == null){
                this.furnaceItemStacks[2] = var1.copy();
            }else if(this.furnaceItemStacks[2].isItemEqual(var1)){
                furnaceItemStacks[2].stackSize += var1.stackSize;
            }

            --this.furnaceItemStacks[0].stackSize;

            if(this.furnaceItemStacks[0].stackSize <= 0){
                this.furnaceItemStacks[0] = null;
            }
        }
    }

    /**
     * Returns the number of ticks that the supplied fuel item will keep the
     * furnace burning, or 0 if the item isn't fuel
     */
    public static int getItemBurnTime(ItemStack par0ItemStack) {
        if(par0ItemStack == null){
            return 0;
        }else{
            int var1 = par0ItemStack.getItem().shiftedIndex;
            Item var2 = par0ItemStack.getItem();

            if(par0ItemStack.getItem() instanceof ItemBlock
                    && Block.blocksList[var1] != null){
                Block var3 = Block.blocksList[var1];

                if(var3 == Block.woodSingleSlab){
                    return 200;
                }

                if(var3.blockMaterial == Material.wood){
                    return 350;
                }
            }
            if(var2 instanceof ItemTool
                    && ((ItemTool)var2).getToolMaterialName().equals("WOOD"))
                return 250;
            if(var2 instanceof ItemSword
                    && ((ItemSword)var2).func_77825_f().equals("WOOD"))
                return 2500;
            if(var2 instanceof ItemHoe
                    && ((ItemHoe)var2).func_77842_f().equals("WOOD"))
                return 250;
            if(var1 == Item.stick.shiftedIndex) return 150;
            if(var1 == Item.coal.shiftedIndex) return 1700;
            if(var1 == Item.bucketLava.shiftedIndex) return 30000;
            if(var1 == Block.sapling.blockID) return 150;
            if(var1 == Item.blazeRod.shiftedIndex) return 2800;
            return GameRegistry.getFuelValue(par0ItemStack);
        }
    }

    /**
     * Return true if item is a fuel source (getItemBurnTime() > 0).
     */
    public static boolean isItemFuel(ItemStack par0ItemStack) {
        return getItemBurnTime(par0ItemStack) > 0;
    }

    /**
     * Sets whether or not the heat should increase.
     * 
     * @param par1 True to make it increase, false to stop it.
     */
    public static void setHeatIncrease(boolean par1) {
        shouldStartIncreasing = par1;
    }

    public boolean getHeatIncrease() {
        return heatIncrease;
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes
     * with Container
     */
    @Override
    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord,
                this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq(
                (double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D,
                (double)this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public int getStartInventorySide(ForgeDirection par1Side) {
        if(par1Side == ForgeDirection.WEST){
            return 0;
        }else if(par1Side == ForgeDirection.DOWN){
            return 1;
        }else if(par1Side == ForgeDirection.EAST){
            return 2;
        }else if(par1Side == ForgeDirection.NORTH
                || par1Side == ForgeDirection.UP){
            return 3;
        }

        return 2;
    }

    @Override
    public int getSizeInventorySide(ForgeDirection par1Side) {
        return 1;
    }

    @Override
    public void openChest() {}

    @Override
    public void closeChest() {}
}
