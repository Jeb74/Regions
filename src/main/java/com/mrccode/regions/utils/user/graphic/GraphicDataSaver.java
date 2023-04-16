package com.mrccode.regions.utils.user.graphic;

import com.mrccode.regions.utils.user.graphic.interfaces.GUI;
import com.mrccode.regions.utils.user.graphic.interfaces.MainInterface;
import lombok.Builder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import xyz.sorridi.stone.utils.constructor.ConstructorCaller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Optional;
import java.util.Stack;
import java.util.UUID;

import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.TextColor.fromCSSHexString;

public abstract class GraphicDataSaver {

    private static final HashMap<UUID, Stack<GUI>> data = new HashMap<>();

    public static boolean hasInventory(Player p) {
        UUID uuid = p.getUniqueId();
        return data.containsKey(uuid) && !data.get(uuid).empty();
    }

    public static void add(Player p, Class<GUI> clazz) {
        UUID uuid = p.getUniqueId();
        GUI gui = ConstructorCaller.call(clazz, p).orElse(new MainInterface(p));
        if (!data.containsKey(uuid)) data.put(uuid, new Stack<>());
        data.get(uuid).push(gui);
    }

    public static void goBack(Player p) {
        Stack<GUI> inventories = data.get(p.getUniqueId());
        inventories.pop();
        p.openInventory(inventories.peek().getInventory());
    }

    public static void closeAll(Player p) {
        data.get(p.getUniqueId()).clear();
    }

}
