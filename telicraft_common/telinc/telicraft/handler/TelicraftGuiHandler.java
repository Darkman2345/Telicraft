package telinc.telicraft.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import telinc.telicraft.client.gui.inventory.GuiACT;
import telinc.telicraft.client.gui.inventory.GuiAdamantFurnace;
import telinc.telicraft.client.gui.inventory.GuiSharpener;
import telinc.telicraft.inventory.ContainerACT;
import telinc.telicraft.inventory.ContainerAdamantFurnace;
import telinc.telicraft.inventory.ContainerSharpener;
import telinc.telicraft.tileentity.TileAdamantFurnace;
import telinc.telicraft.tileentity.TileSharpener;
import cpw.mods.fml.common.network.IGuiHandler;

public class TelicraftGuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world,
			int x, int y, int z) {

		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
		
		if (id == 0) {
			return new ContainerSharpener(player.inventory,
					(TileSharpener) tile_entity);
		} else if (id == 1) {
			return new ContainerAdamantFurnace(player.inventory,
					(TileAdamantFurnace) tile_entity);
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
					(TileSharpener) tile_entity);
		} else if (id == 1) {
			return new GuiAdamantFurnace(player.inventory,
					(TileAdamantFurnace) tile_entity);
		} else if (id == 2) {
			return new GuiACT(player.inventory, world, x, y, z);
		}

		return null;
	}
}