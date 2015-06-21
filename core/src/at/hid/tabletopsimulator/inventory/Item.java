/**
 * 
 */
package at.hid.tabletopsimulator.inventory;

/**
 * @author dunkler_engel
 */
public enum Item {

	Sword("w.sword001");

	private String textureRegion;

	private Item(String textureRegion) {
		this.textureRegion = textureRegion;
	}

	public String getTextureRegion() {
		return textureRegion;
	}
}
