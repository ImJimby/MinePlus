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
import com.harry5573.mine.enums.MessageEnum;
import com.harry5573.mine.enums.MessageEnum.MessageType;
import com.harry5573.mine.storage.DataStore;
import com.harry5573.mine.util.MessageUtil;

/**
 *
 * @author Harry5573
 */
public class MessageManager {

    MinePlusPlugin plugin;
    
    public MessageManager(MinePlusPlugin instance) {
        this.plugin = instance;
    }
    
    /**
     * Loads all the messages into the HashMap
     */
    public void load() {
        DataStore.messages.put(MessageType.PREFIX, MessageUtil.translateToColorCode(plugin.cfManager.getMessagesConfig().getString("messages.prefix")));
    }
    
}
