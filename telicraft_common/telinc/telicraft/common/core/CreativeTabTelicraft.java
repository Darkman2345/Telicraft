package telinc.telicraft.common.core;

import net.minecraft.src.CreativeTabs;
import telinc.telicraft.common.TelicraftMain;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public class CreativeTabTelicraft extends CreativeTabs {
	public CreativeTabTelicraft() {
		super("telicraft");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getTabIconItemIndex() {
		return TelicraftMain.excalibur.shiftedIndex;
	}
}
