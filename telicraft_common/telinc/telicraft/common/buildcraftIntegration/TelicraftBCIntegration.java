package telinc.telicraft.common.buildcraftIntegration;

import net.minecraft.item.ItemStack;
import telinc.telicraft.common.TelicraftMain;
import buildcraft.api.gates.ActionManager;
import buildcraft.api.transport.FacadeManager;

public class TelicraftBCIntegration {
	public static void run(){
		// Registers the Trigger Provider.
		ActionManager.registerTriggerProvider(new TelicraftTriggerProvider());
		
		// Registers all the custom facades.
		FacadeManager.addFacade(new ItemStack(TelicraftMain.adamantBlk));
		FacadeManager.addFacade(new ItemStack(TelicraftMain.megaAdamant));
		FacadeManager.addFacade(new ItemStack(TelicraftMain.adamantOre));
		FacadeManager.addFacade(new ItemStack(TelicraftMain.redstoneBlk));
		FacadeManager.addFacade(new ItemStack(TelicraftMain.darkEndStone));
		FacadeManager.addFacade(new ItemStack(TelicraftMain.crackedNetherrack));
	}
}