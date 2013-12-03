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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Harry5573
 */
public class ConfigManager {

    MinePlusPlugin plugin;
    
    private File mineFolder;
    
    public ConfigManager(MinePlusPlugin instance) {
        this.plugin = instance;
        this.mineFolder = new File(plugin.getDataFolder() + File.separator + "Mines");
    }
    
    /**
     * Called when we want to load everything
     */
    public void load() {
        plugin.saveDefaultConfig();
        
        this.checkMineFolder();
    }

    private void checkMineFolder() {
        if (!mineFolder.exists()) {
            Logger.log(LogType.DEBUG, "Mine folder did not exist... Creating!");
            mineFolder.mkdir();
            Logger.log(LogType.DEBUG, "Mine folder created successfuly");
        }
    }
    
    /**
     * Returns a list of all the mine configurations
     * @return 
     */
    public List<File> getListOfMineConfigs() {
        List<File>returnme = new ArrayList<>();
        
        for (File file : mineFolder.listFiles()) {
            if (file.getName().contains(".yml")) {
                returnme.add(file);
            }
        }
        
        return returnme;
    }
    
    /**
     * Returns the plugins version
     * @return 
     */
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }
}
