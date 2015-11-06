package com.github.hexosse.addlight;

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

import com.github.hexosse.addlight.commands.Commands;
import com.github.hexosse.addlight.configuration.Config;
import com.github.hexosse.addlight.configuration.Messages;
import com.github.hexosse.addlight.events.BlockListener;
import com.github.hexosse.addlight.events.PlayerListener;
import com.github.hexosse.addlight.utils.plugins.WorldEditUtil;
import com.github.hexosse.baseplugin.BasePlugin;
import com.github.hexosse.baseplugin.metric.MetricsLite;
import com.github.hexosse.githubupdater.GitHubUpdater;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import java.io.IOException;

/**
 * This file is part AddLight
 *
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class AddLight extends BasePlugin
{
    public Config config = null;
    public Messages messages = null;
    private String repository = "hexosse/ChestPreview";
    private static Light light = null;

    public boolean enable = false;
    public boolean connected = false;
    public int lightlevel = 12;


    /**
     * Activation du plugin
     */
    @Override
    public void onEnable()
    {
        /* Chargement de la config */
        config = new Config(this, this.getDataFolder(), "config.yml");         config.load();
        messages = new Messages(this, this.getDataFolder(), "message.yml");    messages.load();

        /* Enregistrement des listeners */
        Bukkit.getPluginManager().registerEvents(new PlayerListener(this), this);
        Bukkit.getPluginManager().registerEvents(new BlockListener(this), this);

        /* Enregistrement du gestionnaire de commandes */
        this.getCommand("al").setExecutor(new Commands(this));
        this.getCommand("al").setTabCompleter(new Commands(this));

        /* Updater */
        if(config.useUpdater)
            RunUpdater(config.downloadUpdate);

        /* Metrics */
        if(config.useMetrics)
            RunMetrics();

        /**/
        light = new Light(this);
    }

    /**
     * Désactivation du plugin
     */
    @Override
    public void onDisable()
    {
        setEnable(false);
        super.onDisable();
    }

    public void RunUpdater(final boolean download)
    {
        GitHubUpdater updater = new GitHubUpdater(this, this.repository, this.getFile(), download?GitHubUpdater.UpdateType.DEFAULT:GitHubUpdater.UpdateType.NO_DOWNLOAD, true);
    }

    private void RunMetrics()
    {
        try
        {
            MetricsLite metrics = new MetricsLite(this);
            if(metrics.start())
                pluginLogger.info("Succesfully started Metrics, see http://mcstats.org for more information.");
            else
                pluginLogger.info("Could not start Metrics, see http://mcstats.org for more information.");
        } catch (IOException e)
        {
            // Failed to submit the stats :-(
        }
    }

    /**
     * @return Tell if plugin is enable
     */
    public boolean isEnable() {
        return enable;
    }

    /**
     * @param enable Enable the plugin
     */
    public void setEnable(boolean enable)
    {
        this.enable = enable;

        if(enable)
            light.Start();
        else
            light.Stop();
    }

    /**
     * @return Plugin instance
     */
    public static WorldEditPlugin getWorldEditPlugin()
    {
        PluginManager pm = Bukkit.getServer().getPluginManager();
        WorldEditPlugin worldEditPlugin = (WorldEditPlugin)pm.getPlugin("WorldEdit");
        if(worldEditPlugin != null && pm.isPluginEnabled(worldEditPlugin))
        {
            WorldEditUtil.setPlugin(worldEditPlugin);
            return worldEditPlugin;
        }
        else return null;
    }

    /**
     * @return Light instance
     */
    public static Light getLight()
    {
        return light;
    }
}
