package de.crazycloudcraft.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * @author Butzlabben
 * @since 2017
 */
public class Config {

	private Config() {
	}

	private static File file;

	public static void checkConfig(File f) {
		file = f;
		if (file.exists()) {
			YamlConfiguration cfg = getConfig();
			if (false == (
					cfg.isInt("refresh") && cfg.isBoolean("refresh_at_start_up"))) {
				try {
					Files.copy(file.toPath(),
							new File(file.getParentFile(), "config-broken-"
									+ new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss").format(new Date()) + ".yml").toPath(),
							StandardCopyOption.REPLACE_EXISTING);
					Files.delete(file.toPath());
					System.err.println("[FarmSystem] Config is broken, creating a new one!");
					checkConfig(f);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			try {
				InputStream in = FarmSystem.getInstance().getResource("config.yml");
				Files.copy(in, file.toPath());
			} catch (IOException e) {
				System.err.println("Wasn't able to create Config");
				e.printStackTrace();
			}
		}
	}

	public static long getSeed() {
		return getConfig().getLong("seed");
	}

	public static long getNetherSeed() {
		return getConfig().getLong("seed_nether");
	}

	public static Location getFallbackLocation() {
		World w = Bukkit.getWorld(getConfig().getString("fallback.world"));
		if (w == null)
			return null;
		Location loc = new Location(w, getConfig().getDouble("fallback.x"), getConfig().getDouble("fallback.y"),
				getConfig().getDouble("fallback.z"));
		return loc;
	}

	public static String getNetherTemplate() {
		return getConfig().getString("template_nether");
	}

	public static String getTemplate() {
		return getConfig().getString("template");
	}

	public static String getWorldGenerator() {
		return getConfig().getString("worldgenerator").replace("default", "default_1_1");
	}

	private static YamlConfiguration getConfig() {
		return YamlConfiguration.loadConfiguration(file);
	}

	public static int getRefresh() {
		return getConfig().getInt("refresh", 0);
	}

	public static boolean getRefreshAtStartUp() {
		return getConfig().getBoolean("refresh_at_start_up");
	}

	public static String getWorldName() {
		return getConfig().getString("farmworld_name");
	}

	public static String getNetherWorldName() {
		return getConfig().getString("farmworld_nether_name");
	}
}
