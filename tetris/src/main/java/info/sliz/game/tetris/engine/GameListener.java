package info.sliz.game.tetris.engine;

import java.util.EventListener;

public interface GameListener extends EventListener {

    void gameChanged(final GameChangedEvent e);
}
