package com.github.hexocraft.addlight.commands;

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

import com.github.hexocraft.addlight.AddLightPlugin;
import com.github.hexocraft.addlight.configuration.Permissions;
import com.github.hexocraftapi.command.Command;
import com.github.hexocraftapi.command.CommandInfo;
import com.github.hexocraftapi.message.Prefix;
import com.github.hexocraftapi.message.predifined.message.EmptyMessage;
import com.github.hexocraftapi.message.predifined.message.PluginTitleMessage;
import com.google.common.collect.Lists;
import org.bukkit.ChatColor;

import static com.github.hexocraft.addlight.AddLightPlugin.messages;

/**
 * This file is part of AddLight
 *
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class AlCommands extends Command<AddLightPlugin>
{
	public static Prefix prefix = new Prefix(messages.chatPrefix);

	/**
     * @param plugin The plugin that this object belong to.
     */
    public AlCommands(AddLightPlugin plugin)
    {
        super("AddLight", plugin);
        this.setAliases(Lists.newArrayList("al"));
		this.setPermission(Permissions.USE.toString());

        this.addSubCommand(new AlCommandHelp(plugin));
        this.addSubCommand(new AlCommandEnable(plugin));
		this.addSubCommand(new AlCommandDisable(plugin));
		this.addSubCommand(new AlCommandConnected(plugin));
		this.addSubCommand(new AlCommandLightlevel(plugin));
		this.addSubCommand(new AlCommandReload(plugin));
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
		EmptyMessage.toSender(commandInfo.getPlayer());

		PluginTitleMessage titleMessage = new PluginTitleMessage(plugin, "AddLight : " + ChatColor.AQUA + (plugin.isEnable ? "on" : "off"));
		titleMessage.add("Connected blocks : " + ChatColor.AQUA + (plugin.useConnectedBlocks ? "on" : "off"));
		titleMessage.add("LightsApi intensity : " + ChatColor.AQUA + plugin.lightlevel);
		titleMessage.send(commandInfo.getSenders());

		return true;
	}
}
