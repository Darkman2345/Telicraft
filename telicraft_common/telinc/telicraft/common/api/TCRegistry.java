package telinc.telicraft.common.api;

import java.util.List;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IRecipe;
import net.minecraft.src.ItemStack;

import com.google.common.collect.Lists;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.ICraftingHandler;
import cpw.mods.fml.common.Loader;

/** Telicraft's {@linkplain cpw.mods.fml.common.registry.GameRegistry}. 
 * @author Telinc1*/
public final class TCRegistry {
	
	private static List<ISharpenerHandler> sharpenerHandlers = Lists.newArrayList();
	
	public static boolean isTelicraftLoaded(){
		return Loader.isModLoaded("telicraft");
	}
	
	
	/** Adds a sharpening recipe to the Sharpener. 
	 * @param par1Input The input item. Example: TelicraftMain.flatExca.shiftedIndex
	 * @param par2Output The output item. Example: new ItemStack(TelicraftMain.excalibur, 1)
	 * @param par3XP The experience points you gain.
	 * */
	public static void addSharpenerRecipe(int par1Input, ItemStack par2Output,
			float par3XP) {
		try{
			if(isTelicraftLoaded()){
				telinc.telicraft.common.gui.SharpenerRecipes.instance().addRecipe(par1Input, par2Output, par3XP);
			}else{
				FMLLog.finer("[Telicraft API] Telicraft not detected, ignoring new sharpener recipe request.", new Object[0]);
				return;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/** Adds a sharpening recipe to the Sharpener. 
	 * @param par1Input The input item. Example: TelicraftMain.flatExca.shiftedIndex
	 * @param par2Output The output item. Example: new ItemStack(TelicraftMain.excalibur, 1)
	 * @param par3XP The experience points you gain.
	 * */
	public static void addSharpenerRecipe(int par1Input, int par2Metadata, ItemStack par3Output) {
		try{
			if(isTelicraftLoaded()){
				telinc.telicraft.common.gui.SharpenerRecipes.instance().addRecipe(par1Input, par2Metadata, par3Output);
			}else{
				FMLLog.finer("[Telicraft API] Telicraft not detected, ignoring new sharpener recipe request.", new Object[0]);
				return;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void addACTRecipe(ItemStack output, Object... params) {
		try{
			if(isTelicraftLoaded()){
				telinc.telicraft.common.gui.act.ACTManager.getInstance().addRecipe(output, params);
			}else{
				FMLLog.finer("[Telicraft API] Telicraft not detected, ignoring new Advanced Crafting Table recipe request.", new Object[0]);
				return;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void addACTRecipe(IRecipe recipe) {
		try{
			if(isTelicraftLoaded()){
				telinc.telicraft.common.gui.act.ACTManager.getInstance().getRecipeList().add(recipe);
			}else{
				FMLLog.finer("[Telicraft API] Telicraft not detected, ignoring new Advanced Crafting Table recipe request.", new Object[0]);
				return;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void addShapelessACTRecipe(ItemStack output, Object... params) {
		try{
			if(isTelicraftLoaded()){
				telinc.telicraft.common.gui.act.ACTManager.getInstance().addShapelessRecipe(output, params);
			}else{
				FMLLog.finer("[Telicraft API] Telicraft not detected, ignoring new Advanced Crafting Table recipe request.", new Object[0]);
				return;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Register a sharpener handler.
	 * 
	 * @param handler An instance of ISharpenerHandler.
	 */
	public static void registerSharpenerHandler(ISharpenerHandler handler){
		sharpenerHandlers.add(handler);
	}
	
	/**
	 * Call to simulate an item being taken out of a Sharpener's output slot.
	 * 
	 * @param player The player instance.
	 * @param item The item stack taken out.
	 */
	public static void onItemSharpened(EntityPlayer player, ItemStack item){
        for (ISharpenerHandler handler : sharpenerHandlers){
            handler.onItemTakenOut(player, item);
        }
	}

}