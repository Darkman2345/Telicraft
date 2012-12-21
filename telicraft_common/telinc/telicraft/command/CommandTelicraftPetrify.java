package telinc.telicraft.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.StatCollector;

import telinc.telicraft.TelicraftMain;

public class CommandTelicraftPetrify extends CommandBase {
    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if(args.length > 0){
            String subCommand = args[0];

            if(subCommand.toLowerCase().equals("classic")){
                processClassicCommand(sender);
            }else if(subCommand.toLowerCase().equals("new")){
                processNewCommand(sender);
            }else{
                throw new WrongUsageException("commands.telicraft.petrifylook.usage", new Object[0]);
            }
        }else{
            throw new WrongUsageException("commands.telicraft.petrifylook.usage", new Object[0]);
        }
    }

    private static void processClassicCommand(ICommandSender sender) {
        TelicraftMain.classicPetrifyModel = true;
        TelicraftMain.setConfigOption("Properties","classicPetrifyingPotionLook", "true");
        sender.sendChatToPlayer(StatCollector.translateToLocal("commands.telicraft.petrifylook.classic"));
    }

    private static void processNewCommand(ICommandSender sender) {
        TelicraftMain.classicPetrifyModel = false;
        TelicraftMain.setConfigOption("Properties","classicPetrifyingPotionLook", "false");
        sender.sendChatToPlayer(StatCollector.translateToLocal("commands.telicraft.petrifylook.new"));
    }

    @Override
    public String getCommandName() {
        return "telicraft-petrifylook";
    }
}
