package telinc.telicraft.common.reference;

import java.util.List;
import java.util.Map;

import telinc.telicraft.common.gui.SharpenerRecipes;
import telinc.telicraft.common.gui.act.ACTManager;

/**
 * Just used to return a few payload values.
 */
public class TelicraftManager {
	
	/**
	 * Get the Sharpener's recipes.
	 * 
	 * @return A map containing a list with the recipes.
	 */
	public static Map getSharpenerRecipes(){
		return SharpenerRecipes.instance().getRecipeList();
	}
	
	/**
	 * Get the Advanced Crafting Table's recipes.
	 * 
	 * @return A list containing the table's recipes.
	 */
	public static List getACTRecipesList(){
		return ACTManager.getInstance().getRecipeList();
	}
	
	/**
	 * Get the Advanced Crafting Table's recipes in a Map form.
	 * 
	 * @return A map, casted from a list containing the table's recipes.
	 */
	public static Map getACTRecipesMap(){
		return (Map)ACTManager.getInstance().getRecipeList();
	}
}