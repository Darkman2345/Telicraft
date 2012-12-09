package telinc.telicraft.common.blocks;

import java.util.Random;

import net.minecraft.src.*;

import telinc.telicraft.common.TelicraftMain;
import telinc.telicraft.common.reference.MainReferences;
import telinc.telicraft.common.reference.TextureReferences;
import telinc.telicraft.common.tileEntities.TileEntitySharpener;

public class BlockSharpener extends BlockContainer {

	public BlockSharpener(int par1) {
		super(par1, Material.iron);
		this.setCreativeTab(TelicraftMain.tabTelicraft);
	}

	@Override
	public int getBlockTextureFromSide(int i) {
		switch (i) {
		case 0:
			return 5; // Bottom
		case 1:
			return 6; // Top
		default:
			return 7; // Sides
		}
	}

	@Override
	public void onBlockDestroyedByExplosion(World par1World, int par2,
			int par3, int par4) {
		par1World.createExplosion((Entity)null, par2, par3, par4, MainReferences.MACHINE_EXPLOSION_STRENGTH, true);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int i, float a, float b, float c) {
		if (player.getCurrentEquippedItem() != null
				&& player.getCurrentEquippedItem().itemID == Item.flintAndSteel.shiftedIndex) {

			// Block right clicked while holding a Flint and Steel, ignore GUI
			// and explode.
			this.onBlockDestroyedByPlayer(world, x, y, z, 1);
			world.setBlockWithNotify(x, y, z, 0);

			world.createExplosion((Entity)null, x, y, z, MainReferences.MACHINE_EXPLOSION_STRENGTH, true);
			
			player.addStat(TelicraftMain.notForKids, 1);

			return true;

		} else {
			// Block right clicked without holding a Flint and Steel, open GUI.

			TileEntity tile_entity = world.getBlockTileEntity(x, y, z);

			if (tile_entity == null || player.isSneaking()) {
				return false;
			}

			player.openGui(TelicraftMain.instance, 0, world, x, y, z);

			return true;

		}
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int i, int j) {
		dropItems(world, x, y, z);
		super.breakBlock(world, x, y, z, i, j);
	}
	
	private void dropItems(World world, int x, int y, int z) {
		Random rand = new Random();

		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);

		if (!(tile_entity instanceof IInventory)) {
			return;
		}

		IInventory inventory = (IInventory) tile_entity;

		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			ItemStack item = inventory.getStackInSlot(i);

			if (item != null && item.stackSize > 0) {
				float rx = rand.nextFloat() * 0.6F + 0.1F;
				float ry = rand.nextFloat() * 0.6F + 0.1F;
				float rz = rand.nextFloat() * 0.6F + 0.1F;

				EntityItem entity_item = new EntityItem(world, x + rx, y + ry,
						z + rz, new ItemStack(item.itemID, item.stackSize,
								item.getItemDamage()));

				if (item.hasTagCompound()) {
					entity_item.item.setTagCompound((NBTTagCompound) item
							.getTagCompound().copy());
				}

				float factor = 0.5F;

				entity_item.motionX = rand.nextGaussian() * factor;
				entity_item.motionY = rand.nextGaussian() * factor + 0.2F;
				entity_item.motionZ = rand.nextGaussian() * factor;
				world.spawnEntityInWorld(entity_item);
				item.stackSize = 0;
			}
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntitySharpener();
	}

	@Override
	public String getTextureFile() {
		return TextureReferences.BLOCK_TEXTURE;
	}
}