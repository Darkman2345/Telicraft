package telinc.telicraft.bcIntegration;

import net.minecraft.tileentity.TileEntity;
import telinc.telicraft.lib.TextureReferences;
import telinc.telicraft.tileentity.TileAdamantFurnace;
import buildcraft.api.gates.ITriggerParameter;
import buildcraft.api.gates.Trigger;

public class AdamantFurnaceFuelTrigger extends Trigger {
	public AdamantFurnaceFuelTrigger(int id) {
		super(id);
	}
	
	@Override
	public String getTextureFile(){
		return TextureReferences.BUILDCRAFT_ICONS;
	};
	
	@Override
	public int getIndexInTexture() {
		return 5;
	}
	
	@Override
	public boolean hasParameter() {
		return true;
	}
	
	@Override
	public String getDescription() {
		return "Has fuel";
	}
	
	@Override
	public boolean isTriggerActive(TileEntity tile, ITriggerParameter parameter) {
		if(tile instanceof TileAdamantFurnace){
			TileAdamantFurnace var1 = (TileAdamantFurnace) tile;
			
			if(parameter.getItemStack() != null){
				if(var1.isBurning()){
					return true;
				}else{
					return false;
				}
			}else{
				if(parameter.getItem() == var1.getStackInSlot(1)){
					if(var1.isBurning()){
						return true;
					}else{
						return false;
					}
				}else{
					return false;
				}
			}
		}else{
			return false;
		}
	}
}