package com.github.hexosse.addlight.utils;


/*
 * Copyright 2015 hexosse
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

import com.github.hexosse.baseplugin.utils.block.BlockUtil;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import java.util.ArrayList;

/**
 * This file is part of AddLight
 *
 * @author <b>hexosse</b> (<a href="https://github.com/hexosse">hexosse on GitHub</a>).
 */
public class ConnectedBlocksLight
{
    private static BlockFace[] FACES;

    private static ArrayList<Location> unchecked = new ArrayList<>();
    private static ArrayList<Location> confirmed = new ArrayList<>();

    static { FACES = new BlockFace[]{BlockFace.UP, BlockFace.DOWN, BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST}; }

    public static ArrayList<Location> getConnectedBlocks(Location location, int limit)
    {
        findConnectedBlocks(location.getBlock(), limit);
        return confirmed;
    }

    private static int findConnectedBlocks(Block block, int limit)
    {
        unchecked.clear();
        confirmed.clear();
        unchecked.add(block.getLocation());

        while(unchecked.size() > 0 && confirmed.size()<=(limit>0?limit:unchecked.size()))
        {
            Location uncheckedLocation = unchecked.get(0);
            Block uncheckedBlock = unchecked.get(0).getBlock();

            if(!isValid(uncheckedBlock,block))
            {
                unchecked.remove(uncheckedLocation);
            }
            else
            {
                confirmed.add(uncheckedLocation);

                for (BlockFace face : FACES)
                {
                    Block candidate = uncheckedBlock.getRelative(face);

                    if (isValid(candidate, block) && !isUnchecked(candidate) && !isConfirmed(candidate))
                        unchecked.add(candidate.getLocation());
                }

                unchecked.remove(0);
            }
        }
        return confirmed.size();
    }

    // the block to check must be of the same material
    // and must have one face exposed to transparent block
    protected static boolean isValid(Block toCheck, Block block)
    {
        if(!BlockUtil.compare(toCheck, block))
            return false;

        for(BlockFace face : FACES)
        {
            Block relative = block.getRelative(face);
            if (relative.getType().isTransparent() || !relative.getType().isOccluding()) {
                return true;
            }
        }
        return false;
    }

    // Test if the location is in the unchecked list
    protected static boolean isUnchecked(Location location)
    {
        for(Location uncheckedLocation : unchecked) {
            if(uncheckedLocation.equals(location))
                return true;
        }
        return false;
    }

    // Test if the block is in the unchecked list
    protected static boolean isUnchecked(Block block)
    {
        return isUnchecked(block.getLocation());
    }

    // Test if the block is in the confirmed list
    protected static boolean isConfirmed(Location location)
    {
        for(Location confirmedLocation : confirmed) {
            if(confirmedLocation.equals(location))
                return true;
        }
        return false;
    }

    // Test if the block is in the confirmed list
    protected static boolean isConfirmed(Block block)
    {
        return isConfirmed(block.getLocation());
    }
}
