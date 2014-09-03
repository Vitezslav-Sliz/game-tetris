package info.sliz.game.tetris.engine.elements.event.impl;

import info.sliz.game.tetris.engine.elements.playcube.FxMovableElement;

import java.util.EventObject;

public final class ElementEvent extends EventObject {

    private static final long serialVersionUID = -27069799314247044L;
    
    public ElementEvent(FxMovableElement source) {
        super(source);
    }
    @Override
    public final FxMovableElement getSource() {
        return (FxMovableElement)super.getSource();
    }
}
