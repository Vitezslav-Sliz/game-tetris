package info.sliz.game.tetris.ui.command.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.sliz.game.tetris.engine.IEngine;
import info.sliz.game.tetris.engine.command.ICommand.CommandException;
import info.sliz.game.tetris.engine.impl.Commands;
import info.sliz.game.tetris.ui.command.IUICommand;

public class ExitCommand implements IUICommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExitCommand.class);
    private final IEngine e;

    public ExitCommand(final IEngine engine) {
        this.e = engine;
    }

    public void execute() {
        try {
            e.callCommand(Commands.STOP);
        } catch (CommandException e) {
            LOGGER.warn("Problem with exit program", e);
        }
    }
}
