package com.anawesomebear.climatechangemc;

import com.anawesomebear.climatechangemc.commands.ClimateChangeCmds;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class ClimateChangeMc extends JavaPlugin {

    // if this drops below 7, water starts freezing and it starts to get colder
    // if it gets above 13, water starts rising and ice/snow starts disappearing
    public static double greenhouseGasCounter = 0;

    // as greenhouseGasCounter goes up, so does this
    // as oceanAcidity goes up, spawn rate of sea creatures goes down
    public static double oceanAcidity = 0;

    @Override
    public void onEnable() {
        ClimateChangeCmds pluginCmds = new ClimateChangeCmds();
        getServer().getPluginManager().registerEvents(new ClimateChangeCmds(), this);
        getCommand("runccm").setExecutor(pluginCmds);
        getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "[ClimateChangeMc]: Enabled! Oh no, we started climate change...");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[ClimateChangeMc]: Disabled! We did it guys, we stopped climate change!");
    }
}
