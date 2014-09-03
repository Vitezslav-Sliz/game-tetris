package info.sliz.game.tetris.engine.elements.playcube;

import java.util.Set;

import javafx.geometry.Point3D;

public interface IElement {

    public Point3D getControlPoint();
    public Set<Point3D> getBoundaries();
}
