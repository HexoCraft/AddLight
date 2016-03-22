package ru.BeYkeRYkt.LightAPI.nms.CraftBukkit;

import org.bukkit.Bukkit;
import ru.BeYkeRYkt.LightAPI.LightAPI;
import ru.BeYkeRYkt.LightAPI.nms.BukkitImpl;

/**
 * For Spigot core
 * 
 * @author DinDev
 *
 */
public class SpigotImpl implements BukkitImpl {

	@Override
	public String getNameImpl() {
		return "Spigot";
	}

	@Override
	public String getPath()
	{
		String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
		return "ru.BeYkeRYkt.LightAPI.nms.CraftBukkit." + version + ".NMSHandler";
	}
}
