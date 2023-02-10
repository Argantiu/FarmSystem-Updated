package de.crazycloudcraft.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import de.butzlabben.farm.FarmCreator;
import de.butzlabben.farm.FarmSystem;
import de.butzlabben.farm.MessageConfig;

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
