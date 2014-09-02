package info.sliz.game.tetris.engine.command;

import info.sliz.game.tetris.engine.elements.ICollision;
import info.sliz.game.tetris.engine.elements.playcube.IPlayable;

import java.util.Set;

public abstract class CommandPlay extends CommandMove {
    
    protected IPlayable element;
    public CommandPlay(final IPlayable element,final double step,final Set<ICollision> colidate) {
        super(element, step, colidate);
        this.element = element;
    }

    public void setPlayableElement(IPlayable element) {
        this.element = element;
    }
    public abstract void execute() throws CommandException ;
}
