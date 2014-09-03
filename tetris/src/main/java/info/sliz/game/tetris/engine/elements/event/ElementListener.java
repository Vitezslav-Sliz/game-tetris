package info.sliz.game.tetris.engine.elements.event;

import info.sliz.game.tetris.engine.elements.event.impl.ElementEvent;

import java.util.EventListener;

public interface ElementListener extends EventListener{
    void elementChanged(final ElementEvent e);
}
