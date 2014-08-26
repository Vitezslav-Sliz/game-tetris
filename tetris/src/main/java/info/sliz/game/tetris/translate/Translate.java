package info.sliz.game.tetris.translate;


import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public abstract class Translate {
    public enum KEY{
        APP_NAME;
    }
    protected Locale locale;
    protected Map<KEY, String> translate;
    
    public Translate(Locale locale) {
        this.locale = locale;
        this.translate = new HashMap<KEY, String>();
    }
    
    public Locale getLocale(){
        return this.locale;
    }
    
    public final String getTranslate(KEY key){
        return translate.get(key);
    }
}
