package telinc.telicraft.common.buildcraftIntegration;

import net.minecraft.src.TileEntity;
import telinc.telicraft.common.reference.TextureReferences;
import telinc.telicraft.common.tileEntities.TileEntityAdamantFurnace;
import buildcraft.api.gates.ITriggerParameter;
import buildcraft.api.gates.Trigger;

public class AdamantFurnaceTriggerDangerous extends Trigger {
	public AdamantFurnaceTriggerDangerous(int id) {
		super(id);
	}

	@Override
	public String getTextureFile() {
		return TextureReferences.BUILDCRAFT_ICONS;
	}
	
	@Override
	public int getIndexInTexture() {
		return 3;
	}
	
	@Override
	public boolean hasParameter() {
		return false;
	}
	
	@Override
	public String getDescription() {
		return "Furnace Dangerous";
	}
	
	public boolean isTriggerActive(TileEntity tile, ITriggerParameter parameter) {
		if(tile instanceof TileEntityAdamantFurnace){
			TileEntityAdamantFurnace furnace = (TileEntityAdamantFurnace)tile;
			
			if(furnace.heat > 399){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
}
