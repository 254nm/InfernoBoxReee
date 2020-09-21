package org.l2x9.infernobox.events;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.l2x9.infernobox.Infernobox;

import java.util.List;

public class PlayerMove implements Listener {
    Infernobox plugin;

    public PlayerMove(Infernobox infernobox) {
        plugin = infernobox;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        List<String> blockedPlayers = plugin.getData().getStringList("BlackListed-Players");
        if (blockedPlayers.contains(event.getPlayer().getName())) {
            if (!plugin.getData().getBoolean(event.getPlayer().getName().concat(".HasWaited"))) {
                Location to = event.getFrom();
                to.setPitch(event.getTo().getPitch());
                to.setYaw(event.getTo().getYaw());
                event.setTo(to);
            }
        }
    }
}