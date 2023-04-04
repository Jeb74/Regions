package com.mrccode.regions.data;

import org.bukkit.Location;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RegionGroup implements IRegion {

    private final String name;
    private int precedence = 0;
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
    @Deprecated
    public boolean hasLocation(Location location) {
        return cuboids.stream().anyMatch(r -> r.hasLocation(location));
    }

    public boolean search(Optional<Region> region) {
        return region.filter(cuboids::contains).isPresent();
    }

    public boolean search(Location location) {
        return cuboids.stream().anyMatch(r -> r.hasLocation(location));
    }
}
