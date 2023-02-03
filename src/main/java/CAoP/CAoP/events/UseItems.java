package CAoP.CAoP.events;

import CAoP.CAoP.main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.scheduler.BukkitScheduler;
import CAoP.CAoP.items.ItemManager;

import java.util.ArrayList;

import static org.bukkit.Bukkit.getServer;


public class UseItems implements Listener {

    private final main plugin;
    public UseItems(main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onRightclick(PlayerInteractEvent event) throws InterruptedException {
        if (event.getHand() == EquipmentSlot.OFF_HAND){ return; }
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_AIR) { return; }
        if (event.getItem() == null) { return; }
        if (!event.getItem().getItemMeta().equals(ItemManager.ObsiMushroom.getItemMeta())) { return; }

        Player player = event.getPlayer();
        Location loc = player.getLocation();
        ArrayList<org.bukkit.block.Block> blockers = new ArrayList<org.bukkit.block.Block>();
        ArrayList<String> block_data = new ArrayList<String>();
        for (int i = 0; i < 4; i++){
            for (int j = -1; j < 2; j++) {
                for (int k = -1; k < 2; k++){
                    if (j == 0 && k == 0 && 1 <= i && i <= 2 ||
                            player.getWorld().getBlockAt(loc.getBlockX()+k, loc.getBlockY()-1+i, loc.getBlockZ()+j).getType().equals(Material.OBSIDIAN)){
                        continue;
                    }
                    blockers.add(player.getWorld().getBlockAt(loc.getBlockX()+k, loc.getBlockY()-1+i, loc.getBlockZ()+j));
                    block_data.add(blockers.get(blockers.size()-1).getBlockData().getAsString());
                    blockers.get(blockers.size()-1).setType(Material.OBSIDIAN);

                }
            }
        }

        if (blockers.size() == 0) { return; }

        player.getInventory().removeItem(ItemManager.ObsiMushroom);
        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(this.plugin, new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < blockers.size(); i++) {
                    blockers.get(i).setBlockData(Bukkit.createBlockData(block_data.get(i)));
                }
            }
        }, 200L);

    }
}
