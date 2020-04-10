package pers.Tomoto.LoreTest;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;

import pers.Tomoto.LoreTest.Listener.AttackListener;

public class LoreTest extends JavaPlugin implements Listener {
    private FileConfiguration _playerData;

    @Override
    public void onLoad() {
        saveDefaultConfig();
        saveResource("playerData.yml", false);
        _playerData = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "playerData.yml"));
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new AttackListener(), this);
    }

    @Override
    public void onDisable() {
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(_playerData.get(player.getName()) == null || _playerData.getBoolean(player.getName())) {
            int empty = player.getInventory().firstEmpty();
            if(empty == -1) {
                _playerData.set(player.getName(), true);
                try {
                    _playerData.save(new File(this.getDataFolder(), "playerData.yml"));
                }
                catch(java.io.IOException e) {
                    e.printStackTrace();
                }
                return;
            }
            else {
                ItemStack item = new ItemStack(Material.DIAMOND_AXE, 1);
                ItemMeta meta = item.getItemMeta();
                ArrayList<String> lore = new ArrayList<String>();
                lore.add(ChatColor.DARK_BLUE + "Wither");
                meta.setLore(lore);
                meta.setDisplayName(ChatColor.BLACK + "Wither Axe");
                item.setItemMeta(meta);
                player.getInventory().setItem(empty, item);
                _playerData.set(player.getName(), false);
                try {
                    _playerData.save(new File(this.getDataFolder(), "playerData.yml"));
                }
                catch(java.io.IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
