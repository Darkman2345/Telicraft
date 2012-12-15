package telinc.telicraft.common.buildcraftIntegration;

import net.minecraft.tileentity.TileEntity;
import telinc.telicraft.common.reference.TextureReferences;
import telinc.telicraft.common.tileEntities.TileEntityAdamantFurnace;
import buildcraft.api.gates.ITriggerParameter;
import buildcraft.api.gates.Trigger;

public class AdamantFurnaceTriggerHot extends Trigger {
	public AdamantFurnaceTriggerHot(int id) {
		super(id);
	}

	@Override
	public String getTextureFile() {
		return TextureReferences.BUILDCRAFT_ICONS;
	}
	
	@Override
	public int getIndexInTexture() {
		return 2;
	}
	
	@Override
	public boolean hasParameter() {
		return false;
	}
	
	@Override
	public String getDescription() {
		return "Furnace Hot";
	}
	
	public boolean isTriggerActive(TileEntity tile, ITriggerParameter parameter) {
		if(tile instanceof TileEntityAdamantFurnace){
			TileEntityAdamantFurnace furnace = (TileEntityAdamantFurnace)tile;
			
			if(furnace.heat < 400 && furnace.heat > 99){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
}
