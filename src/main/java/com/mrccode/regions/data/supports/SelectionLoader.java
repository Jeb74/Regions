package com.mrccode.regions.data.supports;

import com.mrccode.regions.data.Selection;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.UUID;

public abstract class SelectionLoader {
    private static final HashMap<UUID, LinkedList<IncompleteSelection>> SELECTION_BANK = new HashMap<>();

    public static int save(PlayerInteractEvent e) {
        UUID p = e.getPlayer().getUniqueId();
        if (!SELECTION_BANK.containsKey(p)) {
            SELECTION_BANK.put(p, new LinkedList<>());
        }
        LinkedList<IncompleteSelection> list = SELECTION_BANK.get(p);
        IncompleteSelection last = list.isEmpty() ? new IncompleteSelection() : list.getLast();
        if (last.closable()) {
            IncompleteSelection s = new IncompleteSelection();
            Location l = e.getInteractionPoint();
            s.setLocation(l != null ? l : e.getPlayer().getLocation());
            list.addLast(s);
        }
        else {
            Location l = e.getInteractionPoint();
            last.setLocation(l != null ? l : e.getPlayer().getLocation());
            if (last.closable()) return list.size();
        }
        return -list.size();
    }

    public static Selection[] get(UUID p) {
        LinkedList<IncompleteSelection> list = SELECTION_BANK.getOrDefault(p, new LinkedList<>());
        if (list.isEmpty()) return new Selection[0];
        return list.stream().map(Selection::new).toArray(Selection[]::new);
    }

    public static int getSize(UUID p) {
        return SELECTION_BANK.getOrDefault(p, new LinkedList<>()).size();
    }

}
