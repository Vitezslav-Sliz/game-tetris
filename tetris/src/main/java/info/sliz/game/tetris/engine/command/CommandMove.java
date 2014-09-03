package info.sliz.game.tetris.engine.command;

import info.sliz.game.tetris.engine.elements.ICollision;
import info.sliz.game.tetris.engine.elements.playcube.IMovable;

import java.util.Set;

public abstract class CommandMove implements ICommand {


    protected IMovable element;
    protected double moveStep;
    protected final Set<ICollision> colidate;

    public CommandMove(final IMovable element, final double step, final Set<ICollision> colidate) {
        super();
        this.element = element;
        this.moveStep = step;
        this.colidate = colidate;
    }

    public void setMovableElement(IMovable element) {
        this.element = element;
    }
    protected final boolean checkCollision(){
        for (ICollision col : this.colidate) {
            if (col.Collidate(this.element)){
                return true;
            }
        }
        return false;
    }
    public abstract void execute() throws CommandException;
}