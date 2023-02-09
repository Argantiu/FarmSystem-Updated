package de.butzlabben.farm;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

/**
 * @author Butzlabben
 * @since 19.07.2018
 */
public class MessageConfig {

	private static File file;
	private static YamlConfiguration cfg;
	
	public static void checkConfig() {
		file = new File(FarmSystem.getInstance().getDataFolder(), "messages.yml");
		if(!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e1) {
				Logger.ERROR.log("Couldn't create message.yml");
				e1.printStackTrace();
			}
		
		cfg = YamlConfiguration.loadConfiguration(file);
		
		cfg.options().copyDefaults(true);
		
		cfg.addDefault("no_permission", "§cYou don't have permissions!");
		
		cfg.addDefault("wrong_usage", "§cWrong usage: For more information do §e/farm help");
		
		cfg.addDefault("left_farmworld", "§aYou left the farm world!");
		
		cfg.addDefault("in_no_farmworld", "§cYou are in no farm world");
		
		cfg.addDefault("already_farmworld", "§cYou are already in farmworld!");
		cfg.addDefault("still_creating_world", "§cThe farmworld is still creating");
		cfg.addDefault("world_not_created", "§cWorld is not created!");
		cfg.addDefault("teleported_world", "§aYou were teleported to farmworld!");
		
		cfg.addDefault("already_farmnether", "§cYou are already in farm nether!");
		cfg.addDefault("still_creating_nether", "§cThe farm nether is still creating");
		cfg.addDefault("nether_not_created", "§cNether is not created!");
		cfg.addDefault("teleported_nether", "§aYou were teleported to farm nether!");
		
		cfg.addDefault("generating_world", "§aGenerating world, this may take a while...");
		cfg.addDefault("generated_world", "§aA new farm world has been generated!");
		
		cfg.addDefault("generating_nether", "§aGenerating nether, this may take a while...");
		cfg.addDefault("generated_nether", "§aA new farm nether has been generated!");
		
		try {
			cfg.save(file);
		} catch (IOException e) {
			Logger.ERROR.log("Couldn't save message.yml");
			e.printStackTrace();
		}
	}
	
	public static String getStillCreatingNether() {
		return cfg.getString("still_creating_world");
	}
	
	public static String getNetherNotCreated() {
		return cfg.getString("world_not_created");
	}
	
	public static String getTeleportedNether() {
		return cfg.getString("teleported_world");
	}
	
	public static String getAlreadyNether() {
		return cfg.getString("already_farmworld");
	}
	
	public static String getStillCreatingWorld() {
		return cfg.getString("still_creating_world");
	}
	
	public static String getWorldNotCreated() {
		return cfg.getString("world_not_created");
	}
	
	public static String getTeleportedWorld() {
		return cfg.getString("teleported_world");
	}
	
	public static String getAlreadyWorld() {
		return cfg.getString("already_farmworld");
	}
	
	
	//===============================
	
	public static String getWrongUsage() {
		return cfg.getString("wrong_usage");
	}
	
	public static String getNoPermission() {
		return cfg.getString("no_permission");
	}
	
	public static String getLeftFarmWorld() {
		return cfg.getString("left_farmworld");
	}
	
	public static String getInNoWorld() {
		return cfg.getString("in_no_farmworld");
	}
	
	public static String getGeneratingWorld() {
		return cfg.getString("generating_world");
	}
	
	public static String getGeneratedWorld() {
		return cfg.getString("generated_world");
	}

	public static String getGeneratingNether() {
		return cfg.getString("generating_nether");
	}
	
	public static String getGeneratedNether() {
		return cfg.getString("generated_nether");
	}

}
