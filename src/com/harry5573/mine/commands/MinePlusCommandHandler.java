/*Copyright (C) 2013-14

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.*/

package com.harry5573.mine.commands;

import com.harry5573.mine.MinePlusPlugin;
import java.util.Arrays;
import java.util.HashMap;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author Harry5573
 */
public class MinePlusCommandHandler implements CommandExecutor {

    MinePlusPlugin plugin;

    public MinePlusCommandHandler(MinePlusPlugin instance) {
        this.plugin = instance;
    }
    private HashMap<String, MinePlusCommand> subCommands = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String base = args.length > 0 ? args[0] : "";
        MinePlusCommand sc = subCommands.get(base);
        if (sc != null) {
            if (args.length > 1) {
                sc.run(sender, Arrays.copyOfRange(args, 1, args.length));
            } else {
                sc.run(sender);
            }
            return true;
        } else {
            showHelp(sender);
            return true;
        }
    }

    private void showHelp(CommandSender sender) {
        sender.sendMessage(ChatColor.GOLD + "___________________.[" + ChatColor.DARK_GREEN + " MinePlus Help " + ChatColor.GOLD + "].___________________");

        for (MinePlusCommand command : subCommands.values()) {
            String permission = command.getPermission();
            if (permission.equalsIgnoreCase("none") || sender.hasPermission(permission)) {
                sender.sendMessage(command.getHelp());
            }
        }
        sender.sendMessage(ChatColor.RED + "MinePlus Version " + plugin.cfManager.getVersion() + " by harry5573!");
        sender.sendMessage(ChatColor.GOLD + "------------------------------------------------");
    }

    public void registerCommand(String name, MinePlusCommand command) {
        subCommands.put(name, command);
        command.init();
    }
}
