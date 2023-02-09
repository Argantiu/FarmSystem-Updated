package de.butzlabben.farm;

import org.bukkit.Bukkit;

public enum Logger {

	NORMAL("§8[§7FarmInfo§8] §7"),
	SUCCESS("§8[§aFarmSuccess§8] §a"),
	WARN("§8[§eFarmWarn§8] §e"),
	ERROR("§8[§cFarmError§8] §c"),
	DEBUG("§8[§bFarmgDebug§8] §b");
	
	private final String prefix;
	
	Logger(String prefix) {
		this.prefix = prefix;
	}
	
	public final String getPrefix() {
		return this.prefix;
	}
	
	public final void log(String msg) {
		Bukkit.getConsoleSender().sendMessage(prefix + msg);
	
	}
}
