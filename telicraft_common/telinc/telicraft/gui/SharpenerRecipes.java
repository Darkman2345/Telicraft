package telinc.telicraft.gui;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.ItemStack;

public class SharpenerRecipes {
	private static final SharpenerRecipes base = new SharpenerRecipes();

	/** The list of smelting results. */
	private Map recipeList = new HashMap();
	private Map experienceList = new HashMap();
	private Map metaRecipeList = new HashMap();

	/**
	 * Used to call methods addSmelting and getSmeltingResult.
	 */
	public static final SharpenerRecipes instance() {
		return base;
	}
	
	/** All recipes are to be added via the API. 
	 * @see telinc.telicraft.api.TCRegistry
	 * */
	private SharpenerRecipes() {}
	
	/**
	 * Adds a smelting recipe.
	 */
	public void addRecipe(int par1, ItemStack par2ItemStack, float par3) {
		this.recipeList.put(Integer.valueOf(par1), par2ItemStack);
		this.experienceList.put(Integer.valueOf(par2ItemStack.itemID),
				Float.valueOf(par3));
	}

	/**
	 * Returns the smelting result of an item. Deprecated in favor of a metadata
	 * sensitive version
	 */
	@Deprecated
	public ItemStack getRecipeResult(int par1) {
		return (ItemStack) this.recipeList.get(Integer.valueOf(par1));
	}

	public Map getRecipeList() {
		return this.recipeList;
	}
	
    public float getExperience(int par1)
    {
        return this.experienceList.containsKey(Integer.valueOf(par1)) ? ((Float)this.experienceList.get(Integer.valueOf(par1))).floatValue() : 0.0F;
    }
	
	public float func_77601_c(int par1) {
		return this.experienceList.containsKey(Integer.valueOf(par1)) ? ((Float) this.experienceList
				.get(Integer.valueOf(par1))).floatValue() : 0.0F;
	}

	/**
	 * Add a metadata-sensitive furnace recipe
	 * 
	 * @param itemID
	 *            The Item ID
	 * @param metadata
	 *            The Item Metadata
	 * @param itemstack
	 *            The ItemStack for the result
	 */
	public void addRecipe(int itemID, int metadata, ItemStack itemstack) {
		metaRecipeList.put(Arrays.asList(itemID, metadata), itemstack);
	}

	/**
	 * Used to get the resulting ItemStack form a source ItemStack
	 * 
	 * @param item
	 *            The Source ItemStack
	 * @return The result ItemStack
	 */
	public ItemStack getRecipeResult(ItemStack item) {
		if (item == null) {
			return null;
		}
		ItemStack ret = (ItemStack) metaRecipeList.get(Arrays.asList(
				item.itemID, item.getItemDamage()));
		if (ret != null) {
			return ret;
		}
		return (ItemStack) recipeList.get(Integer.valueOf(item.itemID));
	}
}
