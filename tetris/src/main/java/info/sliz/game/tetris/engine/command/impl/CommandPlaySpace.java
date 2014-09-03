package info.sliz.game.tetris.engine.command.impl;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.sliz.game.tetris.engine.ICollidable;
import info.sliz.game.tetris.engine.command.CommandPlay;
import info.sliz.game.tetris.engine.elements.playcube.FxPlayableElement;
import info.sliz.game.tetris.engine.elements.playcube.IMovable.MOVE;

public class CommandPlaySpace extends CommandPlay {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandPlayRight.class);
    
    public CommandPlaySpace(final FxPlayableElement el,final double step,final Set<ICollidable> colidate) {
        super(el, step, colidate);
    }
    @Override
    public void execute() throws MoveCommandException {
        
        LOGGER.debug("Execute command");
        while (true) {
            if (this.checkCollision()){
                element.setPlayable(false);
                return;
            }
            element.play(MOVE.FORWARD, moveStep);
        }
    }
}
