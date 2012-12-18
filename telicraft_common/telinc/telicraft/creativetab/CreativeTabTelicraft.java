package telinc.telicraft.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import telinc.telicraft.TelicraftMain;
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
