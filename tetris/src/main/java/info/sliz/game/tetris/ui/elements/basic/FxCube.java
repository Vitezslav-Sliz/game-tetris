package info.sliz.game.tetris.ui.elements.basic;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public abstract class FxCube extends Group {

    protected final Color initialColor = Color.DARKGRAY;
    
    protected final int size;
    
    public FxCube(final int size) {
        super();
        this.size = size;
    }
    
    abstract public void setColor(final Color color);
}
