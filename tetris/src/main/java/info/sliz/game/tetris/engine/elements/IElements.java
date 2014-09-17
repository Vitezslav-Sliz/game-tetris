package info.sliz.game.tetris.engine.elements;

import java.util.List;

import info.sliz.game.tetris.engine.ICollidable;
import info.sliz.game.tetris.engine.elements.playcube.IElement;
import info.sliz.game.tetris.engine.elements.playcube.IMovable;
import javafx.scene.Node;

public interface IElements {

    public void createAddInGameElement(final IElement element);

    public List<Node> getNodes();

    public List<ICollidable> getColidable();

    public List<IMovable> getMovable();

    public void removeAll(List<Object> remove);
}
