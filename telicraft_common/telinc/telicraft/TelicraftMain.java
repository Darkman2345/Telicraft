package telinc.telicraft;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.DungeonHooks;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import telinc.telicraft.api.TCRegistry;
import telinc.telicraft.bcIntegration.TelicraftBCIntegration;
import telinc.telicraft.block.BlockACT;
import telinc.telicraft.block.BlockAdamantBlk;
import telinc.telicraft.block.BlockAdamantFurnace;
import telinc.telicraft.block.BlockAdamantOre;
import telinc.telicraft.block.BlockAlarm;
import telinc.telicraft.block.BlockClearGlass;
import telinc.telicraft.block.BlockCrNether;
import telinc.telicraft.block.BlockDarkEndStone;
import telinc.telicraft.block.BlockMegaAdamant;
import telinc.telicraft.block.BlockMeteor;
import telinc.telicraft.block.BlockMeteorBomb;
import telinc.telicraft.block.BlockPepperCrop;
import telinc.telicraft.block.BlockRedstone;
import telinc.telicraft.block.BlockSharpener;
import telinc.telicraft.block.BlockSquill;
import telinc.telicraft.block.BlockTomatoCrop;
import telinc.telicraft.client.TelicraftClientPacketHandler;
import telinc.telicraft.creativetab.CreativeTabTelicraft;
import telinc.telicraft.entity.EntityMeteorBombPrimed;
import telinc.telicraft.entity.EntityPetrify;
import telinc.telicraft.handler.TelicraftCraftingHandler;
import telinc.telicraft.handler.TelicraftEventHandler;
import telinc.telicraft.handler.TelicraftFuelHandler;
import telinc.telicraft.handler.TelicraftGuiHandler;
import telinc.telicraft.handler.TelicraftServerPacketHandler;
import telinc.telicraft.handler.TelicraftSharpenerHandler;
import telinc.telicraft.item.ItemAdamantAxe;
import telinc.telicraft.item.ItemAdamantBoots;
import telinc.telicraft.item.ItemAdamantChest;
import telinc.telicraft.item.ItemAdamantHelmet;
import telinc.telicraft.item.ItemAdamantLegs;
import telinc.telicraft.item.ItemAdamantPick;
import telinc.telicraft.item.ItemAdamantSpade;
import telinc.telicraft.item.ItemEmergnecy;
import telinc.telicraft.item.ItemExcalibur;
import telinc.telicraft.item.ItemPepper;
import telinc.telicraft.item.ItemPizza;
import telinc.telicraft.item.ItemSharpTool;
import telinc.telicraft.item.ItemTelicraftPotion;
import telinc.telicraft.item.ItemTelicraftTools;
import telinc.telicraft.item.ItemTomato;
import telinc.telicraft.lang.BulgarianLang;
import telinc.telicraft.lib.AchievementReferences;
import telinc.telicraft.lib.DeathMessages;
import telinc.telicraft.lib.MainReferences;
import telinc.telicraft.lib.ProxyReferences;
import telinc.telicraft.lib.TextureReferences;
import telinc.telicraft.proxy.TelicraftCommonEngine;
import telinc.telicraft.proxy.TelicraftCommonProxy;
import telinc.telicraft.tileentity.TileEntityAdamantFurnace;
import telinc.telicraft.tileentity.TileEntityAlarm;
import telinc.telicraft.tileentity.TileEntitySharpener;
import telinc.telicraft.world.gen.TelicraftWorldGenerator;
import telinc.telincCore.ItemUseless;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid=MainReferences.MOD_ID, name=MainReferences.MOD_NAME, version=MainReferences.MOD_VERSION)

@NetworkMod(clientSideRequired=true, serverSideRequired=false, clientPacketHandlerSpec = @SidedPacketHandler(channels = {"Telicraft" }, packetHandler = TelicraftClientPacketHandler.class),
serverPacketHandlerSpec = @SidedPacketHandler(channels = {"Telicraft" }, packetHandler = TelicraftServerPacketHandler.class))

/** Main file for Telicraft.
 * @author Telinc1
 * */
public class TelicraftMain {
	
	///////////////////
	//Block Variables//
	///////////////////
	
	public static Block adamantOre, adamantBlk, megaAdamant, redstoneBlk;
	
	public static Block crackedNetherrack, clearGlass, darkEndStone, sharpener, meteorBomb;
	
	public static Block tomatoCrop, pepperCrop, meteorBlock;
	
	public static Block adamantFurnace, adamantFurnaceActive, alarm, act;
	
	public static BlockFlower squill;
	
	//////////////////
	//Item Variables//
	//////////////////
	
	public static Item excalibur, adamShovel, adamAxe, adamPick;
	
	public static Item adamant, megastick, fuel, emerg;
	
	public static Item tomato, pepper, meteorDust;
	
	public static Item pizza;
	
