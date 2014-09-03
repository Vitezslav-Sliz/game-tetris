package info.sliz.game.tetris.engine;

import info.sliz.game.tetris.engine.elements.playcube.IElement;
import javafx.geometry.Point3D;

public interface ICollidable {

    public boolean Collidate(final Point3D point);
    
    public boolean Collidate(final IElement element);
    
}
