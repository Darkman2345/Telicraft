package telinc.telicraft.common.blocks;

import net.minecraft.src.BlockFlower;
import net.minecraft.src.Material;
import net.minecraft.src.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import telinc.telicraft.common.TelicraftMain;
import telinc.telicraft.common.reference.TextureReferences;

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