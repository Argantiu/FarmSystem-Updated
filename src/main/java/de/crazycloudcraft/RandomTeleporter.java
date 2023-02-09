package de.butzlabben.farm;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class RandomTeleporter {

	private Random r = new Random();
	private World w;

	public Location getRandomLoc() {
		int x = createInt();
		int z = createInt();

		for (int y = 1; y < 128; y++) {
			Block b = w.getBlockAt(x, y, z);
			if (validY(b)) {
				if (validY(b.getRelative(BlockFace.EAST)) && validY(b.getRelative(BlockFace.SOUTH))
						&& validY(b.getRelative(BlockFace.NORTH)) && validY(b.getRelative(BlockFace.WEST)))
					return b.getLocation();
			}
			continue;
		}
		return getRandomLoc();
	}

	public boolean validY(Block b) {
		if (b.getType() == Material.AIR && b.getRelative(BlockFace.UP).getType() == Material.AIR
				&& b.getRelative(BlockFace.UP).getRelative(BlockFace.UP).getType() == Material.AIR) {
			if (b.getRelative(BlockFace.DOWN).getType() != Material.BEDROCK)
				return true;
		}
		return false;
	}

	public RandomTeleporter(World w) {
		this.w = w;
	}

	private int createInt() {
		int d = r.nextInt(300000);
		boolean b = r.nextBoolean();
		if (b) {
			d = d - d * 2;
		}
		return d;
	}
}
