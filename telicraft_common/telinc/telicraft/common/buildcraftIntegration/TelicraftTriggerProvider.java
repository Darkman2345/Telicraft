package telinc.telicraft.common.buildcraftIntegration;

import java.util.LinkedList;

import net.minecraft.src.Block;
import net.minecraft.src.TileEntity;
import telinc.telicraft.common.tileEntities.TileEntityAdamantFurnace;
import telinc.telicraft.common.tileEntities.TileEntityAlarm;
import buildcraft.api.gates.ITrigger;
import buildcraft.api.gates.ITriggerProvider;
import buildcraft.api.transport.IPipe;

public class TelicraftTriggerProvider implements ITriggerProvider {
	@Override
	public LinkedList<ITrigger> getPipeTriggers(IPipe pipe) {
		return null;
	}

	@Override
	public LinkedList<ITrigger> getNeighborTriggers(Block block, TileEntity tile) {
		LinkedList<ITrigger> neighborTriggers = new LinkedList();
		
		if(tile instanceof TileEntityAdamantFurnace){
			neighborTriggers.add(new AdamantFurnaceTriggerNoHeat(1000));
			neighborTriggers.add(new AdamantFurnaceTriggerSafe(1001));
			neighborTriggers.add(new AdamantFurnaceTriggerHot(1002));
			neighborTriggers.add(new AdamantFurnaceTriggerDangerous(1003));
			neighborTriggers.add(new AdamantFurnaceTriggerIsInSuperheat(1004));
		}
		
		if(tile instanceof TileEntityAlarm){
			neighborTriggers.add(new AlarmTriggerActive(1005));
		}
		
		return neighborTriggers;
	}
}