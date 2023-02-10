package de.crazycloudcraft.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import de.crazycloudcraft.main.FarmSystem;

public class PlayerLeaveListener implements Listener {

	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if (e.getPlayer().getWorld().getName().equals(FarmSystem.worldname)
				|| e.getPlayer().getWorld().getName().equals(FarmSystem.nworldname)) {
			Location loc = FarmSystem.farmLocs.get(p);
			if (loc == null)
				return;
			p.teleport(loc);
			FarmSystem.farmLocs.remove(p);
		}
	}
}
