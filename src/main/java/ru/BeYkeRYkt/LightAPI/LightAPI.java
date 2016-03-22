package ru.BeYkeRYkt.LightAPI;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import ru.BeYkeRYkt.LightAPI.nms.BukkitImpl;
import ru.BeYkeRYkt.LightAPI.nms.CraftBukkit.CraftBukkitImpl;
import ru.BeYkeRYkt.LightAPI.nms.CraftBukkit.SpigotImpl;
import ru.BeYkeRYkt.LightAPI.nms.INMSHandler;

import java.util.ArrayList;
import java.util.List;

public class LightAPI{

	private static INMSHandler handler;
	private List<BukkitImpl> support;

	public void onEnable()
	{
		this.support = new ArrayList<BukkitImpl>();

		addSupportImplement(new SpigotImpl()); // Spigot
		addSupportImplement(new CraftBukkitImpl()); // CraftBukkit

		if (!reloadInitHandler()) {
			return;
		}
	}

	public void onDisable()
	{
		this.support.clear();
		LightAPI.handler = null;
	}

	public static LightRegistry getRegistry(Plugin plugin) {
		return new LightRegistry(handler, plugin);
	}

	public boolean reloadInitHandler()
	{
		if (handler != null) {
			handler = null;
		}
		String version = Bukkit.getVersion();
		BukkitImpl impl = checkSupport(version);
		if (impl != null) {
			try {
				final Class<?> clazz = Class.forName(impl.getPath());
				// Check if we have a NMSHandler class at that location.
				if (INMSHandler.class.isAssignableFrom(clazz)) {
					LightAPI.handler = (INMSHandler) clazz.getConstructor().newInstance();
				}
			} catch (final Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		} else {
			return false;
		}
	}

	public void addSupportImplement(BukkitImpl impl) {
		if (checkSupport(impl.getNameImpl()) == null) {
			support.add(impl);
		}
	}

	public List<BukkitImpl> getSupportImplements()
	{
		return support;
	}

	private BukkitImpl checkSupport(String name)
	{
		for (BukkitImpl impl : support) {
			if (name.contains(impl.getNameImpl())) {
				return impl;
			}
		}
		return null;
	}


	public INMSHandler getNMSHandler() {
		return handler;
	}

	public static class LightUpdater implements Runnable {

		private LightRegistry registry;

		public LightUpdater(LightRegistry registry) {
			this.registry = registry;
		}

		@Override
		public void run() {
			registry.sendChunkChanges();
		}

	}
}
