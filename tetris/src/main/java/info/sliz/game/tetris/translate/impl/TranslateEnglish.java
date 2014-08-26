package info.sliz.game.tetris.translate.impl;

import java.util.Locale;

import info.sliz.game.tetris.translate.Translate;

public final class TranslateEnglish extends Translate {

    public TranslateEnglish() {
        super(Locale.ENGLISH);
        this.translate.put(KEY.APP_NAME, "Tetris the Game");
    }

}
