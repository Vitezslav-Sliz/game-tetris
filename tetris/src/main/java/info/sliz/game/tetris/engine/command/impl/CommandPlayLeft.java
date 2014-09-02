package info.sliz.game.tetris.engine.command.impl;

import java.util.Set;

import javafx.geometry.Point3D;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.sliz.game.tetris.engine.command.CommandPlay;
import info.sliz.game.tetris.engine.command.ICommand;
import info.sliz.game.tetris.engine.elements.ICollision;
import info.sliz.game.tetris.engine.elements.playcube.FxPlayableElement;
import info.sliz.game.tetris.engine.elements.playcube.IMovable.MOVE;

public class CommandPlayLeft extends CommandPlay implements ICommand{
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandPlayLeft.class);
    
    public CommandPlayLeft(final FxPlayableElement el,final double step,final Set<ICollision> colidate) {
        super(el, step, colidate);
    }
    public void execute() throws MoveCommandException{
        LOGGER.debug("Execute command");
        for (Point3D p : element.getControlPoints()) {
            Point3D mx = new Point3D(p.getX()-moveStep,p.getY(),p.getZ());
            if (this.checkCollision(mx)){
                throw new MoveCommandException("Can't move: colide with another elements");
            }
        }
        el.play(MOVE.LEFT, moveStep);
    }
}
