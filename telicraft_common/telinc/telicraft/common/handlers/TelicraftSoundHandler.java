package telinc.telicraft.common.handlers;

import cpw.mods.fml.common.FMLLog;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

import telinc.telicraft.common.TelicraftMain;

/**
 * The sound handler for Telicraft.
 */
public class TelicraftSoundHandler {
    @ForgeSubscribe
    public void onSound(SoundLoadEvent event){
        try{
        	// Try and add the alarm sounds.
        	event.manager.soundPoolSounds.addSound("telicraft/alarm.wav", TelicraftMain.class.getResource("/telinc/telicraft/resources/alarm.wav"));
        }catch (Exception e){
        	FMLLog.warning("[Telicraft] Failed to register one or more sounds.", new Object[0]);
        }
    }
}