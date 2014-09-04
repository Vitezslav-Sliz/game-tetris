package info.sliz.game.tetris.engine.command;

import info.sliz.game.tetris.engine.ICollidable;
import info.sliz.game.tetris.engine.elements.playcube.IMovable;
import info.sliz.game.tetris.engine.elements.playcube.IMovable.MOVE;

import java.util.Set;

public abstract class CommandMove implements ICommand {

	protected IMovable element;
	protected double moveStep;
	protected final Set<ICollidable> colidate;

	public CommandMove(final IMovable element, final double step,
			final Set<ICollidable> colidate) {
		super();
		this.element = element;
		this.moveStep = step;
		this.colidate = colidate;
	}

	protected final boolean canMove(final MOVE direction) {
		for (ICollidable col : this.colidate) {
			if (!this.element.canMove(direction, moveStep, col)) {
				return false;
			}
		}
		return true;
	}

	public abstract void execute() throws CommandException;

	public class MoveCommandException extends CommandException {

		private static final long serialVersionUID = 518827421380469331L;

		public MoveCommandException(String message) {
			super(message);
		}
	}
}