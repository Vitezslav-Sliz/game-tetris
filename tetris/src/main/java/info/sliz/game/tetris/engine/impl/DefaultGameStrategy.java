package info.sliz.game.tetris.engine.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javafx.geometry.Point3D;
import info.sliz.game.tetris.engine.IGameStrategy;
import info.sliz.game.tetris.engine.elements.playcube.FxElement;
import info.sliz.game.tetris.engine.elements.playcube.IMovable;

public class DefaultGameStrategy implements IGameStrategy {

    private int score;
    private int levelcount;
    private final List<? extends FxElement> elements;
    public DefaultGameStrategy(List<? extends FxElement> elements, final int levelcount) {
        this.elements = elements;
        this.levelcount = levelcount;
        this.score = 0;
    }
    
    public boolean update() {
        //FIXME
        Set<Double> zVal = getZValues(elements);
        
        Predicate<FxElement> t = new Predicate<FxElement>(){

            public boolean test(FxElement t) {
                // TODO Auto-generated method stub
                t.getControlPoint();
                t.getBoundaries();
                return false;
            }};
            
        
        //this.elements.stream().filter(t).collect(Collectors.toList());
        return false;
    }

    public int getScore() {
        return this.score;
    }

    private static final Set<Double> getZValues(List<? extends FxElement> elements){
        Set<Double> zVal = new HashSet<Double>();
        for (FxElement m : elements) {
            Set<Point3D> p = m.getBoundaries();;
            for (Point3D point3d : p) {
                zVal.add(point3d.getZ());
            }
        }
        return zVal;
    }
}
