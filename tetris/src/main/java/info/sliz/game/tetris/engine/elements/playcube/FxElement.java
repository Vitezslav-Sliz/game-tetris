package info.sliz.game.tetris.engine.elements.playcube;

import java.util.Set;

import info.sliz.game.tetris.engine.elements.event.ElementListener;
import info.sliz.game.tetris.engine.elements.event.impl.ElementEvent;
import javafx.geometry.Point3D;
import javafx.scene.Group;

public abstract class FxElement extends Group {

    protected ElementListener listener;
    protected final ElementEvent event;

    public FxElement(Point3D initial) {

        this.setTranslateX(initial.getX());
        this.setTranslateY(initial.getY());
        this.setTranslateZ(initial.getZ());
        this.event = new ElementEvent(this);
    }

    public final void setEventListener(final ElementListener e) {
        this.listener = e;
    }

    protected final void throwEvent(final ElementEvent e) {
        if (this.listener != null) {
            this.listener.elementChanged(e);
        }
    }

    public abstract Point3D getElementCoordinate();

    public abstract Set<Point3D> getBoundaries();

}