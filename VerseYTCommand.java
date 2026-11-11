package com.verseyt.plugin.commands;

import com.verseyt.plugin.VerseYT;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VerseYTCommand implements CommandExecutor {

    private final VerseYT plugin;

    public VerseYTCommand(VerseYT plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {
            sendMessage(sender, "messages.usage");
            return true;
        }

        String type = args[0].toUpperCase();
        String link = args[1];

        if (!type.equals("СТРИМ") && !type.equals("ВИДЕО")) {
            sendMessage(sender, "messages.invalid-type");
            return true;
        }

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.hasPermission("ytalert.youtube") && !player.hasPermission("ytalert.admin")) {
                sendMessage(sender, "messages.no-permission");
                return true;
            }
        }

        String streamerName = extractStreamerName(sender, link);
        String messageKey = type.equals("СТРИМ") ? "messages.stream" : "messages.video";
        String message = plugin.getConfig().getString(messageKey, "");
        
        message = ChatColor.translateAlternateColorCodes('&', message);
        message = message.replace("{streamer}", streamerName);
        message = message.replace("{link}", link);

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (sender.hasPermission("ytalert.admin")) {
                if (player.hasPermission("ytalert.admin")) {
                    player.sendMessage(message);
                }
            } else {
                if (player.hasPermission("ytalert.youtube")) {
                    player.sendMessage(message);
                }
            }
        }

        sendMessage(sender, "messages.success");
        
        if (plugin.getConfig().getBoolean("settings.log-to-console", true)) {
            plugin.getLogger().info(sender.getName() + " отправил уведомление: " + type + " | " + link);
        }
        
        return true;
    }

    private void sendMessage(CommandSender sender, String key) {
        String message = plugin.getConfig().getString(key, "");
        message = ChatColor.translateAlternateColorCodes('&', message);
        sender.sendMessage(message);
    }

    private String extractStreamerName(CommandSender sender, String link) {
        if (plugin.getConfig().getBoolean("settings.auto-detect-streamer", true)) {
            if (link.contains("youtube.com/@")) {
                String[] parts = link.split("@");
                if (parts.length > 1) {
                    String name = parts[1].split("/")[0].split("\\?")[0];
                    return name;
                }
            } else if (link.contains("youtube.com/c/") || link.contains("youtube.com/channel/")) {
                String[] parts = link.split("/");
                for (int i = 0; i < parts.length - 1; i++) {
                    if (parts[i].equals("c") || parts[i].equals("channel")) {
                        return parts[i + 1].split("\\?")[0];
                    }
                }
            }
        }
        
        return sender.getName();
    }
}
