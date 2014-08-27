package info.sliz.game.tetris.ui.gamespace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.scene.Group;
import javafx.scene.transform.Rotate;

public class FxGameSpace extends Group{
    private final Logger logger = LoggerFactory.getLogger(FxGameSpace.class);
    
    public FxGameSpace(final int lenghtAndHeight,final int depth,final int sizeSquare,final double lineWidthSquare) {
        logger.info(String.format("Start Generate Game Space: %d X %d X %d size: %d line: %f",lenghtAndHeight,lenghtAndHeight,depth,sizeSquare,lineWidthSquare));
        this.getChildren().add(new FxDeskGenerator(lenghtAndHeight * sizeSquare, lenghtAndHeight * sizeSquare, lenghtAndHeight, lenghtAndHeight,lineWidthSquare));
        
        for (int i = 0; i< 4; i++){
            Group des = new Group();
            Rotate r2 = new Rotate();
            r2.setAxis(Rotate.Z_AXIS);
            r2.setAngle(i*90);
            des.getTransforms().add(r2);
            des.getChildren().add(createGroup(lenghtAndHeight*sizeSquare, depth*sizeSquare, lenghtAndHeight, depth, lineWidthSquare));
            this.getChildren().add(des);
        }
    }
    private static Group createGroup(final int lenght, final int height, final int countX,final  int countY, final double lineWidht){
        Group group = new Group();
        Rotate r = new Rotate();
        r.setAxis(Rotate.X_AXIS);
        r.setAngle(90);
        group.getTransforms().add(r);
        group.setTranslateY(lenght/2);
        group.setTranslateZ(-height/2);
        group.getChildren().add(new FxDeskGenerator(lenght, height, countX, countY,lineWidht));
        return group;
    }
}