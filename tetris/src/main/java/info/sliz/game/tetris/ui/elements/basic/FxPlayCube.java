package info.sliz.game.tetris.ui.elements.basic;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class FxPlayCube extends FxCube {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(FxPlayCube.class);
    
    private static final Color COLOR_BORDER = Color.WHITE;
    private static final double ASPECT = 25.0;
    
    private final List<Box> lines = new ArrayList<Box>();
    
    public FxPlayCube(int size) {
        super(size);
        final double trans = (this.size/2.0)-(this.size/ASPECT/2.0);
        final double aspect = this.size/ASPECT;
        LOGGER.debug(String.format("Defined values for Border of Play Cube - translation: %f and aspect size: %f ", trans,aspect));

        double boxSize = this.size-aspect;
        Box n = new Box(boxSize,boxSize,boxSize);
        n.setMaterial(new PhongMaterial(FxCube.COLOR_DEFAULT));
        this.getChildren().add(n);        
        
        addLine(size, aspect, aspect, 0, trans, trans);
        addLine(size, aspect, aspect, 0, -trans, -trans);
        addLine(size, aspect, aspect, 0, trans, -trans);
        addLine(size, aspect, aspect, 0, -trans, trans);
        
        addLine(aspect, size, aspect, trans, 0 , trans);
        addLine(aspect, size, aspect, -trans, 0 , -trans);
        addLine(aspect, size, aspect, trans, 0 , -trans);
        addLine(aspect, size, aspect, -trans, 0 , trans);
        
        addLine(aspect, aspect, size, trans, trans, 0);
        addLine(aspect, aspect, size, -trans, -trans, 0 );
        addLine(aspect, aspect, size, trans, -trans, 0);
        addLine(aspect, aspect, size, -trans, trans, 0);
        
      
    }

    @Override
    public void setColor(Color color) {
        
    }
    
    private final void addLine(final double sizeX,final double sizeY,final double sizeZ,final double translX,final double translY,final double translZ){
        final Group line = new Group();
        line.setTranslateX(translX);
        line.setTranslateY(translY);
        line.setTranslateZ(translZ);
        Box n = new Box(sizeX, sizeY, sizeZ);
        n.setMaterial(new PhongMaterial(FxPlayCube.COLOR_BORDER));
        this.lines.add(n);
        line.getChildren().add(n);
        this.getChildren().add(line);
    }
}
