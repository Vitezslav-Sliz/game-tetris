package info.sliz.game.tetris.engine.gamespace;

import static org.junit.Assert.assertEquals;
import info.sliz.game.tetris.engine.gamespace.impl.FxDeskGenerator;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class FxDeskGeneratorSizesTest {
    private int width;
    private int height;
    private int countX;
    private int countY;
    private int result;

    public FxDeskGeneratorSizesTest(int width, int height, int countX,
            int countY) {
        super();
        this.width = width;
        this.height = height;
        this.countX = countX;
        this.countY = countY;
        this.result = (int)(width/(width/countX)+height/(height/countY)+2);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> StringText() {
       return Arrays.asList(new Object[][] {
          {100,100,10,10},
          {50,50,5,5},
          {100,50,10,10}
       });
    }
    
    @Test
    public void testDeskGenerator() {
        assertEquals(String.format("Count of assets for desk: %d X %d - %d X %d should be: %d ", width,height,countX,countY, result),result,new FxDeskGenerator(width,height, countX, countY,1).getChildren().size());
    }
}
