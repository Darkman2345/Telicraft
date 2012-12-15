package telinc.telicraft.client.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

import telinc.telicraft.common.entities.EntityPetrify;
import telinc.telicraft.common.reference.TextureReferences;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPetrifyNew extends Render {
	/** instance of ModelPetrifyingPotion for rendering */
	protected ModelBase modelPetrify;

	public RenderPetrifyNew() {
		this.shadowSize = 0.5F;
		this.modelPetrify = new ModelPetrifyingPotion();
	}

	/**
	 * The render method used in RenderBoat that renders the boat model.
	 */
	public void renderPetrify(EntityPetrify par1Entity, double par2,
			double par4, double par6, float par8, float par9) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) par2, (float) par4, (float) par6);
		GL11.glRotatef(180.0F - par8, 0.0F, 1.0F, 0.0F);

		this.loadTexture(TextureReferences.ITEM_TEXTURE);
		float var12 = 0.75F;
		GL11.glScalef(var12, var12, var12);
		GL11.glScalef(1.0F / var12, 1.0F / var12, 1.0F / var12);
		this.loadTexture(TextureReferences.POTION_PETRIFY_NEW);
		GL11.glScalef(-1.0F, -1.0F, 1.0F);
		this.modelPetrify.render(par1Entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F,
				0.0625F);
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
		this.renderPetrify((EntityPetrify) par1Entity, par2, par4, par6, par8,
				par9);
	}
}
