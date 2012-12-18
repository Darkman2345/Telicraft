package telinc.telicraft.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import telinc.telicraft.TelicraftMain;
import telinc.telicraft.api.ISharpenerHandler;

public class TelicraftSharpenerHandler implements ISharpenerHandler {
	@Override
	public void onItemTakenOut(EntityPlayer par1EntityPlayer,
			ItemStack par2ItemStack) {
		if (par2ItemStack.itemID == TelicraftMain.excalibur.shiftedIndex) {
			par1EntityPlayer.addStat(TelicraftMain.kingArthur, 1);
		}
	}
}