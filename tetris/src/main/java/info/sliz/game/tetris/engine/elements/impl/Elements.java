package info.sliz.game.tetris.engine.elements.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.geometry.Point3D;
import javafx.scene.Node;
import info.sliz.game.tetris.engine.ICollidable;
import info.sliz.game.tetris.engine.elements.IColorManager;
import info.sliz.game.tetris.engine.elements.IElements;
import info.sliz.game.tetris.engine.elements.event.ElementListener;
import info.sliz.game.tetris.engine.elements.event.impl.ElementEvent;
import info.sliz.game.tetris.engine.elements.playcube.IElement;
import info.sliz.game.tetris.engine.elements.playcube.IMovable;
import info.sliz.game.tetris.engine.gamespace.impl.FxInplaceElement;

public final class Elements implements IElements, ElementListener {

    private final int elementSize;
    private final IColorManager cm;

    private final Set<FxInplaceElement> elements = new HashSet<FxInplaceElement>();

    public Elements(final int size, final IColorManager cm) {
        this.elementSize = size;
        this.cm = cm;
    }

    public void createAddInGameElement(IElement element) {
        for (Point3D point : element.getBoundaries()) {
            FxInplaceElement el = new FxInplaceElement(point, this.elementSize, this.cm.getColor(point));
            el.setEventListener(this);
            this.elements.add(el);
        }

    }

    public List<Node> getNodes() {
        return Collections.unmodifiableList(new ArrayList<Node>(elements));
    }

    public List<ICollidable> getColidable() {
        return Collections.unmodifiableList(new ArrayList<ICollidable>(elements));
    }

    public List<IMovable> getMovable() {
        return Collections.unmodifiableList(new ArrayList<IMovable>(elements));
    }

    public void removeAll(List<Object> remove) {
        this.elements.removeAll(remove);
    }

    public void elementChanged(ElementEvent e) {
        FxInplaceElement el = (FxInplaceElement) e.getSource();
        el.setColor(this.cm.getColor(el.getElementCoordinate()));
    }
}
