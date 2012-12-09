package telinc.telicraft.common.buildcraftIntegration;

import buildcraft.api.gates.ActionManager;

public class TelicraftBCIntegration {
	public static void run(){
		ActionManager.registerTriggerProvider(new TelicraftTriggerProvider());
	}
}