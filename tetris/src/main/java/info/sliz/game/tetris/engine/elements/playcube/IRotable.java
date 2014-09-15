package info.sliz.game.tetris.engine.elements.playcube;

import java.util.Set;

import javafx.geometry.Point3D;
import info.sliz.game.tetris.engine.ICollidable;

public interface IRotable extends IElement {

	public static enum ROTATE {
		X, Y, Z
	}

	public void rotate(final ROTATE axis, final double step);

	public boolean canRotate(final ROTATE axis, final double angle,Set<ICollidable> element);

	public Set<Point3D> getPoints();
	
}
