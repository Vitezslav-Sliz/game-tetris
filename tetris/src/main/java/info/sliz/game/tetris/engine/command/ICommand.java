package info.sliz.game.tetris.engine.command;

public interface ICommand {

    public class CommandException extends Exception {

        private static final long serialVersionUID = 7229208454797687804L;

        public CommandException(String message) {
            super(message);
        }

    }

    public void execute() throws CommandException;
}
