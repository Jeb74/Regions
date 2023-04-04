package com.mrccode.regions.data;

import org.bukkit.Location;
import org.bukkit.World;

import javax.annotation.Nullable;

public class RequiredLocation {
    protected final World world;
    protected final int x;
    protected final int z;

    public RequiredLocation(Location l, @Nullable Integer reqHeight, @Nullable Integer reqHeightDown) {
        x = (int)l.getX();
        z = (int)l.getZ();
        world = l.getWorld();
    }
}
