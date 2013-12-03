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

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author Harry5573
 */
public abstract class MinePlusCommand {

    protected String usage = "FIX";
    protected String description = "FIX";
    protected String permission = "FIX";

    public abstract void init();

    public abstract void run(CommandSender sender, String[] args);

    public abstract void run(CommandSender sender);

    public String getUsage() {
        return usage;
    }

    public String getDescription() {
        return description;
    }

    public String getPermission() {
        return permission;
    }

    public String getHelp() {
        return ChatColor.LIGHT_PURPLE + usage + ChatColor.GRAY + " - " + description;
    }

    public boolean hasPermission(CommandSender sender) {
        if (permission.equalsIgnoreCase("none")) {
            return true;
        }
        return sender.hasPermission(permission);
    }

    public boolean senderIsPlayer(CommandSender sender) {
        if (sender instanceof Player) {
            return true;
        }
        return false;
    }
}
