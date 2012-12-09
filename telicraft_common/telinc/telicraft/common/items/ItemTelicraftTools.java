package telinc.telicraft.common.items;

import java.util.List;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import telinc.telicraft.common.TelicraftMain;
import telinc.telicraft.common.reference.TextureReferences;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public class ItemTelicraftTools extends Item {
	public ItemTelicraftTools(int par1) {
		super(par1);
		this.maxStackSize = 1;
		this.setHasSubtypes(true);
		this.setCreativeTab(TelicraftMain.tabTelicraft);
	}
	
	@Override
	public int getIconFromDamage(int par1){
		switch(par1){
		case 0:
			return 23;
		default:
			return 23;
		}
	}
	
	@Override
	public String getItemNameIS(ItemStack par1ItemStack){
		int var2 = par1ItemStack.getItemDamage();
		
		switch(var2){
		case 0:
			return super.getItemName() + ".adamantSword";
		default:
			return super.getItemName() + ".adamantSword";
		}
	}
	
	@Override
	public boolean isFull3D() {
		return true;
	}

	/**
	 * Returns a list of items with the same ID, but different meta (eg: dye
	 * returns 16 items)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs,
			List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
	}

	@Override
	public String getTextureFile() {
		return TextureReferences.ITEM_TEXTURE;
	}
}