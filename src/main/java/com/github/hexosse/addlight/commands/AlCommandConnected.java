package com.github.hexosse.addlight.commands;

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

import com.github.hexosse.addlight.AddLight;
import com.github.hexosse.addlight.configuration.Permissions;
import com.github.hexosse.pluginframework.pluginapi.PluginCommand;
import com.github.hexosse.pluginframework.pluginapi.command.CommandInfo;
import com.github.hexosse.pluginframework.pluginapi.message.Message;
import com.google.common.collect.Lists;
import org.bukkit.ChatColor;

/**
 * This file is part of AddLight
 *
 * @author <b>hexosse</b> (<a href="https://github.com/hexosse">hexosse on GitHub</a>).
 */
public class AlCommandConnected extends PluginCommand<AddLight>
{
    /**
     * @param plugin The plugin that this object belong to.
     */
    public AlCommandConnected(AddLight plugin) {
        super("ConnectedBlocks", plugin);
        this.setAliases(Lists.newArrayList("cb"));
        this.setDescription(plugin.messages.helpCb);
        this.setPermission(Permissions.CONNECTED.toString());
        this.setPermissionMessage(plugin.messages.useConnected);
    }

    /**
     * Executes the given command, returning its success
     *
     * @param commandInfo Info about the command
     *
     * @return true if a valid command, otherwise false
     */
    @Override
    public boolean onCommand(CommandInfo commandInfo)
    {
        plugin.connected = !plugin.connected;

        // Message
        Message message = new Message();
        message.setPrefix(plugin.messages.chatPrefix);
        message.add(plugin.messages.connectedblocks + " " +  ChatColor.AQUA + (plugin.connected?"on":"off"));
        messageManager.send(commandInfo.getSender(), message);

        return true;
    }
}
