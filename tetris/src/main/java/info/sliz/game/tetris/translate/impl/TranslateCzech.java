package info.sliz.game.tetris.translate.impl;

import java.util.Locale;

import info.sliz.game.tetris.translate.Translate;

public class TranslateCzech extends Translate {
    public static final Locale LOCALE = new Locale("cs", "cz");

    public TranslateCzech() {
        super(TranslateCzech.LOCALE);
        this.translate.put(KEY.APP_NAME, "Hra Tetris");
    }

}
