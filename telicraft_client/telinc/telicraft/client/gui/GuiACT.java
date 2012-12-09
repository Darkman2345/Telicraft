package telinc.telicraft.client.gui;

import net.minecraft.src.GuiContainer;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.StatCollector;
import net.minecraft.src.World;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import telinc.telicraft.common.gui.act.ContainerACT;
import telinc.telicraft.common.reference.TextureReferences;

@SideOnly(Side.CLIENT)
public class GuiACT extends GuiContainer {
	public GuiACT(InventoryPlayer par1InventoryPlayer, World par2World,
			int par3, int par4, int par5) {
		super(new ContainerACT(par1InventoryPlayer, par2World, par3,
				par4, par5));
	}

	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of the items)
	 */
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		this.fontRenderer.drawString(
				StatCollector.translateToLocal("container.crafting.act"), 28, 6,
				793454);
		this.fontRenderer.drawString(
				StatCollector.translateToLocal("container.inventory"), 8,
				this.ySize - 96 + 2, 793454);
	}

	/**
	 * Draw the background layer for the GuiContainer (everything behind the items)
	 */
	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2,
			int par3) {
		int var4 = this.mc.renderEngine.getTexture(TextureReferences.ACT_GUI);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(var4);
		int var5 = (this.width - this.xSize) / 2;
		int var6 = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
	}
}
