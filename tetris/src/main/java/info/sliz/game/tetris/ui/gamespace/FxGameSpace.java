package info.sliz.game.tetris.ui.gamespace;

import javafx.scene.Group;
import javafx.scene.transform.Rotate;

public class FxGameSpace extends Group{
    
    public FxGameSpace(final int lenghtAndHeight,final int depth,final int sizeSquare,final double lineWidthSquare) {
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
    private Group createGroup(final int lenght, final int height, final int countX,final  int countY, final double lineWidht){
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