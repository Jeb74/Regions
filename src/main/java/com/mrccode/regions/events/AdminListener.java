package com.mrccode.regions.events;

import com.mrccode.regions.events.supports.AdminListenerValidator;
import com.mrccode.regions.utils.user.Messages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import static com.mrccode.regions.data.supports.SelectionLoader.save;

public class AdminListener extends AdminListenerValidator implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (isNotAllowed(p) || !e.hasItem()) return;
        ItemStack item = e.getItem();
        if (isPerfect(item)) {
            if (e.getAction().isRightClick()) {
                p.sendMessage(Messages.generateSelectionMessage(save(e)));
            }
            else {
            }
        }
    }
}
