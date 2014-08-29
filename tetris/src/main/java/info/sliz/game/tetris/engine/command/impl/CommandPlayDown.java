package info.sliz.game.tetris.engine.command.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.sliz.game.tetris.engine.command.CommandMove;
import info.sliz.game.tetris.engine.command.ICommand;
import info.sliz.game.tetris.engine.elements.playcube.FxElement;
import info.sliz.game.tetris.engine.elements.playcube.IMovable.MOVE;

public class CommandPlayDown extends CommandMove implements ICommand{

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandPlayDown.class);
    public CommandPlayDown(FxElement el, int step) {
        super(el, step);
    }

    public void execute() {
        LOGGER.debug("Execute command");
        el.play(MOVE.DOWN, moveStep);
    }

}
