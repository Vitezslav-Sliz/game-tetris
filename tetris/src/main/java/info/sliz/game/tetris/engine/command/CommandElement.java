package info.sliz.game.tetris.engine.command;

import info.sliz.game.tetris.engine.elements.playcube.FxElement;

public abstract class CommandElement implements ICommand{

    protected FxElement element;
    
    public CommandElement(final FxElement element) {
        super();
        this.element = element;
    }
    
    public void setElement(FxElement element) {
        this.element = element;
    }
    public abstract void execute() throws CommandException;
}