package telinc.telicraft.handler;

import net.minecraft.item.ItemStack;
import telinc.telicraft.TelicraftMain;
import cpw.mods.fml.common.IFuelHandler;

/**
 * Handles Adamant Fuel Ingots.
 * 
 * @author Telinc1
 * */
public class TelicraftFuelHandler implements IFuelHandler {
	public int getBurnTime(ItemStack fuel) {
		int var1 = fuel.itemID;

		if (var1 == TelicraftMain.fuel.shiftedIndex){
			return 5000;
		}else{
			return 0;
		}
	}
}