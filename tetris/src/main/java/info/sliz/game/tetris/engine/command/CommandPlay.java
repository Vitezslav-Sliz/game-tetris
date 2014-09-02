package info.sliz.game.tetris.engine.command;

import info.sliz.game.tetris.engine.elements.ICollision;
import info.sliz.game.tetris.engine.elements.playcube.FxElement;
import info.sliz.game.tetris.engine.elements.playcube.FxPlayableElement;
import info.sliz.game.tetris.engine.elements.playcube.IPlayable;

import java.util.Set;

public abstract class CommandPlay extends CommandMove {
    
    protected IPlayable el;
    public CommandPlay(final FxPlayableElement el,final double step,final Set<ICollision> colidate) {
        super(el, step, colidate);
        this.el = el;
    }

    @Override
    public void setElement(FxElement element) {
        super.setElement(element);
        el = (IPlayable) element;
    }
    public abstract void execute() throws CommandException ;
}
