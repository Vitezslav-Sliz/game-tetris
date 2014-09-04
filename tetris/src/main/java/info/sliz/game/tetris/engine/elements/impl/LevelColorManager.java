package info.sliz.game.tetris.engine.elements.impl;

import info.sliz.game.tetris.engine.elements.IColorManager;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;

public final class LevelColorManager implements IColorManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(LevelColorManager.class);
    private Map<Double,Color> colors = new HashMap<Double, Color>();

    public LevelColorManager(final double sizeSquare,final double step,Color... colors) {
        double val = -sizeSquare/2;
        for (Color color : colors) {
            this.colors.put(val, color);
            val = val - step;
        }
        LOGGER.debug("Defined colors in levels:"+this.colors);
    }

    public Color getColor(Point3D point) {
        return this.colors.get(point.getZ());
    }
}
