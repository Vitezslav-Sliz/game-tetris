package info.sliz.game.tetris.engine.elements.playcube.impl;

import javafx.geometry.Point3D;
import info.sliz.game.tetris.engine.elements.basic.FxCube;
import info.sliz.game.tetris.engine.elements.basic.impl.FxPlayCube;
import info.sliz.game.tetris.engine.elements.playcube.FxTetrisMultiElement;

public final class FxTetrisTElement extends FxTetrisMultiElement {

    public FxTetrisTElement(final Point3D initial, final int size) {
        super(initial);

        FxCube c = new FxPlayCube(size);
        this.getChildren().add(c);

        c = new FxPlayCube(size);
        c.setTranslateX(size);
        this.getChildren().add(c);
        this.cubes.add(c);

        c = new FxPlayCube(size);
        c.setTranslateX(-size);
        this.getChildren().add(c);
        this.cubes.add(c);

        c = new FxPlayCube(size);
        c.setTranslateY(-size);
        this.getChildren().add(c);
        this.cubes.add(c);

    }
}
