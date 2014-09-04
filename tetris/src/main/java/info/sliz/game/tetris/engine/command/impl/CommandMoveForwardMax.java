package info.sliz.game.tetris.engine.command.impl;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.sliz.game.tetris.engine.ICollidable;
import info.sliz.game.tetris.engine.command.CommandMove;
import info.sliz.game.tetris.engine.elements.playcube.IMovable;
import info.sliz.game.tetris.engine.elements.playcube.IMovable.MOVE;

public class CommandMoveForwardMax extends CommandMove {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandMoveForwardMax.class);
    
    public CommandMoveForwardMax(IMovable element, double step, Set<ICollidable> colidate) {
        super(element, step, colidate);
    }

    @Override
    public void execute() throws CommandException {
        LOGGER.debug("Execute command");
        while (true) {
            if (!this.canMove(MOVE.FORWARD)){
                return;
            }
            element.move(MOVE.FORWARD, moveStep);
        }
    }

}
