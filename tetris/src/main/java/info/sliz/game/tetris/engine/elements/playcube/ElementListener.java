package info.sliz.game.tetris.engine.elements.playcube;

import java.util.EventListener;

public interface ElementListener extends EventListener{
    void elementChanged(final ElementChanged e);
}
