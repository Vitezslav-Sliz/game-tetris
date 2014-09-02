package info.sliz.game.tetris.engine.command;

import info.sliz.game.tetris.engine.elements.ICollision;
import info.sliz.game.tetris.engine.elements.playcube.FxElement;

import java.util.Set;

import javafx.geometry.Point3D;

public abstract class CommandMove extends CommandElement implements ICommand {

    protected double moveStep;
    protected final Set<ICollision> colidate;

    public CommandMove(final FxElement el, final double step, final Set<ICollision> colidate) {
        super(el);
        this.moveStep = step;
        this.colidate = colidate;
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