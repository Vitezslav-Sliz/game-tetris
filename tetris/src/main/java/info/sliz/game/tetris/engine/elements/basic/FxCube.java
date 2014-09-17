package info.sliz.game.tetris.engine.elements.basic;

import info.sliz.game.tetris.config.Configuration;
import javafx.scene.Group;
import javafx.scene.paint.Color;

public abstract class FxCube extends Group {

    protected static final Color COLOR_DEFAULT = Configuration.COLOR_CUBE;

    protected final int size;

    public FxCube(final int size) {
        super();
        this.size = size;
    }

    public abstract void setColor(final Color color);
}
