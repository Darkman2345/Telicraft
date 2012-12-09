package telinc.telicraft.common.handlers;

import cpw.mods.fml.common.network.IGuiHandler;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

import telinc.telicraft.client.gui.GuiACT;
import telinc.telicraft.client.gui.GuiAdamantFurnace;
import telinc.telicraft.client.gui.GuiSharpener;
import telinc.telicraft.common.gui.ContainerAdamantFurnace;
import telinc.telicraft.common.gui.ContainerSharpener;
import telinc.telicraft.common.gui.act.ContainerACT;
import telinc.telicraft.common.tileEntities.TileEntityAdamantFurnace;
import telinc.telicraft.common.tileEntities.TileEntitySharpener;

public class TelicraftGuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world,
			int x, int y, int z) {

		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
		
		if (id == 0) {
			return new ContainerSharpener(player.inventory,
					(TileEntitySharpener) tile_entity);
		} else if (id == 1) {
			return new ContainerAdamantFurnace(player.inventory,
					(TileEntityAdamantFurnace) tile_entity);
		} else if (id == 2) {
			return new ContainerACT(player.inventory, world, x, y, z);
		}

		return null;
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world,
			int x, int y, int z) {

		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
		
		if (id == 0) {
			return new GuiSharpener(player.inventory,
					(TileEntitySharpener) tile_entity);
		} else if (id == 1) {
			return new GuiAdamantFurnace(player.inventory,
					(TileEntityAdamantFurnace) tile_entity);
		} else if (id == 2) {
			return new GuiACT(player.inventory, world, x, y, z);
		}

		return null;
	}
}