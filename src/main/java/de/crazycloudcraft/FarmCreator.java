package de.crazycloudcraft;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.block.Block;
import org.bukkit.World.Environment;
import org.bukkit.entity.Player;

/**
 * @author Butzlabben
 * @since 2017
 */
public class FarmCreator {

	private boolean generatingNether = false;
	private boolean generating = false;

	private static FarmCreator SINGLETON = new FarmCreator();

	public boolean isCreatingNether() {
		return generatingNether;
	}

	public boolean isCreating() {
		return generating;
	}

	public World createFarmWorld() {
		deleteFarmWorld();
		File file = new File(FarmSystem.getInstance().getDataFolder(), "templates/" + Config.getTemplate());
		if (file.exists()) {
			try {
				if (new File(file, "uid.dat").exists())
					FileUtils.deleteQuietly(new File(file, "uid.dat"));
				FileUtils.copyDirectory(file, new File(FarmSystem.worldname));
			} catch (IOException e) {
				Logger.ERROR.log("Could not copy template");
				e.printStackTrace();
			}
		}
		WorldCreator wc = new WorldCreator(FarmSystem.worldname);
		WorldType wt = WorldType.getByName(Config.getWorldGenerator());
		if (wt == null) {
			Logger.WARN.log("Could not choose worldtype. Will be default!");
		} else {
			wc.type(wt);
		}
		wc.environment(Environment.NORMAL);
		World w = Bukkit.createWorld(wc);
		Bukkit.getWorlds().add(w);
		FarmSystem.farmspawn = w.getSpawnLocation();
		generating = false;
		return w;
	}

	public void deleteNetherFarmWorld() {
		generatingNether = true;
		World w = Bukkit.getWorld(FarmSystem.nworldname);
		if (w == null)
			return;
		for (Player p : w.getPlayers()) {
			if (FarmSystem.farmLocs.containsKey(p)) {
				p.teleport(FarmSystem.farmLocs.get(p));
				FarmSystem.farmLocs.remove(p);
			} else {
				if (Config.getFallbackLocation() != null) {
					p.teleport(Config.getFallbackLocation());
				} else {
					for (World all : Bukkit.getWorlds()) {
						if (all.getName().equals(FarmSystem.worldname) || all.getName().equals(FarmSystem.nworldname))
							continue;
						p.teleport(all.getSpawnLocation());
						break;
					}
				}
			}
		}
		Chunk[] arrayOfChunk;
		int j = (arrayOfChunk = w.getLoadedChunks()).length;
		for (int i = 0; i < j; i++) {
			Chunk c = arrayOfChunk[i];
			c.unload();
		}
		if (Bukkit.unloadWorld(w, true)) {
			File file = new File(FarmSystem.nworldname);
			if (file.exists()) {
				try {
					FileUtils.deleteDirectory(file);
				} catch (IOException e) {
					Logger.ERROR.log("Wasn't able do delete old nether world");
					e.printStackTrace();
				}
			}
		}
	}

	public void deleteFarmWorld() {
		generating = true;
		World w = Bukkit.getWorld(FarmSystem.worldname);
		if (w == null)
			return;
		for (Player p : w.getPlayers()) {
			if (FarmSystem.farmLocs.containsKey(p)) {
				p.teleport(FarmSystem.farmLocs.get(p));
				FarmSystem.farmLocs.remove(p);
			} else {
				if (Config.getFallbackLocation() != null) {
					p.teleport(Config.getFallbackLocation());
				} else {
					for (World all : Bukkit.getWorlds()) {
						if (all.getName().equals(FarmSystem.worldname) || all.getName().equals(FarmSystem.nworldname))
							continue;
						p.teleport(all.getSpawnLocation());
						break;
					}
				}
			}
		}
		Chunk[] arrayOfChunk;
		int j = (arrayOfChunk = w.getLoadedChunks()).length;
		for (int i = 0; i < j; i++) {
			Chunk c = arrayOfChunk[i];
			c.unload();
		}
		if (Bukkit.unloadWorld(w, true)) {
			File file = new File(FarmSystem.worldname);
			if (file.exists()) {
				try {
					FileUtils.deleteDirectory(file);
				} catch (IOException e) {
					Logger.ERROR.log("Wasn't able do delete old world");
					e.printStackTrace();
				}
			}
		}
	}

	public static FarmCreator getInstance() {
		return SINGLETON;
	}

