package telinc.telicraft.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import telinc.telicraft.gui.ContainerSharpener;
import telinc.telicraft.lib.TextureReferences;
import telinc.telicraft.tileentity.TileEntitySharpener;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSharpener extends GuiContainer {
	private TileEntitySharpener sharpenerInventory;

	public GuiSharpener(InventoryPlayer par1InventoryPlayer,
			TileEntitySharpener par2TileEntitySharpener) {
		super(
				new ContainerSharpener(par1InventoryPlayer,
						par2TileEntitySharpener));
		this.sharpenerInventory = par2TileEntitySharpener;
	}

	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of
	 * the items)
	 */
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		this.fontRenderer.drawString(StatCollector.translateToLocal("container.sharpener"), 6, 6, 4210752);
		this.fontRenderer.drawString(
				StatCollector.translateToLocal("container.inventory"), 8,
				this.ySize - 96 + 2, 4210752);
	}

	/**
	 * Draw the background layer for the GuiContainer (everything behind the
	 * items)
	 */
	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2,
			int par3) {
		int var4 = this.mc.renderEngine
				.getTexture(TextureReferences.SHARPENER_GUI);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(var4);
		int var5 = (this.width - this.xSize) / 2;
		int var6 = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
		int var7;

		var7 = this.sharpenerInventory.getCookProgressScaled(24);
		this.drawTexturedModalRect(var5 + 79, var6 + 34, 176, 14, var7 + 1, 16);
	}
}
