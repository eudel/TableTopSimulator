/**
 * 
 */
package at.hid.tabletopsimulator.inventory;

import at.hid.tabletopsimulator.TableTopSimulator;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;

/**
 * @author dunkler_engel
 *
 */
public class SlotSource extends Source {

	private Slot slotSource;

	public SlotSource(SlotActor actor) {
		super(actor);
		this.slotSource = actor.getSlot();
	}

	@Override
	public Payload dragStart(InputEvent event, float x, float y, int pointer) {
		if (slotSource.getAmount() == 0) {
			return null;
		}
		
		Payload payload = new Payload();
		Slot payloadSlot = new Slot(slotSource.getItem(), slotSource.getAmount());
		slotSource.take(slotSource.getAmount());
		payload.setObject(payloadSlot);
		
		TextureAtlas icons = TableTopSimulator.assets.get("icons/icons.atlas", TextureAtlas.class);
		TextureRegion icon = icons.findRegion(payloadSlot.getItem().getTextureRegion());
		
		Actor dragActor = new Image(icon);
		payload.setDragActor(dragActor);
		
		Actor validDragActor = new Image(icon);
		validDragActor.setColor(0, 1, 0, 1);
		payload.setValidDragActor(validDragActor);
		
		Actor invalidDragActor = new Image(icon);
		invalidDragActor.setColor(1, 0, 0, 1);
		payload.setInvalidDragActor(invalidDragActor);
		
		return payload;
	}

	@Override
	public void dragStop(InputEvent event, float x, float y, int pointer, Payload payload, Target target) {
		Slot payloadSlot = (Slot) payload.getObject();
		if (target != null) {
			Slot targetSlot = ((SlotActor) target.getActor()).getSlot();
			if (targetSlot.getItem() == payloadSlot.getItem() || targetSlot.getItem() == null) {
				targetSlot.add(payloadSlot.getItem(), payloadSlot.getAmount());
			} else {
				Item targetType = targetSlot.getItem();
				int targetAmount = targetSlot.getAmount();
				targetSlot.take(targetAmount);
				targetSlot.add(payloadSlot.getItem(), payloadSlot.getAmount());
				slotSource.add(targetType, targetAmount);
			}
		} else {
			slotSource.add(payloadSlot.getItem(), payloadSlot.getAmount());
		}
	}
}
