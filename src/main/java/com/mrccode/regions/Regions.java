package com.mrccode.regions;

import org.bukkit.plugin.java.JavaPlugin;

public final class Regions extends JavaPlugin {

    public static JavaPlugin resource;
    @Override
    public void onEnable() {
        resource = this;
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
