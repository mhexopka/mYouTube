package com.verseyt.plugin;

import com.verseyt.plugin.commands.VerseYTCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class VerseYT extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        
        getCommand("alert").setExecutor(new VerseYTCommand(this));
        
        getLogger().info("mYouTube плагин успешно запущен!");
    }

    @Override
    public void onDisable() {
        getLogger().info("mYouTube плагин выключен!");
    }
}
