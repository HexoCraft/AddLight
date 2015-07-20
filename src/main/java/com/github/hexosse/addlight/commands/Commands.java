package com.github.hexosse.addlight.commands;

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
import com.github.hexosse.addlight.utils.NumberUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This file is part AddLight
 *
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class Commands implements CommandExecutor
{
    private final static AddLight plugin = AddLight.getPlugin();

    @Override
    public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args)
    {
        if (!(sender instanceof Player))
            return false;

        if(args.length == 0 || args[0].equalsIgnoreCase("help"))
            CommandHelp.execute(sender);

        else if(args[0].equalsIgnoreCase("enable") || args[0].equalsIgnoreCase("on"))
            CommandEnable.execute(sender);

        else if(args[0].equalsIgnoreCase("disable") || args[0].equalsIgnoreCase("off"))
            CommandDisable.execute(sender);

        else if(args[0].equalsIgnoreCase("ConnectedBlocks") || args[0].equalsIgnoreCase("cb"))
            CommandConnected.execute(sender);

        else if(NumberUtil.isInteger(args[0]))
            CommandLightlevel.execute(sender,args);

        else
            return false;

        return true;
    }
}
