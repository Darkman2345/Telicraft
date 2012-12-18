package telinc.telicraft.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import telinc.telicraft.TelicraftMain;
import telinc.telicraft.api.TCRegistry;
import telinc.telicraft.world.TelicraftDamageSources;

public class EntityPetrify extends EntityThrowable {
	private boolean canDestroyBlock = true;

	public EntityPetrify(World par1World) {
		super(par1World);
		TCRegistry.addBannedBlockForPetrify(Block.bedrock.blockID);
		TCRegistry.addBannedBlockForPetrify(Block.doorWood.blockID);
		TCRegistry.addBannedBlockForPetrify(Block.doorSteel.blockID);
		TCRegistry.addBannedBlockForPetrify(Block.dragonEgg.blockID);
		TCRegistry.addBannedBlockForPetrify(Block.obsidian.blockID);
		TCRegistry.addBannedBlockForPetrify(Block.oreDiamond.blockID);
		TCRegistry.addBannedBlockForPetrify(TelicraftMain.adamantOre.blockID);
		TCRegistry.addBannedBlockForPetrify(TelicraftMain.meteorBlock.blockID);
		TCRegistry.addBannedBlockForPetrify(Block.endPortal.blockID);
		TCRegistry.addBannedBlockForPetrify(Block.endPortalFrame.blockID);
		TCRegistry.addBannedBlockForPetrify(Block.portal.blockID);
		TCRegistry.addBannedBlockForPetrify(Block.enchantmentTable.blockID);
		TCRegistry.addBannedBlockForPetrify(Block.bed.blockID);
	}

	public EntityPetrify(World par1World, EntityLiving par2EntityLiving) {
		super(par1World, par2EntityLiving);
		TCRegistry.addBannedBlockForPetrify(Block.bedrock.blockID);
		TCRegistry.addBannedBlockForPetrify(Block.doorWood.blockID);
		TCRegistry.addBannedBlockForPetrify(Block.doorSteel.blockID);
		TCRegistry.addBannedBlockForPetrify(Block.dragonEgg.blockID);
		TCRegistry.addBannedBlockForPetrify(Block.obsidian.blockID);
		TCRegistry.addBannedBlockForPetrify(Block.oreDiamond.blockID);
		TCRegistry.addBannedBlockForPetrify(TelicraftMain.adamantOre.blockID);
		TCRegistry.addBannedBlockForPetrify(TelicraftMain.meteorBlock.blockID);
		TCRegistry.addBannedBlockForPetrify(Block.endPortal.blockID);
		TCRegistry.addBannedBlockForPetrify(Block.endPortalFrame.blockID);
		TCRegistry.addBannedBlockForPetrify(Block.portal.blockID);
		TCRegistry.addBannedBlockForPetrify(Block.enchantmentTable.blockID);
		TCRegistry.addBannedBlockForPetrify(Block.bed.blockID);
	}

	public EntityPetrify(World par1World, double par2, double par4, double par6) {
		super(par1World, par2, par4, par6);
		TCRegistry.addBannedBlockForPetrify(Block.bedrock.blockID);
		TCRegistry.addBannedBlockForPetrify(Block.doorWood.blockID);
		TCRegistry.addBannedBlockForPetrify(Block.doorSteel.blockID);
		TCRegistry.addBannedBlockForPetrify(Block.dragonEgg.blockID);
		TCRegistry.addBannedBlockForPetrify(Block.obsidian.blockID);
		TCRegistry.addBannedBlockForPetrify(Block.oreDiamond.blockID);
		TCRegistry.addBannedBlockForPetrify(TelicraftMain.adamantOre.blockID);
		TCRegistry.addBannedBlockForPetrify(TelicraftMain.meteorBlock.blockID);
		TCRegistry.addBannedBlockForPetrify(Block.endPortal.blockID);
		TCRegistry.addBannedBlockForPetrify(Block.endPortalFrame.blockID);
		TCRegistry.addBannedBlockForPetrify(Block.portal.blockID);
		TCRegistry.addBannedBlockForPetrify(Block.enchantmentTable.blockID);
		TCRegistry.addBannedBlockForPetrify(Block.bed.blockID);
	}

	@Override
	protected void entityInit() {
		this.canDestroyBlock = true;
	}

	/**
	 * Called when this EntityThrowable hits a block or entity.
	 */
	@Override
	protected void onImpact(MovingObjectPosition par1MovingObjectPosition) {
		if (par1MovingObjectPosition.entityHit != null) {
			par1MovingObjectPosition.entityHit.attackEntityFrom(
					TelicraftDamageSources.petrify, 100);
		}

		int var10 = this.rand.nextInt(99);

		if (var10 < 49 && par1MovingObjectPosition.entityHit == null) {
			int var11 = par1MovingObjectPosition.blockX;
			int var12 = par1MovingObjectPosition.blockY;
			int var13 = par1MovingObjectPosition.blockZ;
			int var14 = this.worldObj.getBlockId(var11, var12, var13);
			boolean var15 = true;

			if (TCRegistry.getBannedBlocksPetrify().contains(var14)) {
				var15 = false;
			}

			if (var15) {
				this.worldObj.setBlockWithNotify(var11, var12, var13,
						Block.stone.blockID); // Sets Stone.
				this.worldObj.markBlockForUpdate(var11, var12, var13); // Scheudles a block update.
			}
		}

		if (!this.worldObj.isRemote && this.rand.nextInt(8) == 0) {
			byte var2 = 1;

			if (this.rand.nextInt(32) == 0) {
				var2 = 4;
			}
		}

		for (int var5 = 0; var5 < 8; ++var5) {
			this.worldObj.spawnParticle("spell", this.posX, this.posY,
					this.posZ, 0.0D, 0.0D, 0.0D);
		}

		this.worldObj.playSoundAtEntity(this, "random.fizz", 0.5F,
				0.4F / (this.rand.nextFloat() * 0.4F + 0.8F));

		if (!this.worldObj.isRemote) {
			this.setDead();
		}
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
	}

}
