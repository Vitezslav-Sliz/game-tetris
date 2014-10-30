package info.sliz.game.tetris.engine.game;

import javafx.scene.paint.Color;
import info.sliz.game.tetris.engine.IGameStrategy;
import info.sliz.game.tetris.engine.IGames;
import info.sliz.game.tetris.engine.game.impl.DefaultGameStrategy;
import info.sliz.game.tetris.engine.game.impl.DefaultNormalScoreStrategy;

public enum Games implements IGames {

    NORMAL_5X5X10(new DefaultGameStrategy(5, 10, 10, new DefaultNormalScoreStrategy(5*5), Color.YELLOW, Color.BLUE, Color.RED, Color.GREEN, Color.INDIGO, Color.CYAN, Color.MAGENTA, Color.VIOLET, Color.BEIGE)),
    NORMAL_7X7X10(new DefaultGameStrategy(7, 10, 10, new DefaultNormalScoreStrategy(7*7), Color.YELLOW, Color.BLUE, Color.RED, Color.GREEN, Color.INDIGO, Color.CYAN, Color.MAGENTA, Color.VIOLET, Color.BEIGE));

    private final IGameStrategy game;

    private Games(IGameStrategy game) {
        this.game = game;
    }

    public IGameStrategy getGame() {
        return this.game;
    }
}
