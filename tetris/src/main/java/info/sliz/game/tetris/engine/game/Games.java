package info.sliz.game.tetris.engine.game;

import info.sliz.game.tetris.engine.IGameStrategy;
import info.sliz.game.tetris.engine.IGames;
import info.sliz.game.tetris.engine.game.impl.DefaultGameStrategy;

public enum Games implements IGames{

    NORMAL_5x5x10(new DefaultGameStrategy());
    
    private final IGameStrategy game;

    private Games(IGameStrategy game) {
        this.game = game;
    }

    public IGameStrategy getGame() {
        return this.game;
    }
}
