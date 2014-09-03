package info.sliz.game.tetris.engine.elements.event.impl;

import info.sliz.game.tetris.engine.elements.playcube.FxElement;

import java.util.EventObject;

public final class ElementEvent extends EventObject {

    private static final long serialVersionUID = -27069799314247044L;
    
    public ElementEvent(FxElement source) {
        super(source);
    }
    @Override
    public final FxElement getSource() {
        return (FxElement)super.getSource();
    }
}
