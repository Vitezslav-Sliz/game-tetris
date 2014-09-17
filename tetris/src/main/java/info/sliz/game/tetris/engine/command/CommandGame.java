package info.sliz.game.tetris.engine.command;

import info.sliz.game.tetris.engine.IGameStrategy;

public abstract class CommandGame implements ICommand {

    protected final IGameStrategy game;

    public CommandGame(final IGameStrategy game) {
        super();
        this.game = game;
    }

    public abstract void execute() throws CommandException;
}