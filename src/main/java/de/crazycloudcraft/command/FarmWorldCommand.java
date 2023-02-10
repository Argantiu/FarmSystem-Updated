package de.crazycloudcraft.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.crazycloudcraft.FarmCreator;
import de.crazycloudcraft.main.FarmSystem;
import de.crazycloudcraft.MessageConfig;

public class FarmWorldCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			if (cs instanceof Player == false) {
				cs.sendMessage("You are not a player!");
				return true;
			}
			if (cs.hasPermission("farm.use.world") == false) {
				cs.sendMessage(MessageConfig.getNoPermission());
				return true;
			}
			Player p = (Player) cs;
			if (p.getWorld().getName().equals(FarmSystem.worldname)) {
				cs.sendMessage(MessageConfig.getAlreadyWorld());
				return true;
			}

			if (FarmCreator.getInstance().isCreating()) {
				cs.sendMessage(MessageConfig.getStillCreatingWorld());
				return true;
			}
			if (FarmSystem.farmspawn == null) {
				p.sendMessage(MessageConfig.getWorldNotCreated());
				return true;
			}

			if (!p.getWorld().getName().equals(FarmSystem.worldname)
					&& !p.getWorld().getName().equals(FarmSystem.nworldname)) {
				FarmSystem.farmLocs.put(p, p.getLocation());
			}

			p.teleport(FarmSystem.farmspawn);
			cs.sendMessage(MessageConfig.getTeleportedWorld());
		} else {
			if (args.length == 1) {
				args[0] = args[0].toLowerCase();
				if (args[0].equals("nether")) {
					return FarmNetherCommand.instance.onCommand(cs, cmd, label, args);
				} else if (args[0].equals("leave")) {
					return FarmLeaveCommand.instance.onCommand(cs, cmd, label, args);
				} else if (args[0].equals("help")) {
					return FarmHelpCommand.instance.onCommand(cs, cmd, label, args);
				}
			} else if (args.length == 2) {
				if (args[0].toLowerCase().equals("generate")) {
					args[1] = args[1].toLowerCase();
					if (args[1].equals("world")) {
						return FarmGenerateWorldCommand.instance.onCommand(cs, cmd, label, args);
					} else if (args[1].equals("nether")) {
						return FarmGenerateNetherCommand.instance.onCommand(cs, cmd, label, args);
					}
				}
			}
			cs.sendMessage(MessageConfig.getWrongUsage());
		}
		return true;
	}
}
