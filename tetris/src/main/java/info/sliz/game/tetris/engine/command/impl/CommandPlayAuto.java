package info.sliz.game.tetris.engine.command.impl;

import java.util.Set;

import info.sliz.game.tetris.engine.command.CommandPlay;
import info.sliz.game.tetris.engine.elements.ICollision;
import info.sliz.game.tetris.engine.elements.playcube.FxPlayableElement;
import info.sliz.game.tetris.engine.elements.playcube.IMovable.MOVE;

public class CommandPlayAuto extends CommandPlay {

    public CommandPlayAuto(final FxPlayableElement el,final double step,final Set<ICollision> colidate) {
        super(el, step, colidate);
    }

    
    @Override
    public void execute() throws MoveCommandException {
        if (this.checkCollision()){
            element.setPlayable(false);
            return;
        }
        element.play(MOVE.FORWARD, moveStep);
    }
}
