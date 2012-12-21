package telinc.telicraft.bcIntegration;

import net.minecraft.tileentity.TileEntity;
import telinc.telicraft.lib.TextureReferences;
import telinc.telicraft.tileentity.TileAlarm;
import buildcraft.api.gates.ITriggerParameter;
import buildcraft.api.gates.Trigger;

public class AlarmTriggerActive extends Trigger {
	public AlarmTriggerActive(int id) {
		super(id);
	}

	@Override
	public String getTextureFile() {
		return TextureReferences.BUILDCRAFT_ICONS;
	}
	
	@Override
	public int getIndexInTexture(){
		return 5;
	}
	
	@Override
	public boolean hasParameter() {
		return false;
	}
	
	@Override
	public String getDescription() {
		return "Alarm active";
	}
	
	@Override
	public boolean isTriggerActive(TileEntity tile, ITriggerParameter parameter) {
		if(tile instanceof TileAlarm){
			TileAlarm alarm = (TileAlarm)tile;
			
			return alarm.active;
		}else{
			return false;
		}
	}
}