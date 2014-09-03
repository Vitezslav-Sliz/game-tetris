package info.sliz.game.tetris.engine.event;

import info.sliz.game.tetris.engine.event.impl.GameChangedEvent;

import java.util.EventListener;

public interface GameListener extends EventListener {

    void gameChanged(final GameChangedEvent e);
}
