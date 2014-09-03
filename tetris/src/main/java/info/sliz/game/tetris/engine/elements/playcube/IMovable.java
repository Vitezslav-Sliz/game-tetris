package info.sliz.game.tetris.engine.elements.playcube;

public interface IMovable extends IElement{

    public static enum MOVE {
        LEFT,RIGHT,UP,DOWN,FORWARD,BACKWARD
    }
    
    public void move(final MOVE direction, final double step);
    
}
