package info.sliz.game.tetris.engine;

import info.sliz.game.tetris.engine.command.ICommand.CommandException;

import java.util.List;

import javafx.scene.Node;

public interface IEngine {

    public abstract void callCommand(Commands command) throws CommandException;
    
    public abstract List<Node> getElements();

}