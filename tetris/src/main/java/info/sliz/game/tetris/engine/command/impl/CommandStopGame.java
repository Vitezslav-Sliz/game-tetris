package info.sliz.game.tetris.engine.command.impl;

import info.sliz.game.tetris.engine.IGameStrategy;
import info.sliz.game.tetris.engine.command.CommandGame;

public class CommandStopGame extends CommandGame{

    public CommandStopGame(IGameStrategy game) {
        super(game);
    }

    public void execute() throws CommandException {
        if (game.isRunning()){
            game.stopGame();
        }else{
            throw new CommandException("Game is not running!");
        }
    }
}
