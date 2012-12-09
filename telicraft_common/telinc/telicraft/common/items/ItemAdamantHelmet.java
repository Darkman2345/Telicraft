package telinc.telicraft.common.items;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import net.minecraft.src.EnumArmorMaterial;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.ItemArmor;
import net.minecraft.src.ItemStack;

import net.minecraftforge.common.IArmorTextureProvider;

import telinc.telicraft.common.TelicraftMain;
import telinc.telicraft.common.reference.TextureReferences;

public class ItemAdamantHelmet extends ItemArmor implements IArmorTextureProvider {

	public ItemAdamantHelmet(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
		super(par1, par2EnumArmorMaterial, par3, par4);
		maxStackSize = 1;
		this.setCreativeTab(TelicraftMain.tabTelicraft);
	}
	
	@Override
	public String getArmorTextureFile(ItemStack par1ItemStack){
		if(par1ItemStack.itemID == TelicraftMain.adamantHelmet.shiftedIndex || par1ItemStack.itemID == TelicraftMain.adamantChest.shiftedIndex || par1ItemStack.itemID == TelicraftMain.adamantBoots.shiftedIndex){
			return TextureReferences.ARMOUR_FIRST_TEXTURE;
		}
		
		if(par1ItemStack.itemID == TelicraftMain.adamantLegs.shiftedIndex) {
			return TextureReferences.ARMOUR_SECOND_TEXTURE;
		}
		
		return TextureReferences.ARMOUR_SECOND_TEXTURE;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public EnumRarity getRarity(ItemStack id) {
		return EnumRarity.rare;
	}
	
	@Override
	public String getTextureFile(){
		return TextureReferences.ITEM_TEXTURE;
	}
	
}