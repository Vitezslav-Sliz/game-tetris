package info.sliz.game.tetris.engine.game.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.sliz.game.tetris.engine.game.IGameScoreStrategy;

public class DefaultNormalScoreStrategy implements IGameScoreStrategy {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultNormalScoreStrategy.class);
    private final long coeficient;
    private final long initialTime; 
    
    public DefaultNormalScoreStrategy(final long coeficient){
        this.coeficient = coeficient;
        this.initialTime = 2000;
        LOGGER.debug("Initial Game speed is: "+ this.initialTime);
    }
    public long getSpeedCoeficient(long score) {
        long speed = this.initialTime - (score*500)/1000;
        LOGGER.debug(String.format("Game speed for score %s is %s",String.valueOf(score),String.valueOf(speed)));
        return speed; 
    }

    public long getScoreCoeficient(long gamecubes) {
        int l = (int)(gamecubes / coeficient);
        switch (l) {
        case 1:
            return 100;
        case 2:
            return 300;            
        case 3:
            return 500;
        case 4: 
            return 800;
        default:
            return 100;
        }
    }
}
