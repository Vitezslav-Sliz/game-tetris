package info.sliz.game.tetris.engine.elements.playcube;

import java.util.Set;

import javafx.geometry.Point3D;
import javafx.scene.Group;

public abstract class FxElement extends Group implements IMovable{

    
    public FxElement(final double x, final double y, final double z,boolean playable) {
        super();
        this.setTranslateX(x);
        this.setTranslateY(y);
        this.setTranslateZ(z);
    }

    public FxElement(final double x, final double y, final double z) {
        this(x,y,z,false);
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
    }
   
    public abstract Set<Point3D> getControlPoints(); 
}