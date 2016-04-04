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
import ru.beykerykt.lightapi.chunks.ChunkInfo;
import ru.beykerykt.lightapi.chunks.Chunks;
import ru.beykerykt.lightapi.light.LightDataRequest;
import ru.beykerykt.lightapi.light.Lights;

import java.util.List;

/**
 * This file is part of AddLight
 *
 * @author <b>hexosse</b> (<a href="https://github.com/hexosse">hexosse on GitHub</a>).
 */
public class Light extends PluginObject<AddLight>
{
    public Light(AddLight plugin)
    {
        super(plugin);
    }

    public void Create(Location location, int lightLevel)
    {
        LightDataRequest request = Lights.createLight(location, lightLevel, false);
        if (request != null) {
            Chunks.addChunkToQueue(request);
        } else {
            for (ChunkInfo info : Chunks.collectModifiedChunks(location)) {
                Chunks.sendChunkUpdate(info);
            }
        }
    }

    public void Create(List<Location> list, int lightlevel)
    {
        synchronized(list) {
            for(Location location : list)
                Create(location, lightlevel);
        }
    }

    public void Delete(Location location)
    {
        LightDataRequest request = Lights.deleteLight(location, false);
        if (request != null) {
            Chunks.addChunkToQueue(request);
        } else {
            for (ChunkInfo info : Chunks.collectModifiedChunks(location)) {
                Chunks.sendChunkUpdate(info);
            }
        }
    }

    public void Delete(List<Location> list)
    {
        synchronized(list) {
            for(Location location : list)
                Delete(location);
        }
    }
}
