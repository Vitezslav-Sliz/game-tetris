package info.sliz.game.tetris.engine.command;

public interface ICommand {

    public class CommandException extends RuntimeException{

        private static final long serialVersionUID = 7229208454797687804L;

        public CommandException(String message) {
            super(message);
        }
        
    }
    
    public void Execute() throws CommandException;
}
