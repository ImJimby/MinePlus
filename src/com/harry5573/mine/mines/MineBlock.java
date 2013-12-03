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

package com.harry5573.mine.mines;

/**
 *
 * @author Harry5573
 */
public class MineBlock {

    private int blockId;
    private byte data;

    public MineBlock(int blockId) {
        this.blockId = blockId;
        data = 0;
    }

    public MineBlock(int blockId, byte data) {
        this.blockId = blockId;
        this.data = data;
    }

    public MineBlock(String self) {
        String[] bits = self.split(":");
        if (bits.length != 2) {
            throw new IllegalArgumentException("String form of SerializableBlock didn't have exactly 2 numbers");
        }
        try {
            blockId = Integer.valueOf(bits[0]).intValue();
            data = Byte.valueOf(bits[1]).byteValue();
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("Unable to convert id to integer and data to byte");
        }
    }

    public int getBlockId() {
        return blockId;
    }

    public byte getData() {
        return data;
    }

    public String toString() {
        return blockId + ":" + data;
    }

    public boolean equals(Object o) {
        return o instanceof MineBlock && (this.blockId == ((MineBlock) o).blockId && this.data == ((MineBlock) o).data);
    }
}
