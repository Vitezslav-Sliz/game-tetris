package info.sliz.game.tetris.ui.gamespace;

import javafx.scene.Group;
import javafx.scene.shape.Box;

public class DeskGenerator extends Group{
    
    public DeskGenerator(int lenght, int height, int countX, int countY, double lineWidht) {
        if (lenght <= 0 || height <= 0){
            throw new IllegalArgumentException("lenght or height are invalid!");
        }
        if (countX <= 0 || countX >= lenght){
            throw new IllegalArgumentException("Number of squares for X is invalid!");
        }
        if (countY <= 0 || countY >= height ){
            throw new IllegalArgumentException("Number of squares for Y is invalid!");
        }
        if (lineWidht <= 0 ){
            throw new IllegalArgumentException("Line height is invalid!");
        }
        this.generateDesk(lenght, height, countX, countY, lineWidht);
    }
    private void generateDesk(int lenght, int height, int countX, int countY, double lineWidht){
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