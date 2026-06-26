package com.newscraft.luzplugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class LuzCommand implements CommandExecutor, TabCompleter {

    private final LuzPlugin plugin;

    public LuzCommand(LuzPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "[NewsCraft] Apenas jogadores podem usar este comando!");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("luzplugin.usar")) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                "&8[&bNewsCraft&8] &cVoce nao tem permissao para usar este comando!"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                "&7Apenas &eVIP &7e &bStaff &7podem usar /luz."));
            return true;
        }

        // Lê a duração do config.yml
        int duracao = plugin.getConfig().getInt("duracao", -1);
        if (duracao <= 0) {
            duracao = Integer.MAX_VALUE;
        }

        if (player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
            player.removePotionEffect(PotionEffectType.NIGHT_VISION);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                "&8[&bNewsCraft&8] &eSua visao noturna foi &cdesativada&e!"));
        } else {
            player.addPotionEffect(new PotionEffect(
                PotionEffectType.NIGHT_VISION, duracao, 0, false, false));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                "&8[&bNewsCraft&8] &eSua visao noturna foi &aativada&e!"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                "&7Use &f/luz &7novamente para desativar."));
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return new ArrayList<>();
    }
}
