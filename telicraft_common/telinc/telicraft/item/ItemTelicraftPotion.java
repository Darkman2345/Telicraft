package telinc.telicraft.item;

import java.util.List;
import java.util.Random;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import telinc.telicraft.TelicraftMain;
import telinc.telicraft.entity.EntityPetrify;
import telinc.telicraft.lib.RandomMessages;
import telinc.telicraft.lib.TextureReferences;
import telinc.telicraft.world.TelicraftDamageSources;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public class ItemTelicraftPotion extends Item {
	
	private Random rand = new Random();
	
	public ItemTelicraftPotion(int par1) {
		super(par1);
		this.setMaxStackSize(1);
		this.setHasSubtypes(true);
		this.setCreativeTab(TelicraftMain.tabTelicraft);
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public int getIconFromDamage(int par1) {
		switch (par1) {
		case 0:
			return 26;
		case 1:
			return 27;
		case 2:
			return this.renderPetrifyDirect();
		case 3:
			return 29;
		default:
			return 26;
		}
	}

	private int renderPetrifyDirect() {
		if(TelicraftMain.classicPetrifyModel){
			return 28;
		}else{
			return 30;
		}
	}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.drink;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 32;
	}

	@Override
	public String getItemNameIS(ItemStack par1ItemStack) {
		int var2 = par1ItemStack.getItemDamage();

		switch (var2) {
		case 0:
			return super.getItemName() + ".crazy";
		case 1:
			return super.getItemName() + ".petrify";
		case 2:
			return super.getItemName() + ".petrifyDirect";
		case 3:
			return super.getItemName() + ".random";
		default:
			return super.getItemName() + ".crazy";
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, List par3List, boolean par4) {

		if (par1ItemStack.getItemDamage() == 0) {
			if (TelicraftMain.potionCrazyEnable) {
				par3List.add("\u00a7c" + "Unknown (3:00)");	//Bright Red
			} else {
				par3List.add("\u00a79" + "Disabled");	//Indigo
			}
		} else if (par1ItemStack.getItemDamage() == 1
				|| par1ItemStack.getItemDamage() == 2) {
			if (TelicraftMain.potionPetrifyEnable) {
				par3List.add("\u00a7c" + "Instant Death");	//Bright Red
			} else {
				par3List.add("\u00a79" + "Disabled");	//Indigo
			}
		}else if (par1ItemStack.getItemDamage() == 3){
			par3List.add("\u00a7e" + "\u00a7k" + "Random Effect" + "\u00a7r" + "\u00a7e" +" (2:00)");	//Yellow + djfslkfdmlf
		}
	}
	
	@Override
	public ItemStack onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer){
		
		par2World.playSoundAtEntity(par3EntityPlayer, "random.drink", 0.5F, par2World.rand.nextFloat() * 0.1F + 0.9F);
		
		if(par1ItemStack.getItemDamage() == 0){
			this.onFoodEatenDamage0(par3EntityPlayer);
		}else if(par1ItemStack.getItemDamage() == 1){
			this.onFoodEatenDamage1(par3EntityPlayer);
		}else if(par1ItemStack.getItemDamage() == 3){
			this.onFoodEatenDamage3(par3EntityPlayer, rand);
		}
		
        if (!par3EntityPlayer.capabilities.isCreativeMode) {
            --par1ItemStack.stackSize;
            
            if (par1ItemStack.stackSize <= 0) {
                return new ItemStack(Item.glassBottle);
            }

            par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Item.glassBottle));
        }
        
		return par1ItemStack;
	}
	
	/**
	 * Called by {@linkplain ItemTelicraftPotion#onFoodEaten(ItemStack, World, EntityPlayer)}
	 * when the item damage (metadata) is 3 (Potion of Randomness).
	 * 
	 * @param par1EntityPlayer
	 */
	private void onFoodEatenDamage3(EntityPlayer par1EntityPlayer, Random par2Random) {
		int var10 = par2Random.nextInt(4);
		
		System.out.println(var10);
		
		if(var10 == 0){
			par1EntityPlayer.addPotionEffect(new PotionEffect(Potion.nightVision.id, 120 * 20, 0));
			par1EntityPlayer.addPotionEffect(new PotionEffect(Potion.weakness.id, 120 * 20, 0));
			return;
		}
		
		if(var10 == 1){
			par1EntityPlayer.addPotionEffect(new PotionEffect(Potion.regeneration.id, 120 * 20, 0));
			par1EntityPlayer.attackEntityFrom(TelicraftDamageSources.random, 10);
			return;
		}
		
		if(var10 == 2){
			par1EntityPlayer.addPotionEffect(new PotionEffect(Potion.invisibility.id, 120 * 20, 0));
			par1EntityPlayer.addPotionEffect(new PotionEffect(Potion.wither.id, 120 * 20, 0));
			return;
		}
		
		if(var10 == 3){
			par1EntityPlayer.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 120 * 20, 0));
			par1EntityPlayer.addPotionEffect(new PotionEffect(Potion.hunger.id, 120 * 20, 0));
			return;
		}
		
		if(var10 == 4){
			par1EntityPlayer.addPotionEffect(new PotionEffect(Potion.jump.id, 120 * 20, 3));
			par1EntityPlayer.addPotionEffect(new PotionEffect(Potion.poison.id, 120 * 20, 0));
			return;
		}
	}

	/**
	 * Called by {@linkplain ItemTelicraftPotion#onFoodEaten(ItemStack, World, EntityPlayer)}
	 * when the item damage (metadata) is 0 (Potion of Craziness).
	 * 
	 * @param par1EntityPlayer
	 */
	private void onFoodEatenDamage0(EntityPlayer par1EntityPlayer) {
		if(TelicraftMain.potionCrazyEnable) {
			par1EntityPlayer.addPotionEffect(new PotionEffect(Potion.poison.id, 180 * 20, 59));
			par1EntityPlayer.addPotionEffect(new PotionEffect(Potion.confusion.id, 180 * 20, 19));
			par1EntityPlayer.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 180 * 20, 3));
			par1EntityPlayer.addPotionEffect(new PotionEffect(Potion.hunger.id, 180 * 20, 5));
			par1EntityPlayer.addPotionEffect(new PotionEffect(Potion.blindness.id, 180 * 20, 17));
			par1EntityPlayer.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 180 * 20, 4));
			par1EntityPlayer.addPotionEffect(new PotionEffect(Potion.weakness.id, 180 * 20, 59));
		}else{
			par1EntityPlayer.addChatMessage(RandomMessages.getPotionCrazyMessage(rand));
		}
		
        par1EntityPlayer.addStat(TelicraftMain.youMad, 1);
	}
	
	/**
	 * Called by {@linkplain ItemTelicraftPotion#onFoodEaten(ItemStack, World, EntityPlayer)}
	 * when the item damage (metadata) is 1 (Petrifying Potion).
	 * 
	 * @param par1EntityPlayer
	 */
	private void onFoodEatenDamage1(EntityPlayer par1EntityPlayer) {
		if(TelicraftMain.potionPetrifyEnable) {
			par1EntityPlayer.attackEntityFrom(TelicraftDamageSources.petrify, 200);
		}else{
			par1EntityPlayer
				.addChatMessage("The Petrifying Potion is disabled. If you think that this is not supposed to happen, go to your configuration file or contact the server administrator.");
		}
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		
		if (par1ItemStack.getItemDamage() == 0 || par1ItemStack.getItemDamage() == 1 || par1ItemStack.getItemDamage() == 3) {
			par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
		}
		
		if (par1ItemStack.getItemDamage() == 2) {
			if (!par2World.isRemote) {
				if (TelicraftMain.potionPetrifyEnable) {
					par2World.spawnEntityInWorld(new EntityPetrify(par2World, par3EntityPlayer));
					par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
				}else{
					par3EntityPlayer.addChatMessage("The Petrifying Potion is disabled. If you think that this is not supposed to happen, go to your configuration file or contact the server administrator.");
					par2World.playSoundAtEntity(par3EntityPlayer, "random.click", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
				}
			}
			
			if (!par3EntityPlayer.capabilities.isCreativeMode) {
				--par1ItemStack.stackSize;
			}
		}

		return par1ItemStack;
	}

	@Override
	public String getTextureFile() {
		return TextureReferences.ITEM_TEXTURE;
	}

	/**
	 * Returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs,
			List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
		par3List.add(new ItemStack(par1, 1, 1));
		par3List.add(new ItemStack(par1, 1, 2));
		par3List.add(new ItemStack(par1, 1, 3));
	}
}