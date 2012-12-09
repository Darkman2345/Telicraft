package telinc.telicraft.common.items;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import net.minecraft.src.EnumRarity;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.ItemAxe;
import net.minecraft.src.ItemStack;

import telinc.telicraft.common.TelicraftMain;
import telinc.telicraft.common.reference.TextureReferences;

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