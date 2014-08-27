package info.sliz.game.tetris.ui.gamespace;

import org.junit.Before;
import org.junit.Test;

public class DeskGeneratorTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testDeskGenerator() {
        new DeskGenerator(100, 100, 10, 10, 1);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testDeskGeneratorInvalid() {
        new DeskGenerator(-50, 50, 100, 100, 1);
    }

}
