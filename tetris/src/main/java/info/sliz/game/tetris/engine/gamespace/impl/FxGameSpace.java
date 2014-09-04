package info.sliz.game.tetris.engine.gamespace.impl;

import info.sliz.game.tetris.engine.ICollidable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.transform.Rotate;

public class FxGameSpace extends Group implements ICollidable {
    private static final Logger LOGGER = LoggerFactory.getLogger(FxGameSpace.class);
    private final double boundaryX;
    private final double boundaryY;
    private final double boundaryZ;

    public FxGameSpace(final int lenghtAndHeight, final int depth, final int sizeSquare, final double lineWidthSquare) {
        LOGGER.debug(String.format(
                "Start Generate Game Space: %d X %d X %d size: %d line: %f",
                lenghtAndHeight, lenghtAndHeight, depth, sizeSquare,
                lineWidthSquare));
        this.getChildren().add(
                new FxDeskGenerator(lenghtAndHeight * sizeSquare,
                        lenghtAndHeight * sizeSquare, lenghtAndHeight,
                        lenghtAndHeight, lineWidthSquare));

        for (int i = 0; i < 4; i++) {
            Group des = new Group();
            Rotate r2 = new Rotate();
            r2.setAxis(Rotate.Z_AXIS);
            r2.setAngle(i * 90);
            des.getTransforms().add(r2);
            des.getChildren().add(
                    createGroup(lenghtAndHeight * sizeSquare, depth
                            * sizeSquare, lenghtAndHeight, depth,
                            lineWidthSquare));
            this.getChildren().add(des);
        }
        boundaryX = lenghtAndHeight * sizeSquare / 2;
        boundaryY = lenghtAndHeight * sizeSquare / 2;
        boundaryZ = depth / 2;
        LOGGER.debug(String
                .format("Boundary for gamespace are X[%.3f,-%.3f] Y[%.3f,-%.3f] Z[%.3f]",
                        boundaryX, boundaryX, boundaryY, boundaryY, boundaryZ));
    }

    private static Group createGroup(final int lenght, final int depth,
            final int countX, final int countY, final double lineWidht) {
        Group group = new Group();
        Rotate r = new Rotate();
        r.setAxis(Rotate.X_AXIS);
        r.setAngle(90);
        group.getTransforms().add(r);
        group.setTranslateY(lenght / 2);
        group.setTranslateZ(-depth / 2);
        LOGGER.debug(String.format("Set Translate for Game desc: [%d,%d,%d]",
                0, lenght / 2, -depth / 2));
        group.getChildren().add(
                new FxDeskGenerator(lenght, depth, countX, countY, lineWidht));
        return group;
    }

    public boolean Collidate(Point3D point) {
        LOGGER.debug("Check collision with: " + point);
        if ((point.getX()) <= -boundaryX || (point.getX()) >= boundaryX) {
            return true;
        }
        if ((point.getY()) <= -boundaryY || (point.getY()) >= boundaryY) {
            return true;
        }
        if ((point.getZ()) > -boundaryZ) {
            return true;
        }
        return false;
    }
}