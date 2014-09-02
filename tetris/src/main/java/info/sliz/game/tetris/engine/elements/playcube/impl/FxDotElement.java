package info.sliz.game.tetris.engine.elements.playcube.impl;

import java.util.HashSet;
import java.util.Set;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import info.sliz.game.tetris.engine.elements.basic.FxCube;
import info.sliz.game.tetris.engine.elements.basic.impl.FxPlayCube;
import info.sliz.game.tetris.engine.elements.playcube.FxPlayableElement;

public final class FxDotElement extends FxPlayableElement {
    private FxCube cube;
    private final int size;
    
    public FxDotElement(final Point3D initial,final int size) {
        super(initial);
        this.size = size;
        this.cube = new FxPlayCube(this.size);
        this.getChildren().add(cube);
    }
  
    public void setColor(final Color color){
        this.cube.setColor(color);
    }
    @Override
    public Set<Point3D> getControlPoints() {
        Set<Point3D> ret = new HashSet<Point3D>(1); 
        ret.add(new Point3D(this.getTranslateX(),this.getTranslateY(),this.getTranslateZ()));
        return ret;
    }
    
    @Override
    public String toString() {
        return String.format("%s[%.3f|%.3f|%.3f]@%s",getClass().getSimpleName(),this.getTranslateX(),this.getTranslateY(),getTranslateZ(),Integer.toHexString(hashCode()));
    }

}
