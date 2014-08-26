package info.sliz.game.tetris.util;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


@RunWith(Parameterized.class)
public class StringUtilsTest {
    private String input;
    private boolean result;
    
    public StringUtilsTest(String input, boolean result) {
        super();
        this.input = input;
        this.result = result;
    }
    @Parameterized.Parameters
    public static Collection<Object[]> StringText() {
       return Arrays.asList(new Object[][] {
          { "", true },
          { null, true },
          { "   ", true },
          { new String(), true },
          { " a ", false },
          { "a b", false },
          { "123", false },
          { "1a3", false },
          { "TestString", false },
          { "^&asd1", false }
       });
    }

    @Test
    public void testIsNullOrEmpty() {
        System.out.println(String.format("Unput String is :'%s'", input));
        assertEquals(result, StringUtils.isNullOrEmpty(input)); 
    }
}
