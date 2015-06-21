/**
 * 
 */
package at.hid.tabletopsimulator.inventory;

import com.badlogic.gdx.utils.Array;

/**
 * @author dunkler_engel
 *
 */
public class Inventory {

	private Array<Slot> slots;

	/**
	 * Creates a new inventory with given size.
	 * 
	 * @param size
	 *            the size of the inventory.
	 */
	public Inventory(int size) {
		slots = new Array<Slot>(size);
		for (int i = 0; i < size; i++) {
			slots.add(new Slot(null, 0));
		}
	}

	/**
	 * Check inventory.
	 *
	 * @param item
	 *            the item to be checked
	 * @return the amount in inventory
	 */
	public int checkInventory(Item item) {
		int amount = 0;

		for (Slot slot : slots) {
			if (slot.getItem() == item) {
				amount += slot.getAmount();
			}
		}
		return amount;
	}
	
	/**
	 * Store an {@code Item} at the first slot with contains that item or on the first free slot.
	 *
	 * @param item the item to be stored
	 * @param amount the amount of items
	 * @return {@code true} if successful. {@code False} otherwise.
	 */
	public boolean store(Item item, int amount) {
		Slot itemSlot = firstSlotWithItem(item);
		if (itemSlot != null) {
			itemSlot.add(item, amount);
			return true;
		} else {
			Slot emptySlot = firstSlotWithItem(null);
			if (emptySlot != null) {
				emptySlot.add(item, amount);
				return true;
			}
		}
		return false;
	}
	
	public Array<Slot> getSlots() {
		return slots;
	}
	
	private Slot firstSlotWithItem(Item item) {
		for (Slot slot : slots) {
			if (slot.getItem() == item) {
				return slot;
			}
		}
		return null;
	}
}
