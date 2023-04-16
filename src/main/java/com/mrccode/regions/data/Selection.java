package com.mrccode.regions.data;

import com.mrccode.regions.data.supports.IncompleteSelection;
import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

public class Selection {
    protected final World world;

    protected final int firstX, secondX;
    protected final int firstZ, secondZ;
    protected final int lowestY;

    private int lowerIncrease = 0;
    private int upperIncrease = 0;

    public Selection(@NotNull Location location1, @NotNull Location location2) {
        world = location1.getWorld();
        firstX = location1.getBlockX();
        secondX = location2.getBlockX();
        firstZ = location1.getBlockZ();
        secondZ = location2.getBlockZ();
        int y1 = location1.getBlockY(), y2 = location2.getBlockY();
        lowestY = Math.min(y1, y2);
        upperIncrease = Math.max(y1, y2) - Math.min(y1, y2);
    }

    public Selection(@NotNull IncompleteSelection incompleteSelection) {
        this(incompleteSelection.getFirst(), incompleteSelection.getSecond());
    }

    public void setUpperIncrease(int increase) {
        upperIncrease = increase;
    }

    public void setUpperIncrease(String increase) {
        if (increase.equalsIgnoreCase("max")) upperIncrease = world.getMaxHeight() - lowestY;
    }

    public void setLowerIncrease(int increase) {
        lowerIncrease = Math.negateExact(increase);
    }

    public void setLowerIncrease(String increase) {
        if (increase.equalsIgnoreCase("min")) lowerIncrease = lowestY - world.getMinHeight();
    }

    public int getUpperLimit() {
        return lowestY + upperIncrease;
    }

    public int getLowerLimit() {
        return lowestY - lowerIncrease;
    }

    public int getY() { return lowestY; }
    public int getUI() { return upperIncrease; }
    public int getLI() { return lowerIncrease; }
}
