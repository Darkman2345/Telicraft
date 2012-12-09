package telinc.telicraft.client.entities;

import net.minecraft.src.Entity;
import net.minecraft.src.Render;
import net.minecraft.src.RenderBlocks;

import org.lwjgl.opengl.GL11;

import telinc.telicraft.common.TelicraftMain;
import telinc.telicraft.common.entities.EntityMeteorBombPrimed;
import telinc.telicraft.common.reference.TextureReferences;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMeteorBomb extends Render {
	private RenderBlocks blockRenderer = new RenderBlocks();

	public RenderMeteorBomb() {
		this.shadowSize = 0.5F;
	}

	public void renderPrimedMeteorBomb(
			EntityMeteorBombPrimed par1EntityMeteorBombPrimed, double par2,
			double par4, double par6, float par8, float par9) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) par2, (float) par4, (float) par6);
		float var10;

		if ((float) par1EntityMeteorBombPrimed.fuse - par9 + 1.0F < 10.0F) {
			var10 = 1.0F - ((float) par1EntityMeteorBombPrimed.fuse - par9 + 1.0F) / 10.0F;

			if (var10 < 0.0F) {
				var10 = 0.0F;
			}

			if (var10 > 1.0F) {
				var10 = 1.0F;
			}

			var10 *= var10;
			var10 *= var10;
			float var11 = 1.0F + var10 * 0.3F;
			GL11.glScalef(var11, var11, var11);
		}

		var10 = (1.0F - ((float) par1EntityMeteorBombPrimed.fuse - par9 + 1.0F) / 100.0F) * 0.8F;
		this.loadTexture(TextureReferences.BLOCK_TEXTURE);
		this.blockRenderer.renderBlockAsItem(TelicraftMain.meteorBomb, 0,
				par1EntityMeteorBombPrimed.getBrightness(par9));

		if (par1EntityMeteorBombPrimed.fuse / 5 % 2 == 0) {
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_DST_ALPHA);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, var10);
			this.blockRenderer.renderBlockAsItem(TelicraftMain.meteorBomb, 0, 1.0F);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
		}

		GL11.glPopMatrix();
	}

	/**
	 * Actually renders the given argument. This is a synthetic bridge method,
	 * always casting down its argument and then handing it off to a worker
	 * function which does the actual work. In all probabilty, the class Render
	 * is generic (Render<T extends Entity) and this method has signature public
	 * void doRender(T entity, double d, double d1, double d2, float f, float
	 * f1). But JAD is pre 1.5 so doesn't do that.
	 */
	public void doRender(Entity par1Entity, double par2, double par4,
			double par6, float par8, float par9) {
		this.renderPrimedMeteorBomb((EntityMeteorBombPrimed) par1Entity, par2,
				par4, par6, par8, par9);
	}
}
