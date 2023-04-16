package com.mrccode.regions.data;

import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class RegionGroup implements IRegion {

    private static final int GROUP_CONSTANT = 0x1723;

    private final String name;
    private int precedence;
    private byte tags = 0;
    private final List<Region> cuboids = new ArrayList<>();

    public RegionGroup(String name, @Nullable Integer precedence, @Nullable TAGS[] tags, @Nullable String[] args) {
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
    public World getWorld() {
        return cuboids.get(0).getWorld();
    }

    @Override
    @Deprecated
    public boolean hasLocation(Location location) {
        return cuboids.stream().anyMatch(r -> r.hasLocation(location));
    }

    @Override
    public boolean search(Optional<Region> region) {
        return region.filter(cuboids::contains).isPresent();
    }

    @Override
    public boolean search(Location location) {
        return cuboids.stream().anyMatch(r -> r.hasLocation(location));
    }
    @Override
    public int hashCode() {
        int result = (name.hashCode() ^ getWorld().getName().hashCode()) >>> 1;
        result = (result << 5) - result;
        result = ((result << 5) - result) + precedence;
        return ((result << 5) - result) * GROUP_CONSTANT;
    }
}
