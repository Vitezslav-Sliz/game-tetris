package info.sliz.game.tetris.engine.impl;

import info.sliz.game.tetris.engine.IStatisticsStrategy;

public class DefaultGameStatistics implements IStatisticsStrategy {

    private int score;

    public DefaultGameStatistics() {
        this.score = 0;
    }
    
    public int getScore() {
        return this.score;
    }
}
