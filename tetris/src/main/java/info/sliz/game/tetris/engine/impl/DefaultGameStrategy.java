package info.sliz.game.tetris.engine.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javafx.geometry.Point3D;
import info.sliz.game.tetris.engine.IGameStrategy;
import info.sliz.game.tetris.engine.elements.playcube.IMovable;

public class DefaultGameStrategy implements IGameStrategy {

    private int score;
    private int levelcount;
    private final List<? extends IMovable> elements;
    public DefaultGameStrategy(List<? extends IMovable> elements, final int levelcount) {
        this.elements = elements;
        this.levelcount = levelcount;
        this.score = 0;
    }
    
    public boolean update() {
        Set<Double> zVal = getZValues(elements);
        
        Predicate<IMovable> t = new Predicate<IMovable>(){

            public boolean test(IMovable t) {
                return false;
            }};
            
        
        //this.elements.stream().filter(t).collect(Collectors.toList());
        return false;
    }

    public int getScore() {
        return this.score;
    }

    private static final Set<Double> getZValues(List<? extends IMovable> elements){
        Set<Double> zVal = new HashSet<Double>();
        for (IMovable m : elements) {
            Set<Point3D> p = m.getControlPoints();
            for (Point3D point3d : p) {
                zVal.add(point3d.getZ());
            }
        }
        return zVal;
    }
}
