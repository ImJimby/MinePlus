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

import com.harry5573.mine.util.CompositionEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

/**
 *
 * @author Harry5573
 */
public class Mine implements Iterable<Block>, Cloneable, ConfigurationSerializable {

    protected final int x1, y1, z1;
    protected final int x2, y2, z2;
    private World world;
    private String name;
    private Map<MineBlock, Double> composition;

    /**
     * Construct a Mine in the given World and xyz co-ordinates
     *
     * @param world - The Mine's world
     * @param x1 - X co-ordinate of corner 1
     * @param y1 - Y co-ordinate of corner 1
     * @param z1 - Z co-ordinate of corner 1
     * @param x2 - X co-ordinate of corner 2
     * @param y2 - Y co-ordinate of corner 2
     * @param z2 - Z co-ordinate of corner 2
     */
    public Mine(int x1, int y1, int z1, int x2, int y2, int z2, String name, World world, HashMap<MineBlock, Double> compAndChance) {
        this.x1 = Math.min(x1, x2);
        this.x2 = Math.max(x1, x2);
        this.y1 = Math.min(y1, y2);
        this.y2 = Math.max(y1, y2);
        this.z1 = Math.min(z1, z2);
        this.z2 = Math.max(z1, z2);
        this.name = name;
        this.world = world;
        this.composition = compAndChance;
    }

