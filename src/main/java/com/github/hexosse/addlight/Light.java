package com.github.hexosse.addlight;

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

import com.github.hexosse.pluginframework.pluginapi.PluginObject;
import org.bukkit.Location;
import ru.BeYkeRYkt.LightAPI.ChunkInfo;
import ru.BeYkeRYkt.LightAPI.LightAPI;
import ru.BeYkeRYkt.LightAPI.LightRegistry;

import java.util.List;

/**
 * This file is part of AddLight
 *
 * @author <b>hexosse</b> (<a href="https://github.com/hexosse">hexosse on GitHub</a>).
 */
public class Light extends PluginObject<AddLight>
{
    private LightRegistry registry;
    private int ticks = 40;

    public Light(AddLight plugin)
    {
        super(plugin);
        registry = LightAPI.getRegistry(plugin);
    }

    public void Start()
    {
        registry.startAutoSend(ticks);
    }

    public void Stop()
    {
        registry.stopAutoSend();
    }

    public void Create(Location location, int lightLevel)
    {
        registry.createLight(location, lightLevel);
        List<ChunkInfo> list = registry.collectChunks(location);
        if(!registry.isAutoSend())
        {
            registry.sendChunks(list);
        }
    }

    public void Create(List<Location> list, int lightlevel)
    {
        for(Location location : list)
        {
            Create(location, lightlevel);
        }
    }

    public void Delete(Location location)
    {
        registry.deleteLight(location);
        List<ChunkInfo> list = registry.collectChunks(location);
        if(!registry.isAutoSend())
        {
            registry.sendChunks(list);
        }
    }

    public void Delete(List<Location> list)
    {
        for(Location location : list)
        {
            Delete(location);
        }
    }
}
