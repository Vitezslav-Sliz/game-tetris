package info.sliz.game.tetris.engine.elements.playcube;

import java.util.Set;

import javafx.geometry.Point3D;
import javafx.scene.transform.Rotate;
import info.sliz.game.tetris.engine.ICollidable;

public interface IRotable extends IElement {

	public static enum ROTATE {
		X, Y, Z
	}

	public void rotate(final ROTATE axis, final double step);

	public boolean canRotate(final ROTATE axis, final double angle,
			ICollidable element);

	public Set<Point3D> getPoints();
	
	static class IRotateUtils {
		public final static Point3D rotatePoint(Point3D control, Point3D point,ROTATE axis, double angle) {
			switch (axis) {
			case X:
				return new Rotate(angle, control.getX(), control.getY(),control.getZ(), Rotate.X_AXIS).transform(point);
			case Y:
				return new Rotate(angle, control.getX(), control.getY(),control.getZ(), Rotate.Y_AXIS).transform(point);
			case Z:
				return new Rotate(angle, control.getX(), control.getY(),control.getZ(), Rotate.Z_AXIS).transform(point);
			default:
				return null;
			}
		}
	}
}
