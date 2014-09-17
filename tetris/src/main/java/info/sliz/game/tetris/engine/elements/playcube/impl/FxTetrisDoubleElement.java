package info.sliz.game.tetris.engine.elements.playcube.impl;

import javafx.geometry.Point3D;
import info.sliz.game.tetris.engine.elements.basic.FxCube;
import info.sliz.game.tetris.engine.elements.basic.impl.FxPlayCube;
import info.sliz.game.tetris.engine.elements.playcube.FxTetrisMultiElement;

public final class FxTetrisDoubleElement extends FxTetrisMultiElement {

    public FxTetrisDoubleElement(final Point3D initial, final int size) {
        super(initial);

        FxCube c = new FxPlayCube(size);
        this.getChildren().add(c);
        this.cubes.add(c);

        c = new FxPlayCube(size);
        c.setTranslateX(initial.getX() + size);
        this.getChildren().add(c);
        this.cubes.add(c);
    }
}
