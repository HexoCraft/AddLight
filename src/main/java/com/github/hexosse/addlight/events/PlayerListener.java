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
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import ru.BeYkeRYkt.LightAPI.LightAPI;

/**
 * This file is part AddGlow
 *
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 * @date 05/07/2015
 */
@SuppressWarnings("unused")
public class PlayerListener implements Listener
{
    private final static AddLight plugin = AddLight.getPlugin();

    @EventHandler(priority=EventPriority.HIGH)
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        final Player player = event.getPlayer();

        if(!plugin.isEnable()) return;

        //
        if(event.getAction().equals(Action.LEFT_CLICK_BLOCK))
        {
            if(player.getItemInHand().getType() != Material.GLOWSTONE_DUST)
                return;

            // Création de la lumière
            LightAPI.createLight(event.getClickedBlock().getLocation(),plugin.lightlevel);
        }

        //
        if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
        {
            if(player.getItemInHand().getType() != Material.GLOWSTONE_DUST)
                return;

            // Création de la lumière
            LightAPI.deleteLight(event.getClickedBlock().getLocation());
        }
    }
}
