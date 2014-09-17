package info.sliz.game.tetris.engine.command.impl;

import java.util.Set;

import info.sliz.game.tetris.engine.ICollidable;
import info.sliz.game.tetris.engine.command.CommandPlay;
import info.sliz.game.tetris.engine.elements.playcube.FxPlayableElement;
import info.sliz.game.tetris.engine.elements.playcube.IMovable.MOVE;

public class CommandPlayAuto extends CommandPlay {

    public CommandPlayAuto(final FxPlayableElement el, final double step, final Set<ICollidable> colidate) {
        super(el, step, 0, colidate);
    }

    @Override
    public void execute() throws CommandPlayException {
        if (!this.canPlay(MOVE.FORWARD)) {
            element.setPlayable(false);
            return;
        }
        element.play(MOVE.FORWARD, moveStep);
    }
}
