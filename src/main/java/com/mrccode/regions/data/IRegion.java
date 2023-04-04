package com.mrccode.regions.data;

import org.bukkit.Location;

import java.util.ArrayList;

public interface IRegion {

    String getName();

    Region.TAGS[] getTags();

    boolean hasLocation(Location location);

    enum TAGS {
        // MODIFIER                                     //DEFAULT
        UNMODIFIABLE("unmodifiable", 1),    //MODIFIABLE
        UNSTACKABLE("unstackable", 2),      //STACKABLE
        EXPANDABLE("expandable", 4),        //NOT EXPANDABLE
        INVIOLABLE("inviolable", 8),        //OVERRIDABLE
        GLOBAL("global", 16),               //LOCAL
        SHAPELESS("shapeless", 32);         //CUBOID

        public final byte code;
        public final String name;
        TAGS(String name, int code) {this.name = name; this.code = (byte)code;}

        public static TAGS[] unPack(byte tags) {
            ArrayList<TAGS> list = new ArrayList<>();
            for (TAGS tag : TAGS.values()) {
                if (tags == 0) break;
                if (tags % 2 != 0) {
                    list.add(tag);
                }
                tags = (byte) (tags >> 1);
            }
            TAGS[] arr = new TAGS[list.size()];
            list.toArray(arr);
            return arr;
        }

        public static byte pack(TAGS[] tags) {
            byte compact = 0;
            for (TAGS tag : tags) {
                compact += tag.code;
            }
            return compact;
        }
    }


}
