package info.sliz.game.tetris.engine.elements.playcube;

import info.sliz.game.tetris.engine.elements.event.ElementListener;
import info.sliz.game.tetris.engine.elements.event.impl.ElementEvent;

import java.util.HashSet;
import java.util.Set;

import javafx.geometry.Point3D;
import javafx.scene.Group;

public abstract class FxMovableElement extends Group implements IMovable {
   
    private ElementListener listener;
    private final ElementEvent event;
    
    public FxMovableElement(final Point3D initial, boolean playable) {
        super();
        this.setTranslateX(initial.getX());
        this.setTranslateY(initial.getY());
        this.setTranslateZ(initial.getZ());
        this.event = new ElementEvent(this);
    }

    public FxMovableElement(final Point3D initial) {
        this(initial,false);
    }
    
    public void move(final MOVE direction, final double step) {
        switch (direction) {
        case LEFT:
            this.setTranslateX(this.getTranslateX()-step);
            break;
        case RIGHT:
            this.setTranslateX(this.getTranslateX()+step);
            break;
        case UP:
            this.setTranslateY(this.getTranslateY()-step);
            break;
        case DOWN:
            this.setTranslateY(this.getTranslateY()+step);
            break;
        case FORWARD:
            this.setTranslateZ(this.getTranslateZ()+step);
            break;
        default:
            throw new UnsupportedOperationException(String.format("Move with direction: '%s ' is not supported", direction));
        }
        this.throwEvent();
    }
   
    public Set<Point3D> getControlPoints(){
        Set<Point3D> ret = new HashSet<Point3D>(1);
        ret.add(new Point3D(this.getTranslateX(), this.getTranslateY(), this.getTranslateZ()));
        return ret;
    } 
    public final void setEventListener(final ElementListener e){
        this.listener = e;
    }
    protected final void throwEvent(){
        if (this.listener != null){
            this.listener.elementChanged(this.event);
        }
    }
}