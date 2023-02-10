package de.crazycloudcraft.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import de.crazycloudcraft.FarmCreator;
import de.crazycloudcraft.main.FarmSystem;
import de.crazycloudcraft.MessageConfig;

public class FarmGenerateNetherCMD extends FarmCMD {
	
	public final static FarmGenerateNetherCMD instance = new FarmGenerateNetherCMD();

	private FarmGenerateNetherCMD() {
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
