package info.sliz.game.tetris.engine.elements.playcube;

import info.sliz.game.tetris.engine.elements.event.impl.ElementEvent;
import javafx.geometry.Point3D;

public abstract class FxMovableElement extends FxElement implements IMovable {
   
    protected ElementEvent event;
    
    public FxMovableElement(final Point3D initial, boolean playable) {
        super(initial);
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
        this.throwEvent(event);
    }
}