package info.sliz.game.tetris.translate.impl;

import java.util.Locale;

import info.sliz.game.tetris.translate.Translate;

public class TranslateCzech extends Translate {

    public TranslateCzech() {
        super(new Locale("cs","cz"));
        this.translate.put(KEY.APP_NAME, "Hra Tetris");
    }

}
