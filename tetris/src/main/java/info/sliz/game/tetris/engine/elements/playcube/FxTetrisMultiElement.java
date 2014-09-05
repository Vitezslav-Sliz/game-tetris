package info.sliz.game.tetris.engine.elements.playcube;

import info.sliz.game.tetris.engine.elements.basic.FxCube;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.geometry.Point3D;

public abstract class FxTetrisMultiElement extends FxPlayableElement{

    protected final List<FxCube> cubes = new ArrayList<FxCube>();
    
    public FxTetrisMultiElement(Point3D initial) {
        super(initial);
    }
    @Override
    public Point3D getElementCoordinate(){
        return new Point3D(Math.round(this.getTranslateX()), Math.round(this.getTranslateY()), Math.round(this.getTranslateZ()));
    }
    
    public Set<Point3D> getBoundaries() {
        Set<Point3D> ret = new HashSet<Point3D>(1+cubes.size()); 
        ret.add(new Point3D(Math.round(this.getTranslateX()), Math.round(this.getTranslateY()), Math.round(this.getTranslateZ())));
        for (FxCube cube: cubes) {
            Point3D p = this.localToParent(new Point3D(Math.round(cube.getTranslateX()), Math.round(cube.getTranslateY()), Math.round(cube.getTranslateZ())));
            ret.add(new Point3D(Math.round(p.getX()),Math.round(p.getY()),Math.round(p.getZ())));
        }
        return ret;
    }
    
    public Set<Point3D> getPoints() {
        Set<Point3D> ret = new HashSet<Point3D>(1+cubes.size());
        for (FxCube cube: cubes) {
            Point3D p = new Point3D(Math.round(cube.getTranslateX()), Math.round(cube.getTranslateY()), Math.round(cube.getTranslateZ()));
            ret.add(new Point3D(Math.round(p.getX()),Math.round(p.getY()),Math.round(p.getZ())));
        }
        return ret;
    }  
}
