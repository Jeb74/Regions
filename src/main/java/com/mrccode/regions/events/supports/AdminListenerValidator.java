package com.mrccode.regions.events.supports;

import com.mrccode.regions.item.RegionItem;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.LinkedList;
import java.util.UUID;
import java.util.function.BooleanSupplier;

public abstract class AdminListenerValidator {

    private static final LinkedList<UUID> ALLOWED_PLAYERS = new LinkedList<>();

    public static boolean setAllowedPlayers(LinkedList<UUID> players) {
        return ALLOWED_PLAYERS.addAll(players);
    }

    public static void addAllowedPlayer(UUID player) {
        ALLOWED_PLAYERS.addLast(player);
    }

    protected final boolean isNotAllowed(Player p) {
        return !ALLOWED_PLAYERS.contains(p.getUniqueId());
    }

    protected final boolean isPerfect(ItemStack item) {
        BooleanSupplier sup = () -> {
            ItemMeta meta = item.getItemMeta();
            boolean e = meta.hasDisplayName() && ((TextComponent)meta.displayName()).content().contains("Ruler");
            e = e && meta.hasLore() && meta.isUnbreakable();
            return e;
        };
        return item instanceof RegionItem &&
                item.getType().equals(Material.NETHERITE_SHOVEL) &&
                item.getItemMeta().hasEnchant(Enchantment.DURABILITY) &&
                item.hasItemMeta() &&
                sup.getAsBoolean();
    }
}
