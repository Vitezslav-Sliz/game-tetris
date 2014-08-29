package info.sliz.game.tetris.ui.elements.playcube;

public interface IPlayable extends IMovable {

    public boolean isPlayable();
    
    public void setPlayable(final boolean play);
    
    public void play(final MOVE direction, final double step);
    
    public class NotPlayableException extends RuntimeException{
        private static final long serialVersionUID = 7295332880366992209L;
        public NotPlayableException(String message) {
            super(message);
        }        
    }
}
