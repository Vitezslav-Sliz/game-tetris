package info.sliz.game.tetris.engine.elements.playcube;

import java.util.Set;

import info.sliz.game.tetris.engine.ICollidable;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.transform.Rotate;

public abstract class FxPlayableElement extends FxElement implements IPlayable {

	protected boolean playable;
	private Rotate x = new Rotate(0,Rotate.X_AXIS);
	private Rotate y = new Rotate(0,Rotate.Y_AXIS);
	private Rotate z = new Rotate(0,Rotate.Z_AXIS);
	
	public FxPlayableElement(final Point3D initial) {
		super(initial);
		this.playable = true;
		this.getTransforms().addAll(x,y,z);
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
	
	public boolean canPlay(MOVE direction, double step, Set<ICollidable> elements) {
		if (this.playable) {
			return this.canMove(direction, step, elements);
		}
		return false;
	}
	public boolean canPlay(ROTATE axis, double angle, Set<ICollidable> elements) {
		if (this.playable) {
			return this.canRotate(axis, angle, elements);
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

	public boolean canRotate(ROTATE axis, double angle, Set<ICollidable> elements) {
	    Group tG = new Group();
	    tG.setTranslateX(this.getTranslateX());
	    tG.setTranslateY(this.getTranslateY());
	    tG.setTranslateZ(this.getTranslateZ());
	    Rotate tX = new Rotate(x.getAngle(),Rotate.X_AXIS);
	    Rotate tY = new Rotate(y.getAngle(),Rotate.Y_AXIS);
	    Rotate tZ = new Rotate(z.getAngle(),Rotate.Z_AXIS);
	    tG.getTransforms().addAll(tX,tY,tZ);
	    
	    switch (axis) {
        case X:
            tX.setAngle(x.getAngle()+angle);
            break;
        case Y:
            tY.setAngle(y.getAngle()+angle);
            break;
        case Z:
            tZ.setAngle(z.getAngle()+angle);
            break;
        default:
            break;
        }
	    for (Point3D point : this.getPoints()) {
	        Point3D check = tG.localToParent(point);
	        for (ICollidable col : elements) {
	            if (col.Collidate(check)) {
	                return false;
	            }
	        }
	    }
	    return true;
	}
	

    public void rotate(ROTATE axis, double angle) {
		switch (axis) {
		case X:
		    x.setAngle(x.getAngle()+angle);
			break;
		case Y:
		    y.setAngle(y.getAngle()+angle);
			break;
		case Z:
		    z.setAngle(z.getAngle()+angle);
			break;
		default:
			break;
		}
	}
    
	public boolean canMove(MOVE direction, double step, Set<ICollidable> elements) {
		for (Point3D point : this.getBoundaries()) {
		    Point3D mPoint = IMoveUtils.movePoint(point, direction, step);
		    for (ICollidable col : elements) {
	            if (col.Collidate(mPoint)) {
	                return false;
	            }
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