package info.sliz.game.tetris.engine.elements.playcube;

import info.sliz.game.tetris.engine.ICollidable;
import javafx.geometry.Point3D;
import javafx.scene.transform.Rotate;

public abstract class FxPlayableElement extends FxElement implements IPlayable {

	protected boolean playable;
	
	
	public FxPlayableElement(final Point3D initial) {
		super(initial);
		this.playable = true;
	}
	
	public boolean isPlayable() {
		return this.playable;
	}

	public void setPlayable(final boolean play) {
		boolean throwEvent = false;
		if (this.playable != play) {
			throwEvent = true;
		}
		this.playable = play;
		if (throwEvent) {
			this.throwEvent(this.event);
		}
	}
	
	public boolean canPlay(MOVE direction, double step, ICollidable element) {
		if (this.playable) {
			return this.canMove(direction, step, element);
		}
		return false;
	}
	public boolean canPlay(ROTATE axis, double angle, ICollidable element) {
		if (this.playable) {
			return this.canRotate(axis, angle, element);
		}
		return false;
	}
	public void play(ROTATE axis, double angle) {
		if (playable) {
			this.rotate(axis, angle);
		} else {
			throw new NotPlayableException("Element is not playable");
		}
	}
	public void play(MOVE direction, double step) {
		if (playable) {
			this.move(direction, step);
		} else {
			throw new NotPlayableException("Element is not playable");
		}
	}

	public boolean canRotate(ROTATE axis, double angle, ICollidable element) {
		for (Point3D point : this.getBoundaries()) {
    		if (element.Collidate(IRotateUtils.rotatePoint(this.getControlPoint(),point, axis, angle))){
    			return false;
    		}
		}
    	return true;
	}
	public void rotate(ROTATE axis, double angle) {
		switch (axis) {
		case X:
			this.getTransforms().add(new Rotate(angle, 0,0,0, Rotate.X_AXIS));
			break;
		case Y:
			this.getTransforms().add(new Rotate(angle, 0,0,0, Rotate.Y_AXIS));
			break;
		case Z:
			this.getTransforms().add(new Rotate(angle, 0,0,0, Rotate.Z_AXIS));
			break;
		default:
			break;
		}
	}
	public boolean canMove(MOVE direction, double step, ICollidable element) {
		for (Point3D point : this.getBoundaries()) {
    		if (element.Collidate(IMoveUtils.movePoint(point, direction, step))){
    			return false;
    		}
		}
    	return true;
	}

	public void move(MOVE direction, double step) {
		Point3D p = IMoveUtils.movePoint(new Point3D(this.getTranslateX(), this.getTranslateY(), this.getTranslateZ()), direction, step);

		this.setTranslateX(p.getX());
		this.setTranslateY(p.getY());
		this.setTranslateZ(p.getZ());

		this.throwEvent(event);
	}
}