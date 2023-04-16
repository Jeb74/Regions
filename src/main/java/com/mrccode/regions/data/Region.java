package com.mrccode.regions.data;

import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Optional;

public final class Region implements IRegion {

    private final String name;
    private int precedence;
    private byte tags;
    private final Selection area;

    public Region(@NotNull String name, @NotNull Selection area, @Nullable Integer precedence) {
        this.name = name;
        this.area = area;
        this.precedence = precedence != null ? precedence : 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public TAGS[] getTags() {
        return TAGS.unPack(tags);
    }

    @Override
    public World getWorld() {
        return area.world;
    }

    @Override
    public boolean hasLocation(Location location) {
        if (!area.world.equals(location.getWorld())) return false;
        int[] x = {area.firstX, area.secondX};
        int[] z = {area.firstZ, area.secondZ};
        Arrays.sort(x);
        Arrays.sort(z);
        int xl = (int)location.getX(), zl = (int)location.getZ(), yl = (int)location.getY();
        return (x[1] >= xl && xl >= x[0]) &&
                (z[1] >= zl && zl >= z[0]) &&
                (area.getUpperLimit() >= yl && yl >= area.getLowerLimit());
    }

    @Override
    public boolean search(Optional<Region> region) {
        return region.isPresent() && region.get().equals(this);
    }

    @Override
    public int hashCode() {
        int x1 = area.firstX, x2 = area.secondX;
        int z1 = area.firstZ, z2 = area.secondZ;
        int y = area.getY();
        int yUP = area.getUI(), yDOWN = area.getLI();
        int precedence = this.precedence;

        int result = (area.world.hashCode() ^ name.hashCode()) >>> 1;
        result = (result << 5) - result;
        result = ((result << 5) - result) + x1;
        result = ((result << 5) - result) + z1;
        result = ((result << 5) - result) + x2;
        result = ((result << 5) - result) + z2;
        result = ((result << 5) - result) * (y != 0 ? y : yUP + yDOWN);
        result = ((result << 5) - result) + yUP * yDOWN + precedence;

        return result;
    }
}
