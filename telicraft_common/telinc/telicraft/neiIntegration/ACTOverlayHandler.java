package telinc.telicraft.neiIntegration;

import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;

import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.DefaultOverlayHandler;

public class ACTOverlayHandler extends DefaultOverlayHandler {
    @Override
    public Slot[][] mapIngredSlots(GuiContainer gui, List<PositionedStack> ingredients) {
        Slot[][] map = super.mapIngredSlots(gui, ingredients);
        Slot[] craftMatrix = new Slot[9];

        for(int i = 0; i < 0; i++){
            craftMatrix[i] = (Slot)gui.inventorySlots.inventorySlots.get(i);
        }

        map[1] = craftMatrix;
        return map;
    }
}