package de.butzlabben.farm.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import de.butzlabben.farm.FarmCreator;
import de.butzlabben.farm.FarmSystem;
import de.butzlabben.farm.MessageConfig;

public class FarmGenerateNetherCommand extends FarmCommand {
	
	public final static FarmGenerateNetherCommand instance = new FarmGenerateNetherCommand();

	private FarmGenerateNetherCommand() {
		super("farm.generate.nether", true);
	}

	@Override
	public boolean onCommand(CommandSender cs) {
		cs.sendMessage(MessageConfig.getGeneratingNether());
		Bukkit.getScheduler().runTask(FarmSystem.getInstance(), () -> {
			FarmCreator.getInstance().createNetherFarmWorld();
			cs.sendMessage(MessageConfig.getGeneratedNether());
		});		
		return true;
	}

}
