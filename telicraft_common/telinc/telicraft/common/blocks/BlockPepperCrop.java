package telinc.telicraft.common.blocks;

import static net.minecraftforge.common.EnumPlantType.Crop;

import java.util.Random;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import net.minecraft.src.Block;
import net.minecraft.src.BlockFlower;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Material;
import net.minecraft.src.World;

import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

import telinc.telicraft.common.TelicraftMain;
import telinc.telicraft.common.reference.TextureReferences;
import telinc.telincCore.TelincCoreFunctions;

public class BlockPepperCrop extends BlockFlower implements IPlantable {

	private int metadata;

	public BlockPepperCrop(int par1, int par2, Material par3) {
		super(par1, par2, par3);
		this.blockIndexInTexture = par2;
		this.setTickRandomly(true);
		float var3 = 0.5F;
		this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 0.25F,
				0.5F + var3);
		this.setCreativeTab((CreativeTabs) null);
		this.setRequiresSelfNotify();
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4,
			EntityLiving par5EntityLiving) {
		TelincCoreFunctions.crashMinecraft(par1World);
	}

	@Override
	public String getTextureFile() {
		return TextureReferences.BLOCK_TEXTURE;
	}

	/**
	 * Gets passed in the blockID of the block below and supposed to return true
	 * if its allowed to grow on the type of blockID passed in. Args: blockID
	 */
	@Override
	protected boolean canThisPlantGrowOnThisBlockID(int par1) {
		return par1 == Block.tilledField.blockID;
	}

	/**
	 * Ticks the block if it's been scheduled
	 */
	@Override
	public void updateTick(World par1World, int par2, int par3, int par4,
			Random par5Random) {
		super.updateTick(par1World, par2, par3, par4, par5Random);

		if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9) {
			int var6 = par1World.getBlockMetadata(par2, par3, par4);

			if (var6 < 7) {
				float var7 = this.getGrowthRate(par1World, par2, par3, par4);

				if (par5Random.nextInt((int) (25.0F / var7) + 1) == 0) {
					++var6;
					par1World
							.setBlockMetadataWithNotify(par2, par3, par4, var6);
				}
			}
		}
	}

	/**
	 * Apply bonemeal to the crops.
	 */
	public void fertilize(World par1World, int par2, int par3, int par4,
			EntityPlayer par5) {
		if (par1World.getBlockMetadata(par2, par3, par4) != 7) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 7);
			--par5.inventory.getCurrentItem().stackSize;
		}
	}

	/**
	 * Gets the growth rate for the crop. Setup to encourage rows by halving
	 * growth rate if there is diagonals, crops on different sides that aren't
	 * opposing, and by adding growth for every crop next to this one (and for
	 * crop below this one). Args: x, y, z
	 */
	private float getGrowthRate(World par1World, int par2, int par3, int par4) {
		float var5 = 1.0F;
		int var6 = par1World.getBlockId(par2, par3, par4 - 1);
		int var7 = par1World.getBlockId(par2, par3, par4 + 1);
		int var8 = par1World.getBlockId(par2 - 1, par3, par4);
		int var9 = par1World.getBlockId(par2 + 1, par3, par4);
		int var10 = par1World.getBlockId(par2 - 1, par3, par4 - 1);
		int var11 = par1World.getBlockId(par2 + 1, par3, par4 - 1);
		int var12 = par1World.getBlockId(par2 + 1, par3, par4 + 1);
		int var13 = par1World.getBlockId(par2 - 1, par3, par4 + 1);
		boolean var14 = var8 == this.blockID || var9 == this.blockID;
		boolean var15 = var6 == this.blockID || var7 == this.blockID;
		boolean var16 = var10 == this.blockID || var11 == this.blockID
				|| var12 == this.blockID || var13 == this.blockID;

		for (int var17 = par2 - 1; var17 <= par2 + 1; ++var17) {
			for (int var18 = par4 - 1; var18 <= par4 + 1; ++var18) {
				int var19 = par1World.getBlockId(var17, par3 - 1, var18);
				float var20 = 0.0F;

				if (var19 == Block.tilledField.blockID) {
					var20 = 1.0F;

					if (par1World.getBlockMetadata(var17, par3 - 1, var18) > 0) {
						var20 = 3.0F;
					}
				}

				if (var17 != par2 || var18 != par4) {
					var20 /= 4.0F;
				}

				var5 += var20;
			}
		}

		if (var16 || var14 && var15) {
			var5 /= 2.0F;
		}

		return var5;
	}

	/**
	 * From the specified side and block metadata retrieves the blocks texture.
	 * Args: side, metadata
	 */
	@Override
	public int getBlockTextureFromSideAndMetadata(int par1, int par2) {

		metadata = par2;

		switch (par2) {
		case 0:
			return TelicraftMain.pepperCrop.blockIndexInTexture;
		case 1:
			return TelicraftMain.pepperCrop.blockIndexInTexture + 1;
		case 2:
			return TelicraftMain.pepperCrop.blockIndexInTexture + 2;
		case 3:
			return TelicraftMain.pepperCrop.blockIndexInTexture + 3;
		case 4:
			return TelicraftMain.pepperCrop.blockIndexInTexture + 4;
		case 5:
			return TelicraftMain.pepperCrop.blockIndexInTexture + 5;
		case 6:
			return TelicraftMain.pepperCrop.blockIndexInTexture + 6;
		case 7:
			return TelicraftMain.pepperCrop.blockIndexInTexture + 7;
		default:
			return this.blockIndexInTexture + par2;
		}
	}

	/**
	 * The type of render function that is called for this block
	 */
	@Override
	public int getRenderType() {
		return 6;
	}

	/**
	 * Drops the block items with a specified chance of dropping the specified
	 * items
	 */
	@Override
	public void dropBlockAsItemWithChance(World par1World, int par2, int par3,
			int par4, int par5, float par6, int par7) {
		super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5,
				par6, 0);
	}

	/**
	 * Returns the ID of the items to drop on destruction.
	 */
	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return TelicraftMain.pepper.shiftedIndex;
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	@Override
	public int quantityDropped(Random par1Random) {
		return metadata < 7 ? 1 : 3;
	}

	@Override
	public EnumPlantType getPlantType(World world, int x, int y, int z) {
		return Crop;
	}

	@Override
	public int getPlantID(World world, int x, int y, int z) {
		return TelicraftMain.pepperCrop.blockID;
	}

	@Override
	public int getPlantMetadata(World world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z);
	}

	@SideOnly(Side.CLIENT)
	@Override
	/**
	 * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
	 */
	public int idPicked(World par1World, int par2, int par3, int par4) {
		return TelicraftMain.pepper.shiftedIndex;
	}
}
