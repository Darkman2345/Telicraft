package telinc.telicraft.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import telinc.telicraft.TelicraftMain;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabTelicraft extends CreativeTabs {
	public CreativeTabTelicraft() {
		super("telicraft");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getTabIconItemIndex() {
		return TelicraftMain.excalibur.shiftedIndex;
	}
}
