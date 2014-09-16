package info.sliz.game.tetris.engine;

import info.sliz.game.tetris.engine.command.ICommand;

public interface ICommands {

    Class<? extends ICommand> getCommandClass();

}
