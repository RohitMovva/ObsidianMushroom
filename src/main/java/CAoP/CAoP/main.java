package CAoP.CAoP;

import org.bukkit.plugin.java.JavaPlugin;
import CAoP.CAoP.events.UseItems;
import CAoP.CAoP.events.JoinHandler;
import CAoP.CAoP.items.ItemManager;

public final class main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        ItemManager.init(this);
        new UseItems(this);
        new JoinHandler(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}


