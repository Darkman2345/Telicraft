package telinc.telicraft.neiIntegration;

import codechicken.nei.MultiItemRange;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;

import telinc.telicraft.TelicraftMain;
import telinc.telicraft.client.gui.inventory.GuiACT;
import telinc.telicraft.lib.MainReferences;

public class NEITelicraftConfig implements IConfigureNEI {
    @Override
    public void loadConfig() {
        API.hideItem(TelicraftMain.pepperCrop.blockID);
        API.hideItem(TelicraftMain.tomatoCrop.blockID);
        
        this.addRecipes();
        this.addSubsets();
    }

    private void addRecipes() {
        API.registerRecipeHandler(new SharpenerRecipeHandler());
        API.registerUsageHandler(new SharpenerRecipeHandler());
        
        API.registerRecipeHandler(new ACTShapedRecipeHandler());
        API.registerUsageHandler(new ACTShapedRecipeHandler());
        API.registerGuiOverlayHandler(GuiACT.class, new ACTOverlayHandler(), "craftingAdvanced");
    }

    private void addSubsets() {
        MultiItemRange blocksDeco = new MultiItemRange();
        blocksDeco.add(TelicraftMain.adamantBlk);
        blocksDeco.add(TelicraftMain.megaAdamant);
        blocksDeco.add(TelicraftMain.redstoneBlk);
        blocksDeco.add(TelicraftMain.crackedNetherrack);
        blocksDeco.add(TelicraftMain.clearGlass);
        blocksDeco.add(TelicraftMain.darkEndStone);
        blocksDeco.add(TelicraftMain.squill);
        
        MultiItemRange blocksWorldGen = new MultiItemRange();
        blocksWorldGen.add(TelicraftMain.adamantOre);
        blocksWorldGen.add(TelicraftMain.meteorBlock);
        
        MultiItemRange blocksMachine = new MultiItemRange();
        blocksMachine.add(TelicraftMain.sharpener);
        blocksMachine.add(TelicraftMain.adamantFurnace);
        blocksMachine.add(TelicraftMain.act);
        blocksMachine.add(TelicraftMain.alarm);
        
        MultiItemRange blocksGeneric = new MultiItemRange();
        blocksGeneric.add(TelicraftMain.meteorBomb);
        
        MultiItemRange itemsResource = new MultiItemRange();
        itemsResource.add(TelicraftMain.adamant);
        itemsResource.add(TelicraftMain.megastick);
        itemsResource.add(TelicraftMain.fuel);
        itemsResource.add(TelicraftMain.ketchup);
        itemsResource.add(TelicraftMain.meteorDust);
        
        MultiItemRange itemsToolsWeapons = new MultiItemRange();
        itemsToolsWeapons.add(TelicraftMain.excalibur);
        itemsToolsWeapons.add(TelicraftMain.adamPick);
        itemsToolsWeapons.add(TelicraftMain.adamShovel);
        itemsToolsWeapons.add(TelicraftMain.adamAxe);
        itemsToolsWeapons.add(TelicraftMain.tools);
        itemsToolsWeapons.add(TelicraftMain.sharpeningTool);
        
        MultiItemRange itemsArmour = new MultiItemRange();
        itemsArmour.add(TelicraftMain.adamantHelmet);
        itemsArmour.add(TelicraftMain.adamantChest);
        itemsArmour.add(TelicraftMain.adamantLegs);
        itemsArmour.add(TelicraftMain.adamantBoots);
        
        MultiItemRange itemsFood = new MultiItemRange();
        itemsFood.add(TelicraftMain.pizza);
        
        MultiItemRange itemsPotion = new MultiItemRange();
        itemsPotion.add(TelicraftMain.potionCustom);
        
        MultiItemRange itemsGeneric = new MultiItemRange();
        itemsGeneric.add(TelicraftMain.emerg);
        itemsGeneric.add(TelicraftMain.dough);
        itemsGeneric.add(TelicraftMain.pepper);
        itemsGeneric.add(TelicraftMain.tomato);
        
        API.addSetRange("Telicraft.Blocks.Decorative", blocksDeco);
        API.addSetRange("Telicraft.Blocks.World Generation", blocksWorldGen);
        API.addSetRange("Telicraft.Blocks.Machines", blocksMachine);
        API.addSetRange("Telicraft.Blocks", blocksGeneric);
        API.addSetRange("Telicraft.Items.Resources", itemsResource);
        API.addSetRange("Telicraft.Items.Tools & Weapons", itemsToolsWeapons);
        API.addSetRange("Telicraft.Items.Armour", itemsArmour);
        API.addSetRange("Telicraft.Items.Food", itemsFood);
        API.addSetRange("Telicraft.Items.Potions", itemsPotion);
        API.addSetRange("Telicraft.Items", itemsGeneric);
    }

    @Override
    public String getName() {
        return MainReferences.MOD_NAME;
    }

    @Override
    public String getVersion() {
        return MainReferences.MOD_VERSION;
    }
}