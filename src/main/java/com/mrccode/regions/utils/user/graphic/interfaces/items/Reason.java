package com.mrccode.regions.utils.user.graphic.interfaces.items;

public enum Reason {
    FILLER(-1),
    LINK(-2),
    ERASER(-3);

    private final int value;

    Reason(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
