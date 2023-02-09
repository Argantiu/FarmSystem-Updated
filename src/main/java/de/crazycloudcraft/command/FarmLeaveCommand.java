package de.butzlabben.farm.command;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.butzlabben.farm.FarmSystem;
import de.butzlabben.farm.MessageConfig;

public class FarmLeaveCommand extends FarmCommand {
	
	public final static FarmLeaveCommand instance = new FarmLeaveCommand();
	
	private FarmLeaveCommand() {
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
