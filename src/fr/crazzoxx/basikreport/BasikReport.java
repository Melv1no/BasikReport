package fr.crazzoxx.basikreport;

import fr.crazzoxx.basikreport.commands.CReport;
import fr.crazzoxx.basikreport.module.gui.listener.ReportGuiClick;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.logging.Logger;

public class BasikReport extends JavaPlugin {

    private static BasikReport instance;
    @Override
    public void onEnable() {
        if(!getConfig().getBoolean("plugin.enable")){
            log.severe("PLUGIN DISABLE IN CONFIG.YML");
            this.getServer().getPluginManager().disablePlugin(this);
        }
    instance = this;
    saveDefaultConfig();
    getCommand("report").setExecutor(new CReport());
    if(getConfig().getBoolean("gui.enable"))
        getServer().getPluginManager().registerEvents(new ReportGuiClick(),this);
    else
        log.warning("Gui Features is not enable in config.yml");
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
    public static Logger log = Logger.getLogger("Minecraft");
    private HashMap<Player, Integer> reportList = new HashMap<Player, Integer>();
    public  HashMap<Player,Integer> getReportList(){ return reportList;}

}
