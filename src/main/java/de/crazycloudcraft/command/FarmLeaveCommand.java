package de.crazycloudcraft.command;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.crazycloudcraft.main.FarmSystem;
import de.crazycloudcraft.MessageConfig;

public class FarmLeaveCMD extends FarmCMD {
	
	public final static FarmLeaveCMD instance = new FarmLeaveCMD();
	
	private FarmLeaveCMD() {
		super("farm.use.leave");
	}

	@Override
	public boolean onCommand(CommandSender cs) {
		Player p = (Player) cs;
		if(p.getWorld().getName().equals(FarmSystem.worldname) || p.getWorld().getName().equals(FarmSystem.nworldname)) {
			Location loc = FarmSystem.farmLocs.remove(p);
			if(loc != null) {
				p.teleport(loc);
				cs.sendMessage(MessageConfig.getLeftFarmWorld());
				return true;
			}
		}
		cs.sendMessage(MessageConfig.getInNoWorld());
		return true;
	}

}
