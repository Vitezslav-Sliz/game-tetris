package info.sliz.game.tetris.engine.command.impl;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.sliz.game.tetris.engine.ICollidable;
import info.sliz.game.tetris.engine.command.CommandMove;
import info.sliz.game.tetris.engine.elements.playcube.IMovable;
import info.sliz.game.tetris.engine.elements.playcube.IMovable.MOVE;

public class CommandMoveForward extends CommandMove {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandMoveForward.class);
    
    public CommandMoveForward(IMovable element, double step, Set<ICollidable> colidate) {
        super(element, step, colidate);
    }

    @Override
    public void execute() throws CommandException {
        LOGGER.debug("Execute command");
        if (this.element.canMove(MOVE.FORWARD, moveStep, this.colidate)){
            element.move(MOVE.FORWARD, moveStep);
        }
    }
}
