package info.sliz.game.tetris.translate.impl;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import info.sliz.game.tetris.translate.ITranslator;
import info.sliz.game.tetris.translate.Translate;
import info.sliz.game.tetris.translate.Translate.KEY;
import info.sliz.game.tetris.util.StringUtils;

public final class Translator implements ITranslator {

    private static ITranslator translator;

    private final Languages languages;

    private Translator() {
        super();
        this.languages = new Languages();
        this.languages.setDefaultTranslate(new TranslateEnglish());
        this.languages.addTranslate(new TranslateCzech());

    }

    public static ITranslator getInstance() {
        if (Translator.translator != null) {
            return Translator.translator;
        } else {
            Translator.translator = new Translator();
            return Translator.translator;
        }
    }

    public String getTranslate(final Locale locale, final KEY key) {
        if (locale == null || key == null) {
            throw new IllegalArgumentException("One of required parrameters is missing!");
        }
        String value = this.languages.getTranslate(locale).getTranslate(key);
        if (StringUtils.isNullOrEmpty(value)) {
            return key.toString();
        } else {
            return value;
        }
    }

    public String getTranslate(KEY key) {
        if (key == null) {
            throw new IllegalArgumentException("key parrameter is missing!");
        }
        String value = this.languages.getTranslate().getTranslate(key);
        if (StringUtils.isNullOrEmpty(value)) {
            return key.toString();
        } else {
            return value;
        }
    }

    private class Languages {
        private final Map<Locale, Translate> languages = new HashMap<Locale, Translate>();
        private Locale def;

        private void setDefaultTranslate(final Translate t) {
            def = t.getLocale();
            addTranslate(t);
        }

        private void addTranslate(final Translate t) {
            this.languages.put(t.getLocale(), t);
        }

        private Translate getTranslate(final Locale locale) {
            if (this.languages.get(locale) == null) {
                if (this.languages.get(def) == null) {
                    return new Translate(null) {
                    };
                } else {
                    return this.languages.get(def);
                }
            } else {
                return this.languages.get(locale);
            }
        }

        private Translate getTranslate() {
            return this.getTranslate(def);
        }
    }
}
