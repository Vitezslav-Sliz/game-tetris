package info.sliz.game.tetris.engine.elements.playcube;

import java.util.Set;

import javafx.geometry.Point3D;
import javafx.scene.Group;

public abstract class FxElement extends Group implements IMovable, IPlayable{
    protected boolean playable;
    
    public FxElement(boolean playable) {
        super();
        this.playable = playable;
    }

    public FxElement() {
        this(false);
    }
    
    public boolean isPlayable() {
        return this.playable;
    }

    public void setPlayable(final boolean play) {
        this.playable = play;
        
    }
    
    public abstract Set<Point3D> getControlPoints(); 
}