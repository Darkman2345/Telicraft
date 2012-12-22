package telinc.telicraft.neiIntegration;

import java.awt.Rectangle;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeSet;

import codechicken.core.ReflectionManager;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.forge.GuiContainerManager;
import codechicken.nei.recipe.TemplateRecipeHandler;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import telinc.telicraft.client.gui.inventory.GuiSharpener;
import telinc.telicraft.item.crafting.SharpenerRecipes;
import telinc.telicraft.lib.TextureReferences;
import telinc.telicraft.tileentity.TileSharpener;

public class SharpenerRecipeHandler extends TemplateRecipeHandler {
    public class SharpeningPair extends CachedRecipe {
        public SharpeningPair(ItemStack ingred, ItemStack result) {
            ingred.stackSize = 1;
            this.ingred = new PositionedStack(ingred, 51, 6);
            this.result = new PositionedStack(result, 111, 24);
        }

        public PositionedStack getIngredient() {
            int cycle = cycleticks / 48;
            if(ingred.item.getItemDamage() == -1){
                PositionedStack stack = ingred.copy();
                int maxDamage = 0;
                do{
                    maxDamage++;
                    stack.item.setItemDamage(maxDamage);
                }while(NEIClientUtils.isValidItem(stack.item));

                stack.item.setItemDamage(cycle % maxDamage);
                return stack;
            }
            return ingred;
        }

        public PositionedStack getResult() {
            return result;
        }

        public PositionedStack getOtherStack() {
            return atools.get((cycleticks / 48) % atools.size()).stack;
        }

        PositionedStack ingred;
        PositionedStack result;
    }

    public static class ToolPair {
        public ToolPair(ItemStack ingred, int sharpness) {
            this.stack = new PositionedStack(ingred, 51, 42);
            this.sharpness = sharpness;
        }

        public PositionedStack stack;
        public int sharpness;
    }

    public static ArrayList<ToolPair> atools;
    public static TreeSet<Integer> etools;

    @Override
    public void loadTransferRects() {
        transferRects.add(new RecipeTransferRect(new Rectangle(50, 23, 18, 18), "tools"));
        transferRects.add(new RecipeTransferRect(new Rectangle(74, 23, 24, 18), "sharpening"));
    }

    @Override
    public Class<? extends GuiContainer> getGuiClass() {
        return GuiSharpener.class;
    }

    @Override
    public String getRecipeName() {
        return "Sharpening";
    }

