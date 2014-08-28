package info.sliz.game.tetris.ui.elements.basic;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class FxStaticCube extends FxCube {
    private final Box box;
    
    public FxStaticCube(final int size) {
        super(size);
        this.box = new Box(this.size,this.size,this.size);
        this.box.setMaterial(new PhongMaterial(initialColor));
        this.getChildren().add(this.box);
    }
    
    public FxStaticCube(final int size,final Color color) {
        this(size);
        this.box.setMaterial(new PhongMaterial(color));
    }

    @Override
    public void setColor(Color color) {
        this.box.setMaterial(new PhongMaterial(color));
    }
}