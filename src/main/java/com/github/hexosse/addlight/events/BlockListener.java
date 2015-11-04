package com.github.hexosse.addlight.events;

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
import com.github.hexosse.baseplugin.event.BaseListener;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;

/**
 * This file is part AddLight
 *
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
@SuppressWarnings("unused")
public class BlockListener extends BaseListener<AddLight>
{
    /**
     * @param plugin The plugin that this object belong to.
     */
    public BlockListener(AddLight plugin)
    {
        super(plugin);
    }


    /**
     * @param event BlockBreakEvent
     */
    @EventHandler(priority= EventPriority.MONITOR)
    public void onBlockBreak(BlockBreakEvent event)
    {
        if(plugin.isEnable() && event.getPlayer().getItemInHand().getType() == Material.GLOWSTONE_DUST)
            event.setCancelled(true);
    }
}
