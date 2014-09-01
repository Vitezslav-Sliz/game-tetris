package info.sliz.game.tetris.engine.command.impl;

import info.sliz.game.tetris.engine.command.CommandGame;
import info.sliz.game.tetris.engine.impl.Game;

public class CommandStartGame extends CommandGame {


    public CommandStartGame(Game game) {
        super(game);
    }

    public void execute() throws CommandException {
        if (!game.isRunning()){
            game.startGame();
        }else{
            throw new CommandException("Game is already running!");
        }
    }

}
