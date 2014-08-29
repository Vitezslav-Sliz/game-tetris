package info.sliz.game.tetris.engine.command;

import info.sliz.game.tetris.engine.elements.playcube.FxElement;

public abstract class CommandMove implements ICommand {

    protected FxElement el;
    protected int moveStep;
    
    public CommandMove(final FxElement el, final int step) {
        super();
        this.el = el;
        this.moveStep = step;
    }
    
    public abstract void execute() throws CommandException;
}