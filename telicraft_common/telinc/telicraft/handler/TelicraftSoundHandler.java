package telinc.telicraft.handler;

import cpw.mods.fml.common.FMLLog;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

import telinc.telicraft.TelicraftMain;

/**
 * The sound handler for Telicraft.
 */
public class TelicraftSoundHandler {
    @ForgeSubscribe
    public void onSoundLoad(SoundLoadEvent event) {
        try{
            event.manager.soundPoolSounds.addSound("telicraft/alarm.wav", TelicraftMain.class.getResource("/telinc/telicraft/resources/alarm.wav"));
            event.manager.soundPoolSounds.addSound("telicraft/meteorBomb.wav", TelicraftMain.class.getResource("/telinc/telicraft/resources/meteorBomb.wav"));
            FMLLog.finer("[Telicraft] All sounds were registered successfully!", this);
        }catch(Exception e){
            FMLLog.warning("[Telicraft] Failed to register one or more sounds.", this);
            e.printStackTrace();
        }
    }
}