package info.sliz.game.tetris.engine.elements.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import info.sliz.game.tetris.engine.elements.IPlaybleElementGenerator;
import info.sliz.game.tetris.engine.elements.playcube.FxPlayableElement;
import info.sliz.game.tetris.engine.elements.playcube.impl.FxTetrisDotElement;
import info.sliz.game.tetris.engine.elements.playcube.impl.FxTetrisDoubleElement;
import info.sliz.game.tetris.engine.elements.playcube.impl.FxTetrisLElement;
import info.sliz.game.tetris.engine.elements.playcube.impl.FxTetrisbigLElement;
import info.sliz.game.tetris.engine.elements.playcube.impl.FxTetrisTElement;
import info.sliz.game.tetris.engine.elements.playcube.impl.FxTetrisTripleElement;
import info.sliz.game.tetris.engine.elements.playcube.impl.FxTetrisZElement;
import javafx.geometry.Point3D;

public class AllElementGeneratorStrategy implements IPlaybleElementGenerator {

    private final Random random;
    private final Point3D origin;
    private final int size;
    private final Map<Integer, Class<? extends FxPlayableElement>> elements = new HashMap<Integer, Class<? extends FxPlayableElement>>();

    public AllElementGeneratorStrategy(Point3D origin, int size) {
        super();
        this.origin = origin;
        this.size = size;
        this.random = new Random(System.currentTimeMillis());

        this.elements.put(this.elements.size(), FxTetrisDotElement.class);
        this.elements.put(this.elements.size(), FxTetrisDoubleElement.class);
        this.elements.put(this.elements.size(), FxTetrisTripleElement.class);
        this.elements.put(this.elements.size(), FxTetrisTElement.class);
        this.elements.put(this.elements.size(), FxTetrisZElement.class);
        this.elements.put(this.elements.size(), FxTetrisbigLElement.class);
        this.elements.put(this.elements.size(), FxTetrisLElement.class);

    }

    public FxPlayableElement generateElement() {
        try {
            return this.elements.get(this.random.nextInt(this.elements.size())).getConstructor(new Class[] { Point3D.class, int.class }).newInstance(origin, size);
        } catch (Exception e) {
            throw new GeneratorException("Problem to generate new random element", e);
        }
    }

    private final class GeneratorException extends RuntimeException {

        private static final long serialVersionUID = 4305275925381353096L;

        public GeneratorException(String string, Exception e) {
            super(string, e);
        }

    }
}