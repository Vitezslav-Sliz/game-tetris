package info.sliz.game.tetris.engine.elements.playcube;

import javafx.geometry.Point3D;
import info.sliz.game.tetris.engine.ICollidable;

public interface IMovable extends IElement{

    public static enum MOVE {
        LEFT,RIGHT,UP,DOWN,FORWARD,BACKWARD
    }
    
    public void move(final MOVE direction, final double step);
    
    public boolean canMove(final MOVE direction, final double step, ICollidable element);
    
	static class  IMoveUtils{
		public final static Point3D movePoint(Point3D p,MOVE direction, double step){
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
}
