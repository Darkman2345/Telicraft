package telinc.telicraft.item;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import net.minecraftforge.common.IShearable;

import telinc.telicraft.TelicraftMain;

public class ItemCutter extends ItemTool {
    public ItemCutter(int par1, int par2,
            EnumToolMaterial par3EnumToolMaterial, Block[] par4ArrayOfBlock) {
        super(par1, par2, par3EnumToolMaterial, par4ArrayOfBlock);
        this.setCreativeTab(TelicraftMain.tabTelicraft);
    }

    /**
     * Current implementations of this method in child classes do not use the
     * entry argument beside ev. They just raise the damage on the stack.
     */
    @Override
    public boolean hitEntity(ItemStack par1ItemStack,
            EntityLiving par2EntityLiving, EntityLiving par3EntityLiving) {
        par1ItemStack.damageItem(5, par3EntityLiving);
        return true;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World,
            int par3, int par4, int par5, int par6,
            EntityLiving par7EntityLiving) {
        if((double)Block.blocksList[par3].getBlockHardness(par2World, par4,
                par5, par6) != 0.0D){
            par1ItemStack.damageItem(2, par7EntityLiving);
        }

        return true;
    }

    /**
     * Returns the damage against a given entity.
     */
    @Override
    public int getDamageVsEntity(Entity par1Entity) {
        return 9;
    }

    /**
     * Dye sheep, place saddles, etc ...
     */
    @Override
    public boolean itemInteractionForEntity(ItemStack itemstack,
            EntityLiving entity) {
        if(entity.worldObj.isRemote){
            return false;
        }
        
        if(entity instanceof IShearable){
            IShearable target = (IShearable)entity;
            if(target.isShearable(itemstack, entity.worldObj, (int)entity.posX,
                    (int)entity.posY, (int)entity.posZ)){
                ArrayList<ItemStack> drops = target.onSheared(itemstack,
                        entity.worldObj, (int)entity.posX, (int)entity.posY,
                        (int)entity.posZ,
                        EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, itemstack));

                Random rand = new Random();
                for(ItemStack stack : drops){
                    EntityItem ent = entity.entityDropItem(stack, 1.0F);
                    ent.motionY += rand.nextFloat() * 0.05F;
                    ent.motionX += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
                    ent.motionZ += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
                }
                
                itemstack.damageItem(3, entity);
                entity.attackEntityFrom(DamageSource.generic, 1);
            }
            
            return true;
        }
        
        return false;
    }
}