package info.sliz.game.tetris.engine.command.impl;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.sliz.game.tetris.engine.ICollidable;
import info.sliz.game.tetris.engine.command.CommandPlay;
import info.sliz.game.tetris.engine.command.ICommand;
import info.sliz.game.tetris.engine.elements.playcube.FxPlayableElement;
import info.sliz.game.tetris.engine.elements.playcube.IMovable.MOVE;

public class CommandPlayDown extends CommandPlay implements ICommand{

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandPlayDown.class);

    public CommandPlayDown(final FxPlayableElement el,final double step,final Set<ICollidable> colidate) {
        super(el, step,0, colidate);
    }

    public void execute() throws CommandPlayException {
    	LOGGER.debug("Execute command");
        if (!this.canPlay(MOVE.DOWN)){
            throw new CommandPlayException("Can't play: colide with another elements, or is not playable");
        }
        element.play(MOVE.DOWN, moveStep);
    }

}
