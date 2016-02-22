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
import com.github.hexosse.pluginframework.pluginapi.command.CommandArgument;
import com.github.hexosse.pluginframework.pluginapi.command.CommandInfo;
import com.github.hexosse.pluginframework.pluginapi.command.type.ArgTypeInteger;
import com.github.hexosse.pluginframework.pluginapi.message.Message;
import com.github.hexosse.pluginframework.pluginapi.message.MessageSeverity;
import com.github.hexosse.pluginframework.utilapi.NumberUtil;
import com.google.common.collect.Lists;

/**
 * This file is part AddLight
 *
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class AlCommandLightlevel extends PluginCommand<AddLight>
{
    /**
     * @param plugin The plugin that this object belong to.
     */
    public AlCommandLightlevel(AddLight plugin) {
        super("LightLevel", plugin);
        this.setAliases(Lists.newArrayList("ll"));
        this.addArgument(new CommandArgument<Integer>("intensity", ArgTypeInteger.get(), 15, false,false, "Light intensity must be between 1 and 15."));
        this.setPermission(Permissions.USE.toString());
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
        int lightlevel = NumberUtil.ToInt(commandInfo.getNamedArg("intensity"));

        if(lightlevel<=0 || lightlevel>15)
        {
            // Message
            Message message = new Message(MessageSeverity.ERROR);
            message.setPrefix(plugin.messages.chatPrefix);
            message.add(plugin.messages.intensityNumber);
            messageManager.send(commandInfo.getSender(), message);

            return false;
        }

        plugin.lightlevel = lightlevel;

        // Message
        Message message = new Message();
        message.setPrefix(plugin.messages.chatPrefix);
        message.add(plugin.messages.lightsIntensity + " : " + lightlevel);
        messageManager.send(commandInfo.getSender(), message);

        return true;
    }
}
