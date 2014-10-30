package info.sliz.game.tetris.engine.game;

public interface IGameScoreStrategy {

    public long getSpeedCoeficient(long score);
    
    public long getScoreCoeficient(long gamecubes);
}
