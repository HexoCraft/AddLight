package com.github.hexosse.addlight.utils;

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

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.material.MaterialData;

/**
 * This file is part AddLight
 *
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class BlockUtil
{
    public static Material getMaterial(Block block)
    {
        return block.getType();
    }

    public static short getDurability(Block block)
    {
        MaterialData md = block.getState().getData();

        return md.toItemStack().getDurability();
    }

    public static boolean compare(Block b1, Block b2)
    {
        return ( getMaterial(b1)==getMaterial(b2) && getDurability(b1)==getDurability(b2) );
    }
}
