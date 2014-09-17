package info.sliz.game.tetris.engine.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.sliz.game.tetris.engine.event.GameListener;
import info.sliz.game.tetris.engine.event.impl.GameChangedEvent;
import info.sliz.game.tetris.engine.game.impl.GameRunner;

public abstract class AbstractGameStrategy {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractGameStrategy.class);
    private final GameChangedEvent e;
    private GameListener listener;
    protected final GameRunner runner;

    public AbstractGameStrategy(final int initialspeed) {
        this.e = new GameChangedEvent(this);
        this.runner = new GameRunner(initialspeed);
    }

    public void setGameListener(final GameListener listener) {
        this.listener = listener;
    }

    protected void gameChanged() {
        if (this.listener != null) {
            LOGGER.debug("Listener is exist - running event trigger");
            this.listener.gameChanged(this.e);
        }
    }

    public void stopGame() {
        this.runner.stopRunner();
    }

    public void startGame() {
        this.runner.start();
    }

    public boolean isRunning() {
        return this.runner.isAlive();
    }
}