package telinc.telicraft.common.handlers;

import cpw.mods.fml.common.FMLLog;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;
import telinc.telicraft.common.TelicraftMain;
import telinc.telicraft.common.blocks.BlockPepperCrop;
import telinc.telicraft.common.blocks.BlockTomatoCrop;

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