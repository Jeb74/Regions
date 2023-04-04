package com.mrccode.regions.data;

import org.bukkit.Location;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class Region implements IRegion {

    private final String name;
    private int precedence;
    private byte tags;

    private int defaultY        = Integer.MAX_VALUE;
    private int upperIncrease   = Integer.MAX_VALUE;
    private int lowerIncrease   = Integer.MAX_VALUE;
    private RequiredLocation[] location = new RequiredLocation[2];

    public Region(String name, @Nullable Integer precedence, @Nullable TAGS[] tags, @Nullable String[] args) {
        this.name = name;
        this.precedence = precedence != null ? precedence : 0;
        this.tags = tags != null ? TAGS.pack(tags) : 0;
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
    public boolean hasLocation(Location location) {
        if (Arrays.stream(this.location).noneMatch(l -> location.getWorld().equals(l.world))) return false;
        int[] x = {this.location[0].x, this.location[1].x};
        int[] z = {this.location[0].z, this.location[1].z};
        Arrays.sort(x);
        Arrays.sort(z);
        int xl = (int)location.getX(), zl = (int)location.getZ(), yl = (int)location.getY();
        return (x[1] >= xl && xl >= x[0]) &&
                (z[1] >= zl && zl >= z[0]) &&
                (defaultY + upperIncrease >= yl && yl >= defaultY - lowerIncrease);
    }
}
