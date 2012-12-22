package telinc.telicraft.item;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import telinc.telicraft.TelicraftMain;
import telinc.telicraft.lib.TextureReferences;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemExcalibur extends ItemSword {
    public ItemExcalibur(int id, EnumToolMaterial toolMaterial) {
        super(id, toolMaterial);
        maxStackSize = 1;
        this.setCreativeTab(TelicraftMain.tabTelicraft);
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