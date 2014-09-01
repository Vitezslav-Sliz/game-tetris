package info.sliz.game.tetris.engine.command;

import java.util.Observable;

import info.sliz.game.tetris.engine.elements.playcube.FxPlayableElement;

public abstract class CommandPlay extends Observable implements ICommand {

    protected FxPlayableElement el;
    protected double moveStep;
    protected double moveBoundary;
    
    public CommandPlay(final FxPlayableElement el, final double step, final double boundary) {
        super();
        this.el = el;
        this.moveStep = step;
        this.moveBoundary = boundary;
    }
    
    public void setElement(FxPlayableElement element) {
        this.el = element;
    }
    
    public void execute() throws CommandException{
        setChanged();
        notifyObservers();
    }

}