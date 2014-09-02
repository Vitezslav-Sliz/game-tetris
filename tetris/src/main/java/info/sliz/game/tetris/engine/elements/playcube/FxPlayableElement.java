package info.sliz.game.tetris.engine.elements.playcube;

public abstract class FxPlayableElement extends FxMovableElement implements IPlayable{

    protected boolean playable;
    public FxPlayableElement(double x, double y, double z) {
        super(x, y, z);
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
            this.throwEvent();
        }
    }
}
