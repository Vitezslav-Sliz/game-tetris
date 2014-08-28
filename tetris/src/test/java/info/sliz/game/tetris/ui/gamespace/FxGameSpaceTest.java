package info.sliz.game.tetris.ui.gamespace;

import static org.junit.Assert.*;
import info.sliz.game.tetris.ui.gamespace.FxGameSpace;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class FxGameSpaceTest {

    private int lenghtAndHeight;
    private int depth;
    private int sizeSquare;
    private double lineWidthSquare;
    
    
    public FxGameSpaceTest(int lenghtAndHeight, int depth, int sizeSquare,
            double lineWidthSquare) {
        super();
        this.lenghtAndHeight = lenghtAndHeight;
        this.depth = depth;
        this.sizeSquare = sizeSquare;
        this.lineWidthSquare = lineWidthSquare;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> StringText() {
       return Arrays.asList(new Object[][] {
          { 3, 10,10, 0.5 },
          { 5, 10,10, 0.1 },
          { 7, 10,10, 0.9 },
          { 9, 10,10, 1.0 },
       });
    }

    @Test
    public void testFxGameSpace() {
        FxGameSpace space = new FxGameSpace(lenghtAndHeight, depth, sizeSquare, lineWidthSquare);
        assertNotNull(space);
        assertEquals("Number of gamespace desks not match" ,5 ,space.getChildren().size());
    }

}
