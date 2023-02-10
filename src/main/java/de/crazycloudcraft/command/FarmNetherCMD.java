package de.crazycloudcraft.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.crazycloudcraft.FarmCreator;
import de.crazycloudcraft.main.FarmSystem;
import de.crazycloudcraft.MessageConfig;

public class FarmNetherCMD extends FarmCMD {

	public final static FarmNetherCMD instance = new FarmNetherCMD();

	private FarmNetherCMD() {
		super("farm.use.nether");
	}

	@Override
	public boolean onCommand(CommandSender cs) {
		if(cs instanceof Player == false) {
			cs.sendMessage("You are not a player!");
			return true;
		}
		Player p = (Player) cs;
		if (p.getWorld().getName().equals(FarmSystem.nworldname)) {
			cs.sendMessage(MessageConfig.getAlreadyNether());
			return true;
		}

		if (FarmCreator.getInstance().isCreatingNether()) {
			cs.sendMessage(MessageConfig.getStillCreatingNether());
			return true;
		}
		if (FarmSystem.nfarmspawn == null) {
			p.sendMessage(MessageConfig.getNetherNotCreated());
			return true;
		}
		
		if (!p.getWorld().getName().equals(FarmSystem.worldname)
				&& !p.getWorld().getName().equals(FarmSystem.nworldname)) {
			FarmSystem.farmLocs.put(p, p.getLocation());
		}		
		
		p.teleport(FarmSystem.nfarmspawn);
		cs.sendMessage(MessageConfig.getTeleportedNether());
		return true;
	}
}
