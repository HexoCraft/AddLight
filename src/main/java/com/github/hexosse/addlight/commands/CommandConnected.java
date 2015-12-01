package com.github.hexosse.addlight.commands;

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
import com.github.hexosse.baseplugin.command.BaseCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This file is part of AddLight
 *
 * @author <b>hexosse</b> (<a href="https://github.com/hexosse">hexosse on GitHub</a>).
 */
public class CommandConnected extends BaseCommand<AddLight>
{
    /**
     * @param plugin The plugin that this object belong to.
     */
    public CommandConnected(AddLight plugin) {
        super(plugin);
    }

    /**
     * @param sender The sender (should be a player)
     */
    public void execute(CommandSender sender)
    {
        final Player player = (sender instanceof Player) ? (Player)sender : null;

        if(!Permissions.has(sender, Permissions.CONNECTED))
        {
            pluginLogger.help(ChatColor.RED + plugin.messages.useConnected, player);
            return;
        }

        plugin.connected = !plugin.connected;
        pluginLogger.help(plugin.messages.prefix() + " " +  plugin.messages.connectedblocks + " " +  ChatColor.AQUA + (plugin.connected?"on":"off"), player);
    }
}
