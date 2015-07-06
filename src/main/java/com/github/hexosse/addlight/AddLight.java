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
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * This file is part AddLight
 *
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 * @date 05/07/2015
 */
public class AddLight extends JavaPlugin
{
    private static AddLight plugin;

    public boolean enable;
    public int lightlevel;


    /**
     * Activation du plugin
     */
    public AddLight()
    {
        plugin = this;
        enable = false;
        lightlevel = 10;
    }

    /**
     *
     */
    @Override
    public void onEnable()
    {
        /* Enregistrement des listeners */
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        Bukkit.getPluginManager().registerEvents(new BlockListener(), this);

        /* Enregistrement du gestionnaire de commandes */
        this.getCommand("al").setExecutor(new Commands());
    }

    /**
     * Désactivation du plugin
     */
    @Override
    public void onDisable()
    {
        super.onDisable();
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

}
