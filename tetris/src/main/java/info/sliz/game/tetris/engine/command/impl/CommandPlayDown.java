package info.sliz.game.tetris.engine.command.impl;

import javafx.geometry.Point3D;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.sliz.game.tetris.engine.command.CommandPlay;
import info.sliz.game.tetris.engine.command.ICommand;
import info.sliz.game.tetris.engine.elements.playcube.FxPlayableElement;
import info.sliz.game.tetris.engine.elements.playcube.IMovable.MOVE;

public class CommandPlayDown extends CommandPlay implements ICommand{

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandPlayDown.class);

    public CommandPlayDown(FxPlayableElement el, double step, double boundary) {
        super(el, step, boundary);
    }

    public void execute() throws CommandException {
        LOGGER.debug("Execute command");
        for (Point3D p : el.getControlPoints()) {
            if ((p.getY()+moveStep) >= moveBoundary){
                throw new CommandException("Cannot move except boundaries");
            }
        }
        el.play(MOVE.DOWN, moveStep);
        super.execute();
    }

}
