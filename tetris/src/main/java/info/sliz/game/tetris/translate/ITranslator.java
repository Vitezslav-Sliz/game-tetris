package info.sliz.game.tetris.translate;

import java.util.Locale;

import info.sliz.game.tetris.translate.Translate.KEY;

public interface ITranslator {

    public String getTranslate(final Locale locale, final KEY key);

    public String getTranslate(final KEY key);
}
