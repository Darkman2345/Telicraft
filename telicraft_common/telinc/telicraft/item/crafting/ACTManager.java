package telinc.telicraft.item.crafting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipesMapCloning;
import net.minecraft.item.crafting.RecipesMapExtending;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.world.World;


public class ACTManager {
	/** The static instance of this class */
	private static final ACTManager instance = new ACTManager();

	/** A list of all the recipes added */
	private List recipes = new ArrayList();

	/**
	 * Returns the static instance of this class
	 */
	public static final ACTManager getInstance() {
		return instance;
	}

	private ACTManager() {
		(new ACTRecipeVanilla()).addRecipes(this);
		
        this.recipes.add(new RecipesMapCloning());
        this.recipes.add(new RecipesMapExtending());
	}

	/**
	 * Adds a recipe. See spreadsheet on first page for details.
	 */
	public void addRecipe(ItemStack par1ItemStack, Object... par2ArrayOfObj) {
		String var3 = "";
		int var4 = 0;
		int var5 = 0;
		int var6 = 0;
		int var9;

		if (par2ArrayOfObj[var4] instanceof String[]) {
			String[] var7 = (String[]) ((String[]) par2ArrayOfObj[var4++]);
			String[] var8 = var7;
			var9 = var7.length;

			for (int var10 = 0; var10 < var9; ++var10) {
				String var11 = var8[var10];
				++var6;
				var5 = var11.length();
				var3 = var3 + var11;
			}
		} else {
			while (par2ArrayOfObj[var4] instanceof String) {
				String var13 = (String) par2ArrayOfObj[var4++];
				++var6;
				var5 = var13.length();
				var3 = var3 + var13;
			}
		}

		HashMap var14;

		for (var14 = new HashMap(); var4 < par2ArrayOfObj.length; var4 += 2) {
			Character var16 = (Character) par2ArrayOfObj[var4];
			ItemStack var17 = null;

			if (par2ArrayOfObj[var4 + 1] instanceof Item) {
				var17 = new ItemStack((Item) par2ArrayOfObj[var4 + 1]);
			} else if (par2ArrayOfObj[var4 + 1] instanceof Block) {
				var17 = new ItemStack((Block) par2ArrayOfObj[var4 + 1], 1, -1);
			} else if (par2ArrayOfObj[var4 + 1] instanceof ItemStack) {
				var17 = (ItemStack) par2ArrayOfObj[var4 + 1];
			}

			var14.put(var16, var17);
		}

		ItemStack[] var15 = new ItemStack[var5 * var6];

		for (var9 = 0; var9 < var5 * var6; ++var9) {
			char var18 = var3.charAt(var9);

			if (var14.containsKey(Character.valueOf(var18))) {
				var15[var9] = ((ItemStack) var14.get(Character.valueOf(var18)))
						.copy();
			} else {
				var15[var9] = null;
			}
		}

		this.recipes.add(new ShapedRecipes(var5, var6, var15, par1ItemStack));
	}

	public void addShapelessRecipe(ItemStack par1ItemStack,
			Object... par2ArrayOfObj) {
		ArrayList var3 = new ArrayList();
		Object[] var4 = par2ArrayOfObj;
		int var5 = par2ArrayOfObj.length;

		for (int var6 = 0; var6 < var5; ++var6) {
			Object var7 = var4[var6];

			if (var7 instanceof ItemStack) {
				var3.add(((ItemStack) var7).copy());
			} else if (var7 instanceof Item) {
				var3.add(new ItemStack((Item) var7));
			} else {
				if (!(var7 instanceof Block)) {
					throw new RuntimeException("[Telicraft] Invalid shapelesss recipe for Advanced Crafting Table!");
				}

				var3.add(new ItemStack((Block) var7));
			}
		}

		this.recipes.add(new ShapelessRecipes(par1ItemStack, var3));
	}

	public ItemStack findMatchingRecipe(InventoryCrafting par1InventoryCrafting,
			World par2World) {
		int var3 = 0;
		ItemStack var4 = null;
		ItemStack var5 = null;

		for (int var6 = 0; var6 < par1InventoryCrafting.getSizeInventory(); ++var6) {
			ItemStack var7 = par1InventoryCrafting.getStackInSlot(var6);

			if (var7 != null) {
				if (var3 == 0) {
					var4 = var7;
				}

				if (var3 == 1) {
					var5 = var7;
				}

				++var3;
			}
		}

		if (var3 == 2 && var4.itemID == var5.itemID && var4.stackSize == 1
				&& var5.stackSize == 1
				&& Item.itemsList[var4.itemID].isRepairable()) {
			Item var13 = Item.itemsList[var4.itemID];
			int var14 = var13.getMaxDamage() - var4.getItemDamageForDisplay();
			int var8 = var13.getMaxDamage() - var5.getItemDamageForDisplay();
			int var9 = var14 + var8 + var13.getMaxDamage() * 5 / 100;
			int var10 = var13.getMaxDamage() - var9;

			if (var10 < 0) {
				var10 = 0;
			}

			return new ItemStack(var4.itemID, 1, var10);
		} else {
			Iterator var11 = this.recipes.iterator();
			IRecipe var12;

			do {
				if (!var11.hasNext()) {
					return null;
				}

				var12 = (IRecipe) var11.next();
			} while (!var12.matches(par1InventoryCrafting, par2World));

			return var12.getCraftingResult(par1InventoryCrafting);
		}
	}

	/**
	 * returns the List<> of all recipes
	 */
	public List getRecipeList() {
		return this.recipes;
	}
}
