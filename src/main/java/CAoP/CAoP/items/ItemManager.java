package CAoP.CAoP.items;

import CAoP.CAoP.main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.ArrayList;

public class ItemManager {

    public static ItemStack ObsiMushroom;

    public static void init(main plugin){
        createObsiMushroom(plugin);
    }

    private static void createObsiMushroom(main plugin) {
        ItemStack item = new ItemStack(Material.BROWN_MUSHROOM, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§1 Obsidian Mushroom");
        List<String> lore = new ArrayList<>();
        lore.add("§8 This mushroom will instantly form a box of ");
        lore.add("§8 obsidian around you that lasts for 10 seconds");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        ObsiMushroom = item;

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "mushroom"), ObsiMushroom);
        recipe.shape("XXX", "XOX", "XXX");
        recipe.setIngredient('X', Material.OBSIDIAN);
        recipe.setIngredient('O', Material.BROWN_MUSHROOM);

        Bukkit.addRecipe(recipe);
    }
}
