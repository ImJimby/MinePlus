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
import com.harry5573.mine.enums.LoggerEnum;
import com.harry5573.mine.enums.LoggerEnum.LogType;
import com.harry5573.mine.log.Logger;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author Harry5573
 */
public class CommandCreateMine extends MinePlusCommand {

    MinePlusPlugin plugin;
    
    public CommandCreateMine(MinePlusPlugin instance) {
        this.plugin = instance;
    }

    @Override
    public void init() {
        usage = "/mine create <name> <X%BlockID:Data,X%BlockID:Data>";
        description = "Create a mine";
        permission = "none";
    }

    @Override
    public void run(CommandSender sender, String[] args) {
        if (!this.senderIsPlayer(sender)) {
            Logger.log(LogType.NORMAL, "Not a console command!");
        }
        Player player = (Player) sender;

        player.sendMessage(this.getHelp());
    }

    @Override
    public void run(CommandSender sender) {
        if (!this.senderIsPlayer(sender)) {
            Logger.log(LogType.NORMAL, "Not a console command!");
        }
        Player player = (Player) sender;

        
        
    }
}
