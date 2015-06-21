package at.hid.tabletopsimulator.inventory;

/**
 * 
 * @author dunkler_engel
 *
 */

public interface SlotListener {

	/**
	 * Keeps track of slot changes
	 * 
	 * @param slot
	 *            the changed slot
	 */
	void hasChanged(Slot slot);

}
