package telinc.telicraft.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import telinc.telicraft.TelicraftMain;
import telinc.telicraft.lib.TextureReferences;
import telinc.telicraft.world.TelicraftDamageSources;

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
