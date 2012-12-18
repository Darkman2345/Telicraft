package telinc.telicraft.block;

import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import telinc.telicraft.TelicraftMain;
import telinc.telicraft.lib.TextureReferences;

public class BlockSquill extends BlockFlower implements IPlantable {
	public BlockSquill(int id, int texture, Material material) {
		super(id, texture, material);
		this.setCreativeTab(TelicraftMain.tabTelicraft);
	}

	@Override
	public EnumPlantType getPlantType(World world, int x, int y, int z) {
		return EnumPlantType.Plains;
	}

	@Override
	public int getPlantID(World world, int x, int y, int z) {
		return TelicraftMain.squill.blockID;
	}

	@Override
	public int getPlantMetadata(World world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z);
	}

	@Override
	public String getTextureFile() {
		return TextureReferences.BLOCK_TEXTURE;
	}
}