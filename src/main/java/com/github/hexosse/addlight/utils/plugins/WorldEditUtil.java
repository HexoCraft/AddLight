package com.github.hexosse.addlight.utils.plugins;

/*
 * Copyright 2016 hexosse
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

import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;
import com.sk89q.worldedit.regions.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

import java.util.Iterator;

/**
 * This file is part of AddLight
 *
 * @author <b>hexosse</b> (<a href="https://github.com/hexosse">hexosse on GitHub</a>).
 */
public class WorldEditUtil
{
    private static WorldEditPlugin worldEdit = null;

    /**
     * @return WorldEdit plugin instance
     */
    public static WorldEditPlugin getWorldEditPlugin()
    {
        if(WorldEditUtil.getPlugin()!=null)
            return WorldEditUtil.getPlugin();

        PluginManager pm = Bukkit.getServer().getPluginManager();
        WorldEditPlugin worldEdit = (WorldEditPlugin)pm.getPlugin("WorldEdit");
        if(worldEdit != null && pm.isPluginEnabled(worldEdit))
        {
            WorldEditUtil.setPlugin(worldEdit);
            return worldEdit;
        }
        else return null;
    }

    public static void setPlugin(WorldEditPlugin plugin)
    {
        worldEdit = plugin;
    }

    public static WorldEditPlugin getPlugin()
    {
        return worldEdit;
    }

    /**
     * Test if WorldEdit is installed
     *
     * @return true if WorldEdit is installed
     */
    private static boolean hasWorldEdit()
    {
        return WorldEditUtil.getWorldEditPlugin()!=null;
    }

    /**
     * @param player Player
     * @return Player selection
     */
    public static Selection getSelection(Player player)
    {
        if (!hasWorldEdit()) return null;

        return worldEdit.getSelection(player);
    }

    /**
     * @param player Player
     * @param location Location
     * @return true if @location is in @player selection
     */
    public static boolean IsLocationInSelection(Player player, Location location)
    {
        if (!hasWorldEdit()) return false;

        Selection selection = getSelection(player);
        return selection != null && selection.contains(location);
    }

    /**
     * this function is inspired from com.sk89q.worldedit.bukkit.WorldEditPlugin.getSelection
     *
     * @param player Player
     * @return player region
     */
    @SuppressWarnings("deprecation")
    protected static Region getRegion(Player player)
    {
        if(!hasWorldEdit()) return null;

        if (player == null) {
            throw new IllegalArgumentException("Null player not allowed");
        }
        if (!player.isOnline()) {
            throw new IllegalArgumentException("Offline player not allowed");
        }

        BukkitPlayer worldEditPlayer = worldEdit.wrapPlayer(player);
        LocalSession session = WorldEdit.getInstance().getSessionManager().get(worldEditPlayer);
        RegionSelector selector = session.getRegionSelector(worldEditPlayer.getWorld());
        try {
            return selector.getRegion();
        } catch (IncompleteRegionException e) {
            return null;
        }
    }

    /**
     * @param player Player
     * @param location Location in selection
     * @return Region
     */
    public static Iterator<BlockVector> getBlockVector(Player player, Location location)
    {
        if(!IsLocationInSelection(player, location)) return null;

        Region region = getRegion(player);

        // CuboidRegion
        if(region instanceof CuboidRegion)
        {
            CuboidRegion cuboid = (CuboidRegion)region;

            return cuboid.iterator();
        }
        else if(region instanceof Polygonal2DRegion)
        {
            Polygonal2DRegion polygonal2D = (Polygonal2DRegion)region;

            return polygonal2D.iterator();
        }
        else if(region instanceof CylinderRegion)
        {
            CylinderRegion cylinder = (CylinderRegion)region;

            return cylinder.iterator();
        }
        else
            return region != null ? region.iterator() : null;
    }
}
