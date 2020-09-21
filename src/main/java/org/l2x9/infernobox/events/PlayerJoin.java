package org.l2x9.infernobox.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.l2x9.infernobox.Handler;
import org.l2x9.infernobox.Infernobox;

import java.util.List;

public class PlayerJoin implements Listener {
    Infernobox plugin;

    public PlayerJoin(Infernobox infernobox) {
        plugin = infernobox;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        List<String> blockedPlayers = plugin.getData().getStringList("BlackListed-Players");
        if (blockedPlayers.contains(event.getPlayer().getName())) {
            Player player = event.getPlayer();
            //Teleport the player to the ban box
            int x = plugin.getConfig().getInt("Ban-world.x");
            int y = plugin.getConfig().getInt("Ban-world.y");
            int z = plugin.getConfig().getInt("Ban-world.z");
            World world = Bukkit.getWorld(plugin.getConfig().getString("Ban-world.name"));
            player.teleport(new Location(world, x, y, z));
            Bukkit.getScheduler().runTaskLater(plugin, new Handler(player, plugin), plugin.getConfig().getInt("Player-in-box-time"));
        }
    }
}
