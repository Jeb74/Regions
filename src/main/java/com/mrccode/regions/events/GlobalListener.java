package com.mrccode.regions.events;

import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.UUID;

public class GlobalListener implements Listener {

    private final ArrayList<UUID> allowed = new ArrayList<>();

    public void onQuit(PlayerQuitEvent e) {}

    public void onChat(AsyncChatEvent e) {}
}
