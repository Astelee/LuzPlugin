package com.newscraft.luzplugin;

import org.bukkit.plugin.java.JavaPlugin;

public class LuzPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getLogger().info("LuzPlugin | NewsCraft ativado com sucesso!");
        LuzCommand luzCommand = new LuzCommand(this);
        getCommand("luz").setExecutor(luzCommand);
        getCommand("luz").setTabCompleter(luzCommand);
    }

    @Override
    public void onDisable() {
        getLogger().info("LuzPlugin | NewsCraft desativado.");
    }
}
