package info.sliz.game.tetris.engine.command;

import info.sliz.game.tetris.engine.impl.Game;

public abstract class CommandGame implements ICommand{

    protected final Game game;

    public CommandGame(final Game game) {
        super();
        this.game = game;
    }
    
}