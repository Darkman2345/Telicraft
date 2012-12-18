package telinc.telicraft.item;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import telinc.telicraft.TelicraftMain;
import telinc.telicraft.lib.TextureReferences;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public class ItemAdamantAxe extends ItemAxe {
	public ItemAdamantAxe(int id, EnumToolMaterial toolMaterial) {
		super(id, toolMaterial);
		maxStackSize = 1;
		this.setCreativeTab(TelicraftMain.tabTelicraft);
	}

	@Override
	public String getTextureFile() {
		return TextureReferences.ITEM_TEXTURE;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public EnumRarity getRarity(ItemStack id) {
		return EnumRarity.rare;
	}

}