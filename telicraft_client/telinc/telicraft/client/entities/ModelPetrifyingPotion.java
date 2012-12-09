package telinc.telicraft.client.entities;

import net.minecraft.src.Entity;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelPetrifyingPotion extends ModelBase
{
  //fields
    ModelRenderer squareOne;
    ModelRenderer squareTwo;
    ModelRenderer squareThree;
    ModelRenderer squareFour;
    ModelRenderer squareFive;
    ModelRenderer topper;
    ModelRenderer pillar;
    ModelRenderer lineFront;
    ModelRenderer lineBack;
  
  public ModelPetrifyingPotion()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      squareOne = new ModelRenderer(this, 0, 0);
      squareOne.addBox(0F, 0F, 0F, 2, 2, 2);
      squareOne.setRotationPoint(-1F, 22F, -1F);
      squareOne.setTextureSize(64, 32);
      squareOne.mirror = true;
      setRotation(squareOne, 0F, 0F, 0F);
      squareTwo = new ModelRenderer(this, 9, 0);
      squareTwo.addBox(0F, 0F, 0F, 2, 2, 2);
      squareTwo.setRotationPoint(1F, 22F, 1F);
      squareTwo.setTextureSize(64, 32);
      squareTwo.mirror = true;
      setRotation(squareTwo, 0F, 0F, 0F);
      squareThree = new ModelRenderer(this, 18, 0);
      squareThree.addBox(0F, 0F, 0F, 2, 2, 2);
      squareThree.setRotationPoint(1F, 22F, -3F);
      squareThree.setTextureSize(64, 32);
      squareThree.mirror = true;
      setRotation(squareThree, 0F, 0F, 0F);
      squareFour = new ModelRenderer(this, 27, 0);
      squareFour.addBox(0F, 0F, 0F, 2, 2, 2);
      squareFour.setRotationPoint(-3F, 22F, -3F);
      squareFour.setTextureSize(64, 32);
      squareFour.mirror = true;
      setRotation(squareFour, 0F, 0F, 0F);
      squareFive = new ModelRenderer(this, 36, 0);
      squareFive.addBox(0F, 0F, 0F, 2, 2, 2);
      squareFive.setRotationPoint(-3F, 22F, 1F);
      squareFive.setTextureSize(64, 32);
      squareFive.mirror = true;
      setRotation(squareFive, 0F, 0F, 0F);
      topper = new ModelRenderer(this, 0, 5);
      topper.addBox(0F, 0F, 0F, 6, 1, 6);
      topper.setRotationPoint(-3F, 19F, -3F);
      topper.setTextureSize(64, 32);
      topper.mirror = true;
      setRotation(topper, 0F, 0F, 0F);
      pillar = new ModelRenderer(this, 25, 5);
      pillar.addBox(0F, 0F, 0F, 2, 5, 2);
      pillar.setRotationPoint(-1F, 14F, -1F);
      pillar.setTextureSize(64, 32);
      pillar.mirror = true;
      setRotation(pillar, 0F, 0F, 0F);
      lineFront = new ModelRenderer(this, 0, 13);
      lineFront.addBox(0F, 0F, 0F, 2, 2, 5);
      lineFront.setRotationPoint(-1F, 22F, -8F);
      lineFront.setTextureSize(64, 32);
      lineFront.mirror = true;
      setRotation(lineFront, 0F, 0F, 0F);
      lineBack = new ModelRenderer(this, 15, 13);
      lineBack.addBox(0F, 0F, 0F, 2, 2, 5);
      lineBack.setRotationPoint(-1F, 22F, 3F);
      lineBack.setTextureSize(64, 32);
      lineBack.mirror = true;
      setRotation(lineBack, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    squareOne.render(f5);
    squareTwo.render(f5);
    squareThree.render(f5);
    squareFour.render(f5);
    squareFive.render(f5);
    topper.render(f5);
    pillar.render(f5);
    lineFront.render(f5);
    lineBack.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
  }

}
