package telinc.telicraft.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import telinc.telicraft.TelicraftMain;
import telinc.telicraft.lib.TextureReferences;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public class ItemPizza extends ItemFood {
	public ItemPizza(int par1, int par2, float par3){
		super(par1, par2, par3, false);
		this.setAlwaysEdible();
		this.setHasSubtypes(true);
		this.setCreativeTab(TelicraftMain.tabTelicraft);
	}
	
	@Override
	public ItemStack onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer){
		int var3 = par1ItemStack.getItemDamage();
		
		--par1ItemStack.stackSize;
		par3EntityPlayer.getFoodStats().addStats(this);
		par2World.playSoundAtEntity(par3EntityPlayer, "random.burp", 0.5F,
				par2World.rand.nextFloat() * 0.1F + 0.9F);
		this.func_77849_c(par1ItemStack, par2World, par3EntityPlayer);
		
		if(var3 == 0){
			par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.confusion.id, 1000, 0));
		}else if(var3 == 1){
			par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 1000, 0));
		}else if(var3 == 2){
			par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 1400, 0));
		}else{
			par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.confusion.id, 1000, 0));
		}
		
		return par1ItemStack;
	}
	
	@Override
	public int getIconFromDamage(int par1){
		switch(par1){
		case 0:
			return 18;
		case 1:
			return 19;
		case 2:
			return 20;
		default:
			return 18;
		}
	}
	
	@Override
	public String getItemNameIS(ItemStack par1ItemStack){
		int var2 = par1ItemStack.getItemDamage();
		
		switch(var2){
		case 0:
			return super.getItemName() + ".raw";
		case 1:
			return super.getItemName() + ".cookedFirst";
		case 2:
			return super.getItemName() + ".cookedSecond";
		default:
			return super.getItemName() + ".raw";
		}
	}
	
	@Override
	public String getTextureFile(){
		return TextureReferences.ITEM_TEXTURE;
	}
	
    /**
     * Returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
	@Override
	@SideOnly(Side.CLIENT)
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
    }
}