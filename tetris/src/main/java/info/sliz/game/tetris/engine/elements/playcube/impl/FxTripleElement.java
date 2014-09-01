package info.sliz.game.tetris.engine.elements.playcube.impl;

import java.util.HashSet;
import java.util.Set;

import javafx.geometry.Point3D;
import info.sliz.game.tetris.engine.elements.basic.FxCube;
import info.sliz.game.tetris.engine.elements.basic.impl.FxPlayCube;
import info.sliz.game.tetris.engine.elements.playcube.FxPlayableElement;

public class FxTripleElement extends FxPlayableElement {

    private FxCube masterCube;
    private FxCube rightCube;
    private FxCube leftCube;
    private final int size;
    
    public FxTripleElement(final double x, final double y, final double z,final int size) {
        super(x,y,z);
        this.size = size;
        this.masterCube = new FxPlayCube(this.size);
        this.leftCube = new FxPlayCube(this.size);
        this.rightCube = new FxPlayCube(this.size);

        leftCube.setTranslateX(x-this.size);
        rightCube.setTranslateX(x+this.size);
       
        this.getChildren().add(this.masterCube);
        this.getChildren().add(this.leftCube);
        this.getChildren().add(this.rightCube);
    }

    @Override
    public Set<Point3D> getControlPoints() {
        Set<Point3D> ret = new HashSet<Point3D>(1); 
        ret.add(new Point3D(this.getTranslateX(),this.getTranslateY(),this.getTranslateZ()));
        ret.add(this.localToParent(new Point3D(leftCube.getTranslateX(),leftCube.getTranslateY(),leftCube.getTranslateZ())));
        ret.add(this.localToParent(new Point3D(rightCube.getTranslateX(),rightCube.getTranslateY(),rightCube.getTranslateZ())));
        return ret;
    }
}
