package info.sliz.game.tetris.util;

public final class StringUtils {

    private StringUtils(){
        super();
    }
    
    public static boolean isNullOrEmpty(String string){
        return string == null || string.isEmpty() || string.trim().isEmpty();
    }
}
