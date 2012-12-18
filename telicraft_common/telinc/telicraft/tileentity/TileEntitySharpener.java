package telinc.telicraft.tileentity;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.ISidedInventory;
import telinc.telicraft.TelicraftMain;
import telinc.telicraft.gui.SharpenerRecipes;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public class TileEntitySharpener extends TileEntity implements IInventory,
		ISidedInventory {
	
	/**
	 * The Random used by the Sharpener.
	 */
	private Random rand = new Random();
	
	/**
	 * Random value used by the sharpener.
	 */
	private static Random sharpenerRand = new Random();
	
	/**
	 * The speeds of the sharpener. The smaller the number, the faster the machine.
	 */
	private static int speed = 800;

	/**
	 * The ItemStacks that hold the items currently being used in the furnace
	 */
	private ItemStack[] sharpenerItemStacks = new ItemStack[3];

	/** The number of ticks that the furnace will keep burning */
	public int sharpenerSharpTime = 0;

	/**
	 * The number of ticks that a fresh copy of the currently-burning item would
	 * keep the furnace burning for
	 */
	public int currentItemSharpTime = 0;

	/** The number of ticks that the current item has been cooking for */
	public int sharpenerSharpenTime = 0;

	private EntityPlayer player;

	/**
	 * Returns the number of slots in the inventory.
	 */
	@Override
	public int getSizeInventory() {
		return this.sharpenerItemStacks.length;
	}

	/**
	 * Returns the stack in slot i
	 */
	@Override
	public ItemStack getStackInSlot(int par1) {
		return this.sharpenerItemStacks[par1];
	}

	/**
	 * Removes from an inventory slot (first arg) up to a specified number
	 * (second arg) of items and returns them in a new stack.
	 */
	@Override
	public ItemStack decrStackSize(int par1, int par2) {
		if (this.sharpenerItemStacks[par1] != null) {
			ItemStack var3;

			if (this.sharpenerItemStacks[par1].stackSize <= par2) {
				var3 = this.sharpenerItemStacks[par1];
				this.sharpenerItemStacks[par1] = null;
				return var3;
			} else {
				var3 = this.sharpenerItemStacks[par1].splitStack(par2);

				if (this.sharpenerItemStacks[par1].stackSize == 0) {
					this.sharpenerItemStacks[par1] = null;
				}

				return var3;
			}
		} else {
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
		if (this.sharpenerItemStacks[par1] != null) {
			ItemStack var2 = this.sharpenerItemStacks[par1];
			this.sharpenerItemStacks[par1] = null;
			return var2;
		} else {
			return null;
		}
	}

	/**
	 * Sets the given item stack to the specified slot in the inventory (can be
	 * crafting or armor sections).
	 */
	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
		this.sharpenerItemStacks[par1] = par2ItemStack;

		if (par2ItemStack != null
				&& par2ItemStack.stackSize > this.getInventoryStackLimit()) {
			par2ItemStack.stackSize = this.getInventoryStackLimit();
		}
	}

	/**
	 * Returns the name of the inventory.
	 */
	@Override
	public String getInvName() {
		return "container.sharpener";
	}

	/**
	 * Reads a tile entity from NBT.
	 */
	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		NBTTagList var2 = par1NBTTagCompound.getTagList("Items");
		this.sharpenerItemStacks = new ItemStack[this.getSizeInventory()];

		for (int var3 = 0; var3 < var2.tagCount(); ++var3) {
			NBTTagCompound var4 = (NBTTagCompound) var2.tagAt(var3);
			byte var5 = var4.getByte("Slot");

			if (var5 >= 0 && var5 < this.sharpenerItemStacks.length) {
				this.sharpenerItemStacks[var5] = ItemStack
						.loadItemStackFromNBT(var4);
			}
		}

		this.sharpenerSharpTime = par1NBTTagCompound.getShort("BurnTime");
		this.sharpenerSharpenTime = par1NBTTagCompound.getShort("CookTime");
		this.currentItemSharpTime = getItemBurnTime(this.sharpenerItemStacks[1]);
	}

	/**
	 * Writes a tile entity to NBT.
	 */
	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound
				.setShort("BurnTime", (short) this.sharpenerSharpTime);
		par1NBTTagCompound.setShort("CookTime",
				(short) this.sharpenerSharpenTime);
		NBTTagList var2 = new NBTTagList();

		for (int var3 = 0; var3 < this.sharpenerItemStacks.length; ++var3) {
			if (this.sharpenerItemStacks[var3] != null) {
				NBTTagCompound var4 = new NBTTagCompound();
				var4.setByte("Slot", (byte) var3);
				this.sharpenerItemStacks[var3].writeToNBT(var4);
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
		return this.sharpenerSharpenTime * par1 / this.speed;
	}

	/**
	 * Returns true if the furnace is currently burning
	 */
	public boolean isBurning() {
		return this.sharpenerSharpTime > 0;
	}

	/**
	 * Allows the entity to update its state. Overridden in most subclasses,
	 * e.g. the mob spawner uses this to count ticks and creates a new spawn
	 * inside its implementation.
	 */
	@Override
	public void updateEntity() {
		boolean var1 = this.sharpenerSharpTime > 0;
		boolean var2 = false;

		if (this.sharpenerSharpTime > 0) {
			--this.sharpenerSharpTime;
		}

		if (!this.worldObj.isRemote) {
			if (this.sharpenerSharpTime == 0 && this.canSmelt()) {
				this.currentItemSharpTime = this.sharpenerSharpTime = getItemBurnTime(this.sharpenerItemStacks[1]);

				if (this.sharpenerSharpTime > 0) {
					var2 = true;

					if (this.sharpenerItemStacks[1] != null) {
						// --this.sharpenerItemStacks[1].stackSize;
						//this.updateShapTool();

						if (this.sharpenerItemStacks[1].stackSize == 0) {
							this.sharpenerItemStacks[1] = this.sharpenerItemStacks[1]
									.getItem().getContainerItemStack(
											sharpenerItemStacks[1]);
						}
					}
				}

			}

			if (this.isBurning() && this.canSmelt()) {
				++this.sharpenerSharpenTime;

				if (this.sharpenerSharpenTime == this.speed) {
					this.sharpenerSharpenTime = 0;
					this.smeltItem();
					var2 = true;
				}
			} else {
				this.sharpenerSharpenTime = 0;
			}

			if (var1 != this.sharpenerSharpTime > 0) {
				var2 = true;
			}
		}

		if (var2) {
			this.onInventoryChanged();
		}

	}

	/**
	 * Returns true if the furnace can smelt an item, i.e. has a source item,
	 * destination stack isn't full, etc.
	 */
	private boolean canSmelt() {
		if (this.sharpenerItemStacks[0] == null) {
			return false;
		} else {
			ItemStack var1 = SharpenerRecipes.instance().getRecipeResult(
					this.sharpenerItemStacks[0]);
			if (var1 == null) return false;
			if (this.sharpenerItemStacks[2] == null) return true;
			if (!this.sharpenerItemStacks[2].isItemEqual(var1)) return false;
			int result = sharpenerItemStacks[2].stackSize + var1.stackSize;
			return (result <= getInventoryStackLimit() && result <= var1
					.getMaxStackSize());
		}
	}

	/**
	 * Turn one item from the furnace source stack into the appropriate smelted
	 * item in the furnace result stack
	 */
	public void smeltItem() {
		if (this.canSmelt()) {
			ItemStack var1 = SharpenerRecipes.instance().getRecipeResult(
					this.sharpenerItemStacks[0]);

			if (this.sharpenerItemStacks[2] == null) {
				this.sharpenerItemStacks[2] = var1.copy();
			} else if (this.sharpenerItemStacks[2].isItemEqual(var1)) {
				sharpenerItemStacks[2].stackSize += var1.stackSize;
			}

			--this.sharpenerItemStacks[0].stackSize;

			if (this.sharpenerItemStacks[0].stackSize <= 0) {
				this.sharpenerItemStacks[0] = null;
			}
			
			sharpenerItemStacks[1].setItemDamage(sharpenerItemStacks[1].getItemDamage() + 250);
			
			if(sharpenerItemStacks[1].getItemDamage() <= 0){
				--sharpenerItemStacks[1].stackSize;
			}
			//sharpenerItemStacks[1].damageItem(250, TelicraftMain.engine.getPlayerInstance());
		}
	}

	/**
	 * Returns the number of ticks that the supplied fuel item will keep the
	 * furnace burning, or 0 if the item isn't fuel
	 */
	public static int getItemBurnTime(ItemStack par0ItemStack) {
		if (par0ItemStack == null) {
			return 0;
		} else {
			int var1 = par0ItemStack.getItem().shiftedIndex;
			Item var2 = par0ItemStack.getItem();

			if (var1 == TelicraftMain.sharpeningTool.shiftedIndex) return 1;

			return 0;
		}
	}

	/**
	 * Return true if item is a fuel source (getItemBurnTime() > 0).
	 */
	public static boolean isItemFuel(ItemStack par0ItemStack) {
		return getItemBurnTime(par0ItemStack) > 0;
	}

	/**
	 * Do not make give this method the name canInteractWith because it clashes
	 * with Container
	 */
	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord,
				this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq(
				(double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D,
				(double) this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openChest() {}

	@Override
	public void closeChest() {}

	@Override
	public int getStartInventorySide(ForgeDirection side) {
		if (side == ForgeDirection.DOWN) return 1;
		if (side == ForgeDirection.UP) return 0;
		return 2;
	}

	@Override
	public int getSizeInventorySide(ForgeDirection side) {
		return 1;
	}
	
	/*private void updateShapTool(){
		int var6 = sharpenerItemStacks[1].getItemDamage();
		int var7 = var6 + 250;
		
		if(var6 + var7 >= 500){
			sharpenerItemStacks[1] = null;
			this.worldObj.spawnParticle("iconcrack_" + TelicraftMain.sharpeningTool.shiftedIndex, (double)((float)this.xCoord + this.rand.nextFloat()), (double)((float)this.yCoord + 1.1F), (double)((float)this.yCoord + rand.nextFloat()), 0.0D, 0.0D, 0.0D);
			this.worldObj.playSoundEffect((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D, "random.break", 1.0F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
		}else{
			sharpenerItemStacks[1].setItemDamage(var7);
		}
		
		//sharpenerItemStacks[1].setItemDamage(var7);
		//sharpenerItemStacks[1].damageItem(1, TelicraftMain.engine.getPlayerInstance());
	}*/
}
