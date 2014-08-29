package info.sliz.game.tetris.engine.command.impl;

import javafx.geometry.Point3D;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.sliz.game.tetris.engine.command.CommandMove;
import info.sliz.game.tetris.engine.command.ICommand;
import info.sliz.game.tetris.engine.elements.playcube.FxElement;
import info.sliz.game.tetris.engine.elements.playcube.IMovable.MOVE;

public class CommandPlayRight extends CommandMove implements ICommand{
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandPlayRight.class);

    public CommandPlayRight(FxElement el, double step, double boundary) {
        super(el, step, boundary);
    }

    public void execute() throws CommandException {
        LOGGER.debug("Execute command");
        for (Point3D p : el.getControlPoints()) {
            if ((p.getX()+moveStep) >= moveBoundary){
                throw new CommandException("Cannot move except boundaries");
            }
        }
        el.play(MOVE.RIGHT, moveStep);
    }

}
