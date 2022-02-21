package fr.crazzoxx.basikreport.module.gui.listener;

import fr.crazzoxx.basikreport.BasikReport;
import fr.crazzoxx.basikreport.module.gui.ReportGui;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ReportGuiClick implements Listener {
    private BasikReport instance;
    public ReportGuiClick(){
        instance = BasikReport.getInstance();
    }

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent e){
        if(e.getInventory().getName().equals(new ReportGui().getReportGui().getName())){
            if(e.getClick() == ClickType.RIGHT && e.getCurrentItem().getType() == Material.SKULL_ITEM){
                if(e.getWhoClicked().hasPermission("basikreport.admin.gui.teleport")){
                    for(Player target : instance.getServer().getOnlinePlayers()){
                        if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(target.getDisplayName())){
                            e.getWhoClicked().teleport(target.getLocation());
                            e.getWhoClicked().sendMessage(instance.getConfStr("gui.report-gui-teleport"));
                            e.setCancelled(true);
                            return;
                        }
                    }
                    e.getWhoClicked().sendMessage(instance.getConfStr("message.player.report-failed").replace("{player}",e.getCurrentItem().getItemMeta().getDisplayName()));
                    e.setCancelled(true);
                }else{
                    e.getWhoClicked().sendMessage(instance.getConfStr("plugin.permission-denied"));
                }
            }else{
                e.setCancelled(true);
            }
        }else{

        }
    }
}
