package fr.crazzoxx.basikreport.module.gui;

import fr.crazzoxx.basikreport.BasikReport;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class ReportGui {

    private BasikReport instance;
    public ReportGui(){
        instance = BasikReport.getInstance();
    }
    private Inventory ReportGui;
    public Inventory getReportGui(){
        ReportGui = Bukkit.createInventory(null, 54, instance.getConfStr("gui.title"));
        ReportItems reportItems = new ReportItems(instance.getReportList());
        reportItems.loadReportedPlayer(ReportGui);

        return ReportGui;
    }
}
