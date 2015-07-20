package com.github.hexosse.addlight.events;

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

import com.github.hexosse.addlight.AddLight;
import com.github.hexosse.addlight.configuration.Permissions;
import com.github.hexosse.addlight.utils.ConnectedBlocksLight;
import com.github.hexosse.addlight.utils.WorldEditUtil;
import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import ru.BeYkeRYkt.LightAPI.LightAPI;

import java.util.Iterator;

/**
 * This file is part AddGlow
 *
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
@SuppressWarnings("unused")
public class PlayerListener implements Listener
{
    private final static AddLight plugin = AddLight.getPlugin();
    private final static WorldEditPlugin worldEditPlugin = AddLight.getWorldEditPlugin();

    @EventHandler(priority=EventPriority.HIGH)
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        final Player player = event.getPlayer();

        if(!plugin.isEnable()) return;

        //
        if(event.getAction().equals(Action.LEFT_CLICK_BLOCK))
        {
            // Material in hand must be glowstone dust
            if(player.getItemInHand().getType() != Material.GLOWSTONE_DUST)
                return;

            // Clicked location
            Location clickedLoc = event.getClickedBlock().getLocation();

            // Création de la lumière pour des block connectés
            if(plugin.connected && Permissions.has(player, Permissions.CONNECTED))
                ConnectedCreateLight(player,clickedLoc,plugin.lightlevel);

            // Création de la lumière sur une selection WorldEdit
            else if(worldEditPlugin!=null && Permissions.has(player, Permissions.WORLDEDIT))
                WorldEditCreateLight(player, clickedLoc,plugin.lightlevel);

                // Création de la lumière
            else
                CreateLight(clickedLoc, plugin.lightlevel);
        }

        //
        if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
        {
            // Material in hand must be glowstone dust
            if(player.getItemInHand().getType() != Material.GLOWSTONE_DUST)
                return;

            // Clicked location
            Location clickedLoc = event.getClickedBlock().getLocation();

            // Suppression de la lumière pour des block connectés
            if(plugin.connected && Permissions.has(player, Permissions.CONNECTED))
                ConnectedDeleteLight(player, clickedLoc);

            // Suppression de la lumière sur une selection WorldEdit
            else if(worldEditPlugin!=null && Permissions.has(player, Permissions.WORLDEDIT))
                WorldEditDeleteLight(player, clickedLoc);

            // Suppression de la lumière
            else
                DeleteLight(clickedLoc);
        }
    }




    public void CreateLight(Location location, int lightLevel)
    {
        LightAPI.createLight(location, lightLevel);
    }

    public void WorldEditCreateLight(Player player, Location location, int lightLevel)
    {
        if(WorldEditUtil.IsLocationInSelection(player, location))
        {
            Iterator<BlockVector> blocks = WorldEditUtil.getBlockVector(player, location);

            while (blocks.hasNext())
            {
                BlockVector block = blocks.next();
                Location blockLocation = new Location(location.getWorld(), block.getBlockX(), block.getBlockY(), block.getBlockZ());
                LightAPI.createLight(blockLocation,lightLevel);
            }
        }
        else
            LightAPI.createLight(location,lightLevel);
    }

    public void ConnectedCreateLight(final Player player, final Location location, final int lightLevel)
    {
        // Create the lights anonymously
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                int count = ConnectedBlocksLight.createConnectedBlocksLight(location, lightLevel);
                plugin.log("" + count + " lights created",player);
            }

        }.runTaskAsynchronously(plugin);
    }

    public void DeleteLight(Location location)
    {
        LightAPI.deleteLight(location);
    }

    public void WorldEditDeleteLight(Player player, Location location)
    {
        if(WorldEditUtil.IsLocationInSelection(player, location))
        {
            Iterator<BlockVector> blocks = WorldEditUtil.getBlockVector(player, location);

            while (blocks.hasNext())
            {
                BlockVector block = blocks.next();
                Location blockLocation = new Location(location.getWorld(), block.getBlockX(), block.getBlockY(), block.getBlockZ());
                LightAPI.deleteLight(blockLocation);
            }
        }
        else
            LightAPI.deleteLight(location);
    }

    public void ConnectedDeleteLight(final Player player, final Location location)
    {
        // Create the lights anonymously
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                int count = ConnectedBlocksLight.deleteConnectedBlocksLight(location);
                plugin.log("" + count + " lights deleted",player);
            }

        }.runTaskAsynchronously(plugin);
    }
}
