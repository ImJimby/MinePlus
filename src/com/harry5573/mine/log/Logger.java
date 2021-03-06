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

package com.harry5573.mine.log;

import com.harry5573.mine.MinePlusPlugin;
import com.harry5573.mine.enums.LoggerEnum.LogType;

/**
 *
 * @author Harry5573
 */
public class Logger {

    /**
     * Simple method to manage internal logging
     *
     * @param type
     * @param message
     */
    public static void log(LogType type, String message) {
        if (type == LogType.NORMAL) {
            MinePlusPlugin.inst().getServer().getLogger().info(message);
        } else if (type == LogType.DEBUG && MinePlusPlugin.inst().getConfig().getBoolean("debug")) {
            MinePlusPlugin.inst().getServer().getLogger().info("[DEBUG] " + message);
        } else if (type == LogType.SEVERE) {
            MinePlusPlugin.inst().getServer().getLogger().info("[SEVERE] " + message);
        }
    }
}