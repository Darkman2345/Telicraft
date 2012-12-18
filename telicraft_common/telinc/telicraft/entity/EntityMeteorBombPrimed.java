package telinc.telicraft.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import telinc.telicraft.world.ExplosionMeteor;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public class EntityMeteorBombPrimed extends Entity {
	
	public int fuse;
	
	public EntityMeteorBombPrimed(World par1World) {
		super(par1World);
		this.fuse = 0;
		this.preventEntitySpawning = true;
		this.setSize(0.98F, 0.98F);
		this.yOffset = this.height / 2.0F;
	}

	public EntityMeteorBombPrimed(World par1World, double par2, double par4,
			double par6) {
		this(par1World);
		this.setPosition(par2, par4, par6);
		float var8 = (float) (Math.random() * Math.PI * 2.0D);
		this.motionX = (double) (-((float) Math.sin((double) var8)) * 0.02F);
		this.motionY = 0.20000000298023224D;
		this.motionZ = (double) (-((float) Math.cos((double) var8)) * 0.02F);
		this.fuse = 200;
		this.prevPosX = par2;
		this.prevPosY = par4;
		this.prevPosZ = par6;
	}

	@Override
	protected void entityInit() {}

	@Override
	protected boolean canTriggerWalking() {
		return false;
	}

	@Override
	public boolean canBeCollidedWith() {
		return !isDead;
	}

	@Override
	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		this.motionY -= 0.03999999910593033D;
		this.moveEntity(this.motionX, this.motionY, this.motionZ);
		this.motionX *= 0.9800000190734863D;
		this.motionY *= 0.9800000190734863D;
		this.motionZ *= 0.9800000190734863D;

		if (this.onGround) {
			this.motionX *= 0.699999988079071D;
			this.motionZ *= 0.699999988079071D;
			this.motionY *= -0.5D;
		}

		if (this.fuse-- <= 0) {
			this.setDead();

			if (!this.worldObj.isRemote) {
				this.explode();
			}
		} else {
			this.worldObj.spawnParticle("smoke", this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
			this.worldObj.spawnParticle("flame", this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
			this.worldObj.spawnParticle("lava", this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		fuse = par1NBTTagCompound.getByte("Fuse");
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		par1NBTTagCompound.setByte("Fuse", (byte)fuse);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public float getShadowSize(){
        return 0.0F;
    }
	
	private void explode() {
		float power = 45.69F;
		
		ExplosionMeteor explosion = new ExplosionMeteor(this.worldObj, this, this.posX, this.posY, this.posZ, power);
		explosion.doExplosionA();
		explosion.doExplosionB(true);
	}
}