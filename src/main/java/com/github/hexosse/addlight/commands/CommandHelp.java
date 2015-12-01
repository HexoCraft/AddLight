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
public class CommandHelp extends BaseCommand<AddLight>
{
    /**
     * @param plugin The plugin that this object belong to.
     */
    public CommandHelp(AddLight plugin) {
        super(plugin);
    }

    /**
     * @param sender The sender (should be a player)
     */
    public void execute(CommandSender sender)
    {
        final Player player = (sender instanceof Player) ? (Player)sender : null;

        if(!Permissions.has(sender, Permissions.USE))
        {
            pluginLogger.help(ChatColor.RED + plugin.messages.usePlugin, player);
            return;
        }


        pluginLogger.help(ChatColor.YELLOW + "-----------------------------------------------", player);
        pluginLogger.help(ChatColor.YELLOW + plugin.getDescription().getName() + " help", player);
        pluginLogger.help(ChatColor.AQUA + "/AddLight " + ChatColor.GREEN + "[enable|on] : " + ChatColor.WHITE + plugin.messages.helpEnable, player);
        pluginLogger.help(ChatColor.AQUA + "/AddLight " + ChatColor.GREEN + "[disable|off] : " + ChatColor.WHITE + plugin.messages.helpDisable, player);
        pluginLogger.help(ChatColor.AQUA + "/AddLight " + ChatColor.GREEN + "[ConnectedBlocks|cb] : " + ChatColor.WHITE + plugin.messages.helpCb, player);
        pluginLogger.help(ChatColor.AQUA + "/AddLight " + ChatColor.GREEN + "<number> : " + ChatColor.WHITE + plugin.messages.helpNumber, player);
        pluginLogger.help(ChatColor.AQUA + "/AddLight " + ChatColor.GREEN + "[reload] : " + ChatColor.WHITE + plugin.messages.helpReload, player);
        pluginLogger.help(ChatColor.YELLOW + "-----------------------------------------------", player);
        pluginLogger.help("Plugin : " + ChatColor.AQUA + (plugin.enable ? "on" : "off"), player);
        pluginLogger.help("Connected blocks : " + ChatColor.AQUA + (plugin.connected ? "on" : "off"), player);
        pluginLogger.help("Light intensity : " + ChatColor.AQUA + plugin.lightlevel, player);
        pluginLogger.help(ChatColor.YELLOW + "-----------------------------------------------", player);

        pluginLogger.info("Plugin : " + ChatColor.AQUA + (plugin.enable ? "on" : "off"));
        pluginLogger.info("Connected blocks : " + ChatColor.AQUA + (plugin.connected ? "on" : "off"));
        pluginLogger.info("Light intensity : " + ChatColor.AQUA + plugin.lightlevel);
    }
}
