package telinc.telicraft.common.api;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;

/**
 * Interface containing methods for the sharpener.
 * 
 * @see telinc.telicraft.common.api.TCRegistry#registerSharpenerHandler(ISharpenerHandler handler)
 */
public interface ISharpenerHandler {
	/**
	 * Called every time an item is taken out of the Sharpener's output slot.
	 */
	void onItemTakenOut(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack);
}