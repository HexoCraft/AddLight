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
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * This file is part AddLight
 *
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 * @date 05/07/2015
 */
public class CommandEnable
{
    private final static AddLight plugin = AddLight.getPlugin();

    /**
     * @param sender The sender (should be a player)
     */
    public static void execute(CommandSender sender)
    {
        if (!Permissions.has(sender, Permissions.ADMIN))
        {
            sender.sendMessage(ChatColor.GREEN + "[AddLight] " + ChatColor.WHITE + "You don't have permission to create or delete light!");
            return;
        }

        plugin.setEnable(true);
        sender.sendMessage(ChatColor.GREEN + "[AddLight] " + ChatColor.WHITE + "is enable!");
        sender.sendMessage(ChatColor.GREEN + "[AddLight] " + ChatColor.WHITE + "left click an item with glowstone dust to add light!");
        sender.sendMessage(ChatColor.GREEN + "[AddLight] " + ChatColor.WHITE + "right click an item with glowstone dust to remove light!");
    }
}
