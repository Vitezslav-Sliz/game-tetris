package info.sliz.game.tetris.engine.command.impl;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.sliz.game.tetris.engine.command.CommandPlay;
import info.sliz.game.tetris.engine.command.ICommand;
import info.sliz.game.tetris.engine.elements.ICollision;
import info.sliz.game.tetris.engine.elements.playcube.FxPlayableElement;
import info.sliz.game.tetris.engine.elements.playcube.IMovable.MOVE;

public class CommandPlayDown extends CommandPlay implements ICommand{

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandPlayDown.class);

    public CommandPlayDown(final FxPlayableElement el,final double step,final Set<ICollision> colidate) {
        super(el, step, colidate);
    }

    public void execute() throws MoveCommandException {
        LOGGER.debug("Execute command");
        if (this.checkCollision()){
            throw new MoveCommandException("Can't move: colide with another elements");
        }
        element.play(MOVE.DOWN, moveStep);
    }

}
