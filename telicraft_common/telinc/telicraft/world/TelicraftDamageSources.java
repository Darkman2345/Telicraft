package telinc.telicraft.world;

import telinc.telincCore.CustomDS;

public class TelicraftDamageSources extends CustomDS {
	
	public static TelicraftDamageSources suicide = (TelicraftDamageSources) (new TelicraftDamageSources("suicide")).setCanHarmInCreative().setIgnoresArmour();
	public static TelicraftDamageSources stone = (TelicraftDamageSources) (new TelicraftDamageSources("stone")).setIgnoresArmour();
	public static TelicraftDamageSources petrify = (TelicraftDamageSources) (new TelicraftDamageSources("petrify")).setIgnoresArmour();
	public static TelicraftDamageSources random = (TelicraftDamageSources) (new TelicraftDamageSources("random")).setIgnoresArmour();
	public static TelicraftDamageSources meteorBomb = (TelicraftDamageSources)(new TelicraftDamageSources("meteorBomb")).setIgnoresArmour();
	
	protected TelicraftDamageSources(String par1Str) {
		super(par1Str);
	}
	
}