package info.sliz.game.tetris.engine.elements.playcube;

import info.sliz.game.tetris.engine.ICollidable;

public interface IMovable extends IElement{

    public static enum MOVE {
        LEFT,RIGHT,UP,DOWN,FORWARD,BACKWARD
    }
    
    public void move(final MOVE direction, final double step);
    
    public boolean canMove(final MOVE direction, final double step, ICollidable element);
}
