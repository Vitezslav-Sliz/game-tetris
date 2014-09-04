package info.sliz.game.tetris.engine.gamespace.impl;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import info.sliz.game.tetris.config.Configuration;
import info.sliz.game.tetris.engine.ICollidable;
import info.sliz.game.tetris.engine.elements.basic.FxCube;
import info.sliz.game.tetris.engine.elements.basic.impl.FxStaticCube;
import info.sliz.game.tetris.engine.elements.playcube.FxElement;
import info.sliz.game.tetris.engine.elements.playcube.IMovable;

public class FxInplaceElement extends FxElement implements ICollidable,IMovable{
    private FxCube cube;
    private final int size;
    private Color color = Configuration.COLOR_CUBE;

    public FxInplaceElement(final Point3D initial,final int size, final Color color) {
        this(initial,size);
        if (color != null){
            this.cube.setColor(color);
        }
    }
    public FxInplaceElement(final Point3D initial,final int size) {
        super(initial);
        this.size = size;
        this.cube = new FxStaticCube(this.size);
        this.cube.setColor(color);
        this.getChildren().add(cube);
    }
  
    public void setColor(final Color color){
        this.cube.setColor(color);
    }

    @Override
    public String toString() {
        return String.format("%s[%.3f|%.3f|%.3f]@%s",getClass().getSimpleName(),this.getTranslateX(),this.getTranslateY(),getTranslateZ(),Integer.toHexString(hashCode()));
    }

    public boolean Collidate(Point3D point) {
    	Point3D c = this.getControlPoint();
        return point.getX() == c.getX() && point.getY() == c.getY() && point.getZ() == c.getZ();
    }
	public void move(MOVE direction, double step) {
		Point3D p = IMoveUtils.movePoint(new Point3D(this.getTranslateX(), this.getTranslateY(), this.getTranslateZ()), direction, step);
		this.setTranslateX(p.getX());
		this.setTranslateY(p.getY());
		this.setTranslateZ(p.getZ());
		
		this.throwEvent(this.event);
	}
	public boolean canMove(MOVE direction, double step, ICollidable element) {
		return !element.Collidate(IMoveUtils.movePoint(this.getControlPoint(), direction, step));
	}

}
