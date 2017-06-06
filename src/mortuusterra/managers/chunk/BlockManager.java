package mortuusterra.managers.chunk;
/*
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
*/
public class BlockManager {

	/**
	 * Gets data from all blocks in the parameters
	 */
	/*
	int xStart;
	int xEnd;
	int yStart;
	int yEnd;
	int zStart;
	int zEnd;

	private List<String> getBlocks(World world) {
		List<String> blockData = new ArrayList<>();
		for (int x = xStart; x <= xEnd; x++) {
			for (int y = yStart; y <= yEnd; y++) {
				for (int z = zStart; z <= zEnd; z++) {
					Location location = new Location(world, x, y, z);
					blockData.add(toBlockData(location.getBlock()));
				}
			}
		}
		return blockData;

	}

	/**
	 * Prepares the block to be saved in YAML
	 */
	/*
	@SuppressWarnings("deprecation")
	private String toBlockData(Block block) {
		return block.getX() + "_" + block.getY() + "_" + block.getZ() + "_" + block.getType().toString() + "_"
				+ block.getData();
	}

	/**
	 * Turns YAML String data into int[] array
	 */
	/*
	@SuppressWarnings("deprecation")
	private int[] getBlockData(String data) {
		String[] dat = data.split("_");
		int x = Integer.parseInt(dat[1]);
		int y = Integer.parseInt(dat[2]);
		int z = Integer.parseInt(dat[3]);
		Material material = Material.valueOf(dat[4]);
		byte bit = (byte) Integer.parseInt(dat[5]);

		return new int[] { x, y, z, material.getId(), bit };
	}

	/**
	 * Places a block using that int[] array
	 */
	/*
	@SuppressWarnings("deprecation")
	private boolean placeBlockByArray(Location center, int[] array) {
		try {
			center.add(array[0], array[1], array[2]);
			center.getBlock().setType(Material.getMaterial(array[3]));
			center.getBlock().setData((byte) array[4]);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}
*/
}
