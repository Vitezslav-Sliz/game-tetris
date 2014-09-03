package info.sliz.game.tetris.engine.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
    	Map<Double, Integer> elByZ = new HashMap<Double, Integer>();
        for (IMovable e : elements) {
        	Integer v = elByZ.get(e.getControlPoint().getZ()); 
			if (v == null){
				elByZ.put(e.getControlPoint().getZ(), new Integer(1));
			}else{
				elByZ.put(e.getControlPoint().getZ(), new Integer(++v));
			}
		}
    	List<Object> toRemove = new ArrayList<Object>();
    	//Set<Double> toRemoveId = new HashSet<Double>();
    	for (final Entry<Double,Integer> entry : elByZ.entrySet()) {
			if (entry.getValue() == levelcount){
				Predicate<IMovable> p = new Predicate<IMovable>(){
					public boolean test(IMovable t) {
						return  t.getControlPoint().getZ() == entry.getKey();
					}
				};
				List<Object> part = this.elements.stream().filter(p).collect(Collectors.toList());
				toRemove.addAll(part);
			}
		}
        this.elements.removeAll(toRemove);
        //TODO sort elements by Z
        //TODO each element try move while by Z
        return true;
    }

    public int getScore() {
        return this.score;
    }
}
