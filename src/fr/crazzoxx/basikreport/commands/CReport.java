package fr.crazzoxx.basikreport.commands;

import fr.crazzoxx.basikreport.BasikReport;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CReport implements CommandExecutor {

    private BasikReport instance;

    public CReport(){
        instance = BasikReport.getInstance();
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(args.length == 0){
            commandSender.sendMessage(instance.getConfStr("plugin.usage"));
        }else if(args.length == 1){
            switch(args[0]){
                 case "gui":
                    if(commandSender instanceof Player)
                       ReportGui(commandSender);
                    else
                        commandSender.sendMessage(instance.getConfStr("plugin.no-console"));
                    break;
                case "list":
                    if(commandSender.hasPermission("basikreport.admin.list"))
                       ReportList(commandSender);
                    else
                        commandSender.sendMessage(instance.getConfStr("plugin.permission-denied"));
                    break;
                case "reload":
                    break;
                default:
                    commandSender.sendMessage(instance.getConfStr("plugin.usage"));
                    break;
            }
            return false;
        }else if(args.length >= 2){
            boolean onlinePlayer = false;
            for(Player player : instance.getServer().getOnlinePlayers()){
                if(player.equals(Bukkit.getPlayer(args[0]))){
                    onlinePlayer = true;
                    break;
                }
            }
            StringBuilder reason = new StringBuilder();
            for(int i = 1; i < args.length;i++){
                reason.append(args[i] + " ");
            }
            commandSender.sendMessage(onlinePlayer ? instance.getConfStr("message.player.report-confirmed").replace("{player}", args[0]) : instance.getConfStr("message.player.report-failed").replace("{player}",args[0]));
            for(Player player : instance.getServer().getOnlinePlayers()){
                if(player.hasPermission("basikreport.admin.report"))
                    player.sendMessage(instance.getConfStr("message.staff.report").replace("{player_reported}", args[0])
                            .replace("{player}", commandSender.getName()).replace("{reason}", reason));
            }
            boolean PlayerAlreadyReported = false;
            for(Map.Entry<Player, Integer> reportPlayer : instance.getReportList().entrySet()){
                    if(reportPlayer.getKey().getDisplayName().equalsIgnoreCase(args[0])){
                        PlayerAlreadyReported = true;
                        reportPlayer.setValue(reportPlayer.getValue() + 1);
                    }
            }
            if(!PlayerAlreadyReported)
                instance.getReportList().put(Bukkit.getPlayer(args[0]), 1);

        }
        return false;
    }

    private void ReportList(CommandSender commandSender) {
        if(instance.getReportList().isEmpty()){
            commandSender.sendMessage(instance.getConfStr("message.staff.report-list-empty"));
            return ;
        }
        for(Map.Entry<Player, Integer> reportedPlayer : instance.getReportList().entrySet()){

            commandSender.sendMessage(instance.getConfStr("message.staff.report-list-row").replace("{player}", reportedPlayer.getKey().getDisplayName()).replace("{report_counter}", reportedPlayer.getValue().toString()));
        }
    }

    private void ReportGui(CommandSender commandSender) {
    }
}
