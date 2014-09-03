package info.sliz.game.tetris.engine.elements.playcube;

import java.util.HashSet;
import java.util.Set;

import info.sliz.game.tetris.engine.elements.event.ElementListener;
import info.sliz.game.tetris.engine.elements.event.impl.ElementEvent;
import javafx.geometry.Point3D;
import javafx.scene.Group;

public abstract class FxElement extends Group {

    protected ElementListener listener;

    public FxElement(Point3D initial) {
        
        this.setTranslateX(initial.getX());
        this.setTranslateY(initial.getY());
        this.setTranslateZ(initial.getZ());
    }

    public final void setEventListener(final ElementListener e){
        this.listener = e;
    }
    
    protected final void throwEvent(final ElementEvent e){
        if (this.listener != null){
            this.listener.elementChanged(e);
        }
    }
    
    public Point3D getControlPoint(){
        return new Point3D(this.getTranslateX(), this.getTranslateY(), this.getTranslateZ());
    }
    
    public Set<Point3D> getBoundaries() {
        Set<Point3D> ret = new HashSet<Point3D>(1); 
        ret.add(new Point3D(this.getTranslateX(),this.getTranslateY(),this.getTranslateZ()));
        return ret;
    }
}