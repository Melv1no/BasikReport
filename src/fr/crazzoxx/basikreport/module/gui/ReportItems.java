package fr.crazzoxx.basikreport.module.gui;

import fr.crazzoxx.basikreport.BasikReport;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportItems {
    private HashMap<Player, Integer> reportList;
    private BasikReport instance;

    public  ReportItems(HashMap<Player, Integer> reportList) {
        instance = BasikReport.getInstance();
        this.reportList = reportList;
    }

    public void loadReportedPlayer(Inventory inv) {

        for (Map.Entry<Player, Integer> reportedPlayer : reportList.entrySet()) {

            ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
            SkullMeta meta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.SKULL_ITEM);
            meta.setOwner(reportedPlayer.getKey().getDisplayName());
            List<String> lore = new ArrayList<String>();
            for (String lores : instance.getConfig().getStringList("gui.items-lores")) {
                lore.add(lores.replace("{report_count}", reportedPlayer.getValue().toString()));
            }
            meta.setLore(lore);
            meta.setDisplayName(reportedPlayer.getKey().getDisplayName());
            skull.setItemMeta(meta);
            inv.addItem(skull);
        }
    }
}
