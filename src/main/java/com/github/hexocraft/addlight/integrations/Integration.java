package com.github.hexocraft.addlight.integrations;

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

import com.github.hexocraftapi.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public abstract class Integration<PluginHook extends JavaPlugin>
{
	// Plugin to hook
	private Plugin     plugin     = null;
	private PluginHook pluginHook = null;
	private String     pluginName = null;

	public Integration(Plugin plugin, String pluginName )
	{
		this.plugin = plugin;
		this.pluginName = pluginName;
	}

	public PluginHook get()
	{
		if(this.pluginHook != null) return this.pluginHook;

		PluginManager pm = this.plugin.getServer().getPluginManager();
		PluginHook pluginHook = (PluginHook)pm.getPlugin(this.pluginName);
		if(pluginHook != null && pm.isPluginEnabled(pluginHook))
			this.pluginHook = pluginHook;

		return this.pluginHook;
	}

	public String getName()
	{
		return pluginName;
	}

	public boolean enabled()
	{
		return get() != null;
	}
}