	public static Item sharpeningTool, tools, dough, ketchup, potionCustom;
	
	public static Item adamantHelmet, adamantChest, adamantLegs, adamantBoots;
	
	/////////////////////////
	//Achievement Variables//
	/////////////////////////
	
	public static Achievement blueStuff, kingArthur, notForKids, youMad, deliciousPizza, griefer;
	
	////////////
	//Textures//
	////////////
	
	public static String itemsPng = TextureReferences.ITEM_TEXTURE;
	
	////////////////
	//Creative Tab//
	////////////////
	public static CreativeTabTelicraft tabTelicraft = (CreativeTabTelicraft) new CreativeTabTelicraft().setBackgroundImageName("/telicraftCT.png");
	
	////////////
	//Instance//
	////////////
	
	@Instance(MainReferences.MOD_ID)
	public static TelicraftMain instance;
	
	/////////
	//Proxy//
	/////////
	
	@SidedProxy(clientSide=ProxyReferences.CLIENT_PROXY, serverSide=ProxyReferences.SERVER_PROXY)
	public static TelicraftCommonProxy proxy;
	
	@SidedProxy(clientSide=ProxyReferences.CLIENT_ENGINE, serverSide=ProxyReferences.SERVER_ENGINE)
	public static TelicraftCommonEngine engine;
	
	/////////////////////////////
	//Pre-Initialization Method//
	/////////////////////////////
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		
		//Check Forge Version
		engine.checkForgeVersion();
		
		//Event Handler
		MinecraftForge.EVENT_BUS.register(new TelicraftEventHandler());
		
		//Config Files
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		
		config.load();
		
		// Blocks
		adOreID = config.getBlock("adamantOreID", 256).getInt();
		adBlkID = config.getBlock("adamantBlockID", 257).getInt();
		megaAdID = config.getBlock("megaAdamantBlockID", 258).getInt();
		crackedNetherrackID = config.getBlock("crackedNetherrackID", 259).getInt();
		clearGlassID = config.getBlock("clearGlassID", 230).getInt();
		sharpenerID = config.getBlock("sharpenerID", 231).getInt();
		tomatoCropID = config.getBlock("tomatoCropID", 232).getInt();
		darkEndStoneID = config.getBlock("darkEndStoneID", 235).getInt();
		squillID = config.getBlock("squillID", 233).getInt();
		pepperCropID = config.getBlock("pepperCropID", 234).getInt();
		meteorBombID = config.getBlock("meteorBombID", 238).getInt();
		adamantFurnaceID = config.getBlock("adamantFurnaceID", 236).getInt();
		meteorBlockID = config.getBlock("meteorBlockID", 237).getInt();
		alarmID = config.getBlock("alarmID", 238).getInt();
		actID = config.getBlock("advancedCraftingTableID", 239).getInt();
		redstoneBlkID = config.getBlock("redstoneBlockID", 240).getInt();
		adamantFurnaceActiveID = config.getBlock("activeAdamantFurnace", 241).getInt();
		
		// Items
		adID = config.getItem("adamantID", 3843).getInt();
		megastickID = config.getItem("megaStickID", 3844).getInt();
		excaliburID = config.getItem("excaliburID", 3845).getInt();
		adamPickID = config.getItem("adamamntPickaxeID", 3846).getInt();
		fuelID = config.getItem("adamantFuelIngotID", 3847).getInt();
		emergID = config.getItem("emergencyItemID", 3848).getInt();
		tomatoID = config.getItem("tomatoID", 3850).getInt();
		pizzaID = config.getItem("pizzaID", 3851).getInt();
		sharpeningToolID = config.getItem("sharpeningToolID", 3852).getInt();
		toolsID = config.getItem("toolsID", 3853).getInt();
		pepperID = config.getItem("pepperID", 3854).getInt();
		doughID = config.getItem("doughID", 3855).getInt();
		ketchupID = config.getItem("ketchupBottleID", 3856).getInt();
		adamShovelID = config.getItem("adamantShovelID", 3857).getInt();
		adamAxeID = config.getItem("adamamntAxeID", 3858).getInt();
		potionCustomID = config.getItem("potionID", 3859).getInt();
		meteorDustID = config.getItem("meteorDustID", 3860).getInt();
		
		// Armour
		adamantHelmetID = config.getItem("adamantHelmetID", 3861).getInt();
		adamantChestID = config.getItem("adamantChesplateID", 3862).getInt();
		adamantLegsID = config.getItem("adamantLeggingsID", 3863).getInt();
		adamantBootsID = config.getItem("adamantBootsID", 3864).getInt();
		
		// Properties
		potionCrazyEnable = config.get("Properties", "enablePotionCrazy", true).getBoolean(true);
		potionPetrifyEnable = config.get("Properties", "enablePotionPetrify", true).getBoolean(true);
		doEndstoneDamage = config.get("Properties", "darkEndstoneProperty", true).getBoolean(true);
		classicPetrifyModel = config.get("Properties", "classicPetrifyingPotionLook", false).getBoolean(false);
		
