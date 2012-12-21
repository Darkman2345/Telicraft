package telinc.telicraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import telinc.telicraft.TelicraftMain;
import telinc.telicraft.lib.TextureReferences;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAdamantAxe extends ItemAxe {
    public ItemAdamantAxe(int id, EnumToolMaterial toolMaterial) {
        super(id, toolMaterial);
        maxStackSize = 1;
        this.setCreativeTab(TelicraftMain.tabTelicraft);
    }

    @Override
    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) {
        if(par2Block.blockMaterial == Material.wood){
            return 80.13F;
        }else{
            return par2Block != null
                    && (par2Block.blockMaterial == Material.plants || par2Block.blockMaterial == Material.vine) ? this.efficiencyOnProperMaterial
                    : super.getStrVsBlock(par1ItemStack, par2Block);
        }
    }

    @Override
    public String getTextureFile() {
        return TextureReferences.ITEM_TEXTURE;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack id) {
        return EnumRarity.rare;
    }

}