    /**
     * Reset the mine
     */
    public void reset() {
        List<CompositionEntry> probabilityMap = mineComposition(composition);

        Random rand = new Random();
        for (int x = x1; x <= x2; ++x) {
            for (int y = y1; y <= y2; ++y) {
                for (int z = z1; z <= z2; ++z) {
                    double r = rand.nextDouble();
                    for (CompositionEntry ce : probabilityMap) {
                        if (r <= ce.getChance()) {
                            world.getBlockAt(x, y, z).setTypeIdAndData(ce.getBlock().getBlockId(), ce.getBlock().getData(), false);
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * Grab an ArrayList of the mines Composition
     * @param compositionIn
     * @return 
     */
    public ArrayList<CompositionEntry> mineComposition(Map<MineBlock, Double> compositionIn) {
        ArrayList<CompositionEntry> probabilityMap = new ArrayList<>();
        Map<MineBlock, Double> composition = new HashMap<>(compositionIn);
        double max = 0;
        for (Map.Entry<MineBlock, Double> entry : composition.entrySet()) {
            max += entry.getValue().doubleValue();
        }
        //Pad the remaining percentages with air
        if (max < 1) {
            composition.put(new MineBlock(0), 1 - max);
            max = 1;
        }
        double i = 0;
        for (Map.Entry<MineBlock, Double> entry : composition.entrySet()) {
            double v = entry.getValue().doubleValue() / max;
            i += v;
            probabilityMap.add(new CompositionEntry(entry.getKey(), i));
        }
        return probabilityMap;
    }

    /**
     * Get the Location of the lower northeast corner of the Mine (minimum XYZ
     * co-ordinates).
     *
     * @return Location of the lower northeast corner
     */
    public Location getLowerNE() {
        return new Location(this.getWorld(), this.x1, this.y1, this.z1);
    }

    /**
     * Get the Location of the upper southwest corner of the Mine (maximum XYZ
     * co-ordinates).
     *
     * @return Location of the upper southwest corner
     */
    public Location getUpperSW() {
        return new Location(this.getWorld(), this.x2, this.y2, this.z2);
    }

    /**
     * Get the blocks in the Mine.
     *
     * @return The blocks in the Mine
     */
    public List<Block> getBlocks() {
        Iterator<Block> blockI = this.iterator();
        List<Block> copy = new ArrayList<>();
        while (blockI.hasNext()) {
            copy.add(blockI.next());
        }
        return copy;
    }

    /**
     * Get the the centre of the Mine.
     *
     * @return Location at the centre of the Mine
     */
    public Location getCenter() {
        int x1 = this.getUpperX() + 1;
        int y1 = this.getUpperY() + 1;
        int z1 = this.getUpperZ() + 1;
        return new Location(this.getWorld(), this.getLowerX() + (x1 - this.getLowerX()) / 2.0, this.getLowerY() + (y1 - this.getLowerY()) / 2.0, this.getLowerZ() + (z1 - this.getLowerZ()) / 2.0);
    }

    /**
     * Get the Mine's world.
     *
     * @return The World object representing this Mine's world
     * @throws IllegalStateException if the world is not loaded
     */
    public World getWorld() {
        if (world == null) {
            throw new IllegalStateException("World '" + this.world.getName() + "' is not loaded");
        }
        return world;
    }

    /**
     * Get the size of this Mine along the X axis
     *
     * @return Size of Mine along the X axis
     */
    public int getSizeX() {
        return (this.x2 - this.x1) + 1;
    }

    /**
     * Get the size of this Mine along the Y axis
     *
     * @return Size of Mine along the Y axis
     */
    public int getSizeY() {
        return (this.y2 - this.y1) + 1;
    }

    /**
     * Get the size of this Mine along the Z axis
     *
     * @return Size of Mine along the Z axis
     */
    public int getSizeZ() {
        return (this.z2 - this.z1) + 1;
    }

    /**
     * Get the minimum X co-ordinate of this Mine
     *
     * @return the minimum X co-ordinate
     */
    public int getLowerX() {
        return this.x1;
    }

    /**
     * Get the minimum Y co-ordinate of this Mine
     *
     * @return the minimum Y co-ordinate
     */
    public int getLowerY() {
        return this.y1;
    }

    /**
     * Get the minimum Z co-ordinate of this Mine
     *
     * @return the minimum Z co-ordinate
     */
    public int getLowerZ() {
        return this.z1;
    }

    /**
     * Get the maximum X co-ordinate of this Mine
     *
     * @return the maximum X co-ordinate
     */
    public int getUpperX() {
        return this.x2;
    }

    /**
     * Get the maximum Y co-ordinate of this Mine
     *
     * @return the maximum Y co-ordinate
     */
    public int getUpperY() {
        return this.y2;
    }

    /**
     * Get the maximum Z co-ordinate of this Mine
     *
     * @return the maximum Z co-ordinate
     */
    public int getUpperZ() {
        return this.z2;
    }

    /**
     * Get the Blocks at the eight corners of the Mine.
     *
     * @return array of Block objects representing the Mine corners
     */
    public Block[] corners() {
        Block[] res = new Block[8];
        World w = this.getWorld();
        res[0] = w.getBlockAt(this.x1, this.y1, this.z1);
        res[1] = w.getBlockAt(this.x1, this.y1, this.z2);
        res[2] = w.getBlockAt(this.x1, this.y2, this.z1);
        res[3] = w.getBlockAt(this.x1, this.y2, this.z2);
        res[4] = w.getBlockAt(this.x2, this.y1, this.z1);
        res[5] = w.getBlockAt(this.x2, this.y1, this.z2);
        res[6] = w.getBlockAt(this.x2, this.y2, this.z1);
        res[7] = w.getBlockAt(this.x2, this.y2, this.z2);
        return res;
    }

    /**
     * Return true if the point at (x,y,z) is contained within this Mine.
     *
     * @param x - The X co-ordinate
     * @param y - The Y co-ordinate
     * @param z - The Z co-ordinate
     * @return true if the given point is within this Mine, false otherwise
     */
    public boolean contains(int x, int y, int z) {
        return x >= this.x1 && x <= this.x2 && y >= this.y1 && y <= this.y2 && z >= this.z1 && z <= this.z2;
    }

    /**
     * Check if the given Block is contained within this Mine.
     *
     * @param b - The Block to check for
     * @return true if the Block is within this Mine, false otherwise
     */
    public boolean contains(Block b) {
        return this.contains(b.getLocation());
    }

    /**
     * Check if the given Location is contained within this Mine.
     *
     * @param l - The Location to check for
     * @return true if the Location is within this Mine, false otherwise
     */
    public boolean contains(Location l) {
        if (!this.world.getName().equals(l.getWorld().getName())) {
            return false;
        }
        return this.contains(l.getBlockX(), l.getBlockY(), l.getBlockZ());
    }

    /**
     * Get the volume of this Mine.
     *
     * @return The Mine volume, in blocks
     */
    public int getVolume() {
        return this.getSizeX() * this.getSizeY() * this.getSizeZ();
    }

    /**
     * Check if the Mine contains only blocks of the given type
     *
     * @param blockId - The block ID to check for
     * @return true if this Mine contains only blocks of the given type
     */
    public boolean containsOnly(int blockId) {
        for (Block b : this) {
            if (b.getTypeId() != blockId) {
                return false;
            }
        }
        return true;
    }

    /**
     * Get a block relative to the lower NE point of the Mine.
     *
     * @param x - The X co-ordinate
     * @param y - The Y co-ordinate
     * @param z - The Z co-ordinate
     * @return The block at the given position
     */
    public Block getRelativeBlock(int x, int y, int z) {
        return this.getWorld().getBlockAt(this.x1 + x, this.y1 + y, this.z1 + z);
    }

    /**
     * Get a block relative to the lower NE point of the Mine in the given
     * World. This version of getRelativeBlock() should be used if being called
     * many times, to avoid excessive calls to getWorld().
     *
     * @param w - The world
     * @param x - The X co-ordinate
     * @param y - The Y co-ordinate
     * @param z - The Z co-ordinate
     * @return The block at the given position
     */
    public Block getRelativeBlock(World w, int x, int y, int z) {
        return w.getBlockAt(this.x1 + x, y1 + y, this.z1 + z);
    }

    /**
     * Get a list of the chunks which are fully or partially contained in this
     * mine.
     *
     * @return A list of Chunk objects
     */
    public List<Chunk> getChunks() {
        List<Chunk> res = new ArrayList<>();

        World w = this.getWorld();
        int x1 = this.getLowerX() & ~0xf;
        int x2 = this.getUpperX() & ~0xf;
        int z1 = this.getLowerZ() & ~0xf;
        int z2 = this.getUpperZ() & ~0xf;
        for (int x = x1; x <= x2; x += 16) {
            for (int z = z1; z <= z2; z += 16) {
                res.add(w.getChunkAt(x >> 4, z >> 4));
            }
        }
        return res;
    }

    @Override
    public Iterator<Block> iterator() {
        return new MineIterator(this.getWorld(), this.x1, this.y1, this.z1, this.x2, this.y2, this.z2);
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("worldName", this.world.getName());
        map.put("x1", this.x1);
        map.put("y1", this.y1);
        map.put("z1", this.z1);
        map.put("x2", this.x2);
        map.put("y2", this.y2);
        map.put("z2", this.z2);
        return map;
    }

    public class MineIterator implements Iterator<Block> {

        private World w;
        private int baseX, baseY, baseZ;
        private int x, y, z;
        private int sizeX, sizeY, sizeZ;

        public MineIterator(World w, int x1, int y1, int z1, int x2, int y2, int z2) {
            this.w = w;
            this.baseX = x1;
            this.baseY = y1;
            this.baseZ = z1;
            this.sizeX = Math.abs(x2 - x1) + 1;
            this.sizeY = Math.abs(y2 - y1) + 1;
            this.sizeZ = Math.abs(z2 - z1) + 1;
            this.x = this.y = this.z = 0;
        }

        @Override
        public boolean hasNext() {
            return this.x < this.sizeX && this.y < this.sizeY && this.z < this.sizeZ;
        }

        @Override
        public Block next() {
            Block b = this.w.getBlockAt(this.baseX + this.x, this.baseY + this.y, this.baseZ + this.z);
            if (++x >= this.sizeX) {
                this.x = 0;
                if (++this.y >= this.sizeY) {
                    this.y = 0;
                    ++this.z;
                }
            }
            return b;
        }

        @Override
        public void remove() {
        }
    }

    /**
     * Gets a random location within the mine. May get in walls, caution.
     *
     * @return A random location within the mine.
     */
    public Location getRandomLocationInside() {
        Random rand = new Random();
        Location randomLocation = getLowerNE();
        randomLocation.setX(randomLocation.getX() + (Math.abs(rand.nextInt()) % Math.abs(getSizeX())));
        randomLocation.setY(randomLocation.getY() + 2);
        randomLocation.setZ(randomLocation.getZ() + (Math.abs(rand.nextInt()) % Math.abs(getSizeZ())));

        return randomLocation;
    }
}
