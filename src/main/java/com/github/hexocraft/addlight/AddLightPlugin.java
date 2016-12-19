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

import com.github.hexocraft.addlight.commands.AlCommands;
import com.github.hexocraft.addlight.configuration.Config;
import com.github.hexocraft.addlight.configuration.Messages;
import com.github.hexocraft.addlight.integrations.WorldEditPlugin;
import com.github.hexocraft.addlight.listeners.BlockListener;
import com.github.hexocraft.addlight.listeners.PlayerListener;
import com.github.hexocraftapi.message.Line;
import com.github.hexocraftapi.message.predifined.message.PluginMessage;
import com.github.hexocraftapi.message.predifined.message.PluginTitleMessage;
import com.github.hexocraftapi.plugin.Plugin;
import com.github.hexocraftapi.updater.BukkitUpdater;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * This file is part of AddLight
 *
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class AddLightPlugin extends Plugin
{
	public static AddLightPlugin instance = null;
	public static Config         config   = null;
	public static Messages       messages = null;

	/* Plugins */
	public static WorldEditPlugin          worldEdit = null;

    public static boolean isEnable = false;
    public static boolean useConnectedBlocks = false;
    public static int lightlevel = 15;


    /**
     * Activation du plugin
     */
    @Override
    public void onEnable()
    {
		/* Instance du plugin */
		instance = this;

        /* Chargement de la config */
        config = new Config(this, "config.yml", true);
        messages = new Messages(this, "message.yml", true);

        /* Enregistrement du gestionnaire de commandes */
	    registerCommands(new AlCommands(this));

        /* Enregistrement des listeners */
        Bukkit.getPluginManager().registerEvents(new PlayerListener(this), this);
        Bukkit.getPluginManager().registerEvents(new BlockListener(this), this);

		/* Plugins */
	    worldEdit = new WorldEditPlugin(this);

		/* Enable message */
	    PluginTitleMessage titleMessage = new PluginTitleMessage(this, "AddLight is enable ...", ChatColor.YELLOW);
	    if(worldEdit.enabled()) titleMessage.add("Integration with " + ChatColor.YELLOW + worldEdit.getName());
	    titleMessage.send(Bukkit.getConsoleSender());

        /* Updater */
	    if(config.useUpdater)
		    runUpdater(getServer().getConsoleSender(), 20 * 10);

        /* Metrics */
	    if(config.useMetrics)
		    runMetrics(20 * 2);
    }

    /**
     * Désactivation du plugin
     */
    @Override
    public void onDisable()
    {
        setEnable(false);

	    super.onDisable();

	    PluginMessage.toConsole(this, "Disabled", ChatColor.RED, new Line("AddLight is now disabled", ChatColor.DARK_RED));
    }

    public void runUpdater(final CommandSender sender, int delay)
    {
	    super.runUpdater(new BukkitUpdater(this, "255160"), sender, delay);
    }

    private void runMetrics(int delay)
    {
	    super.RunMetrics(delay);
    }

    /**
     * @return Tell if plugin is enable
     */
    public boolean isEnable() {
        return isEnable;
    }

    /**
     * @param enable Enable the plugin
     */
    public void setEnable(boolean enable)
    {
	    isEnable = enable;
    }
}
