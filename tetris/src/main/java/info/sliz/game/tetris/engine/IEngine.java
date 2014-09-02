package info.sliz.game.tetris.engine;

import info.sliz.game.tetris.engine.command.ICommand;

import java.util.List;

import javafx.scene.Node;

public interface IEngine {

    public abstract ICommand getCommand(Class<? extends ICommand> command);

    public abstract List<Node> getElements();

}