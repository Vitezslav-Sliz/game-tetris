package info.sliz.game.tetris.engine.elements.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import info.sliz.game.tetris.engine.elements.IPlaybleElementGenerator;
import info.sliz.game.tetris.engine.elements.playcube.FxPlayableElement;
import info.sliz.game.tetris.engine.elements.playcube.impl.FxDotElement;
import info.sliz.game.tetris.engine.elements.playcube.impl.FxDoubleElement;
import info.sliz.game.tetris.engine.elements.playcube.impl.FxTripleElement;
import javafx.geometry.Point3D;

public class RandomFxPlayableElementGenerator implements IPlaybleElementGenerator {
	
	private final Random random;
	private final Point3D origin;
	private final int size;
	private final Map<Integer,Class<? extends FxPlayableElement>> elements = new HashMap<Integer,Class<? extends FxPlayableElement>>();
	
	public RandomFxPlayableElementGenerator(Point3D origin, int size){
		super();
		this.origin = origin;
		this.size = size;
		this.random = new Random(System.currentTimeMillis());
		
		this.elements.put(this.elements.size(),FxDotElement.class);
		this.elements.put(this.elements.size(),FxDoubleElement.class);
		this.elements.put(this.elements.size(),FxTripleElement.class);
		
	}

	public FxPlayableElement generateElement() {
		try {
			return this.elements.get(this.random.nextInt(this.elements.size())).getConstructor(new Class[]{Point3D.class,int.class}).newInstance(origin,size);
		} catch (Exception e) {
			throw new RuntimeException("Problem to generate new random element",e);
		}
	}
}