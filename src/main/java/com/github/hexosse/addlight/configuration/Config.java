package com.github.hexosse.addlight.configuration;

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
import com.github.hexosse.baseplugin.config.BaseConfig;

import java.io.File;

@BaseConfig.ConfigHeader(comment = {
        "############################################################",
        "# | AddLight by hexosse                                  | #",
        "############################################################"
})
@BaseConfig.ConfigFooter(comment = {
        " ",
        " ",
        "############################################################"
})

public class Config extends BaseConfig
{
    /* Plugin */
    @ConfigComment(path = "plugin")
    @ConfigOptions(path = "plugin.useMetrics")
    public boolean useMetrics = (boolean) true;
    @ConfigOptions(path = "plugin.useUpdater")
    public boolean useUpdater = (boolean) true;
    @ConfigOptions(path = "plugin.downloadUpdate")
    public boolean downloadUpdate = (boolean) true;


    /**
     * @param dataFolder Folder that contains the config file
     * @param filename   Name of the config file
     */
    public Config(AddLight plugin, File dataFolder, String filename)
    {
        super(plugin, new File(dataFolder, filename), filename);
    }

    public void reloadConfig() {
        load();
    }
}
