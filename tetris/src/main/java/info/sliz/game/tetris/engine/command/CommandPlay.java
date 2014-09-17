package info.sliz.game.tetris.engine.command;

import info.sliz.game.tetris.engine.ICollidable;
import info.sliz.game.tetris.engine.elements.playcube.IPlayable;
import info.sliz.game.tetris.engine.elements.playcube.IMovable.MOVE;
import info.sliz.game.tetris.engine.elements.playcube.IRotable.ROTATE;

import java.util.Set;

public abstract class CommandPlay implements ICommand {

    protected IPlayable element;
    protected double moveStep;
    protected double angleStep;
    protected final Set<ICollidable> colidate;

    private CommandPlay(final IPlayable element, final Set<ICollidable> colidate) {
        this.element = element;
        this.colidate = colidate;
    }

    public CommandPlay(final IPlayable element, final double step, final double angle, final Set<ICollidable> colidate) {
        this(element, colidate);
        this.moveStep = step;
        this.angleStep = angle;
    }

    protected final boolean canPlay(final MOVE direction) {
        if (this.element.isPlayable()) {
            return this.element.canPlay(direction, moveStep, this.colidate);
        } else {
            return false;
        }
    }

    protected final boolean canPlay(final ROTATE axis) {
        if (this.element.isPlayable()) {
            return this.element.canRotate(axis, angleStep, this.colidate);
        } else {
            return false;
        }
    }

    public abstract void execute() throws CommandException;

    public class CommandPlayException extends CommandException {

        private static final long serialVersionUID = 518827425380469331L;

        public CommandPlayException(String message) {
            super(message);
        }
    }
}
