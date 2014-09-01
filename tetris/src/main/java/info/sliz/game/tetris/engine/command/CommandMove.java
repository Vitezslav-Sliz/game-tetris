package info.sliz.game.tetris.engine.command;

import java.util.Observable;

import info.sliz.game.tetris.engine.elements.playcube.FxElement;

public abstract class CommandMove extends Observable implements ICommand {

    protected FxElement el;
    protected double moveStep;
    protected double moveBoundary;
    
    public CommandMove(final FxElement el, final double step, final double boundary) {
        super();
        this.el = el;
        this.moveStep = step;
        this.moveBoundary = boundary;
    }
    
    public void setElement(FxElement element) {
        this.el = element;
    }
    
    public void execute() throws CommandException{
        setChanged();
        notifyObservers();
    }

}