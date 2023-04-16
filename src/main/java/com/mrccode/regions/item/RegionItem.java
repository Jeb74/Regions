package com.mrccode.regions.item;

import net.kyori.adventure.text.Component;

import static java.awt.SystemColor.text;
import static net.kyori.adventure.text.format.TextColor.fromCSSHexString;
import static net.kyori.adventure.text.Component.text;

import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.LinkedList;
import java.util.List;

public class RegionItem extends ItemStack {

    public RegionItem(Player p) {
        this.setType(Material.NETHERITE_SHOVEL);
        this.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE);
        this.addEnchantment(Enchantment.DURABILITY, 10);
        ItemMeta meta = this.getItemMeta();
        meta.setUnbreakable(true);
        meta.displayName(text(p.getName() + "'s Ruler", fromCSSHexString("#1682C8")));
        meta.lore(addDefaultLore());
    }

    public void setCurrentSelectionCorner(World world, int x, int y, int z) {
        ItemMeta meta = this.getItemMeta();
        List<Component> lore = new LinkedList<>();
        lore.add(text("Last corner:", fromCSSHexString("#7C868C")));
        lore.add(
                text("World: ", fromCSSHexString("#7C868C"), TextDecoration.ITALIC)
                        .append(text(world.getName(), fromCSSHexString("#18994B")))
        );
        lore.add(
                text("X: ", fromCSSHexString("#7C868C"), TextDecoration.ITALIC)
                        .append(text(x, fromCSSHexString("#A0DA33")))
        );
        lore.add(
                text("Z: ", fromCSSHexString("#7C868C"), TextDecoration.ITALIC)
                        .append(text(z, fromCSSHexString("#A0DA33")))
        );
        lore.add(
                text("Y: ", fromCSSHexString("#7C868C"), TextDecoration.ITALIC)
                        .append(text(y, fromCSSHexString("#A0DA33")))
        );
        meta.lore(lore);
    }

    private List<Component> addDefaultLore() {
        List<Component> lore = new LinkedList<>();
        lore.add(text("Last corner:", fromCSSHexString("#7C868C")));
        lore.add(
                text("World: ", fromCSSHexString("#7C868C"), TextDecoration.ITALIC)
        );
        lore.add(
                text("X: ", fromCSSHexString("#7C868C"), TextDecoration.ITALIC)
        );
        lore.add(
                text("Z: ", fromCSSHexString("#7C868C"), TextDecoration.ITALIC)
        );
        lore.add(
                text("Y: ", fromCSSHexString("#7C868C"), TextDecoration.ITALIC)
        );
        return lore;
    }

    public void resetLore() {
        this.getItemMeta().lore(addDefaultLore());
    }

}
