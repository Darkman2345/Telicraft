package telinc.telicraft.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import telinc.telicraft.TelicraftMain;
import telinc.telicraft.entity.EntityMeteorBombPrimed;
import telinc.telicraft.lib.TextureReferences;
import cpw.mods.fml.common.FMLLog;

public class BlockMeteorBomb extends Block {
	public BlockMeteorBomb(int par1, int par2) {
		super(par1, par2, Material.tnt);
		this.setCreativeTab(TelicraftMain.tabTelicraft);
	}

	/**
	 * Returns the block texture based on the side being looked at. Args: side
	 */
	@Override
	public int getBlockTextureFromSide(int par1) {
		switch (par1) {
		case 0:
			return 14; // Top
		case 1:
			return 14; // Bottom
		default:
			return 13; // Sides
		}
	}

	/**
	 * Called whenever the block is added into the world. Args: world, x, y, z
	 */
	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4) {
		super.onBlockAdded(par1World, par2, par3, par4);

		if (par1World.isBlockIndirectlyGettingPowered(par2, par3, par4)) {
			this.onBlockDestroyedByPlayer(par1World, par2, par3, par4, 1);
			par1World.setBlockWithNotify(par2, par3, par4, 0);
		}
	}

	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which
	 * neighbor changed (coordinates passed are their own) Args: x, y, z,
	 * neighbor blockID
	 */
	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3,
			int par4, int par5) {
		if (par5 > 0 && Block.blocksList[par5].canProvidePower()
				&& par1World.isBlockIndirectlyGettingPowered(par2, par3, par4)) {
			this.onBlockDestroyedByPlayer(par1World, par2, par3, par4, 1);
			par1World.setBlockWithNotify(par2, par3, par4, 0);
		}
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	@Override
	public int quantityDropped(Random par1Random) {
		return 1;
	}

	/**
	 * Called upon the block being destroyed by an explosion
	 */
	@Override
	public void onBlockDestroyedByExplosion(World par1World, int par2,
			int par3, int par4) {
		if (!par1World.isRemote) {
			EntityMeteorBombPrimed var5 = new EntityMeteorBombPrimed(par1World,
					(double) ((float) par2 + 0.5F),
					(double) ((float) par3 + 0.5F),
					(double) ((float) par4 + 0.5F));
			var5.fuse = par1World.rand.nextInt(var5.fuse / 4) + var5.fuse / 8;
			par1World.spawnEntityInWorld(var5);
			
			this.warnIgnite(par1World, TelicraftMain.engine.getPlayerInstance(), par2, par3, par4);
		}
	}

	/**
	 * Called right before the block is destroyed by a player. Args: world, x,
	 * y, z, metaData
	 */
	@Override
	public void onBlockDestroyedByPlayer(World par1World, int par2, int par3,
			int par4, int par5) {
		if (!par1World.isRemote) {
			if ((par5 & 1) == 1) {
				EntityMeteorBombPrimed var6 = new EntityMeteorBombPrimed(par1World,
						(double) ((float) par2 + 0.5F),
						(double) ((float) par3 + 0.5F),
						(double) ((float) par4 + 0.5F));
				par1World.spawnEntityInWorld(var6);
				par1World.playSoundAtEntity(var6, "random.fuse", 1.0F, 1.0F);
				
				this.warnIgnite(par1World, TelicraftMain.engine.getPlayerInstance(), par2, par3, par4);
			}
		}
	}

	/**
	 * Called upon block activation (right click on the block.)
	 */
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3,
			int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
			float par8, float par9) {
		
		if (par5EntityPlayer.getCurrentEquippedItem() != null
				&& par5EntityPlayer.getCurrentEquippedItem().itemID == Item.flintAndSteel.shiftedIndex) {
			this.onBlockDestroyedByPlayer(par1World, par2, par3, par4, 1);
			par1World.setBlockWithNotify(par2, par3, par4, 0);
			
			return true;
		} else {
			return super.onBlockActivated(par1World, par2, par3, par4,
					par5EntityPlayer, par6, par7, par8, par9);
		}
	}

	/**
	 * Triggered whenever an entity collides with this block (enters into the
	 * block). Args: world, x, y, z, entity
	 */
	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3,
			int par4, Entity par5Entity) {
		if (par5Entity instanceof EntityArrow && !par1World.isRemote) {
			EntityArrow var6 = (EntityArrow) par5Entity;

			if (var6.isBurning()) {
				this.onBlockDestroyedByPlayer(par1World, par2, par3, par4, 1);
				par1World.setBlockWithNotify(par2, par3, par4, 0);
			}
		}
	}
	
	private void warnIgnite(World world, EntityPlayer player, int x, int y, int z){
		FMLLog.warning("[Telicraft] WARNING: Player " + player.username + " ignited Meteor Bomb at XYZ (" + x + ", " + y + ", " + z +") in world \"" + world.getWorldInfo().getWorldName() +"\"!", this);
	}
	
	@Override
	public String getTextureFile() {
		return TextureReferences.BLOCK_TEXTURE;
	}
}