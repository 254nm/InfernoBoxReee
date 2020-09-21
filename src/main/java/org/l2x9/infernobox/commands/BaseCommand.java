package org.l2x9.infernobox.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.l2x9.infernobox.Infernobox;

import java.util.List;

public class BaseCommand implements CommandExecutor {
    Infernobox plugin;

    public BaseCommand(Infernobox infernobox) {
        plugin = infernobox;
    }

    /**
     * Executes the given command, returning its success
     *
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    Passed command arguments
     * @return true if a valid command, otherwise false
     * @retard true if the sender is a fucking retard, otherwise false
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("blacklist.blacklist")) {
            if (args.length > 0) {
                List<String> list = plugin.getData().getStringList("BlackListed-Players");
                switch (args[0]) {
                    case "on":
                        if (args.length > 1) {
                            OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                            if (!list.contains(target.getName())) {
                                list.add(target.getName());
                                plugin.getData().set("BlackListed-Players", list);
                                plugin.saveData();
                                sendMessage(sender, "[&b&lInfernoBox&r&3&lBox&r]&a Blacklisted&r&e " + target.getName() + " &r&aSuccessfully");
                            } else {
                                sendMessage(sender, "[&b&lInfernoBox&r&3&lBox&r]&c" + target.getName() + "&r&6 Is already blacklisted");
                            }
                        } else {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "[&b&lInfernoBox&r&3&lBox&r]&6 Please see&r&c /infernobox&6 help for help"));
                        }
                        break;
                    case "off":
                        if (args.length > 1) {
                            OfflinePlayer target2 = Bukkit.getOfflinePlayer(args[1]);
                            if (list.contains(target2.getName())) {
                                list.remove(target2.getName());
                                plugin.getData().set("BlackListed-Players", list);
                                plugin.saveData();
                                sendMessage(sender, "[&b&lInfernoBox&r&3&lBox&r]&a Un-blacklisted&r&e " + target2.getName() + " &r&aSuccessfully");
                            } else {
                                sendMessage(sender, "[&b&lInfernoBox&r&3&lBox&r]&c" + target2.getName() + "&r&6 Is not blacklisted");
                            }
                        } else {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "[&b&lInfernoBox&r&3&lBox&r]&6 Please see&r&c /infernobox&6 help for help"));
                        }
                        break;
                    case "help":
                        sendMessage(sender, getPrefix() + "&1---&r " + getPrefix() + "&6Help &r&1---");
                        sendMessage(sender, getPrefix() + "&6 /infernobox on <Player> |&r&e Blacklists the specified player");
                        sendMessage(sender, getPrefix() + "&6 /infernobox off <Player>  |&r&e Un-blacklists the specified player");
                        sendMessage(sender, getPrefix() + "&6 /infernobox reload |&r&e Shows help for the plugin");
                        break;
                    case "reload":
                        plugin.reloadConfig();
                        sendMessage(sender, getPrefix() + "&aConfiguration file reloaded successfully");
                        break;
                    default:
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "[&b&lInfernoBox&r&3&lBox&r]&6 Please see&r&c /infernobox&6 help for help"));
                        break;
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "[&b&lInfernoBox&r&3&lBox&r]&6 Please see&r&c /infernobox&6 help for help"));
            }
        } else {
            sendMessage(sender, "No permission");
        }
        return true;
    }

    private String getPrefix() {
        return "[&b&lInfernoBox&r&3&lBox&r] ";
    }

    private void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}
