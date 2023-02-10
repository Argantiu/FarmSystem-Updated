package de.crazycloudcraft.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import de.crazycloudcraft.FarmCreator;
import de.crazycloudcraft.main.FarmSystem;
import de.crazycloudcraft.MessageConfig;

public class FarmGenerateWorldCMD extends FarmCMD {
	
	public final static FarmGenerateWorldCMD instance = new FarmGenerateWorldCMD();

	public FarmGenerateWorldCMD() {
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
