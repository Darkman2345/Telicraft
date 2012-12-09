package telinc.telicraft.common.handlers;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import telinc.telicraft.common.TelicraftMain;
import telinc.telicraft.common.api.ISharpenerHandler;

public class TelicraftSharpenerHandler implements ISharpenerHandler {
	@Override
	public void onItemTakenOut(EntityPlayer par1EntityPlayer,
			ItemStack par2ItemStack) {
		if (par2ItemStack.itemID == TelicraftMain.excalibur.shiftedIndex) {
			par1EntityPlayer.addStat(TelicraftMain.kingArthur, 1);
		}
	}
}