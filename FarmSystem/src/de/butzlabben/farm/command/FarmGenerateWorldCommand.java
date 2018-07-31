package de.butzlabben.farm.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import de.butzlabben.farm.FarmCreator;
import de.butzlabben.farm.FarmSystem;
import de.butzlabben.farm.MessageConfig;

public class FarmGenerateWorldCommand extends FarmCommand {
	
	public final static FarmGenerateWorldCommand instance = new FarmGenerateWorldCommand();

	public FarmGenerateWorldCommand() {
		super("farm.generate.world", true);
	}

	@Override
	public boolean onCommand(CommandSender cs) {
		cs.sendMessage(MessageConfig.getGeneratingWorld());
		Bukkit.getScheduler().runTask(FarmSystem.getInstance(), () -> {
			FarmCreator.getInstance().createFarmWorld();
			cs.sendMessage(MessageConfig.getGeneratedWorld());
		});		
		return true;
	}

}
