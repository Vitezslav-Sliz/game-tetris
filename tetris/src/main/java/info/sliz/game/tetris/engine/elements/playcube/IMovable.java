package info.sliz.game.tetris.engine.elements.playcube;

import java.util.Set;

import javafx.geometry.Point3D;

public interface IMovable {

    public static enum MOVE {
        LEFT,RIGHT,UP,DOWN,FORWARD,BACKWARD
    }
    
    public void move(final MOVE direction, final double step);
    
    public Set<Point3D> getControlPoints();
}
