package info.sliz.game.tetris.engine.elements.playcube.impl;

import java.util.HashSet;
import java.util.Set;

import javafx.geometry.Point3D;
import info.sliz.game.tetris.engine.elements.basic.FxCube;
import info.sliz.game.tetris.engine.elements.basic.impl.FxPlayCube;
import info.sliz.game.tetris.engine.elements.playcube.FxPlayableElement;

public final class FxDoubleElement extends FxPlayableElement {

    private FxCube masterCube;
    private FxCube otherCube;
    private final int size;
    
    public FxDoubleElement(final Point3D initial,final int size) {
        super(initial);
        this.size = size;
        this.masterCube = new FxPlayCube(this.size);
        this.otherCube = new FxPlayCube(this.size);

        otherCube.setTranslateX(initial.getX()+this.size);
       
        this.getChildren().add(this.masterCube);
        this.getChildren().add(this.otherCube);
    }

    @Override
    public Point3D getControlPoint() {
    	return new Point3D(this.getTranslateX(),this.getTranslateY(),this.getTranslateZ());
    }
    
    @Override
    public Set<Point3D> getBoundaries() {
        Set<Point3D> ret = new HashSet<Point3D>(2); 
        ret.add(new Point3D(Math.round(this.getTranslateX()), Math.round(this.getTranslateY()), Math.round(this.getTranslateZ())));
        Point3D p = this.localToParent(new Point3D(Math.round(this.otherCube.getTranslateX()), Math.round(this.otherCube.getTranslateY()), Math.round(this.otherCube.getTranslateZ())));
        ret.add(new Point3D(Math.round(p.getX()),Math.round(p.getY()),Math.round(p.getZ())));
        return ret;
    }
}
