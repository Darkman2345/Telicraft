package telinc.telicraft.inventory;

import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import telinc.telicraft.item.crafting.SharpenerRecipes;
import telinc.telicraft.tileentity.TileSharpener;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerSharpener extends Container {
	private TileSharpener sharpener;
	private int lastSharpenTime = 0;
	private int lastSharpTime = 0;
	private int lastItemSharpTime = 0;

	public ContainerSharpener(InventoryPlayer par1InventoryPlayer,
			TileSharpener par2TileEntitySharpener) {
		this.sharpener = par2TileEntitySharpener;
		this.addSlotToContainer(new Slot(par2TileEntitySharpener, 0, 56, 17));
		this.addSlotToContainer(new Slot(par2TileEntitySharpener, 1, 56, 53));
		this.addSlotToContainer(new SlotSharpener(par1InventoryPlayer.player,
				par2TileEntitySharpener, 2, 116, 35));
		int var3;

		for (var3 = 0; var3 < 3; ++var3) {
			for (int var4 = 0; var4 < 9; ++var4) {
				this.addSlotToContainer(new Slot(par1InventoryPlayer, var4
						+ var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
			}
		}

		for (var3 = 0; var3 < 9; ++var3) {
			this.addSlotToContainer(new Slot(par1InventoryPlayer, var3,
					8 + var3 * 18, 142));
		}
	}
	
	@Override
	public void addCraftingToCrafters(ICrafting par1ICrafting) {
		super.addCraftingToCrafters(par1ICrafting);
		par1ICrafting.sendProgressBarUpdate(this, 0,
				this.sharpener.sharpenerSharpenTime);
		par1ICrafting.sendProgressBarUpdate(this, 1,
				this.sharpener.sharpenerSharpTime);
		par1ICrafting.sendProgressBarUpdate(this, 2,
				this.sharpener.currentItemSharpTime);
	}

	/**
	 * Updates crafting matrix; called from onCraftMatrixChanged. Args: none
	 */
	@Override
	public void updateCraftingResults() {
		super.updateCraftingResults();
		Iterator var1 = this.crafters.iterator();

		while (var1.hasNext()) {
			ICrafting var2 = (ICrafting) var1.next();

			if (this.lastSharpenTime != this.sharpener.sharpenerSharpenTime) {
				var2.sendProgressBarUpdate(this, 0,
						this.sharpener.sharpenerSharpenTime);
			}

			if (this.lastSharpTime != this.sharpener.sharpenerSharpTime) {
				var2.sendProgressBarUpdate(this, 1,
						this.sharpener.sharpenerSharpTime);
			}

			if (this.lastItemSharpTime != this.sharpener.currentItemSharpTime) {
				var2.sendProgressBarUpdate(this, 2,
						this.sharpener.currentItemSharpTime);
			}
		}

		this.lastSharpenTime = this.sharpener.sharpenerSharpenTime;
		this.lastSharpTime = this.sharpener.sharpenerSharpTime;
		this.lastItemSharpTime = this.sharpener.currentItemSharpTime;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2) {
		if (par1 == 0) {
			this.sharpener.sharpenerSharpenTime = par2;
		}

		if (par1 == 1) {
			this.sharpener.sharpenerSharpenTime = par2;
		}

		if (par1 == 2) {
			this.sharpener.currentItemSharpTime = par2;
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
		return this.sharpener.isUseableByPlayer(par1EntityPlayer);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
		ItemStack var3 = null;
		Slot var4 = (Slot) this.inventorySlots.get(par2);

		if (var4 != null && var4.getHasStack()) {
			ItemStack var5 = var4.getStack();
			var3 = var5.copy();

			if (par2 == 2) {
				if (!this.mergeItemStack(var5, 3, 39, true)) {
					return null;
				}

				var4.onSlotChange(var5, var3);
			} else if (par2 != 1 && par2 != 0) {
				if (SharpenerRecipes.instance().getRecipeResult(var5) != null) {
					if (!this.mergeItemStack(var5, 0, 1, false)) {
						return null;
					}
				} else if (TileSharpener.isItemSharp(var5)) {
					if (!this.mergeItemStack(var5, 1, 2, false)) {
						return null;
					}
				} else if (par2 >= 3 && par2 < 30) {
					if (!this.mergeItemStack(var5, 30, 39, false)) {
						return null;
					}
				} else if (par2 >= 30 && par2 < 39
						&& !this.mergeItemStack(var5, 3, 30, false)) {
					return null;
				}
			} else if (!this.mergeItemStack(var5, 3, 39, false)) {
				return null;
			}

			if (var5.stackSize == 0) {
				var4.putStack((ItemStack) null);
			} else {
				var4.onSlotChanged();
			}

			if (var5.stackSize == var3.stackSize) {
				return null;
			}

			var4.onPickupFromSlot(par1EntityPlayer, var5);
		}

		return var3;
	}
}
