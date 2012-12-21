package telinc.telicraft.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import telinc.telicraft.TelicraftMain;
import telinc.telicraft.lib.TextureReferences;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMeteor extends Block {

	public BlockMeteor(int par1, int par2, Material par3Material) {
		super(par1, par2, par3Material);
		this.setCreativeTab(TelicraftMain.tabTelicraft);
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return TelicraftMain.meteorDust.shiftedIndex;
	}
	
	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4) {
		this.checkEmpty(par1World, par2, par3, par4);
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int id) {
		this.checkEmpty(world, x, y, z);
	}

	/**
	 * Triggered whenever an entity collides with this block (enters into the
	 * block). Args: world, x, y, z, entity
	 */
	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3,
			int par4, Entity par5Entity) {
		if (par5Entity instanceof EntityLightningBolt) {
			par1World.setBlockWithNotify(par2, par3, par4, 0);
		}
	}

	@Override
	public int quantityDropped(Random par1Random) {
		return 1 + par1Random.nextInt(1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int idPicked(World par1World, int par2, int par3, int par4) {
		return TelicraftMain.meteorDust.shiftedIndex;
	}
	
	private void checkEmpty(World world, int x, int y, int z){
		if (world.getBlockId(x, y - 1, z) == 0) {
			if (!world.isRemote) {
				EntityItem var1 = new EntityItem(world, (double)x, (double)y,
						(double)z, new ItemStack(TelicraftMain.meteorDust, 1));

				world.spawnEntityInWorld(var1);
			}

			world.setBlockWithNotify(x, y, z, 0);
		}
	}
	
	@Override
	public String getTextureFile() {
		return TextureReferences.BLOCK_TEXTURE;
	}
}