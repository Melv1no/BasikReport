package fr.crazzoxx.basikreport.commands;

import fr.crazzoxx.basikreport.BasikReport;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CReport implements CommandExecutor {

    private BasikReport instance;

    public CReport(){
        instance = BasikReport.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if(args.length == 0){
            ConsoleOutput(1,sender);
            return false;
        }
        if(args.length == 1){
            switch (args[0]){
                case "gui":
                    //GUI
                    break;
                case "list":
                    //LIST
                    break;
                case "reload":
                    //RELOAD
                    break;
                default:
                    ConsoleOutput(1, sender);
                    break;
            }
            return false;
        }
        if(args.length > 2){
            Player target = Bukkit.getPlayer(args[0]);
            boolean targetConnected = false;
            StringBuilder reason = new StringBuilder();
            for(int i = 1; i < args.length;i++){
                reason.append(i);
            }
            for(Player player : instance.getServer().getOnlinePlayers()){
                if(player.equals(target)){
                    targetConnected = true;
                    break;
                }
            }
            if(!targetConnected) {
                sender.sendMessage(instance.getConfStr("message.player.report-failed").replace("{player}", args[0]));
                return false;
            }
            sender.sendMessage(instance.getConfStr("message.player.report-confirmed").replace("{player}", target.getDisplayName()));
            for (Player moderator : instance.getServer().getOnlinePlayers()){
                if(moderator.hasPermission("basikreport.mod")){
                    moderator.sendMessage(instance.getConfStr("message.staff.report").replace("{player_reported}", target.getDisplayName()).replace("{player}",sender.getName()).replace("{reason}",reason));
                }
            }
            //GUI
        }
        return false;
    }
    public void ConsoleOutput(int level, CommandSender sender){
        switch(level){
            case 1:
                sender.sendMessage(instance.getConfStr("message.server.args-helper"));
                break;
            case 2:
                System.out.println("");
                break;
            default:
                System.out.println("");
                break;
        }
    }
}
