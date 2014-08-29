package info.sliz.game.tetris.engine.command;

import info.sliz.game.tetris.engine.elements.playcube.FxElement;

public abstract class CommandMove implements ICommand {

    protected FxElement el;
    protected double moveStep;
    protected double moveBoundary;
    
    public CommandMove(final FxElement el, final double step, final double boundary) {
        super();
        this.el = el;
        this.moveStep = step;
        this.moveBoundary = boundary;
    }
    
    public abstract void execute() throws CommandException;
}