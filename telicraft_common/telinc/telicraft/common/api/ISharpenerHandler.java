package telinc.telicraft.common.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * Interface containing methods for the sharpener.
 * 
 * @see telinc.telicraft.common.api.TCRegistry#registerSharpenerHandler(ISharpenerHandler handler)
 */
public interface ISharpenerHandler {
	/**
	 * Called every time an item is taken out of the Sharpener's output slot.
	 * 
	 * @param par1EntityPlayer The player instance.
	 * @param par2ItemStack The item that's taken out. This would be the perfect time to check if you
	 * want to give an achievement.
	 */
	void onItemTakenOut(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack);
}