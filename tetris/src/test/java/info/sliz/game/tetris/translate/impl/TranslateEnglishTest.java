package info.sliz.game.tetris.translate.impl;

import static org.junit.Assert.*;

import java.util.Locale;

import info.sliz.game.tetris.translate.Translate;
import info.sliz.game.tetris.translate.Translate.KEY;

import org.junit.Before;
import org.junit.Test;

public class TranslateEnglishTest {

    private Translate translate;
    
    @Before
    public void setUp() throws Exception {
        translate = new TranslateEnglish();
    }
    
    @Test
    public void testTranslateEnglish() {
        assertNotNull(translate);
    }

    @Test
    public void testGetLocale() {
        assertEquals(translate.getLocale(), Locale.ENGLISH);
    }

    @Test(expected=NullPointerException.class)
    public void testGetTranslate() {
        translate.getTranslate(null);
    }

    @Test
    public void testGetTranslateAllKeys() {
        for (KEY k : KEY.values()) {
            assertNotEquals(String.format("Check translatation for key:'%s' Fail! ", k),k.toString(), translate.getTranslate(k));
        }
    }
}
