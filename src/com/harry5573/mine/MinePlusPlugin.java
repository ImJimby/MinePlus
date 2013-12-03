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

package com.harry5573.mine;

import com.harry5573.mine.managers.ConfigManager;
import com.harry5573.mine.enums.LoggerEnum.LogType;
import com.harry5573.mine.log.Logger;
import com.harry5573.mine.managers.CommandManager;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Harry5573
 */
public class MinePlusPlugin extends JavaPlugin {

    public static MinePlusPlugin plugin;
    
    /**
     * Get the current instance of MinePlus
     * @return 
     */
    public static MinePlusPlugin inst() {
        return plugin;
    }
    
    /**
     * Classes
     */
    public ConfigManager cfManager;
    public CommandManager commandManager;

    /**
     * Called on plugin enable
     */
    @SuppressWarnings("deprecation")
    @Override
    public void onEnable() {
        plugin = this;

        String ver = this.getDescription().getVersion();
        Logger.log(LogType.NORMAL, "Plugin version " + ver + " starting up!");

        if (!this.checkHardDependencies()) {
            return;
        }
        
        this.registerClasses();

        this.cfManager.load();
        this.commandManager.registerCommands();

        Logger.log(LogType.NORMAL, "Plugin version " + ver + " started!");
    }

    /**
     * Called on plugin disable
     */
    @SuppressWarnings("deprecation")
    @Override
    public void onDisable() {
        String ver = this.getDescription().getVersion();
        Logger.log(LogType.NORMAL, "Plugin version " + ver + " shutting down!");

        Logger.log(LogType.NORMAL, "Plugin version " + ver + " shutdown!");
    }

    /**
     * Registers our classes
     */
    public void registerClasses() {
        this.cfManager = new ConfigManager(this);
        this.commandManager = new CommandManager(this);
    }

    /**
     * Checks that all the needed dependencies are installed on the server
     */
    public boolean checkHardDependencies() {
        PluginManager pm = this.getServer().getPluginManager();
        for (Iterator<String> it = this.getDescription().getSoftDepend().iterator(); it.hasNext();) {
            String plugin = it.next();
            if (pm.getPlugin(plugin) == null) {
                Logger.log(LogType.SEVERE, "Could not find dependencie " + plugin + " plugin shutting down!");
                this.suicide();
                return false;
            } else {
                Logger.log(LogType.SEVERE, "Registered Dependencie " + plugin + "!");
            }
        }
        
        return true;
    }
    
    /**
     * Disables the plugin
     */
    public void suicide() {
        Bukkit.getServer().getPluginManager().disablePlugin(this);
    }
}
