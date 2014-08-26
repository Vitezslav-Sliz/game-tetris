package info.sliz.game.tetris.translate.impl;

import static org.junit.Assert.*;
import info.sliz.game.tetris.translate.Translate;
import info.sliz.game.tetris.translate.Translate.KEY;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class TranslateCzechTest {

    private Translate translate;
    
    @Before
    public void setUp() throws Exception {
        translate = new TranslateCzech();
    }
    
    @Test
    public void testTranslateCzech() {
        assertNotNull(translate);
    }

    @Test
    public void testGetLocale() {
        assertNotNull(translate.getLocale());
    }

    @Test(expected=NullPointerException.class)    
    public void testGetTranslate() {
       translate.getTranslate(null);
    }
    
    @Test
    @Ignore("not ready yet")
    public void testGetTranslateAllKeys() {
        for (KEY k : KEY.values()) {
            assertNotEquals(String.format("Check translatation for key:'%s' fail", k),k.toString(), translate.getTranslate(k));
        }
    }
}
