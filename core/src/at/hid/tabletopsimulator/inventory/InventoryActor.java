/**
 * 
 */
package at.hid.tabletopsimulator.inventory;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

/**
 * @author dunkler_engel
 *
 */
public class InventoryActor extends Window {
	public InventoryActor(Inventory inventory, DragAndDrop dragAndDrop, Skin skin) {
		super("Inventory...", skin);
		
		TextButton btnClose = new TextButton("X", skin);
		btnClose.addListener(new HidingClickListener(this));
		getButtonTable().add(btnClose).height(getPadTop());
		
		setPosition(400, 100);
		defaults().space(8);
		row().fill().expand();
		
		int i = 0;
		for (Slot slot : inventory.getSlots()) {
			SlotActor slotActor = new SlotActor(skin, slot);
			dragAndDrop.addSource(new SlotSource(slotActor));
			dragAndDrop.addTarget(new SlotTarget(slotActor));
			add(slotActor);
			
			i++;
			if (i % 5 == 0) {
				row();
			}
		}
		pack();
		
		setVisible(false);
	}
}
