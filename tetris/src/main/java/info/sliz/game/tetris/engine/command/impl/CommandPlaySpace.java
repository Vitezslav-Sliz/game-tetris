package info.sliz.game.tetris.engine.command.impl;

import javafx.geometry.Point3D;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.sliz.game.tetris.engine.command.CommandPlay;
import info.sliz.game.tetris.engine.elements.playcube.FxPlayableElement;
import info.sliz.game.tetris.engine.elements.playcube.IMovable.MOVE;

public class CommandPlaySpace extends CommandPlay {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandPlayRight.class);
    
    public CommandPlaySpace(FxPlayableElement el, double step, double boundary) {
        super(el, step, boundary);
    }

    @Override
    public void execute() throws CommandException {
        
        LOGGER.debug("Execute command");
        while (true) {
            for (Point3D p : el.getControlPoints()) {
                if ((p.getZ()+moveStep) > moveBoundary){
                    el.setPlayable(false);
                    super.execute();
                    return;
                }
            }
            el.play(MOVE.FORWARD, moveStep);
            super.execute();
        }
    }
}
