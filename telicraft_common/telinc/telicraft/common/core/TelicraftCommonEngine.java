package telinc.telicraft.common.core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class TelicraftCommonEngine {
	
	/**
	 * Same as ModLoader.addArmor, but this one works on a server.
	 * 
	 *  @param name Name of the armour.
	 *  */
	public int addArmor(String name) {
		return 0;
	}
	
	/**
	 * Adds a localization to the Language Registry.
	 * 
	 *  @param par1Obj The object to add, ex.: "tile.adamantOre.name"
	 *  @param par2Str The name to add.
	 */
	public void addLocal(String par1Obj, String par2Str) {
		LanguageRegistry.instance().addStringLocalization(par1Obj, "en_US", par2Str);
	}
	
	/**
	 * Adds a bulgarian localization to the Language Registry.
	 * 
	 *  @param par1Obj The object to add, ex.: "tile.adamantOre.name"
	 *  @param par2Str The name to add.
	 */
	public void addLocalBG(String par1Obj, String par2Str) {
		LanguageRegistry.instance().addStringLocalization(par1Obj, "bg_BG", par2Str);
	}
	
	public void addOreDictRecipeShaped(ItemStack par1Result, Object... par2Recipe){
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(par1Result, par2Recipe));
	}
	
	public void addOreDictRecipeShapeless(ItemStack par1Result, Object... par2Recipe){
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(par1Result, par2Recipe));
	}
	
	public EntityPlayer getPlayerInstance(){
		return null;
	}
	
	public void checkForgeVersion() {
		ForgeVersion fv = new ForgeVersion();
		EnumForgeVersionState state;
		
		if(fv.getMajorVersion() != 6){
			state = EnumForgeVersionState.MAJOR_NOT_MATCH;
			throw new RuntimeException("Minecraft Forge's major version is invalid.");
		}else if(fv.getRevisionVersion() != 1){
			state = EnumForgeVersionState.REVISION_NOT_MATCH;
			throw new RuntimeException("Minecraft Forge's revision version is invalid.");
		}else if(fv.getBuildVersion() < 410){
			state = EnumForgeVersionState.BUILD_TOO_LOW;
			throw new RuntimeException("Minecraft Forge's build version is invalid.");
		}else if(fv.getMinorVersion() != 4){
			state = EnumForgeVersionState.MINOR_NOT_MATCH;
			FMLLog.warning("[Telicraft] Minecraft Forge's minor version is not the expected one. While this may not have any effect, it can break Telicraft.", new Object[0]);
		}else{
			state = EnumForgeVersionState.OKAY;
			FMLLog.finer("[Telicraft] Minecraft Forge version check complete, all's good!", new Object[0]);
			return;
		}
	}
}