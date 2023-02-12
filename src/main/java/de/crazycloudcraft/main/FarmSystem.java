package de.crazycloudcraft.main;

import java.io.File;
import java.util.HashMap;

import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import de.crazycloudcraft.command.FarmWorldCMD;
import de.crazycloudcraft.listeners.PlayerLeaveListener;
import de.crazycloudcraft.Logger;
import de.crazycloudcraft.config.Config;
import de.crazycloudcraft.MessageConfig;
import de.crazycloudcraft.FarmCreator;

public class FarmSystem extends JavaPlugin {

	private static FarmSystem instance;
	public static World w;
	public static World nw;
	public static Location farmspawn;
	public static Location nfarmspawn;

	public static String worldname = null;
	public static String nworldname = null;

	private final String version = this.getDescription().getVersion();

	public static HashMap<Player, Location> farmLocs = new HashMap<>();

	@Override
	public void onEnable() {
		instance = this;
		File dir = new File(this.getDataFolder() + "/templates");
		File config = new File(this.getDataFolder(), "config.yml");
		if (!dir.isDirectory())
			dir.mkdirs();
		Config.checkConfig(config);
		MessageConfig.checkConfig();

		if (worldname == null)
			worldname = Config.getWorldName();

		if (nworldname == null)
			nworldname = Config.getNetherWorldName();

		if (Config.getRefreshAtStartUp()) {
			Logger.NORMAL.log("Generating worlds, this may take a while...");
			farmspawn = FarmCreator.getInstance().createFarmWorld().getSpawnLocation();
			nfarmspawn = FarmCreator.getInstance().createNetherFarmWorld().getSpawnLocation();
		}

		if (Config.getRefresh() > 0) {
			Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
				Logger.NORMAL.log("Generating worlds, this may take a while...");
				farmspawn = FarmCreator.getInstance().createFarmWorld().getSpawnLocation();
				nfarmspawn = FarmCreator.getInstance().createNetherFarmWorld().getSpawnLocation();
			}, Config.getRefresh() * 20 * 60 * 60, Config.getRefresh() * 20 * 60 * 60);
		}

		Bukkit.getPluginManager().registerEvents(new PlayerLeaveListener(), this);
		getCommand("farm").setExecutor(new FarmWorldCMD());

		if (Config.getFallbackLocation() == null)
			Logger.WARN.log("Fallback-location was not set! I recommend to set it!");

		System.setProperty("bstats.relocatecheck", "false");
		Metrics m = new Metrics(this);
		m.getPluginData();

		Logger.SUCCESS.log("Succesfulley enabled FarmCreater v" + version);
	}

	@Override
	public void onDisable() {
		if (Config.getRefreshAtStartUp()) {
			Logger.NORMAL.log("Deleting old worlds, this may take a while...");
			FarmCreator.getInstance().deleteFarmWorld();
			FarmCreator.getInstance().deleteNetherFarmWorld();
		}
		Logger.SUCCESS.log("Succesfulley disabled FarmCreater v" + version);
	}

	public static FarmSystem getInstance() {
		return instance;
	}
}