		config.save();
		
	}
	
	/////////////////////////
	//Initialization Method//
	/////////////////////////
	
	@Init
	public void load(FMLInitializationEvent event) {
		
		/////////////
		//Materials//
		/////////////
		
		EnumToolMaterial EnumToolMaterialAdamant = EnumHelper.addToolMaterial("adamant", 3, 2000, 13.10F, 15, 14);
		EnumArmorMaterial EnumArmorMaterialAdamant = EnumHelper.addArmorMaterial("adamantArmor", 4, new int[]{4, 9, 8, 5}, 25);
		
		//////////
		//Blocks//
		//////////
		
		//Adamant Ore
		adamantOre = (new BlockAdamantOre(adOreID, 0)).setStepSound(Block.soundStoneFootstep).setHardness(4.0F).setResistance(1.0F).setBlockName("adamantOre");
		LanguageRegistry.addName(adamantOre, "Adamant Ore");
		MinecraftForge.setBlockHarvestLevel(adamantOre, "pickaxe", 2);
		GameRegistry.registerBlock(adamantOre);
		
		//Adamant Block
		adamantBlk = (new BlockAdamantBlk(adBlkID, 1, Material.iron)).setStepSound(Block.soundMetalFootstep).setHardness(4.0F).setResistance(30).setBlockName("adamantBlk");
		LanguageRegistry.addName(adamantBlk, "Block of Adamant");
		MinecraftForge.setBlockHarvestLevel(adamantBlk, "pickaxe", 3);
		GameRegistry.registerBlock(adamantBlk);
		
		//Mega Adamant Block
		megaAdamant = (new BlockMegaAdamant(megaAdID, 2, Material.rock)).setStepSound(Block.soundStoneFootstep).setHardness(99.9F).setResistance(9000000.0F).setBlockName("megaAdamant");
		LanguageRegistry.addName(megaAdamant, "Mega Adamant Block");
		MinecraftForge.setBlockHarvestLevel(megaAdamant, "pickaxe", 3);
		GameRegistry.registerBlock(megaAdamant);
		
		//Cracked Netherrack
		crackedNetherrack = (new BlockCrNether(crackedNetherrackID, 3, Material.rock)).setStepSound(Block.soundStoneFootstep).setHardness(0.3F).setResistance(0.1F).setBlockName("crackedNetherrack");
		LanguageRegistry.addName(crackedNetherrack, "Cracked Netherrack");
		MinecraftForge.setBlockHarvestLevel(crackedNetherrack, "pickaxe", 0);
		GameRegistry.registerBlock(crackedNetherrack);
		
		//Clear Glass
		clearGlass = (new BlockClearGlass(clearGlassID, 4, Material.glass)).setStepSound(Block.soundGlassFootstep).setHardness(0.3F).setBlockName("clearGlass");
		LanguageRegistry.addName(clearGlass, "Clear Glass");
		GameRegistry.registerBlock(clearGlass);
		
		//Sharpener
		sharpener = (new BlockSharpener(sharpenerID)).setStepSound(Block.soundMetalFootstep).setHardness(5.0F).setResistance(10.0F).setBlockName("sharpener");
		LanguageRegistry.addName(sharpener, "Sharpener");
		MinecraftForge.setBlockHarvestLevel(sharpener, "pickaxe", 1);
		GameRegistry.registerBlock(sharpener);
		
		//Tomato Crops
		tomatoCrop = (new BlockTomatoCrop(tomatoCropID, 16, Material.plants)).setHardness(0F).setBlockName("tomatoCrop");
		LanguageRegistry.addName(tomatoCrop, "If you're reading this you've either found a bug or you're a 1337 haXXor.");
		GameRegistry.registerBlock(tomatoCrop);
		
		//Pepper Crops
		pepperCrop = (new BlockPepperCrop(pepperCropID, 32, Material.plants)).setHardness(0F).setBlockName("pepperCrop");
		LanguageRegistry.addName(pepperCrop, "\"So be it! Let my life fuel the spell that ends his!\"");
		GameRegistry.registerBlock(pepperCrop);
		
		//Dark End Stone
		darkEndStone = (new BlockDarkEndStone(darkEndStoneID, 9, Material.rock)).setHardness(3.0F).setResistance(15.0F).setStepSound(Block.soundStoneFootstep).setBlockName("darkEndStone");
		LanguageRegistry.addName(darkEndStone, "Dark End Stone");
		GameRegistry.registerBlock(darkEndStone);
		
		//Meteor Block
		meteorBlock = (new BlockMeteor(meteorBlockID, 15, Material.sand)).setHardness(0.1F).setResistance(0.1F).setStepSound(Block.soundSandFootstep).setBlockName("meteorBlock");
		LanguageRegistry.addName(meteorBlock, "Meteor Block");
		GameRegistry.registerBlock(meteorBlock);
		
		//Meteor Bomb
		meteorBomb = (new BlockMeteorBomb(meteorBombID, 0)).setHardness(8.1F).setResistance(1.0F).setStepSound(Block.soundPowderFootstep).setBlockName("meteorBomb");
		LanguageRegistry.addName(meteorBomb, "Meteor Bomb");
		GameRegistry.registerBlock(meteorBomb);
		
		//Block of Redstone
		redstoneBlk = (new BlockRedstone(redstoneBlkID, 30)).setHardness(6F).setResistance(56.3F).setStepSound(Block.soundMetalFootstep).setBlockName("redstoneBlk");
		MinecraftForge.setBlockHarvestLevel(redstoneBlk, "pickaxe", 2);
		LanguageRegistry.addName(redstoneBlk, "Block of Redstone");
		GameRegistry.registerBlock(redstoneBlk);
		
		//Adamant Furnace + Active
		adamantFurnace = (new BlockAdamantFurnace(adamantFurnaceID, false)).setHardness(12.56F).setResistance(15F).setStepSound(Block.soundMetalFootstep).setCreativeTab(tabTelicraft).setBlockName("adamantFurnace");
		adamantFurnaceActive = (new BlockAdamantFurnace(adamantFurnaceActiveID, true)).setHardness(12.56F).setResistance(15F).setStepSound(Block.soundMetalFootstep).setCreativeTab((CreativeTabs)null).setBlockName("adamantFurnaceActive");
		MinecraftForge.setBlockHarvestLevel(adamantFurnace, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(adamantFurnaceActive, "pickaxe", 2);
		LanguageRegistry.addName(adamantFurnace, "Adamant Furnace");
		LanguageRegistry.addName(adamantFurnaceActive, "Adamant Furnace");
		GameRegistry.registerBlock(adamantFurnace);
		GameRegistry.registerBlock(adamantFurnaceActive);
		
		//Alarm
		alarm = (new BlockAlarm(alarmID)).setStepSound(Block.soundPowderFootstep).setBlockName("alarm");
		LanguageRegistry.addName(alarm, "Alarm");
		GameRegistry.registerBlock(alarm);
		
		//Advanced Crafting Table
		act = (new BlockACT(actID)).setHardness(12.5F).setResistance(50F).setStepSound(Block.soundWoodFootstep).setBlockName("act");
		LanguageRegistry.addName(act, "Advanced Crafting Table");
		GameRegistry.registerBlock(act);
		
		//Squill
		squill = (BlockFlower) (new BlockSquill(squillID, 8, Material.plants)).setBlockName("squill");
		LanguageRegistry.addName(squill, "Squill");
		GameRegistry.registerBlock(squill);
		
		/////////
		//Items//
		/////////
		
		//Adamant (Gem)
		adamant = new ItemUseless(adID, tabTelicraft, 64, false, "uncommon", itemsPng).setIconIndex(0).setItemName("adamant");
		LanguageRegistry.addName(adamant, "Adamant");
		
		//Mega Stick
		megastick = new ItemUseless(megastickID, tabTelicraft, 64, false, "common", itemsPng).setIconIndex(2).setItemName("megastick");
		LanguageRegistry.addName(megastick, "Mega Stick");
		
		//Adamant Fuel Ingot
		fuel = new ItemUseless(fuelID, tabTelicraft, 64, false, "common", itemsPng).setIconIndex(1).setItemName("adamantFuelIngot");
		LanguageRegistry.addName(fuel, "Adamant Fuel Ingot");
		
		//Emergency Item
		emerg = new ItemEmergnecy(emergID).setIconIndex(6).setItemName("emerg");
		LanguageRegistry.addName(emerg, "Emergency Item");
		
		//Tomato
		tomato = new ItemTomato(tomatoID, tomatoCrop.blockID, Block.tilledField.blockID).setIconIndex(5).setItemName("tomato");
		LanguageRegistry.addName(tomato, "Tomato");
		
		//Pepper
		pepper = new ItemPepper(pepperID, pepperCrop.blockID, Block.tilledField.blockID).setIconIndex(21).setItemName("pepper");
		LanguageRegistry.addName(pepper, "Pepper");
		
		//Raw Pizza
		pizza = new ItemPizza(pizzaID, 4, 1.12F).setItemName("pizza");
		
		//Tools
		tools = new ItemTelicraftTools(toolsID).setItemName("tools");
		
		//Dough
		dough = new ItemUseless(doughID, tabTelicraft, 16, false, "common", itemsPng).setIconIndex(24).setItemName("dough");
		LanguageRegistry.addName(dough, "Dough");
		
		//Ketchup
		ketchup = new ItemUseless(ketchupID, tabTelicraft, 1, false, "common", itemsPng).setIconIndex(25).setItemName("ketchup");
		LanguageRegistry.addName(ketchup, "Bottle of Ketchup");
		
		//Meteor Dust
		meteorDust = new ItemUseless(meteorDustID, tabTelicraft, 16, false, "epic", itemsPng).setIconIndex(12).setItemName("meteorDust");
		LanguageRegistry.addName(meteorDust, "Meteor Dust");
		
		//Custom Potions
		potionCustom = new ItemTelicraftPotion(potionCustomID).setItemName("customPotion");
		
		////////////////////////////
		//Tools / Weapons / Armour//
		////////////////////////////
		
		//Excalibur
		excalibur = new ItemExcalibur(excaliburID, EnumToolMaterialAdamant).setIconIndex(3).setItemName("excalibur");
		LanguageRegistry.addName(excalibur, "Excalibur");
		
		//Adamant Pickaxe
		adamPick = new ItemAdamantPick(adamPickID, EnumToolMaterialAdamant).setIconIndex(17).setItemName("adamPick");
		LanguageRegistry.addName(adamPick, "Adamant Pickaxe");
		
		//Adamant Shovel
		adamShovel = new ItemAdamantSpade(adamShovelID, EnumToolMaterialAdamant).setIconIndex(16).setItemName("adamSpade");
		LanguageRegistry.addName(adamShovel, "Adamant Shovel");
		
		//Adamant Axe
		adamAxe = new ItemAdamantAxe(adamAxeID, EnumToolMaterialAdamant).setIconIndex(22).setItemName("adamAxe");
		LanguageRegistry.addName(adamAxe, "Adamant Axe");
		
		//Sharpening Tool
		sharpeningTool = new ItemSharpTool(sharpeningToolID).setIconIndex(11).setItemName("sharpTool");
		LanguageRegistry.addName(sharpeningTool, "Sharpening Tool");
		
		//Adamant Helmet
		adamantHelmet = new ItemAdamantHelmet(adamantHelmetID, EnumArmorMaterialAdamant, engine.addArmor("gemAdamant"), 0).setIconIndex(7).setItemName("adamantHelmet");
		LanguageRegistry.addName(adamantHelmet, "Adamant Helmet");
		
		//Adamant Chesplate
		adamantChest = new ItemAdamantChest(adamantChestID, EnumArmorMaterialAdamant, engine.addArmor("gemAdamant"), 1).setIconIndex(8).setItemName("adamantChestplate");
		LanguageRegistry.addName(adamantChest, "Adamant Chestplate");
		
		//Adamant Helmet
		adamantLegs = new ItemAdamantLegs(adamantLegsID, EnumArmorMaterialAdamant, engine.addArmor("gemAdamant"), 2).setIconIndex(9).setItemName("adamantLegs");
		LanguageRegistry.addName(adamantLegs, "Adamant Leggings");
		
		//Adamant Helmet
		adamantBoots = new ItemAdamantBoots(adamantBootsID, EnumArmorMaterialAdamant, engine.addArmor("gemAdamant"), 3).setIconIndex(10).setItemName("adamantBoots");
		LanguageRegistry.addName(adamantBoots, "Adamant Boots");
		
		
		/////////////////////
		//Shapeless Recipes//
		/////////////////////
		
		//Mega Stick
		engine.addOreDictRecipeShapeless(new ItemStack(megastick, 1), new Object[]{
			"gemAdamant",
			new ItemStack(Item.stick)
		});
		
		//Emerald x3
		engine.addOreDictRecipeShapeless(new ItemStack(Item.emerald, 3), new Object[]{
			"gemAdamant",
			new ItemStack(Item.diamond),
			new ItemStack(Item.dyePowder, 1, 2)
		});
		
		//Mega Adamant Block x6
		GameRegistry.addShapelessRecipe(new ItemStack(megaAdamant, 6), new Object[]{
			new ItemStack(adamantBlk),
			new ItemStack(Item.dyePowder, 1, 0),
			new ItemStack(Item.dyePowder, 1, 0),
			new ItemStack(Block.obsidian),
			new ItemStack(Block.obsidian),
			new ItemStack(Block.obsidian),
			new ItemStack(Block.obsidian),
			new ItemStack(Block.obsidian),
			new ItemStack(Block.obsidian)
		});
		
		//Bottle of Ketchup
		GameRegistry.addShapelessRecipe(new ItemStack(ketchup, 1), new Object[]{
			new ItemStack(tomato),
			new ItemStack(Item.glassBottle)
		});
		
		//Cooked Pizza with Ketchup
		GameRegistry.addShapelessRecipe(new ItemStack(pizza, 1, 2), new Object[]{
			new ItemStack(pizza, 1),
			new ItemStack(ketchup)
		});
		
		//Dough
		GameRegistry.addShapelessRecipe(new ItemStack(dough, 3), new Object[]{
			new ItemStack(Item.wheat),
			new ItemStack(Item.egg)
		});
		
		//Adamant Fuel Ingot
		engine.addOreDictRecipeShapeless(new ItemStack(fuel, 2), new Object[]{
			new ItemStack(Item.coal),
			new ItemStack(Item.ingotIron),
			"gemAdamant"
		});
		
		//////////////////
		//Shaped Recipes//
		//////////////////
		
		//Flat Excalibur
		engine.addOreDictRecipeShaped(new ItemStack(tools, 1, 0), new Object[]{
			"A", "A", "S",
			Character.valueOf('A'), "gemAdamant",
			Character.valueOf('S'), new ItemStack(megastick)
		});
		
		//Sharpening Tool
		engine.addOreDictRecipeShaped(new ItemStack(sharpeningTool, 1), new Object[]{
			" G ", "GAG", "III",
			Character.valueOf('G'),  new ItemStack(Item.ingotGold),
			Character.valueOf('A'), "gemAdamant",
			Character.valueOf('I'), new ItemStack(Item.ingotIron)
		});
		
		//Adamant Block
		engine.addOreDictRecipeShaped(new ItemStack(adamantBlk, 1), new Object[]{
			"AAA", "AAA", "AAA",
			Character.valueOf('A'), "gemAdamant"
		});
		
		//Mob Spawner
		GameRegistry.addRecipe(new ItemStack(Block.mobSpawner, 1), new Object[]{
			"IAI", "RDR", "IAI",
			Character.valueOf('I'), new ItemStack(Block.fenceIron),
			Character.valueOf('A'), new ItemStack(adamantBlk),
			Character.valueOf('R'), new ItemStack(Item.ingotIron),
			Character.valueOf('D'), new ItemStack(Block.blockDiamond)
		});
		
		//Adamant Pickaxe
		engine.addOreDictRecipeShaped(new ItemStack(adamPick, 1), new Object[]{
			"AAA", " S ", " S ",
			Character.valueOf('A'), "gemAdamant",
			Character.valueOf('S'), new ItemStack(megastick)
		});
		
		//Adamant Shovel
		engine.addOreDictRecipeShaped(new ItemStack(adamShovel, 1), new Object[]{
			"A", "S", "S",
			Character.valueOf('A'), "gemAdamant",
			Character.valueOf('S'), new ItemStack(megastick)
		});
		
		//Adamant Axe
		engine.addOreDictRecipeShaped(new ItemStack(adamAxe, 1), new Object[]{
			"AA", "AS", " S",
			Character.valueOf('A'), "gemAdamant",
			Character.valueOf('S'), new ItemStack(megastick)
		});
		
		//Adamant Helmet
		engine.addOreDictRecipeShaped(new ItemStack(adamantHelmet, 1), new Object[]{
			"AAA", "A A",
			Character.valueOf('A'), "gemAdamant"
		});
		
		//Adamant Chestplate
		engine.addOreDictRecipeShaped(new ItemStack(adamantChest, 1), new Object[]{
			"A A", "AAA", "AAA",
			Character.valueOf('A'), "gemAdamant"
		});
		
		//Adamant Leggings
		engine.addOreDictRecipeShaped(new ItemStack(adamantLegs, 1), new Object[]{
			"AAA", "A A", "A A",
			Character.valueOf('A'), "gemAdamant"
		});
		
		//Adamant Helmet
		engine.addOreDictRecipeShaped(new ItemStack(adamantBoots, 1), new Object[]{
			"A A", "A A",
			Character.valueOf('A'), "gemAdamant"
		});
		
		//Emergency Item
		GameRegistry.addRecipe(new ItemStack(emerg, 3), new Object[]{
			"BWB", "WBW", "BWB",
			Character.valueOf('B'), new ItemStack(Block.cloth, 1, 15),
			Character.valueOf('W'), new ItemStack(Block.cloth, 1, 0)
		});
		
		//Raw Pizza
		GameRegistry.addRecipe(new ItemStack(pizza, 1, 0), new Object[]{
			"PTP", "BBB",
			Character.valueOf('P'), new ItemStack(pepper),
			Character.valueOf('T'), new ItemStack(tomato),
			Character.valueOf('B'), new ItemStack(dough)
		});
		
		//Sharpener
		engine.addOreDictRecipeShaped(new ItemStack(sharpener, 1), new Object[]{
			"III", "SIS", "IAI",
			Character.valueOf('I'), new ItemStack(Item.ingotIron),
			Character.valueOf('S'), new ItemStack(Block.stone),
			Character.valueOf('A'), "gemAdamant"
		});
		
		//Advanced Crafting Table
		GameRegistry.addRecipe(new ItemStack(act, 1), new Object[]{
			"LLL", "DCD", "LLL",
			Character.valueOf('L'), new ItemStack(Item.dyePowder, 1, 4),
			Character.valueOf('D'), new ItemStack(Item.diamond, 1),
			Character.valueOf('C'), new ItemStack(Block.workbench, 1)
		});
		
		
		////////////////////
		//Smelting Recipes//
		////////////////////
		
		//Adamant Ore -> Adamant (Gem)
		GameRegistry.addSmelting(adamantOre.blockID, new ItemStack(adamant), 1F);
		
		//Glass -> Clear Glass
		GameRegistry.addSmelting(Block.glass.blockID, new ItemStack(clearGlass), 0.1F);
		
		//Netherrack -> Cracked Netherrack
		GameRegistry.addSmelting(Block.netherrack.blockID, new ItemStack(crackedNetherrack), 0.1F);
		
		//Raw Pizza -> Cooked Pizza
		GameRegistry.addSmelting(pizza.shiftedIndex, new ItemStack(pizza, 1, 1), 1.1F);
		
		/////////////////////
		//Sharpener Recipes//
		/////////////////////
		
		//Excalibur
		TCRegistry.addSharpenerRecipe(tools.shiftedIndex, 0, new ItemStack(excalibur));
		
		///////////////
		//ACT Recipes//
		///////////////
		
		//Meteor Bomb
		TCRegistry.addACTRecipe(new ItemStack(meteorBomb, 1), new Object[]{
			"DGD", "GDG", "DGD",
			Character.valueOf('D'), new ItemStack(meteorDust),
			Character.valueOf('G'), new ItemStack(Item.gunpowder)
		});
		
		/////////
		//Other//
		/////////
		
		//Fuel Handler
		GameRegistry.registerFuelHandler(new TelicraftFuelHandler());
		
		//Mega Adamant Blocks in dungeons
		DungeonHooks.addDungeonLoot(new ItemStack(megaAdamant), 55, 1, 3);
		
		//Tomato and Pepper seeds in dugeons
		DungeonHooks.addDungeonLoot(new ItemStack(tomato), 95, 3, 4);
		DungeonHooks.addDungeonLoot(new ItemStack(pepper), 95, 3, 4);
		
		//Register the Squill to be added when a Grass Block is Bonemeal'd
		MinecraftForge.addGrassPlant(squill, 0, 20);
		
		//Main World Generator
		GameRegistry.registerWorldGenerator(new TelicraftWorldGenerator());
		
		//Crafting Handler
		GameRegistry.registerCraftingHandler(new TelicraftCraftingHandler());
		
		//Sharpener Handler
		TCRegistry.registerSharpenerHandler(new TelicraftSharpenerHandler());
		
		//Register Achievements
		blueStuff = (new Achievement(1000, "smeltAdamant", 0, 0, adamant, AchievementList.acquireIron)).registerAchievement();
		kingArthur = (new Achievement(1001, "makeExcalibur", -5, 0, excalibur, blueStuff)).setSpecial().registerAchievement();
		notForKids = (new Achievement(1002, "explodeSharpener", 0, 5, sharpener, blueStuff)).registerAchievement();
		youMad = (new Achievement(1003, "drinkCrazyPotion", 0, 7, Item.potion, AchievementList.potion)).registerAchievement();
		deliciousPizza = (new Achievement(1004, "cookPizza", 4, 0, pizza, AchievementList.buildFurnace)).registerAchievement();
		griefer = (new Achievement(1005, "makeMeteorBomb", 0, -3, meteorBomb, AchievementList.diamonds)).registerAchievement();
		
		//Main Localizations
		this.addGeneralLocalizations();
		
		//Achievement Localizations
		this.addAchievementLocalizations();
		
		//Block Localizations
		this.addBlockLocalizations();
		
		//Item Localizations
		this.addItemLocalizations();
		
		//Achievement Page
		AchievementPage.registerAchievementPage(new AchievementPage("Telicraft", blueStuff, kingArthur, notForKids, youMad, deliciousPizza, griefer));
		
		//Entities
		EntityRegistry.registerModEntity(EntityPetrify.class, "thrownPetrifyingPotion", 0, this, 160, 5, true);
		EntityRegistry.registerModEntity(EntityMeteorBombPrimed.class, "primedMeteorBomb", 1, this, 160, 5, true);
		
		//Tile Entities
		GameRegistry.registerTileEntity(TileEntitySharpener.class, "tileEntitySharpener");
		GameRegistry.registerTileEntity(TileEntityAdamantFurnace.class, "tileEntityAdamantFurnace");
		GameRegistry.registerTileEntity(TileEntityAlarm.class, "tileEntityAlarm");
		
		//Register GUI Handler
		NetworkRegistry.instance().registerGuiHandler(this, new TelicraftGuiHandler());
		
		//Buildcraft Integration
		TelicraftBCIntegration.run();
		
		//Register Sounds
		proxy.registerSoundHandler();
		
		//Register Renderers
		proxy.registerRenderThings();
	}
	
	//////////////////////////////
	//Post-Initialization Method//
	//////////////////////////////

	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		System.out.println("Telicraft " + MainReferences.MOD_VERSION + " Initialized");
		
		OreDictionary.registerOre("oreAdamant", adamantOre);
		OreDictionary.registerOre("gemAdamant", adamant);
		OreDictionary.registerOre("stickAdamant", megastick);
	}
	
	private void addAchievementName(String ach, String name){
		engine.addLocal("achievement." + ach, name);
	}

	private void addAchievementDesc(String ach, String desc){
		engine.addLocal("achievement." + ach + ".desc", desc);
	}
	
    private void addGeneralLocalizations(){
		engine.addLocal("container.sharpener", "Sharpener");
		engine.addLocal("container.furnace.adamant", "Adamant Furnace");
		engine.addLocal("container.crafting.act", "Advanced Crafting Table");
		
		engine.addLocal("itemGroup.telicraft", "Telicraft");
		
		engine.addLocal("gui.telicraft.heat", "Heat");
		
		engine.addLocal("death.suicide", DeathMessages.DEATH_SUICIDE);
		engine.addLocal("death.stone", DeathMessages.DEATH_STONE);
		engine.addLocal("death.petrify", DeathMessages.DEATH_PETRIFY);
		engine.addLocal("death.random", DeathMessages.DEATH_RANDOM);
		engine.addLocal("death.meteorBomb", DeathMessages.DEATH_METEOR_BOMB);
		
		(new BulgarianLang()).addLocalizationsGeneral(); // Adds in bulgarian localizations.
    }
    
    private void addAchievementLocalizations(){
		this.addAchievementName("smeltAdamant", AchievementReferences.ACH_BLUE_STUFF_NAME);
		this.addAchievementDesc("smeltAdamant", AchievementReferences.ACH_BLUE_STUFF_DESC);
    	
		this.addAchievementName("makeExcalibur", AchievementReferences.ACH_KING_ARTHUR_NAME);
		this.addAchievementDesc("makeExcalibur", AchievementReferences.ACH_KING_ARTHUR_DESC);
		
		this.addAchievementName("explodeSharpener", AchievementReferences.ACH_NOT_FOR_KIDS_NAME);
		this.addAchievementDesc("explodeSharpener", AchievementReferences.ACH_NOT_FOR_KIDS_DESC);
		
		this.addAchievementName("drinkCrazyPotion", AchievementReferences.ACH_YOU_MAD_NAME);
		this.addAchievementDesc("drinkCrazyPotion", AchievementReferences.ACH_YOU_MAD_DESC);
		
		this.addAchievementName("cookPizza", AchievementReferences.ACH_PIZZA_NAME);
		this.addAchievementDesc("cookPizza", AchievementReferences.ACH_PIZZA_DESC);
		
		this.addAchievementName("makeMeteorBomb", AchievementReferences.ACH_METEOR_NAME);
		this.addAchievementDesc("makeMeteorBomb", AchievementReferences.ACH_METEOR_DESC);
    }
    
    private void addBlockLocalizations(){
    	(new BulgarianLang()).addLocalizationsBlocks();	// Adds in the bulgarian localizations.
    }
    
    private void addItemLocalizations(){
		engine.addLocal("item.pizza.raw.name", "Raw Pizza");
		engine.addLocal("item.pizza.cookedFirst.name", "Cooked Pizza");
		engine.addLocal("item.pizza.cookedSecond.name", "Cooked Pizza with Ketchup");
	
		engine.addLocal("item.customPotion.crazy.name", "Potion of Craziness");
		engine.addLocal("item.customPotion.random.name", "Potion of Randomness");
		engine.addLocal("item.customPotion.petrify.name", "Petrifying Potion");
		engine.addLocal("item.customPotion.petrifyDirect.name", "Direct Petrifying Potion");
		
		engine.addLocal("item.tools.adamantSword.name", "Flat Adamant Sword");
    }
	
	////////////
	//Main IDs//
	////////////
	
	public static int adOreID, adBlkID, adID, crackedNetherrackID, clearGlassID, sharpenerID, meteorBombID;
	public static int adamantFurnaceID, meteorBlockID, alarmID, actID, redstoneBlkID;
	public static int adamantFurnaceActiveID;
	public static int tomatoCropID, pepperCropID, darkEndStoneID, squillID, megastickID;
	public static int excaliburID, adamAxeID, adamPickID, adamShovelID, fuelID, emergID, megaAdID;
	public static int tomatoID, pepperID, meteorDustID;
	public static int pizzaID;
	public static int sharpeningToolID, toolsID, doughID, ketchupID, potionCustomID;
	
	public static int adamantHelmetID, adamantChestID, adamantLegsID, adamantBootsID;
	
	public static boolean potionCrazyEnable, potionPetrifyEnable, doEndstoneDamage,
		classicPetrifyModel;
	
}