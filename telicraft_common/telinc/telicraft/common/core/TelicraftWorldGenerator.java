package telinc.telicraft.common.core;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenMinable;
import telinc.telicraft.common.TelicraftMain;
import telinc.telicraft.common.misc.WorldGenImpactCrater;
import telinc.telincCore.WorldGenEnd;
import telinc.telincCore.WorldGenNether;
import cpw.mods.fml.common.IWorldGenerator;

/** World generator.
 * @author Telinc1
 * */
public class TelicraftWorldGenerator implements IWorldGenerator {
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.dimensionId){
		case -1:
			generateNether(world, random, chunkX * 16, chunkZ * 16);
			break;
		case 0:
			generateSurface(world, random, chunkX * 16, chunkZ * 16);
			break;
		case 1:
			generateEnd(world, random, chunkX * 16, chunkZ * 16);
			break;
		}
	}

	private void generateEnd(World world, Random rand, int chunkX, int chunkZ) {
		for (int i = 0; i < 30; i++) {
			int desXcoord = chunkX + rand.nextInt(16);
			int desYcoord = rand.nextInt(256);
			int desZcoord = chunkZ + rand.nextInt(16);

			(new WorldGenEnd(TelicraftMain.darkEndStone.blockID, 0, 10))
					.generate(world, rand, desXcoord, desYcoord, desZcoord);
		}
	}

	public void generateNether(World world, Random rand, int chunkX, int chunkZ) {
		for (int i = 0; i < 30; i++) {
			int nrXcoord = chunkX + rand.nextInt(16);
			int nrYcoord = rand.nextInt(100);
			int nrZcoord = chunkZ + rand.nextInt(16);

			(new WorldGenNether(TelicraftMain.crackedNetherrack.blockID, 0, 10))
					.generate(world, rand, nrXcoord, nrYcoord, nrZcoord);
		}
	}

	public void generateSurface(World world, Random rand, int chunkX, int chunkZ) {
		
		for (int i = 0; i < 10; i++) {
			int adamantXcoord = chunkX + rand.nextInt(16);
			int adamantYcoord = rand.nextInt(45);
			int adamantZcoord = chunkZ + rand.nextInt(16);

			(new WorldGenMinable(TelicraftMain.adamantOre.blockID, 7)).generate(world, rand, adamantXcoord, adamantYcoord, adamantZcoord);
		}

		for (int j = 0; j < 2; j++) {
			int squillXcoord = chunkX + rand.nextInt(16);
			int squillYcoord = rand.nextInt(256);
			int squillZcoord = chunkZ + rand.nextInt(16);

			(new WorldGenFlowers(TelicraftMain.squill.blockID)).generate(world,
					rand, squillXcoord, squillYcoord, squillZcoord);
		}
		
		for (int k = 0; k < 2; k++){
			int craterXcoord = chunkX + rand.nextInt(16);
			int craterYcoord = rand.nextInt(70);
			int craterZcoord = chunkZ + rand.nextInt(16);
			
			(new WorldGenImpactCrater()).generate(world, rand, craterXcoord, craterYcoord, craterZcoord);
		}
	}
}
