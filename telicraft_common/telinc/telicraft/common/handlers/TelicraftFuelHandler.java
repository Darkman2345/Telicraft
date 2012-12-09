package telinc.telicraft.common.handlers;

import telinc.telicraft.common.TelicraftMain;
import net.minecraft.src.ItemStack;
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