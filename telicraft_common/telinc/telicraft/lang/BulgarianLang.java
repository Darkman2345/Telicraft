package telinc.telicraft.lang;

import telinc.telicraft.TelicraftMain;
import telinc.telicraft.proxy.TelicraftCommonEngine;

public class BulgarianLang {
	
	private static TelicraftCommonEngine engine = TelicraftMain.engine;
	
	public static final String adamantOreName = "\u0421\u0442\u043e\u043c\u0430\u043d\u043d\u0430 " +
			"\u0440\u0443\u0434\u0430";
	
	public static final String adamantBlockName = "\u0421\u0442\u043e\u043c\u0430\u043d\u0435\u043d " +
			"\u0431\u043b\u043e\u043a";
	
	public static final String megaAdamantBlockName = "\u041c\u0435\u0433\u0430 " +
			"\u0421\u0442\u043e\u043c\u0430\u043d\u0435\u043d " +
			"\u0431\u043b\u043e\u043a";
	
	public static final String crackedNetherrackName = "\u041d\u0430\u043f\u0443\u043a\u0430\u043d " +
			"\u043a\u0430\u043c\u044a\u043a " +
			"\u043e\u0442 " +
			"\u0410\u0434\u0430";
	
	public static final String clearGlassName = "\u0427\u0438\u0441\u0442\u043e " +
			"\u0441\u0442\u044a\u043a\u043b\u043e";
	
	public static final String sharpenerName = "\u041c\u0430\u0448\u0438\u043d\u0430 " +
			"\u0437\u0430 " +
			"\u043f\u043e\u0434\u043e\u0441\u0442\u0440\u044f\u043d\u0435";
	
	public static final String adamantFurnaceName = "\u0421\u0442\u043e\u043c\u0430\u043d\u043d\u0430 " +
			"\u0444\u0443\u0440\u043d\u0430";
	
	public static final String actName = "\u0421\u044a\u0432\u0440\u0435\u043c\u0435\u043d\u043d\u0430 " +
			"\u0440\u0430\u0431\u043e\u0442\u043d\u0430 " +
			"\u043c\u0430\u0441\u0430";
	
	public static final String guiHeat = "\u0422\u043e\u043f\u043b\u0438\u043d\u0430";
	
	public static void addLocalizationsGeneral(){
		engine.addLocalBG("container.sharpener", sharpenerName);
		engine.addLocalBG("container.furnace.adamant", adamantFurnaceName);
		engine.addLocalBG("container.crafting.act", actName);
		engine.addLocalBG("gui.telicraft.heat", guiHeat);
	}
	
	public static void addLocalizationsBlocks(){
    	engine.addLocalBG("tile.adamantOre.name", adamantOreName);
    	engine.addLocalBG("tile.adamantBlk.name", adamantBlockName);
    	engine.addLocalBG("tile.megaAdamant.name", megaAdamantBlockName);
    	engine.addLocalBG("tile.crackedNetherrack.name", crackedNetherrackName);
    	engine.addLocalBG("tile.clearGlass.name", clearGlassName);
    	engine.addLocalBG("tile.sharpener.name", sharpenerName);
    	engine.addLocalBG("tile.adamantFurnace.name", adamantFurnaceName);
    	engine.addLocalBG("tile.adamantFurnaceActive.name", adamantFurnaceName);
    	engine.addLocalBG("tile.act.name", actName);
	}
}