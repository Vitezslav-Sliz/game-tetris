package info.sliz.game.tetris.engine.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.sliz.game.tetris.engine.event.GameListener;
import info.sliz.game.tetris.engine.event.impl.GameChangedEvent;

public abstract class AbstractStrategy {
    static final Logger LOGGER = LoggerFactory.getLogger(AbstractStrategy.class);
    protected final GameChangedEvent e;
    protected GameListener listener;

    public AbstractStrategy() {
        this.e = new GameChangedEvent(this);
    }
    
    protected void gameChanged(){
        if(this.listener != null){
            LOGGER.debug("Listener is exist - running event trigger");
            this.listener.gameChanged(this.e);
        }
    }
}