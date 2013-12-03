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

package com.harry5573.mine.managers;

import com.harry5573.mine.MinePlusPlugin;
import com.harry5573.mine.enums.LoggerEnum;
import com.harry5573.mine.enums.LoggerEnum.LogType;
import com.harry5573.mine.log.Logger;
import java.io.File;

/**
 *
 * @author Harry5573
 */
public class MineManager {

    MinePlusPlugin plugin;

    public MineManager(MinePlusPlugin instance) {
        this.plugin = instance;
    }

    /**
     * Attempts to load all the Mines
     */
    public void loadMines() {
        for (File mineConfig : plugin.cfManager.getListOfMineConfigs()) {
            Logger.log(LogType.DEBUG, "Attempting to load mine " + mineConfig.getName().replaceAll(".yml", null));
            this.loadMine(mineConfig.getName().replaceAll(".yml", null));
        }
    }
    
    public void loadMine(String name) {
        
    }
}
