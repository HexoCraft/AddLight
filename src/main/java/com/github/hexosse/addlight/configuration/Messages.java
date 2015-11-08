package com.github.hexosse.addlight.configuration;

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
import com.github.hexosse.baseplugin.config.BaseConfig;
import org.bukkit.ChatColor;

import java.io.File;

/**
 * This file is part of ChestPreview
 *
 * @author <b>hexosse</b> (<a href="https://github.com/hexosse">hexosse on GitHub</a>).
 */

@BaseConfig.ConfigHeader(comment = {
        "############################################################",
        "# | ChestPreview by hexosse                              | #",
        "############################################################"
})
@BaseConfig.ConfigFooter(comment = {
        " ",
        " ",
        "############################################################"
})

public class Messages extends BaseConfig<AddLight>
{
    /* Chat */
    @ConfigComment(path = "chat")
    @ConfigOptions(path = "chat.prefix")
    public String chatPrefix;

    /* Help */
    @ConfigComment(path = "help")
    @ConfigOptions(path = "help.HelpEnable")
    public String helpEnable;
    @ConfigOptions(path = "help.HelpDisable")
    public String helpDisable;
    @ConfigOptions(path = "help.HelpCb")
    public String helpCb;
    @ConfigOptions(path = "help.HelpNumber")
    public String helpNumber;
    @ConfigOptions(path = "help.HelpReload")
    public String helpReload;

    /* Errors */
    @ConfigComment(path = "errors")
    @ConfigOptions(path = "errors.AccesDenied")
    public String AccesDenied;
    @ConfigOptions(path = "errors.UseConnected")
    public String useConnected;
    @ConfigOptions(path = "errors.UsePlugin")
    public String usePlugin;
    @ConfigOptions(path = "errors.UseIntensity")
    public String useIntensity;
    @ConfigOptions(path = "errors.IntensityNumber")
    public String intensityNumber;

    /* Messages */
    @ConfigComment(path = "messages")
    @ConfigOptions(path = "messages.LightsCreated")
    public String lightsCreated;
    @ConfigOptions(path = "messages.LightsDeleted")
    public String lightsDeleted;
    @ConfigOptions(path = "messages.LightsIntensity")
    public String lightsIntensity;
    @ConfigOptions(path = "messages.Connectedblocks")
    public String connectedblocks;
    @ConfigOptions(path = "messages.IsDisable")
    public String isDisable;
    @ConfigOptions(path = "messages.IsEnable")
    public String isEnable;
    @ConfigOptions(path = "messages.HelpLeftClick")
    public String helpLeftClick;
    @ConfigOptions(path = "messages.HelpRightClick")
    public String helpRightClick;
    @ConfigOptions(path = "messages.Reloaded")
    public String reloaded;


    /**
     * @param plugin The plugin that this object belong to.
     * @param dataFolder Folder that contains the config file
     * @param filename   Name of the config file
     */
    public Messages(AddLight plugin, File dataFolder, String filename)
    {
        super(plugin, new File(dataFolder, filename), filename);
    }

    public void reloadConfig() {
        load();
    }

    public String prefix()
    {
        return ChatColor.AQUA + plugin.messages.chatPrefix + ChatColor.WHITE;
    }
}
