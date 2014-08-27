package info.sliz.game.tetris.ui.gamespace;

import javafx.scene.Group;
import javafx.scene.shape.Box;

public class DeskGenerator extends Group{
    
    public DeskGenerator(int lenght, int height, int countX, int countY, double lineWidht) {
        if (lenght <= 0 || height <= 0 || countX <= 0 || countX >= lenght || countY <= 0 || countY >= height || lineWidht <= 0 ){
            throw new IllegalArgumentException("One or more requred parrameters are invalid!");
        }
        Group line;
        //Line x generate
        for (double x = (height/2)*-1;x<=height/2;x=x+(height/countY)){
            line = new Group();
            line.setTranslateY(x);
            line.getChildren().add(new Box(lenght, lineWidht, lineWidht));
            this.getChildren().add(line);
        }
        //Line y generate
        for (double y = (lenght/2)*-1;y<=lenght/2;y=y+(lenght/countX)){
            line = new Group();
            line.setTranslateX(y);
            line.getChildren().add(new Box(lineWidht, height, lineWidht));
            this.getChildren().add(line);
        }
    }
}