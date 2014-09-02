package info.sliz.game.tetris.engine.gamespace.impl;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import info.sliz.game.tetris.config.Configuration;
import info.sliz.game.tetris.engine.elements.ICollision;
import info.sliz.game.tetris.engine.elements.basic.FxCube;
import info.sliz.game.tetris.engine.elements.basic.impl.FxStaticCube;
import info.sliz.game.tetris.engine.elements.playcube.FxMovableElement;

public class FxInplaceElement extends FxMovableElement implements ICollision{
    private FxCube cube;
    private final int size;
    private Color color = Configuration.COLOR_CUBE;

    public FxInplaceElement(final double x, final double y, final double z,final int size) {
        super(x,y,z);
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
        return point.getX() == this.getTranslateX() && point.getY() == this.getTranslateY() && point.getZ() == this.getTranslateZ();
    }
}
