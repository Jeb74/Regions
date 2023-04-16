package com.mrccode.regions.data.supports;

import com.mrccode.regions.data.IRegion;
import org.bukkit.Location;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.LinkedHashSet;

public abstract class RegisteredRegions {
    private static final Collection<IRegion> REGISTERED_REGIONS = new LinkedHashSet<>();

    public static boolean add(IRegion region) {
        return REGISTERED_REGIONS.add(region);
    }

    public static boolean remove(IRegion region) {
        return REGISTERED_REGIONS.remove(region);
    }

    @Nullable
    public static IRegion[] getFocussedRegions(Location location) {
        IRegion[] results;
        try {
            results = REGISTERED_REGIONS.stream().filter(r -> r.search(location)).toArray(IRegion[]::new);
        } catch (Exception ignored) {
            return null;
        }
        return results;
    }
}
