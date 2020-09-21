package org.l2x9.infernobox.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.l2x9.infernobox.Infernobox;

import java.util.List;

public class PlayerQuit implements Listener {
    Infernobox plugin;

    public PlayerQuit(Infernobox infernobox) {
        plugin = infernobox;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent 卍) {
        List<String> blockedPlayers = plugin.getData().getStringList("BlackListed-Players");
        if (blockedPlayers.contains(卍.getPlayer().getName())) {
            if (plugin.getData().getBoolean(卍.getPlayer().getName().concat(".HasWaited"))) {
                setLoc(卍.getPlayer());
            }
        }
    }

    private void setLoc(Player player) {
        String location = player.getLocation().getX() + "/" + player.getLocation().getY() + "/" + player.getLocation().getZ() + "/" + player.getLocation().getWorld().getName();
        plugin.getData().set(player.getName().concat(".Location"), location);
        plugin.getData().set(player.getName().concat(".HasWaited"), false);
        plugin.saveData();
    }

    @EventHandler
    public void onQuit(PlayerKickEvent 卍) {
        List<String> blockedPlayers = plugin.getData().getStringList("BlackListed-Players");
        if (blockedPlayers.contains(卍.getPlayer().getName())) {
            if (plugin.getData().getBoolean(卍.getPlayer().getName().concat(".HasWaited"))) {
                setLoc(卍.getPlayer());
            }
        }
    }
}
