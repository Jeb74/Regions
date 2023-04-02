package com.mrccode.regions.data;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class Region {

    private final String name;
    private int precedence = 0;
    private byte tags;

    public Region(String name, @Nullable Integer precedence) {
        this.name  = name;
        this.precedence = precedence != null ? precedence : 0;
    }

    public Region(String name) {
        this.name  = name;
    }

    public Region(String name) {
        this.name  = name;
        this.precedence = precedence != null ? precedence : 0;
    }

    public Region(String name, @Nullable Integer precedence) {
        this.name  = name;
        this.precedence = precedence != null ? precedence : 0;
    }

    public enum TAGS {
        UNMODIFIABLE("unmodifiable", 1), //MODIFIABLE
        UNSTACKABLE("unstackable", 2), //STACKABLE
        EXPANDABLE("expandable", 4), //NOT EXPANDABLE
        INVIOLABLE("inviolable", 8), //OVERRIDABLE
        GLOBAL("global", 16), //LOCAL
        SHAPELESS("shapeless", 32), //CUBOID
        AGGLOMERATION("agglomeration", 64); //SINGLE

        public final int code;
        public final String name;
        TAGS(String name, int code) {this.name = name; this.code = code;}
    }
}
