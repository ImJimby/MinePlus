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

package com.harry5573.mine.util;

import com.harry5573.mine.mines.MineBlock;

/**
 *
 * @author Harry5573
 */
public class CompositionEntry {

    private MineBlock block;
    private double chance;

    public CompositionEntry(MineBlock block, double chance) {
        this.block = block;
        this.chance = chance;
    }

    public MineBlock getBlock() {
        return block;
    }

    public double getChance() {
        return chance;
    }
}
