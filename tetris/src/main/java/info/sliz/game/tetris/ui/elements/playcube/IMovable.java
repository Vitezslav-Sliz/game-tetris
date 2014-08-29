package info.sliz.game.tetris.ui.elements.playcube;

public interface IMovable {

    public static enum MOVE {
        LEFT,RIGHT,UP,DOWN,FORWARD,BACKWARD
    }
    
    public void move(final MOVE direction, final double Step);
}
