package telinc.telicraft.common.items;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import net.minecraft.src.EnumRarity;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.ItemSpade;
import net.minecraft.src.ItemStack;

import telinc.telicraft.common.TelicraftMain;
import telinc.telicraft.common.reference.TextureReferences;

public class ItemAdamantSpade extends ItemSpade {
	public ItemAdamantSpade(int id, EnumToolMaterial toolMaterial) {
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