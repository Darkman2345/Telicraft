package telinc.telicraft.lib;

import java.util.Random;

public class RandomMessages {
	public static String getPotionCrazyMessage(Random par1Random){
		int messageID = par1Random.nextInt(5);
		
		switch(messageID){
		case 1:
			return "Fun Fact: The Potion of Craziness is disabled.";
		case 2:
			return "What are you even trying to do?!";
		case 3:
			return "TelincCoreFunctons.crashMinecraft(World world);";
		case 4:
			return "Wait, was this actually supposed to do something? Sorry!";
		case 5:
			return "Error initializing potion effects...";
		default:
			return "telinc.telicraft.common.PotionCrazyMessageException: unable to get message";
		}
		
	}
}