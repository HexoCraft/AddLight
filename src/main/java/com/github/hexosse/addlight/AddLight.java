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
import com.github.hexosse.addlight.events.BlockListener;
import com.github.hexosse.addlight.events.PlayerListener;
import com.github.hexosse.addlight.utils.MetricsLite;
import com.github.hexosse.addlight.utils.WorldEditUtil;
import com.github.hexosse.githubupdater.GitHubUpdater;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

/**
 * This file is part AddLight
 *
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class AddLight extends JavaPlugin
{
    private static AddLight plugin;
    private static String repository;

    public boolean enable;
    public int lightlevel;


    /**
     * Activation du plugin
     */
    public AddLight()
    {
        plugin = this;
        repository = "hexosse/AddLight";

        enable = false;
        lightlevel = 10;
    }

    /**
     *
     */
    @Override
    public void onEnable()
    {
        loadConfig();

        /* Enregistrement des listeners */
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        Bukkit.getPluginManager().registerEvents(new BlockListener(), this);

        /* Enregistrement du gestionnaire de commandes */
        this.getCommand("al").setExecutor(new Commands());

        /* Updater */
        if(this.getConfig().getBoolean("plugin.useUpdater"))
            RunUpdater(this.getConfig().getBoolean("plugin.downloadUpdate"));

        /* Metrics */
        if(this.getConfig().getBoolean("plugin.useMetrics"))
            RunMetrics();
    }

    /**
     * Désactivation du plugin
     */
    @Override
    public void onDisable()
    {
        super.onDisable();
    }

    public void loadConfig()
    {
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
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
                getLogger().info("Succesfully started Metrics, see http://mcstats.org for more information.");
            else
                getLogger().info("Could not start Metrics, see http://mcstats.org for more information.");
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
    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    /**
     * @return Plugin instance
     */
    public static AddLight getPlugin() {
        return plugin;
    }

    /**
     * @return Plugin instance
     */
    public static WorldEditPlugin getWorldEditPlugin()
    {
        PluginManager pm = Bukkit.getServer().getPluginManager();
        WorldEditPlugin worldEditPlugin = (WorldEditPlugin)pm.getPlugin("WorldEdit");
        if(worldEditPlugin instanceof WorldEditPlugin && pm.isPluginEnabled(worldEditPlugin))
        {
            WorldEditUtil.setPlugin(worldEditPlugin);
            return worldEditPlugin;
        }
        else return null;
    }

}
