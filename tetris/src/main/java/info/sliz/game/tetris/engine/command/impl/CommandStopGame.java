package info.sliz.game.tetris.engine.command.impl;

import info.sliz.game.tetris.engine.command.CommandGame;
import info.sliz.game.tetris.engine.impl.Game;

public class CommandStopGame extends CommandGame{

    public CommandStopGame(Game game) {
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
