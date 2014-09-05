package info.sliz.game.tetris.engine.elements.playcube.impl;

import java.util.HashSet;
import java.util.Set;

import javafx.geometry.Point3D;
import info.sliz.game.tetris.engine.elements.basic.FxCube;
import info.sliz.game.tetris.engine.elements.basic.impl.FxPlayCube;
import info.sliz.game.tetris.engine.elements.playcube.FxPlayableElement;

public final class FxTetrisDotElement extends FxPlayableElement {
    private FxCube cube;
    private final int size;
    
    public FxTetrisDotElement(final Point3D initial,final int size) {
        super(initial);
        this.size = size;
        this.cube = new FxPlayCube(this.size);
        this.getChildren().add(cube);
    }
    
    @Override
    public String toString() {
        return String.format("%s[%.3f|%.3f|%.3f]@%s",getClass().getSimpleName(),this.getTranslateX(),this.getTranslateY(),getTranslateZ(),Integer.toHexString(hashCode()));
    }
    public Point3D getElementCoordinate(){
        return new Point3D(Math.round(this.getTranslateX()), Math.round(this.getTranslateY()), Math.round(this.getTranslateZ()));
    }
    
    public Set<Point3D> getBoundaries() {
        Set<Point3D> ret = new HashSet<Point3D>(1); 
        ret.add(this.getElementCoordinate());
        return ret;
    }

    public Set<Point3D> getPoints() {
        return getBoundaries();
    }
}
