package info.sliz.game.tetris.translate;

import info.sliz.game.tetris.util.StringUtils;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public abstract class Translate {
    public enum KEY {
        APP_NAME;
    }

    protected Locale locale;
    protected Map<KEY, String> translate;

    public Translate(final Locale locale) {
        this.locale = locale;
        this.translate = new HashMap<KEY, String>();
    }

    public Locale getLocale() {
        return this.locale;
    }

    public final String getTranslate(final KEY key) {
        if (key == null) {
            throw new NullPointerException("Parameter 'key' should not be empty!");
        }
        String val = translate.get(key);
        if (StringUtils.isNullOrEmpty(val)) {
            return key.toString();
        } else {
            return val;
        }
    }
}
