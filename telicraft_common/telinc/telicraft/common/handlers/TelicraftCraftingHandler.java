package telinc.telicraft.common.handlers;

import telinc.telicraft.common.TelicraftMain;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import cpw.mods.fml.common.ICraftingHandler;

public class TelicraftCraftingHandler implements ICraftingHandler {

	@Override
	public void onCrafting(EntityPlayer player, ItemStack item,
			IInventory craftMatrix) {
		// Check for Pizza State 3.
		if (item.itemID == TelicraftMain.pizza.shiftedIndex && item.getItemDamage() == 2) {
			player.addStat(TelicraftMain.deliciousPizza, 1);
		}
	}

	@Override
	public void onSmelting(EntityPlayer player, ItemStack item) {
		// Check for Adamant.
		if (item.itemID == TelicraftMain.adamant.shiftedIndex) {
			player.addStat(TelicraftMain.blueStuff, 1);
		}

	}

}