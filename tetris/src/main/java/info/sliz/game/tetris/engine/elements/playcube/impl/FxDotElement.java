package info.sliz.game.tetris.engine.elements.playcube.impl;

import java.util.HashSet;
import java.util.Set;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import info.sliz.game.tetris.config.Configuration;
import info.sliz.game.tetris.engine.elements.basic.FxCube;
import info.sliz.game.tetris.engine.elements.basic.impl.FxPlayCube;
import info.sliz.game.tetris.engine.elements.basic.impl.FxStaticCube;
import info.sliz.game.tetris.engine.elements.playcube.FxElement;

public class FxDotElement extends FxElement {
    private FxCube cube;
    private final int size;
    private Color color = Configuration.COLOR_CUBE;

    public FxDotElement(final double x, final double y, final double z,final int size, final boolean playable) {
        super(playable);
        this.size = size;
        if (playable){
            this.cube = new FxPlayCube(this.size);
        }else{
            this.cube = new FxStaticCube(this.size);
            this.cube.setColor(color);
        }
        this.setTranslateX(x);
        this.setTranslateY(y);
        this.setTranslateZ(z);
        this.getChildren().add(cube);
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

    public void play(MOVE direction, double step) {
        if (playable){
            this.move(direction, step);            
        }else{
            throw new NotPlayableException("Element is not playable");
        }
        
    }
    
    public void setColor(final Color color){
        this.cube.setColor(color);
    }
    @Override
    public void setPlayable(boolean play) {
        if (play != this.playable){
            this.getChildren().remove(cube);
            if (play){
                this.cube = new FxPlayCube(this.size); 
                this.getChildren().add(cube);
            }else{
                this.cube = new FxStaticCube(this.size); 
                this.getChildren().add(cube);
            }
        }
        super.setPlayable(play);
    }
    @Override
    public Set<Point3D> getControlPoints() {
        Set<Point3D> ret = new HashSet<Point3D>(1); 
        ret.add(new Point3D(this.getTranslateX(),this.getTranslateY(),this.getTranslateZ()));
        return ret;
    }
    
    @Override
    public String toString() {
        return String.format("%s[%.3f|%.3f|%.3f]@%s",getClass().getSimpleName(),this.getTranslateX(),this.getTranslateY(),getTranslateZ(),Integer.toHexString(hashCode()));
    }

}
