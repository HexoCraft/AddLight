package ru.BeYkeRYkt.LightAPI;

import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import ru.BeYkeRYkt.LightAPI.chunks.ChunkInfo;
import ru.BeYkeRYkt.LightAPI.events.DeleteLightEvent;
import ru.BeYkeRYkt.LightAPI.events.SetLightEvent;
import ru.BeYkeRYkt.LightAPI.events.UpdateChunkEvent;
import ru.BeYkeRYkt.LightAPI.nms.INMSHandler;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class LightRegistry {

	private boolean autoSend;
	private int id;
	private Plugin plugin;
	private INMSHandler handler;
	private List<ru.BeYkeRYkt.LightAPI.chunks.ChunkInfo> chunkList;

	public LightRegistry(INMSHandler handler, Plugin plugin) {
		this.handler = handler;
		this.plugin = plugin;
		this.chunkList = new CopyOnWriteArrayList<ru.BeYkeRYkt.LightAPI.chunks.ChunkInfo>();
	}

	public void createLight(Location location, int light) {
		SetLightEvent event = new SetLightEvent(location, light);
		getPlugin().getServer().getPluginManager().callEvent(event);

		if (event.isCancelled())
			return;
		handler.createLight(location, light);
	}

	public void deleteLight(Location location) {
		DeleteLightEvent event = new DeleteLightEvent(location);
		getPlugin().getServer().getPluginManager().callEvent(event);

		if (event.isCancelled())
			return;
		handler.deleteLight(location);
	}

	public List<ru.BeYkeRYkt.LightAPI.chunks.ChunkInfo> collectChunks(Location loc) {
		List<ru.BeYkeRYkt.LightAPI.chunks.ChunkInfo> list = handler.collectChunks(loc);
		for (ru.BeYkeRYkt.LightAPI.chunks.ChunkInfo cCoord : list) {
			if (ru.BeYkeRYkt.LightAPI.chunks.ChunkCache.CHUNK_INFO_CACHE.contains(cCoord)) {
				list.remove(cCoord);
			} else {
				if (!ru.BeYkeRYkt.LightAPI.chunks.ChunkCache.CHUNK_INFO_CACHE.contains(cCoord)) {
					ru.BeYkeRYkt.LightAPI.chunks.ChunkCache.CHUNK_INFO_CACHE.add(cCoord);
				}
				if (!getChunkCoordsList().contains(cCoord)) {
					getChunkCoordsList().add(cCoord);
				}
			}
		}
		return list;
	}

	public void sendChunks(List<ru.BeYkeRYkt.LightAPI.chunks.ChunkInfo> list) {
		for (ru.BeYkeRYkt.LightAPI.chunks.ChunkInfo cCoord : list) {
			sendChunk(cCoord);
		}
	}

	public void sendChunk(ru.BeYkeRYkt.LightAPI.chunks.ChunkInfo cCoord) {
		UpdateChunkEvent event = new UpdateChunkEvent(cCoord);
		getPlugin().getServer().getPluginManager().callEvent(event);
		if (event.isCancelled()) {
			return;
		}

		handler.updateChunk(event.getChunkInfo());

		if (getChunkCoordsList().contains(event.getChunkInfo())) {
			getChunkCoordsList().remove(event.getChunkInfo());
		}

		if (ru.BeYkeRYkt.LightAPI.chunks.ChunkCache.CHUNK_INFO_CACHE.contains(event.getChunkInfo())) {
			ru.BeYkeRYkt.LightAPI.chunks.ChunkCache.CHUNK_INFO_CACHE.remove(event.getChunkInfo());
		}
	}

	public void sendChunkChanges() {
		sendChunks(getChunkCoordsList());
	}

	public boolean isAutoSend() {
		return autoSend;
	}

	public void startAutoSend(int delay) {
		if (!isAutoSend()) {
			id = getPlugin().getServer().getScheduler().runTaskTimer(getPlugin(), new LightAPI.LightUpdater(this), 0, delay).getTaskId();
			autoSend = true;
		}
	}

	public void stopAutoSend() {
		if (isAutoSend()) {
			getPlugin().getServer().getScheduler().cancelTask(id);
			id = 0;
			autoSend = false;
		}
	}

	public Plugin getPlugin() {
		return plugin;
	}

	public List<ChunkInfo> getChunkCoordsList() {
		return chunkList;
	}
}
