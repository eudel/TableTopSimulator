/**
 * 
 */
package at.hid.tabletopsimulator.inventory;

import com.badlogic.gdx.utils.Array;

/**
 * @author dunkler_engel
 *
 */
public class Slot {

	private Item item;

	private int amount;

	private Array<SlotListener> slotListeners = new Array<SlotListener>();

	/**
	 * Creates a new slot with an item and amount.
	 * 
	 * @param item
	 *            the item to be set.
	 * @param amount
	 *            the amount of the item.
	 */
	public Slot(Item item, int amount) {
		this.item = item;
		this.amount = amount;
	}

	/**
	 * Checks if the Slot is empty.
	 * 
	 * @return {@code True} if the Slot is empty. {@code False} otherwise.
	 */
	public boolean isEmpty() {
		return item == null || amount <= 0;
	}

	/**
	 * Adds a Listener.
	 * 
	 * @param slotListener
	 *            the SlotListener to be added.
	 */
	public void addListener(SlotListener slotListener) {
		slotListeners.add(slotListener);
	}

	/**
	 * Removes a SlotListener.
	 * 
	 * @param slotListener
	 *            the SlotListener to be removed.
	 */
	public void removeListener(SlotListener slotListener) {
		slotListeners.removeValue(slotListener, true);
	}

	/**
	 * Returns {@code True} if this slot has the same item and at least the same
	 * amount as the other slot.
	 * 
	 * @param other
	 *            the other slot
	 * @return {@code True} if this slot has the same item and at least the same
	 *         amount as the other slot. {@code False} otherwise.
	 */
	public boolean matches(Slot other) {
		return this.item == other.item && this.amount >= other.amount;
	}

	/**
	 * Adds an item with an given amount.
	 * 
	 * @param item
	 *            the item to be set.
	 * @param amount
	 *            the amount of the item.
	 * @return {@code True} if successful. {@code False} otherwise.
	 */
	public boolean add(Item item, int amount) {
		if (this.item == item || this.item == null) {
			this.item = item;
			this.amount += amount;
			notifyListeners();
			return true;
		}
		return false;
	}

	/**
	 * Removes the given {@code amount} of items.
	 * 
	 * @param amount
	 *            the amount of items to be removed.
	 * @return {@code True} if successful. {@code False} otherwise.
	 */
	public boolean take(int amount) {
		if (this.amount >= amount) {
			this.amount -= amount;
			if (this.amount == 0) {
				item = null;
			}
			notifyListeners();
			return true;
		}
		return false;
	}

	/**
	 * fires the registered {@code SlotListeners}.
	 */
	private void notifyListeners() {
		for (SlotListener slotlistener : slotListeners) {
			slotlistener.hasChanged(this);
		}
	}

	public Item getItem() {
		return item;
	}

	public int getAmount() {
		return amount;
	}

	/**
	 * returns a string representing the object in the form "Slot[" + item + ":"
	 * + amount + "]"
	 */
	@Override
	public String toString() {
		return "Slot[" + item + ":" + amount + "]";
	}
}
