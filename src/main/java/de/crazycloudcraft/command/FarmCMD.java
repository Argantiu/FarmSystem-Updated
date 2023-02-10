package de.crazycloudcraft.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import de.crazycloudcraft.MessageConfig;

public abstract class FarmCMD implements CommandExecutor {

	private final String permission;
	private final boolean console;

	public FarmCMD(String permission) {
		this(permission, false);
	}

	public FarmCMD(String permission, boolean console) {
		this.console = console;
		this.permission = permission;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!hasPermission(sender)) {
			sender.sendMessage(MessageConfig.getNoPermission());
			return true;
		}
		return onCommand(sender);
	}

	public abstract boolean onCommand(CommandSender cs);

	public boolean hasPermission(CommandSender cs) {
		if (console) {
			return cs instanceof ConsoleCommandSender || cs.hasPermission(permission);
		}
		return cs instanceof ConsoleCommandSender == false && cs.hasPermission(permission);
	}

}
