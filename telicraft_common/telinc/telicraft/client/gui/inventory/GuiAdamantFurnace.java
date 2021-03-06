package telinc.telicraft.client.gui.inventory;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import telinc.telicraft.inventory.ContainerAdamantFurnace;
import telinc.telicraft.lib.TextureReferences;
import telinc.telicraft.tileentity.TileAdamantFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiAdamantFurnace extends GuiContainer {
	private TileAdamantFurnace furnaceInventory;

	public GuiAdamantFurnace(InventoryPlayer par1InventoryPlayer,
			TileAdamantFurnace par2TileEntityFurnace) {
		super(new ContainerAdamantFurnace(par1InventoryPlayer,
				par2TileEntityFurnace));
		this.furnaceInventory = par2TileEntityFurnace;
	}

	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of
	 * the items)
	 */
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		this.fontRenderer.drawString(
				StatCollector.translateToLocal("container.furnace.adamant"),
				6, 6, 4210752);
		this.fontRenderer.drawString(
				StatCollector.translateToLocal("container.inventory"), 8,
				this.ySize - 96 + 2, 4210752);
		this.fontRenderer.drawString(this.getHeatText(this.furnaceInventory.heat, StatCollector.translateToLocal("gui.telicraft.heat")), 120, 6, 4210752);
	}

	/**
	 * Draw the background layer for the GuiContainer (everything behind the
	 * items)
	 */
	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2,
			int par3) {
		int var4 = this.mc.renderEngine
				.getTexture(TextureReferences.ADAMANT_FURNACE_GUI);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(var4);
		int var5 = (this.width - this.xSize) / 2;
		int var6 = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
		int var7;

		if (this.furnaceInventory.isBurning()) {
			var7 = this.furnaceInventory.getBurnTimeRemainingScaled(12);
			this.drawTexturedModalRect(var5 + 56, var6 + 36 + 12 - var7, 176,
					12 - var7, 14, var7 + 2);
		}

		var7 = this.furnaceInventory.getCookProgressScaled(24);
		this.drawTexturedModalRect(var5 + 79, var6 + 34, 176, 14, var7 + 1, 16);
	}
	
	private String getHeatText(int par1, String par2Str){
		if(par1 < 100){
			return "\u00a72" + par2Str + ": " + par1;
		}else if(par1 < 400){
			return "\u00a76" + par2Str + ": " + par1;
		}else{
			return "\u00a74" + par2Str + ": " + par1;
		}
	}
}
