package info.sliz.game.tetris.engine.elements.playcube;

import info.sliz.game.tetris.engine.ICollidable;
import javafx.geometry.Point3D;
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
	    //FIXME can rotate - problems with rotation Z axis
	    System.out.println("ORIGINAL POSITION");
	    System.out.println(this.getBoundaries());
	    System.out.println("---------------------");
	    Point3D ctrl = this.getElementCoordinate();
	    System.out.println("Control point:"+ctrl+"\n");
		for (Point3D point : this.getBoundaries()) {
		    
		    Point3D toRotate = new Point3D(point.getX()-ctrl.getX(), point.getY()-ctrl.getY(), point.getZ()-ctrl.getZ());
		    System.out.println("Rotate point "+ point);
		    System.out.println("Rotate to locale point "+ toRotate+"\n");
		    
		    Point3D s = IRotateUtils.rotatePoint(new Point3D(0,0,0),toRotate, axis, angle);
		    Point3D check = new Point3D(s.getX()+ctrl.getX(), s.getY()+ctrl.getY(), s.getZ()+ctrl.getZ());
		    System.out.println("Rotated point"+check);
    		if (element.Collidate(check)){
    			return false;
    		}
		}
		System.out.println("---------------------");
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
		System.out.println("After systematic rotate");
		System.out.println(this.getBoundaries());
		System.out.println("----------------------------");
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