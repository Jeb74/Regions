package com.mrccode.regions.utils.user.graphic.interfaces;

import com.mrccode.regions.data.supports.SelectionLoader;
import com.mrccode.regions.utils.user.graphic.interfaces.items.Reason;
import com.mrccode.regions.utils.user.graphic.interfaces.items.ReceptiveItem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.TextColor.fromCSSHexString;

public class MainInterface implements GUI {
    private final Inventory inventory;

    public MainInterface(Player p) {
        inventory = Bukkit.createInventory(p, 7, text("Region options", fromCSSHexString("#501179")));
        int size = SelectionLoader.getSize(p.getUniqueId());
        String lore = "You have " + size + "selections available";

        ReceptiveItem close = new ReceptiveItem(Reason.ERASER, "Close", "#C21A26").setMaterial(Material.BARRIER);

        ReceptiveItem filler = new ReceptiveItem(Reason.FILLER).setMaterial(Material.GRAY_STAINED_GLASS_PANE);

        ReceptiveItem openSelection = new ReceptiveItem(
                Reason.LINK,
                "Open Selection List",
                "#1AC227",
                TextDecoration.ITALIC,
                lore,
                "#1AC26E"
        ).setMaterial((size > 0) ? Material.GRASS_BLOCK : Material.COARSE_DIRT);
        openSelection.setUpdate(i -> {
            int curSize = SelectionLoader.getSize(p.getUniqueId());
            String newLore = curSize > 0 ? "You have " + curSize + "selections available" : "You have no pending selection";
            List<Component> list = new LinkedList<>();
            list.add(text(newLore, fromCSSHexString(curSize > 0 ? "#1AC26E" : "#CD2109")));
            i.getItemMeta().lore(list);
            i.setMaterial((curSize > 0) ? Material.GRASS_BLOCK : Material.COARSE_DIRT);
        });

        ReceptiveItem regionConfirmation = new ReceptiveItem(
                Reason.LINK,
                size > 0 ? "Complete the Region" : "No Region to be completed",
                size > 0 ? "#1AC227" : "#CD2109",
                TextDecoration.ITALIC
        ).setMaterial(size > 0 ? Material.ANVIL : Material.DAMAGED_ANVIL);
        regionConfirmation.setUpdate(i -> {
            int curSize = SelectionLoader.getSize(p.getUniqueId());
            String newName = curSize > 0 ? "Complete the Region" : "No Region to be completed";
            i.getItemMeta().displayName(text(newName, fromCSSHexString(curSize > 0 ? "#1AC227" : "#CD2109"), TextDecoration.ITALIC));
            i.setMaterial(curSize > 0 ? Material.ANVIL : Material.DAMAGED_ANVIL);
        });

        ReceptiveItem regionList = new ReceptiveItem(
                Reason.LINK,
                "Open Region List",
                "#2F97E3",
                TextDecoration.ITALIC
        ).setMaterial(Material.CRAFTING_TABLE);

        inventory.setItem(0, close);
        inventory.setItem(1, filler);
        inventory.setItem(2, openSelection);
        inventory.setItem(3, regionConfirmation);
        inventory.setItem(4, regionList);
        inventory.setItem(5, filler);
        inventory.setItem(6, close);
        p.openInventory(inventory);
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }
}