    @Override
    @SuppressWarnings("unchecked")
    public void loadCraftingRecipes(String outputId, Object... results) {
        if(outputId.equals("sharpening") && getClass() == SharpenerRecipeHandler.class)// don't
                                                                                     // want
                                                                                     // subclasses
                                                                                     // getting
                                                                                     // a
                                                                                     // hold
                                                                                     // of
                                                                                     // this
        {
            HashMap<Integer, ItemStack> recipes;
            HashMap<List<Integer>, ItemStack> metarecipes = null;
            try{
                recipes = (HashMap<Integer, ItemStack>)ReflectionManager.getField(SharpenerRecipes.class, HashMap.class, SharpenerRecipes.instance(), 1);
                try{
                    metarecipes = ReflectionManager.getField(SharpenerRecipes.class, HashMap.class, SharpenerRecipes.instance(), 3);
                }catch(ArrayIndexOutOfBoundsException e){}
            }catch(Exception e){
                e.printStackTrace();
                return;
            }
            for(Entry<Integer, ItemStack> recipe : recipes.entrySet()){
                ItemStack item = recipe.getValue();
                arecipes.add(new SharpeningPair(new ItemStack(recipe.getKey(), 1, -1), item));
            }
            if(metarecipes == null) return;
            for(Entry<List<Integer>, ItemStack> recipe : metarecipes.entrySet()){
                ItemStack item = recipe.getValue();
                arecipes.add(new SharpeningPair(new ItemStack(recipe.getKey().get(0), 1, recipe.getKey().get(1)), item));
            }
        }else{
            super.loadCraftingRecipes(outputId, results);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void loadCraftingRecipes(ItemStack result) {
        HashMap<Integer, ItemStack> recipes;
        HashMap<List<Integer>, ItemStack> metarecipes = null;
        try{
            recipes = (HashMap<Integer, ItemStack>)ReflectionManager.getField(SharpenerRecipes.class, HashMap.class, SharpenerRecipes.instance(), 1);
            try{
                metarecipes = ReflectionManager.getField(SharpenerRecipes.class, HashMap.class, SharpenerRecipes.instance(), 3);
            }catch(ArrayIndexOutOfBoundsException e){}
        }catch(Exception e){
            e.printStackTrace();
            return;
        }
        for(Entry<Integer, ItemStack> recipe : recipes.entrySet()){
            ItemStack item = recipe.getValue();
            if(NEIClientUtils.areStacksSameType(item, result)){
                arecipes.add(new SharpeningPair(new ItemStack(recipe.getKey(), 1, -1), item));
            }
        }
        if(metarecipes == null) return;
        for(Entry<List<Integer>, ItemStack> recipe : metarecipes.entrySet()){
            ItemStack item = recipe.getValue();
            if(NEIClientUtils.areStacksSameType(item, result)){
                arecipes.add(new SharpeningPair(new ItemStack(recipe.getKey().get(0), 1, recipe.getKey().get(1)), item));
            }
        }
    }

    @Override
    public void loadUsageRecipes(String inputId, Object... ingredients) {
        if(inputId.equals("tools") && getClass() == SharpenerRecipeHandler.class)// don't
                                                                                // want
                                                                                // subclasses
                                                                                // getting
                                                                                // a
                                                                                // hold
                                                                                // of
                                                                                // this
        {
            loadCraftingRecipes("sharpening");
        }else{
            super.loadUsageRecipes(inputId, ingredients);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void loadUsageRecipes(ItemStack ingredient) {
        HashMap<Integer, ItemStack> recipes;
        HashMap<List<Integer>, ItemStack> metarecipes = null;
        try{
            recipes = (HashMap<Integer, ItemStack>)ReflectionManager.getField(SharpenerRecipes.class, HashMap.class, SharpenerRecipes.instance(), 1);
            try{
                metarecipes = (HashMap<List<Integer>, ItemStack>)ReflectionManager.getField(SharpenerRecipes.class, HashMap.class, SharpenerRecipes.instance(), 3);
            }catch(ArrayIndexOutOfBoundsException e){}
        }catch(Exception e){
            e.printStackTrace();
            return;
        }
        for(Entry<Integer, ItemStack> recipe : recipes.entrySet()){
            ItemStack item = recipe.getValue();
            if(ingredient.itemID == recipe.getKey()){
                arecipes.add(new SharpeningPair(ingredient, item));
            }
        }
        if(metarecipes == null) return;
        for(Entry<List<Integer>, ItemStack> recipe : metarecipes.entrySet()){
            ItemStack item = recipe.getValue();
            if(ingredient.itemID == recipe.getKey().get(0) && ingredient.getItemDamage() == recipe.getKey().get(1)){
                arecipes.add(new SharpeningPair(ingredient, item));
            }
        }
    }

    @Override
    public String getGuiTexture() {
        return "/gui/furnace.png";
    }

    public void drawExtras(GuiContainerManager gui, int recipe) {
        drawProgressBar(gui, 74, 23, 176, 14, 24, 16, 48, 0);
    }

    private static void removetools() {
        etools = new TreeSet<Integer>();
    }

    private static void findTools() {
        Method getsharpness;
        try{
            getsharpness = TileSharpener.class.getDeclaredMethod("getItemSharpness", ItemStack.class);
            getsharpness.setAccessible(true);
        }catch(SecurityException e){
            e.printStackTrace();
            return;
        }catch(NoSuchMethodException e){
            try{
                getsharpness = TileSharpener.class.getDeclaredMethod("a", ItemStack.class);
                getsharpness.setAccessible(true);
            }catch(SecurityException e1){
                e1.printStackTrace();
                return;
            }catch(NoSuchMethodException e1){
                e1.printStackTrace();
                return;
            }
        }

        TileSharpener asharpener = new TileSharpener();
        atools = new ArrayList<ToolPair>();
        for(Item item : Item.itemsList){
            if(item != null && !etools.contains(item.shiftedIndex)){
                int sharpness;
                try{
                    sharpness = (Integer)getsharpness.invoke(asharpener, new ItemStack(item, 1));
                }catch(Exception e){
                    e.printStackTrace();
                    continue;
                }
                if(sharpness > 0){
                    atools.add(new ToolPair(new ItemStack(item, 1), sharpness));
                    for(int i = 1; i < 16; i++){
                        ItemStack stack = new ItemStack(item, 1, i);
                        if(!NEIClientUtils.isValidItem(stack)){
                            break;
                        }
                        atools.add(new ToolPair(stack, sharpness));
                    }
                }
            }
        }
    }

    @Override
    public String getOverlayIdentifier() {
        return "sharpening";
    }

    static{
        removetools();
        findTools();
    }
}
