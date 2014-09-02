package info.sliz.game.tetris.engine.command.impl;

import java.util.Set;

import javafx.geometry.Point3D;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.sliz.game.tetris.engine.command.CommandPlay;
import info.sliz.game.tetris.engine.elements.ICollision;
import info.sliz.game.tetris.engine.elements.playcube.FxPlayableElement;
import info.sliz.game.tetris.engine.elements.playcube.IMovable.MOVE;

public class CommandPlaySpace extends CommandPlay {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandPlayRight.class);
    
    public CommandPlaySpace(final FxPlayableElement el,final double step,final Set<ICollision> colidate) {
        super(el, step, colidate);
    }
    @Override
    public void execute() throws MoveCommandException {
        
        LOGGER.debug("Execute command");
        while (true) {
            for (Point3D p : element.getControlPoints()) {
                Point3D mx = new Point3D(p.getX(),p.getY(),p.getZ()+moveStep);
                if (this.checkCollision(mx)){
                    throw new MoveCommandException("Can't move: colide with another elements");
                }
            }
            el.play(MOVE.FORWARD, moveStep);
        }
    }
}
