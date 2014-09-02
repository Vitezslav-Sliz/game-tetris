package info.sliz.game.tetris.engine.elements.playcube;

import java.util.EventObject;

public class ElementChanged extends EventObject {

    private static final long serialVersionUID = -27069799314247044L;
    
    public ElementChanged(FxElement source) {
        super(source);
    }
}
