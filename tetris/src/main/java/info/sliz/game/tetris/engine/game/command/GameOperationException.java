package info.sliz.game.tetris.engine.game.command;

public class GameOperationException extends Exception {

    private static final long serialVersionUID = 8682869597343753828L;
    
    public GameOperationException(String string, Exception e) {
        super(string, e);
    }
}
