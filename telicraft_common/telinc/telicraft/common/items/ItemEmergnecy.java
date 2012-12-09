package telinc.telicraft.common.items;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumAction;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

import telinc.telicraft.common.TelicraftMain;
import telinc.telicraft.common.core.TelicraftDamageSources;
import telinc.telicraft.common.reference.TextureReferences;

public class ItemEmergnecy extends Item {
	public ItemEmergnecy(int par1ID) {
		super(par1ID);
		this.setMaxStackSize(3);
		this.setCreativeTab(TelicraftMain.tabTelicraft);
	}

	/**
	 * called when the player releases the use item button. Args: itemstack,
	 * world, entityplayer, itemInUseCount
	 */
	@Override
	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer, int par4) {
		int var6 = this.getMaxItemUseDuration(par1ItemStack) - par4;
		
		float var7 = (float) var6 / 20.0F;
		var7 = (var7 * var7 + var7 * 2.0F) / 3.0F;

		if ((double) var7 < 0.1D) {
			return;
		}

		if (var7 > (float)70000) {
			var7 = (float)70000;
		}
		
		par3EntityPlayer.attackEntityFrom(TelicraftDamageSources.suicide, 99999);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		par3EntityPlayer.setItemInUse(par1ItemStack,
				this.getMaxItemUseDuration(par1ItemStack));
		return par1ItemStack;
	}

	@Override
	public ItemStack onFoodEaten(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		return par1ItemStack;
	}

	/**
	 * How long it takes to use or consume an item
	 */
	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 72000;
	}

	/**
	 * Returns the action that specifies what animation to play when the items
	 * is being used
	 */
	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.bow;
	}

	@Override
	public String getTextureFile() {
		return TextureReferences.ITEM_TEXTURE;
	}
}
