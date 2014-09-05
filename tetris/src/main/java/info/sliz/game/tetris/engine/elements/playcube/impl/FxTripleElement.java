package info.sliz.game.tetris.engine.elements.playcube.impl;

import java.util.HashSet;
import java.util.Set;

import javafx.geometry.Point3D;
import info.sliz.game.tetris.engine.elements.basic.FxCube;
import info.sliz.game.tetris.engine.elements.basic.impl.FxPlayCube;
import info.sliz.game.tetris.engine.elements.playcube.FxPlayableElement;

public final class FxTripleElement extends FxPlayableElement {

    private FxCube masterCube;
    private FxCube rightCube;
    private FxCube leftCube;
    private final int size;
    
    public FxTripleElement(final Point3D initial,final int size) {
        super(initial);
        this.size = size;
        this.masterCube = new FxPlayCube(this.size);
        this.leftCube = new FxPlayCube(this.size);
        this.rightCube = new FxPlayCube(this.size);

        leftCube.setTranslateX(initial.getX()-this.size);
        rightCube.setTranslateX(initial.getX()+this.size);
       
        this.getChildren().add(this.masterCube);
        this.getChildren().add(this.leftCube);
        this.getChildren().add(this.rightCube);
    }
	@Override
	public Point3D getControlPoint() {
		return new Point3D(this.getTranslateX(),this.getTranslateY(),this.getTranslateZ());
	}
    @Override
    public Set<Point3D> getBoundaries() {
        Set<Point3D> ret = new HashSet<Point3D>(1); 
        ret.add(new Point3D(Math.round(this.getTranslateX()), Math.round(this.getTranslateY()), Math.round(this.getTranslateZ())));
        Point3D p = this.localToParent(new Point3D(this.leftCube.getTranslateX(), this.leftCube.getTranslateY(), this.leftCube.getTranslateZ()));
        ret.add(new Point3D(Math.round(p.getX()),Math.round(p.getY()),Math.round(p.getZ())));
        p = this.localToParent(new Point3D(this.rightCube.getTranslateX(), this.rightCube.getTranslateY(), this.rightCube.getTranslateZ()));
        ret.add(new Point3D(Math.round(p.getX()),Math.round(p.getY()),Math.round(p.getZ())));
        return ret;
    }
}
