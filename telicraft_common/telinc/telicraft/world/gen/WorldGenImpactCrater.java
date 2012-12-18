package telinc.telicraft.world.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import telinc.telicraft.TelicraftMain;

/**
 * Handles generating Impact Craters.
 * 
 * @author Mithion's .schematic to .java converter version 1.7
 * @see WorldGenImpactCrater#generate(World, Random, int, int, int)
 */
public class WorldGenImpactCrater extends WorldGenerator {
	protected int[] getValidSpawnBlocks() {
		return new int[] { Block.grass.blockID, Block.sand.blockID };
	}

	public boolean locationIsValidSpawn(World world, int i, int j, int k) {
		int distanceToAir = 0;
		int checkID = world.getBlockId(i, j, k);

		while (checkID != 0) {
			distanceToAir++;
			checkID = world.getBlockId(i, j + distanceToAir, k);
		}

		if (distanceToAir > 12) {
			return false;
		}
		j += distanceToAir - 1;

		int blockID = world.getBlockId(i, j, k);
		int blockIDAbove = world.getBlockId(i, j + 1, k);
		int blockIDBelow = world.getBlockId(i, j - 1, k);
		for (int x : getValidSpawnBlocks()) {
			if (blockIDAbove != 0) {
				return false;
			}
			if (blockID == x) {
				return true;
			} else if (blockID == Block.snow.blockID && blockIDBelow == x) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Constructor, empty.
	 */
	public WorldGenImpactCrater() {
	}

	/**
	 * The method that handles the generating.
	 * 
	 * @param world
	 *            The world object.
	 * @param rand
	 *            A random.
	 * @param i
	 *            X position.
	 * @param j
	 *            Y position.
	 * @param k
	 *            Z position.
	 */
	@Override
	public boolean generate(World world, Random rand, int i, int j, int k) {
		BiomeGenBase b = world.getBiomeGenForCoords(i, k);

		// check that each corner is one of the valid spawn blocks
		if (!locationIsValidSpawn(world, i, j, k)
				|| !locationIsValidSpawn(world, i + 3, j, k)
				|| !locationIsValidSpawn(world, i + 3, j, k + 3)
				|| !locationIsValidSpawn(world, i, j, k + 3)) {
			return false;
		}

		int var1 = rand.nextInt(99);
		int var2 = rand.nextInt(99);

		if (b.biomeName == "Desert" || b.biomeName == "DesertHills"
				|| b.biomeName == "Beach") {
			if (var1 < 6) {
				this.genBlocks(world, i, j, k);
				return true;
			} else {
				return false;
			}
		} else {
			if (var1 < 38) {
				this.genBlocks(world, i, j, k);
				return true;
			} else {
				return false;
			}
		}
	}

	public void genBlocks(World world, int i, int j, int k) {
		world.setBlock(i + 0, j + 0, k + 1, TelicraftMain.meteorBlock.blockID);
		world.setBlock(i + 0, j + 0, k + 2, TelicraftMain.meteorBlock.blockID);
		world.setBlock(i + 0, j + 1, k + 1, TelicraftMain.meteorBlock.blockID);
		world.setBlock(i + 0, j + 1, k + 2, TelicraftMain.meteorBlock.blockID);
		world.setBlock(i + 0, j + 2, k + 1, TelicraftMain.meteorBlock.blockID);
		world.setBlock(i + 0, j + 2, k + 2, TelicraftMain.meteorBlock.blockID);
		world.setBlock(i + 1, j + 0, k + 0, TelicraftMain.meteorBlock.blockID);
		world.setBlock(i + 1, j + 0, k + 1, TelicraftMain.meteorBlock.blockID);
		world.setBlock(i + 1, j + 0, k + 2, TelicraftMain.meteorBlock.blockID);
		world.setBlock(i + 1, j + 0, k + 3, TelicraftMain.meteorBlock.blockID);
		world.setBlock(i + 1, j + 1, k + 0, TelicraftMain.meteorBlock.blockID);
		world.setBlock(i + 1, j + 1, k + 1, 0);
		world.setBlock(i + 1, j + 1, k + 2, 0);
		world.setBlock(i + 1, j + 1, k + 3, TelicraftMain.meteorBlock.blockID);
		world.setBlock(i + 1, j + 2, k + 0, TelicraftMain.meteorBlock.blockID);
		world.setBlock(i + 1, j + 2, k + 1, 0);
		world.setBlock(i + 1, j + 2, k + 2, 0);
		world.setBlock(i + 1, j + 2, k + 3, TelicraftMain.meteorBlock.blockID);
		world.setBlock(i + 2, j + 0, k + 0, TelicraftMain.meteorBlock.blockID);
		world.setBlock(i + 2, j + 0, k + 1, TelicraftMain.meteorBlock.blockID);
		world.setBlock(i + 2, j + 0, k + 2, TelicraftMain.meteorBlock.blockID);
		world.setBlock(i + 2, j + 0, k + 3, TelicraftMain.meteorBlock.blockID);
		world.setBlock(i + 2, j + 1, k + 0, TelicraftMain.meteorBlock.blockID);
		world.setBlock(i + 2, j + 1, k + 1, 0);
		world.setBlock(i + 2, j + 1, k + 2, 0);
		world.setBlock(i + 2, j + 1, k + 3, TelicraftMain.meteorBlock.blockID);
		world.setBlock(i + 2, j + 2, k + 0, TelicraftMain.meteorBlock.blockID);
		world.setBlock(i + 2, j + 2, k + 1, 0);
		world.setBlock(i + 2, j + 2, k + 2, 0);
		world.setBlock(i + 2, j + 2, k + 3, TelicraftMain.meteorBlock.blockID);
		world.setBlock(i + 3, j + 0, k + 1, TelicraftMain.meteorBlock.blockID);
		world.setBlock(i + 3, j + 0, k + 2, TelicraftMain.meteorBlock.blockID);
		world.setBlock(i + 3, j + 1, k + 1, TelicraftMain.meteorBlock.blockID);
		world.setBlock(i + 3, j + 1, k + 2, TelicraftMain.meteorBlock.blockID);
		world.setBlock(i + 3, j + 2, k + 1, TelicraftMain.meteorBlock.blockID);
		world.setBlock(i + 3, j + 2, k + 2, TelicraftMain.meteorBlock.blockID);
	}
}