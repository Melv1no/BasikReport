package fr.crazzoxx.basikreport;

import fr.crazzoxx.basikreport.commands.CReport;
import org.bukkit.plugin.java.JavaPlugin;

public class BasikReport extends JavaPlugin {

    private static BasikReport instance;
    @Override
    public void onEnable() {
    instance = this;
    saveDefaultConfig();
    getCommand("report").setExecutor(new CReport());
    }

    @Override
    public void onDisable() {

    }

    public static BasikReport getInstance(){
        return instance;
    }

    public String getConfStr(String confPath){
        return this.getConfig().getString(confPath).replace("{prefix}",getConfig().getString("plugin.prefix")).replace("&","§");
    }

}
