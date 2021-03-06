package com.github.hexocraft.addlight.integrations;

/*
 * Copyright 2017 hexosse
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

import com.github.hexocraftapi.integration.Hooker;
import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.CylinderRegion;
import com.sk89q.worldedit.regions.Polygonal2DRegion;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.regions.RegionSelector;
import com.sk89q.worldedit.world.World;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Iterator;

/**
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class WorldEditHooker extends Hooker<com.sk89q.worldedit.bukkit.WorldEditPlugin, WorldEditHooker> {
    public WorldEditHooker() {
        super();
    }

    // Capture the plugin if exist
    public WorldEditHooker capture(com.sk89q.worldedit.bukkit.WorldEditPlugin worldEditPlugin) {
        this.plugin = worldEditPlugin;
        return this;
    }

    /**
     * this function is inspired from com.sk89q.worldedit.bukkit.WorldEditPlugin.getSelection
     *
     * @param player Player
     *
     * @return player region
     */
    public Region getRegion(Player player) {
        if(player == null) throw new IllegalArgumentException("Null player not allowed");
        if(!player.isOnline()) throw new IllegalArgumentException("Offline player not allowed");

        try {
            BukkitPlayer wPlayer = plugin.wrapPlayer(player);
            LocalSession session = WorldEdit.getInstance().getSessionManager().get(wPlayer);
            RegionSelector selector = session.getRegionSelector((World) wPlayer.getWorld());

            return selector.getRegion();
        } catch(IncompleteRegionException e) {
            return null;
        }
    }

    public Region getSelection(Player player) {
        if(player == null) throw new IllegalArgumentException("Null player not allowed");
        if(!player.isOnline()) throw new IllegalArgumentException("Offline player not allowed");

        try {
            BukkitPlayer wPlayer = plugin.wrapPlayer(player);
            LocalSession session = WorldEdit.getInstance().getSessionManager().get(wPlayer);
            return session.getSelection((World) wPlayer.getWorld());
        } catch(IncompleteRegionException e) {
            return null;
        }
    }

    /**
     * @param player Player
     * @param location Location
     *
     * @return true if @location is in @player selection
     */
    public boolean isLocationInSelection(Player player, Location location) {
        if(player == null) throw new IllegalArgumentException("Null player not allowed");
        if(!player.isOnline()) throw new IllegalArgumentException("Offline player not allowed");

        try {
            BukkitPlayer wPlayer   = plugin.wrapPlayer(player);
            LocalSession session   = WorldEdit.getInstance().getSessionManager().get(wPlayer);
            Region       region    = session.getSelection((World) wPlayer.getWorld());
            BlockVector3 vLocation = BlockVector3.at(location.getX(), location.getY(), location.getZ());
            return region != null && region.contains(vLocation);
        } catch(IncompleteRegionException e) {
            return false;
        }
    }

    /**
     * @param player Player
     * @param location Location in selection
     *
     * @return Region
     */
    public Iterator<BlockVector3> getBlockVector(Player player, Location location) {
        if(!isLocationInSelection(player, location))
            return null;

        Region region = getRegion(player);

        // CuboidRegion
        if(region instanceof CuboidRegion) {
            CuboidRegion cuboid = (CuboidRegion) region;

            return cuboid.iterator();
        }
        else if(region instanceof Polygonal2DRegion) {
            Polygonal2DRegion polygonal2D = (Polygonal2DRegion) region;

            return polygonal2D.iterator();
        }
        else if(region instanceof CylinderRegion) {
            CylinderRegion cylinder = (CylinderRegion) region;

            return cylinder.iterator();
        }
        else
            return region != null ? region.iterator() : null;
    }
}
