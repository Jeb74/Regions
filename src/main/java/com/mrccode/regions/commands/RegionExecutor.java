package com.mrccode.regions.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RegionExecutor implements CommandExecutor {

    private final String REGION_STATE_COMMAND = "rg";
    @Override
    public boolean onCommand(@NotNull CommandSender s, @NotNull Command c, @NotNull String alias, @NotNull String[] args) {
        boolean isPlayer = s instanceof Player;

        return false;
    }
}
