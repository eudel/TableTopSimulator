/**
 * 
 */
package at.hid.tabletopsimulator.inventory;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

/**
 * @author dunkler_engel
 *
 */
public class SlotTooltip extends Window implements SlotListener {

	private Skin skin;

	private Slot slot;

	public SlotTooltip(Slot slot, Skin skin) {
		super("Tooltip...", skin);
		this.slot = slot;
		this.skin = skin;
		hasChanged(slot);
		slot.addListener(this);
		setVisible(false);
	}

	@Override
	public void hasChanged(Slot slot) {
		if (slot.isEmpty()) {
			setVisible(false);
			return;
		}

		// display the amount in the window title
		setTitle(slot.getAmount() + "x " + slot.getItem());
		clear();
		Label label = new Label("Description of " + slot.getItem(), skin);
		add(label);
		pack();
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (slot.isEmpty()) {
			super.setVisible(false);
		}
	}
}
