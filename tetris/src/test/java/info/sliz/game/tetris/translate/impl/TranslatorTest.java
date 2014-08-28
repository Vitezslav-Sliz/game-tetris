package info.sliz.game.tetris.translate.impl;

import static org.junit.Assert.*;
import info.sliz.game.tetris.translate.Translate.KEY;

import java.util.Locale;

import org.junit.Test;

public class TranslatorTest {

    @Test
    public void testTranslator() {
        assertNotNull(Translator.getInstance());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testGetTranslateInvalidParamNullNull() {
        assertNotNull(Translator.getInstance().getTranslate(null, null));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testGetTranslateInvalidParamValueNull() {
        assertNotNull(Translator.getInstance().getTranslate(Locale.ENGLISH, null));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testGetTranslateInvalidParamNullValue() {
        assertNotNull(Translator.getInstance().getTranslate(null, KEY.APP_NAME));
    }
    @Test(expected=IllegalArgumentException.class)
    public void testGetTranslateInvalidParamNull() {
        assertNotNull(Translator.getInstance().getTranslate(null));
    }
    
    @Test
    public void testTranslatorGetTtanslate() {
        for (KEY key : KEY.values()) {
            assertNotNull(Translator.getInstance().getTranslate(key));
            assertNotEquals(String.format("Default translation for key: '%s' not defined.", key),key.toString(), Translator.getInstance().getTranslate(key));
            
        }
    }
    @Test
    public void testTranslatorGetTtanslateEnglish() {
        for (KEY key : KEY.values()) {
            assertNotNull(Translator.getInstance().getTranslate(Locale.ENGLISH,key));
        }
    }
    
    @Test
    public void testTranslatorGetTtanslateCzech() {
        for (KEY key : KEY.values()) {
            assertNotNull(Translator.getInstance().getTranslate(TranslateCzech.LOCALE,key));
        }
    }
}
