package telinc.telicraft.handler;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;
import telinc.telicraft.TelicraftMain;
import telinc.telicraft.block.BlockPepperCrop;
import telinc.telicraft.block.BlockTomatoCrop;

/** The main event handler for Telicraft. */
public class TelicraftEventHandler {
	
	/** Bonemeal event */
    @ForgeSubscribe
    public void onUseBonemeal(BonemealEvent event) {
    	
    	// Fertilize tomatoes.
        if (event.ID == TelicraftMain.tomatoCrop.blockID) {
            if (!event.world.isRemote) {
                ((BlockTomatoCrop)TelicraftMain.tomatoCrop).fertilize(event.world, event.X, event.Y, event.Z, event.entityPlayer);                
            }
        }
        
        // Fertilize peppers.
        if (event.ID == TelicraftMain.pepperCrop.blockID) {
            if (!event.world.isRemote) {
                ((BlockPepperCrop)TelicraftMain.pepperCrop).fertilize(event.world, event.X, event.Y, event.Z, event.entityPlayer);                
            }
        }
        
    }
}