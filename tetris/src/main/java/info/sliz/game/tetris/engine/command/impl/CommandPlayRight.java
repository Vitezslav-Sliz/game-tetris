package info.sliz.game.tetris.engine.command.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.sliz.game.tetris.engine.command.ICommand;
import info.sliz.game.tetris.ui.elements.playcube.FxElement;
import info.sliz.game.tetris.ui.elements.playcube.IMovable.MOVE;

public class CommandPlayRight implements ICommand{
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandPlayRight.class);
    
    private FxElement el;
    private int move_step;
    
    public CommandPlayRight(FxElement el, final int step) {
        this.el = el;
        this.move_step = step;
    }
    
    public void Execute() {
        LOGGER.debug("Execute command");
        el.play(MOVE.RIGHT, move_step);
    }

}
