package com.peaches.epicskyblock.Inventories;

import com.peaches.epicskyblock.EpicSkyBlock;
import com.peaches.epicskyblock.Island;
import com.peaches.epicskyblock.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class Boosters implements Listener {

    public static Inventory inv(Island island) {
        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', "&e&lIsland Boosters"));
        for (int i = 0; i < 9; i++) {
            inv.setItem(i, EpicSkyBlock.getSkyblock.makeItem(Material.STAINED_GLASS_PANE, 1, 15, " "));
            inv.setItem(i + 9, EpicSkyBlock.getSkyblock.makeItem(Material.STAINED_GLASS_PANE, 1, 8, " "));
            inv.setItem(i + 18, EpicSkyBlock.getSkyblock.makeItem(Material.STAINED_GLASS_PANE, 1, 15, " "));
        }
        ItemStack spawner = EpicSkyBlock.getSkyblock.makeItem(Material.MOB_SPAWNER, 1, 0, "&e&lSpawner Booster");
        ArrayList<String> spawnerlore = new ArrayList<>();
        if (island.getSpawner() != 0) {
            spawnerlore.add(ChatColor.translateAlternateColorCodes('&', "&6&lTime: &e&l" + island.getSpawner() / 60 + "m " + island.getSpawner() % 60 + "s"));
        } else {
            spawnerlore.add(ChatColor.translateAlternateColorCodes('&', "&6&lClick to activate"));
        }
        inv.setItem(9, EpicSkyBlock.getSkyblock.makeItem(Material.MOB_SPAWNER, 1, 0, "&e&lSpawner Booster", spawnerlore));
        inv.setItem(11, EpicSkyBlock.getSkyblock.makeItem(Material.WHEAT, 1, 0, "&e&lFarming Booster"));
        inv.setItem(13, EpicSkyBlock.getSkyblock.makeItem(Material.EXP_BOTTLE, 1, 0, "&e&lXp Booster"));
        inv.setItem(15, EpicSkyBlock.getSkyblock.makeItem(Material.FEATHER, 1, 0, "&e&lFly Booster"));
        inv.setItem(17, EpicSkyBlock.getSkyblock.makeItem(Material.DOUBLE_PLANT, 1, 0, "&e&lMobCoins Booster"));
        return inv;
    }

    @EventHandler
    public void onclick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        Island island = User.getbyPlayer(p).getIsland();
        if (e.getInventory().getTitle().equals(inv(island).getTitle())) {
            e.setCancelled(true);
            if (e.getSlot() == 9) {
                //Spawner Booster
                island.setSpawnerBoosterActive(true);
                island.startspawnercountdown(60 * 60);
                p.closeInventory();
            }
            if (e.getSlot() == 11) {
                //Farming Booster
                island.setFarmingBoosterActive(true);
                p.closeInventory();
            }
            if (e.getSlot() == 13) {
                //Xp Booster
                island.setXPBoosterActive(true);
                p.closeInventory();
            }
            if (e.getSlot() == 15) {
                //Fly Booster
                island.setFlyBoosterActive(true);
                p.closeInventory();
            }
            if (e.getSlot() == 17) {
                //MobCoins Booster
                island.setMobCoinsBoosterActive(true);
                p.closeInventory();
            }
        }
    }

}
