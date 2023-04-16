package com.mrccode.regions.data.supports;

import org.bukkit.Location;

public class IncompleteSelection {
    private Location l1 = null;
    private Location l2 = null;

    public IncompleteSelection() {}

    public void setLocation(Location location) {
        if (l1 != null) {
            l2 = location;
            return;
        }
        l1 = location;
    }

    public Location getFirst() { return l1; }
    public Location getSecond() { return l2; }

    public boolean closable() {
        return l1 != null && l2 != null;
    }
}
