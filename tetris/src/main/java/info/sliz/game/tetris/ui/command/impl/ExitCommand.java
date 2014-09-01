package info.sliz.game.tetris.ui.command.impl;

import info.sliz.game.tetris.engine.command.ICommand.CommandException;
import info.sliz.game.tetris.engine.command.impl.CommandStopGame;
import info.sliz.game.tetris.engine.impl.CoreEngine;
import info.sliz.game.tetris.ui.command.IUICommand;

public class ExitCommand implements IUICommand {

    private final CoreEngine e;
    public ExitCommand(final CoreEngine engine) {
        this.e = engine;
    }

    public void execute() {
        try {
            e.getCommand(CommandStopGame.class).execute();
        } catch (CommandException e) {
            e.printStackTrace();
        };
    }
}
