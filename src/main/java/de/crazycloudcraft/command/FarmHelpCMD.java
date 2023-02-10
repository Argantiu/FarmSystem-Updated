package de.crazycloudcraft.command;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

public class FarmHelpCMD extends FarmCMD {

	public final static FarmHelpCMD instance = new FarmHelpCMD();

	private FarmHelpCMD() {
		super("farm.help", true);
	}

	@Override
	public boolean onCommand(CommandSender cs) {
		if (cs instanceof ConsoleCommandSender == false && cs.hasPermission("farm.use.world")) {
			cs.sendMessage("§6/farm §8- §7Enter farm world");
		}
		if (FarmLeaveCommand.instance.hasPermission(cs)) {
			cs.sendMessage("§6/farm leave §8- §7Leave the farm world/nether");
		}
		if (FarmNetherCommand.instance.hasPermission(cs)) {
			cs.sendMessage("§6/farm nether §8- §7Enter farm nether");
		}
		if (FarmGenerateWorldCommand.instance.hasPermission(cs)) {
			cs.sendMessage("§6/farm generate world §8- §7Generate a new farm world");
		}
		if (FarmGenerateNetherCommand.instance.hasPermission(cs)) {
			cs.sendMessage("§6/farm generate nether §8- §7Generate a new farm nether");
		}
		cs.sendMessage("§6/farm help §8- §7Shows this help");
		return true;
	}

}
