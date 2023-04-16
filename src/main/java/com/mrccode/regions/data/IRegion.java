package com.mrccode.regions.data;

import org.bukkit.Location;
import org.bukkit.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;

public interface IRegion {

    String getName();

    IRegion.TAGS[] getTags();

    World getWorld();

    boolean hasLocation(Location location);

    boolean search(Optional<Region> region);

    default boolean search(Location location) {
        return hasLocation(location);
    }

    public enum TAGS {
        // MODIFIER                                     //DEFAULT
        /**
         * Makes the region not modifiable, and it can only be modified by console.
         */
        UNMODIFIABLE("unmodifiable", 1),    //MODIFIABLE

        /**
         * Makes the region not stackable: <p>None can create a region over it.</p>
         */
        UNSTACKABLE("unstackable", 2),      //STACKABLE

        /**
         * Makes the region expandable to the top, to the bottom or both.
         * <p>
         * This means you don't have to define a specified upper or lower increase depending on the direction
         * you choose to expand.
         */
        EXPANDABLE("expandable", 4),        //NOT EXPANDABLE

        /**
         * Makes some specified region's flag inviolable:
         * <p>
         * if there are other regions over it, and they have different setups,
         * you can choose a specific flag to be not overridable by other regions in the same space,
         * ignoring precedence value.
         * </p>
         */
        INVIOLABLE("inviolable", 8),        //OVERRIDABLE

        /**
         * Makes the region global, ignoring any specified size.
         */
        GLOBAL("global", 16),               //LOCAL

        /**
         * Makes all region's rules not ignorable even by administrators.
         */
        RESTRICTED("restricted", 32);       //NOT RESTRICTED

        public final byte code;
        public final String name;
        TAGS(String name, int code) {this.name = name; this.code = (byte)code;}

        @Nullable
        protected static TAGS[] unPack(byte tags) {
            if (tags == 0) return null;
            LinkedList<TAGS> list = new LinkedList<>();
            for (TAGS tag : TAGS.values()) {
                if (tags == 0) break;
                if (tags % 2 != 0) {
                    list.add(tag);
                }
                tags = (byte) (tags >> 1);
            }
            return list.toArray(new TAGS[0]);
        }

        protected static byte pack(TAGS[] tags) {
            byte compact = 0;
            for (TAGS tag : tags) {
                compact += tag.code;
            }
            return compact;
        }
    }


}
