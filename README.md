# AddLight
This plugin allow server admin to add invisible light sources.

Enable the plugin using the command **/AddLight enable** then left click any item or inside a [WorldEdit](https://dev.bukkit.org/bukkit-plugins/worldedit/) selection with glowstone dust to add invisible light source.
You can also remove the light source by right clicking.

### Dependency :
From <red>4.0.0</red>, AddLight do not have any dependencies.

But if you are using an old version, AddLight depend on [LightAPI](https://www.spigotmc.org/resources/lightapi.4510/) from BeYkeRYkt.</br>
As BeYkeRYkt often change it's API, you'll find which version to use for any versions of AddLight previously to 4.0.0

[AddLight 1.4.0](https://www.spigotmc.org/resources/addlight.9163/download?version=80297) with [LightAPI 2.0.2](https://github.com/hexosse/maven-repo/raw/master/3rdParty/LightAPI/2.0.2/LightAPI-2.0.2.jar) from 1.8.8 to 1.9.4</br>
[AddLight 1.5.0](https://www.spigotmc.org/resources/addlight.9163/download?version=97990) with [LightAPI 3.0.0](https://www.spigotmc.org/resources/lightapi.4510/download?version=97912) from 1.8.8 to 1.10</br>
[AddLight 3.1.1](https://www.spigotmc.org/resources/addlight.9163/download?version=102251) with [LightAPI 3.2.3](https://www.spigotmc.org/resources/lightapi.4510/download?version=98597) from 1.8.8 to 1.10</br>
[AddLight 4.0.0](https://www.spigotmc.org/resources/addlight.9163/download?version=102251) from 1.8.8 to 1.11</br>

(for minecraft 1.9.4 use this version [LightAPI 2.0.2](https://github.com/HexoCraft/LightAPI/releases/download/2.0.2/LightAPI-2.0.2.jar))

### Commands :
* /AddLight : Display the status of the plugin
* /AddLight [help] : Display AddLight help
* /AddLight [enable|on] : Enable light creation
* /AddLight [disable|off] : Disable light creation
* /AddLight [LightLevel] <intensity> : Define light intensity (1-15)
* /AddLight [ConnectedBlocks] : Toggle the connected block status (Max connected blocks can be configure in the config file)

### Permissions :
* AddLight.admin : Allows creation of light to admin users
* AddLight.user : Allows creation of light to any users
* AddLight.use : Allows a user to use this plugin
* AddLight.worldedit : AddLight can apply to a WorldEditSelection
* AddLight.connected : AddLight can apply to any connected blocks
* AddLight.intensity : user can change the light intensity
* AddLight.debug : Display debug info

* AddLight.* : given to op
  * AddLight.admin

* AddLight.admin
  * AddLight.use
  * AddLight.worldedit
  * AddLight.connected
  * AddLight.intensity

* AddLight.user
  * AddLight.use

### Config :
The plugin use metrics and an integrated updater.<br>Both can be disable in config.yml

### Ressources :
Releases : https://github.com/hexocraft/AddLight/releases<br>
Latest build :  https://drone.io/github.com/hexocraft/AddLight/files
