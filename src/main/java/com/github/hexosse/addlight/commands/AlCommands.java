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
import com.github.hexosse.pluginframework.pluginapi.PluginCommand;
import com.github.hexosse.pluginframework.pluginapi.command.CommandInfo;
import com.github.hexosse.pluginframework.pluginapi.message.Message;
import com.github.hexosse.pluginframework.pluginapi.message.MessageColor;
import com.github.hexosse.pluginframework.pluginapi.message.MessagePart;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;

/**
 * This file is part AddLight
 *
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class AlCommands extends PluginCommand<AddLight>
{
    /**
     * @param plugin The plugin that this object belong to.
     */
    public AlCommands(AddLight plugin)
    {
        super("AddLight", plugin);
        this.setAliases(Lists.newArrayList("al"));

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
		//plugin.getServer().dispatchCommand(commandInfo.getSender(), "AddLight help");

		MessagePart dash = new MessagePart(ChatColor.STRIKETHROUGH + StringUtils.leftPad("", 51, "-")).color(MessageColor.DESCRIPTION);

		Message message = new Message();
		message.add(dash);
		message.add("Plugin : " + ChatColor.AQUA + (plugin.enable ? "on" : "off"));
		message.add("Connected blocks : " + ChatColor.AQUA + (plugin.connected ? "on" : "off"));
		message.add("Light intensity : " + ChatColor.AQUA + plugin.lightlevel);
		message.add(dash);
		messageManager.send(commandInfo.getSender(), message);

		return true;
	}
}
