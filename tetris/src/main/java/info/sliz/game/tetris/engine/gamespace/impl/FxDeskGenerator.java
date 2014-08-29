package info.sliz.game.tetris.engine.gamespace.impl;

import info.sliz.game.tetris.config.Configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class FxDeskGenerator extends Group{
    private static final Logger LOGGER = LoggerFactory.getLogger(FxDeskGenerator.class);
    private static final Color COLOR_LINE = Configuration.COLOR_SPACE;
    
    public FxDeskGenerator(final int lenght, final int height, final int countX,final  int countY, final double lineWidht) {
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
        LOGGER.debug(String.format("Generate Game desc: %d X %d sizes: %d X %d line: %f", lenght,height,countX,countY,lineWidht));
        this.generateDesk(lenght, height, countX, countY, lineWidht);
    }
    private void generateDesk(int lenght, int height, int countX, int countY, double lineWidht){
        Group line;
        //Line x generate
        for (double x = (height/2)*-1;x<=height/2;x=x+(height/countY)){
            line = new Group();
            line.setTranslateY(x);
            Box b = new Box(lenght, lineWidht, lineWidht);
            b.setMaterial(new PhongMaterial(COLOR_LINE));
            line.getChildren().add(b);
            this.getChildren().add(line);
        }
        //Line y generate
        for (double y = (lenght/2)*-1;y<=lenght/2;y=y+(lenght/countX)){
            line = new Group();
            line.setTranslateX(y);
            Box b = new Box(lineWidht, height, lineWidht);
            b.setMaterial(new PhongMaterial(COLOR_LINE));
            line.getChildren().add(b);
            this.getChildren().add(line);
        }
    }
}