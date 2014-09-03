package info.sliz.game.tetris.engine.elements.playcube;

import javafx.geometry.Point3D;

public abstract class FxPlayableElement extends FxMovableElement implements IPlayable{

    protected boolean playable;
    public FxPlayableElement(final Point3D initial) {
        super(initial);
        this.playable = true;
    }
    public void play(MOVE direction, double step) {
        if (playable){
            this.move(direction, step);            
        }else{
            throw new NotPlayableException("Element is not playable");
        }
        
    }
    
    public boolean isPlayable() {
        return this.playable;
    }

    public void setPlayable(final boolean play) {
        boolean throwEvent = false;
        if (this.playable!=play){
            throwEvent = true;
        }
        this.playable = play;
        if (throwEvent){
            this.throwEvent(this.event);
        }
    }
}
