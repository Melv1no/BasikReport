package fr.crazzoxx.basikreport;

import fr.crazzoxx.basikreport.commands.CReport;
import fr.crazzoxx.basikreport.module.gui.listener.ReportGuiClick;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class BasikReport extends JavaPlugin {

    private static BasikReport instance;
    @Override
    public void onEnable() {
    instance = this;
    saveDefaultConfig();
    getCommand("report").setExecutor(new CReport());
    getServer().getPluginManager().registerEvents(new ReportGuiClick(),this);
    }
    @Override
    public void onDisable() {

    }
    public static BasikReport getInstance() {
        return instance;
    }
    public String getConfStr(String confPath){
        return this.getConfig().getString(confPath).replace("{prefix}",getConfig().getString("plugin.prefix")).replace("&","§");
    }
    private HashMap<Player, Integer> reportList = new HashMap<Player, Integer>();
    public  HashMap<Player,Integer> getReportList(){ return reportList;}

}
