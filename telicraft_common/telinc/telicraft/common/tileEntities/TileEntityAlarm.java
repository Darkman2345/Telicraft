package telinc.telicraft.common.tileEntities;

import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.TileEntity;

public class TileEntityAlarm extends TileEntity {
	
	//TODO Make this actually work.
	
	/**
	 * The timer of the alarm. 20 means that the alarm is off, or the sound has just started playing.
	 * 0 means that the music finished playing, and the timer's ready to be reset back to 20.
	 */
	public static int timer = 0;
	
	/**
	 * How long the timer is. Default: 20
	 */
	public static int timerLength = 20;
	
	/**
	 * Is the alarm active. If false, the block is not recieving power. If true, it is.
	 */
	public static boolean active = false;
	
	/**
	 * Allows the tile entity to update its state. In here, it used to check for activness
	 * and play sound effects.
	 */
	@Override
	public void updateEntity(){
		if(this.active){
			if(this.timer == 0){
				this.worldObj.playSoundEffect((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D, "telicraft.alarm", 1.0F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
				this.timer = timerLength;
			}else{
				--this.timer;
			}
		}else{
			this.timer = 0;
		}
	}
	
	/**
	 * Reads the tile entity from NBT.
	 * 
	 * @param nbt Used for reading and for calling back the original method.
	 */
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		
		// Sets this.timer to Timer if the key exists.
		if(nbt.hasKey("Timer")){
			this.timer = nbt.getInteger("Timer");
		}
		
		// Sets this.timerLength to TimerLength if the key exists.
		if(nbt.hasKey("TimerLength")){
			this.timerLength = nbt.getInteger("TimerLength");
		}
		
		// Sets this.active to Active if the key exists.
		if(nbt.hasKey("Active")){
			this.active = nbt.getBoolean("Active");
		}
	}
	
	/**
	 * Writes the tile entity to NBT.
	 * 
	 * @param nbt An NBTTagCompound. Used for writing and for calling back the original method.
	 */
	@Override
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		
		nbt.setInteger("Timer", timer);
		nbt.setInteger("TimerLength", timerLength);
		nbt.setBoolean("Active", active);
	}
}
