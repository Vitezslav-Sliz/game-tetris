package info.sliz.game.tetris.engine;

import info.sliz.game.tetris.engine.command.ICommand;
import info.sliz.game.tetris.engine.event.GameListener;

import java.util.List;
import java.util.Set;

import javafx.scene.Node;

public interface IGameStrategy {

    public List<Node> getElements();

    public Set<ICommand> getCommands();

    public boolean isRunning();

    public void startGame();

    public void stopGame();

    public void setGameListener(GameListener listener);

}