	public World createNetherFarmWorld() {
		deleteNetherFarmWorld();
		File file = new File(FarmSystem.getInstance().getDataFolder(), "templates/" + Config.getNetherTemplate());
		if (file.exists()) {
			try {
				if (new File(file, "uid.dat").exists())
					FileUtils.deleteQuietly(new File(file, "uid.dat"));
				FileUtils.copyDirectory(file, new File(FarmSystem.nworldname));
			} catch (IOException e) {
				Logger.ERROR.log("Could not copy template");
				e.printStackTrace();
			}
		}
		WorldCreator wc = new WorldCreator(FarmSystem.nworldname);
		WorldType wt = WorldType.getByName(Config.getWorldGenerator());
		if (wt == null) {
			Logger.WARN.log("Could not choose worldgenerator. Will be default!");
		} else {
			wc.type(wt);
		}
		wc.environment(Environment.NETHER);
		World w = Bukkit.createWorld(wc);
		Bukkit.getWorlds().add(w);
		RandomTeleporter teleporter = new RandomTeleporter(w);
		FarmSystem.nfarmspawn = teleporter.getRandomLoc();
		w.setSpawnLocation(FarmSystem.nfarmspawn.getBlockX(), FarmSystem.nfarmspawn.getBlockY(), FarmSystem.nfarmspawn.getBlockZ());
		int x = FarmSystem.nfarmspawn.getBlockX();
		int y = FarmSystem.nfarmspawn.getBlockY() - 64;
		int z = FarmSystem.nfarmspawn.getBlockZ();
		Block b = w.getBlockAt(x, y, z);
		b = w.getBlockAt(x, y, z);
		b.setType(Material.BEDROCK);

		b = w.getBlockAt(x + 1, y, z);
		b.setType(Material.BEDROCK);
		b = w.getBlockAt(x + 1, y, z + 1);
		b.setType(Material.BEDROCK);
		b = w.getBlockAt(x + 1, y, z - 1);
		b.setType(Material.BEDROCK);

		b = w.getBlockAt(x, y, z - 1);
		b.setType(Material.BEDROCK);
		b = w.getBlockAt(x, y, z + 1);
		b.setType(Material.BEDROCK);

		b = w.getBlockAt(x - 1, y, z);
		b.setType(Material.BEDROCK);
		b = w.getBlockAt(x - 1, y, z + 1);
		b.setType(Material.BEDROCK);
		b = w.getBlockAt(x - 1, y, z - 1);
		b.setType(Material.BEDROCK);

		b = w.getBlockAt(x - 2, y, z);
		b.setType(Material.BEDROCK);
		b = w.getBlockAt(x - 2, y, z + 1);
		b.setType(Material.BEDROCK);
		b = w.getBlockAt(x - 2, y, z - 1);
		b.setType(Material.BEDROCK);
		b = w.getBlockAt(x - 2, y, z + 2);
		b.setType(Material.BEDROCK);
		b = w.getBlockAt(x - 2, y, z - 2);
		b.setType(Material.BEDROCK);

		b = w.getBlockAt(x + 2, y, z);
		b.setType(Material.BEDROCK);
		b = w.getBlockAt(x + 2, y, z + 1);
		b.setType(Material.BEDROCK);
		b = w.getBlockAt(x + 2, y, z - 1);
		b.setType(Material.BEDROCK);
		b = w.getBlockAt(x + 2, y, z + 2);
		b.setType(Material.BEDROCK);
		b = w.getBlockAt(x + 2, y, z - 2);
		b.setType(Material.BEDROCK);

		b = w.getBlockAt(x - 2, y, z);
		b.setType(Material.BEDROCK);
		b = w.getBlockAt(x - 2, y, z + 1);
		b.setType(Material.BEDROCK);
		b = w.getBlockAt(x - 2, y, z - 1);
		b.setType(Material.BEDROCK);
		b = w.getBlockAt(x - 2, y, z + 2);
		b.setType(Material.BEDROCK);
		b = w.getBlockAt(x - 2, y, z - 2);
		b.setType(Material.BEDROCK);

		b = w.getBlockAt(x, y, z + 2);
		b.setType(Material.BEDROCK);
		b = w.getBlockAt(x + 1, y, z + 2);
		b.setType(Material.BEDROCK);
		b = w.getBlockAt(x - 1, y, z + 2);
		b.setType(Material.BEDROCK);

		b = w.getBlockAt(x, y, z - 2);
		b.setType(Material.BEDROCK);
		b = w.getBlockAt(x + 1, y, z - 2);
		b.setType(Material.BEDROCK);
		b = w.getBlockAt(x - 1, y, z - 2);
		b.setType(Material.BEDROCK);

		generatingNether = false;
		return w;
	}

	private FarmCreator() {
	}
}
