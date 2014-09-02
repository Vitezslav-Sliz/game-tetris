package info.sliz.game.tetris.engine.command;

import info.sliz.game.tetris.engine.elements.ICollision;
import info.sliz.game.tetris.engine.elements.playcube.IMovable;

import java.util.Set;

import javafx.geometry.Point3D;

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
    protected final boolean checkCollision(Point3D point){
        for (ICollision col : this.colidate) {
            if (col.Collidate(point)){
                return true;
            }
        }
        return false;
    }
    public abstract void execute() throws CommandException;
}