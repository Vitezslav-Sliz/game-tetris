package info.sliz.game.tetris.engine.elements.playcube;

import info.sliz.game.tetris.engine.ICollidable;
import info.sliz.game.tetris.engine.elements.event.impl.ElementEvent;
import javafx.geometry.Point3D;

public abstract class FxMovableElement extends FxElement implements IMovable {
   
    protected ElementEvent event;
    
    public FxMovableElement(final Point3D initial) {
        super(initial);
        this.event = new ElementEvent(this);
    }

   
    public void move(final MOVE direction, final double step) {
    	Point3D p = FxMovableElement.movePoint(new Point3D(this.getTranslateX(),this.getTranslateY(),this.getTranslateZ()), direction, step);
    	
    	this.setTranslateX(p.getX());
    	this.setTranslateY(p.getY());
    	this.setTranslateZ(p.getZ());
        
    	this.throwEvent(event);
    }
    public final boolean canMove(MOVE direction, double step, ICollidable element) {
    	for (Point3D point : this.getBoundaries()) {
    		if (element.Collidate(FxMovableElement.movePoint(point, direction, step))){
    			return false;
    		}
		}
    	return true;
    }
    private final static Point3D movePoint(Point3D p,MOVE direction, double step){
    	double x = p.getX();
    	double y = p.getY();
    	double z = p.getZ();
    	 switch (direction) {
         case LEFT:
             x -=step;
             break;
         case RIGHT:
             x +=step;
             break;
         case UP:
             y-=step;
             break;
         case DOWN:
        	 y+=step;
             break;
         case FORWARD:
        	 z+=step;
             break;
         default:
             throw new UnsupportedOperationException(String.format("Calculate Point for direction: '%s ' is not supported", direction));
         }
    	 return new Point3D(x, y, z);
    }
}