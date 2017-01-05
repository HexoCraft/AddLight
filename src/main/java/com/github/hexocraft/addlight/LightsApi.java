package com.github.hexocraft.addlight;

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

import com.github.hexocraft.addlight.configuration.Permissions;
import com.github.hexocraft.addlight.utils.ConnectedBlocks;
import com.github.hexocraftapi.lights.Lights;
import com.github.hexocraftapi.message.predifined.message.SimplePrefixedMessage;
import com.sk89q.worldedit.BlockVector;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Iterator;
import java.util.List;

import static com.github.hexocraft.addlight.AddLightPlugin.*;
import static com.github.hexocraft.addlight.commands.AlCommands.prefix;

/**
 * This file is part of AddLight
 *
 * @author <b>hexosse</b> (<a href="https://github.com/hexosse">hexosse on GitHub</a>).
 */
public class LightsApi
{
	private static int            delay     = 10;

    public static void createLight(final Player player, final Location location, final int lightLevel)
    {
		// Création de la lumière pour des block connectés
		if(useConnectedBlocks && Permissions.has(player, Permissions.CONNECTED))
		{
			new BukkitRunnable()
			{
				@Override
				public void run()
				{
					Location corner1 = null;
					Location corner2 = null;
					if(worldEdit != null && Permissions.has(player, Permissions.WORLDEDIT) && worldEdit.isLocationInSelection(player, location))
					{
						corner1 = worldEdit.get().getSelection(player).getMaximumPoint();
						corner2 = worldEdit.get().getSelection(player).getMinimumPoint();
					}

					List<Location> locations = ConnectedBlocks.getConnectedBlocks(location, config.cbLimit, corner1, corner2);
					Lights.createLight(locations, lightLevel);

					// Message
					SimplePrefixedMessage.toPlayer(player, prefix, Integer.toString(locations.size()) + " " + messages.lightsCreated);
				}
			}.runTaskLaterAsynchronously(instance, delay);
		}
		// Création de la lumière sur une selection WorldEditPlugin
        else if(worldEdit != null && Permissions.has(player, Permissions.WORLDEDIT) && worldEdit.isLocationInSelection(player, location))
        {
			new BukkitRunnable()
			{
				@Override
				public void run()
				{
					Iterator<BlockVector> blocks = worldEdit.getBlockVector(player, location);
					int count = 0;

					while(blocks != null && blocks.hasNext())
					{
						BlockVector block = blocks.next();
						Location blockLocation = new Location(location.getWorld(), block.getBlockX(), block.getBlockY(), block.getBlockZ());
						Lights.createLight(blockLocation, lightLevel);
						count++;
					}

					// Message
					SimplePrefixedMessage.toPlayer(player, prefix, "" + count + " " + messages.lightsCreated);
				}
			}.runTaskLaterAsynchronously(instance, delay);
		}
        // Création de la lumière
        else
		{
			new BukkitRunnable()
			{
				@Override
				public void run()
				{
					Lights.createLight(location, lightLevel);
				}
			}.runTaskLaterAsynchronously(instance, delay);
		}
    }

    public static void removeLight(final Player player, final Location location)
    {
        // Suppression de la lumière pour des block connectés
        if(useConnectedBlocks && Permissions.has(player, Permissions.CONNECTED))
        {
			new BukkitRunnable()
			{
				@Override
				public void run()
				{
					Location corner1 = null;
					Location corner2 = null;
					if(worldEdit != null && Permissions.has(player, Permissions.WORLDEDIT) && worldEdit.isLocationInSelection(player, location))
					{
						corner1 = worldEdit.get().getSelection(player).getMaximumPoint();
						corner2 = worldEdit.get().getSelection(player).getMinimumPoint();
					}

					List<Location> locations = ConnectedBlocks.getConnectedBlocks(location, config.cbLimit, corner1, corner2);
					Lights.removeLight(locations);

					// Message
					SimplePrefixedMessage.toPlayer(player, prefix, Integer.toString(locations.size()) + " " + messages.lightsDeleted);
				}
			}.runTaskLaterAsynchronously(instance, delay);
		}
		// Suppression de la lumière sur une selection WorldEditPlugin
        else if(worldEdit != null && Permissions.has(player, Permissions.WORLDEDIT) && worldEdit.isLocationInSelection(player, location))
        {
			new BukkitRunnable()
			{
				@Override
				public void run()
				{
					Iterator<BlockVector> blocks = worldEdit.getBlockVector(player, location);
					int count = 0;

					while(blocks != null && blocks.hasNext())
					{
						BlockVector block = blocks.next();
						Location blockLocation = new Location(location.getWorld(), block.getBlockX(), block.getBlockY(), block.getBlockZ());
						Lights.removeLight(blockLocation);
						count++;
					}

					// Message
					SimplePrefixedMessage.toPlayer(player, prefix, "" + count + " " + messages.lightsDeleted);
				}
			}.runTaskLaterAsynchronously(instance, delay);
		}
        // Suppression de la lumière
        else
		{
			new BukkitRunnable()
			{
				@Override
				public void run()
				{
					Lights.removeLight(location);
				}
			}.runTaskLaterAsynchronously(instance, delay);
		}
    }
}
