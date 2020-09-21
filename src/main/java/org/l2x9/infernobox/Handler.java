package org.l2x9.infernobox;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Handler implements Runnable {
    Player player;
    Infernobox plugin;

    public Handler(Player player, Infernobox plugin) {
        this.player = player;
        this.plugin = plugin;
    }

    @Override
    public void run() {
        String[] locationFromData = plugin.getData().getString(player.getName().concat(".Location")).split("/");
        double x = Double.parseDouble(locationFromData[0]);
        double y = Double.parseDouble(locationFromData[1]);
        double z = Double.parseDouble(locationFromData[2]);
        World world = Bukkit.getWorld(locationFromData[3]);
        Location location = new Location(world, x, y, z);
        plugin.getData().set(player.getName().concat(".HasWaited"), true);
        plugin.saveData();
        player.teleport(location);
    }
}
