package info.sliz.game.tetris.ui.graphic.control;

import info.sliz.game.tetris.engine.command.impl.CommandPlayRight;
import info.sliz.game.tetris.engine.impl.CoreEngine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyboardControl implements EventHandler<KeyEvent> {

    private CoreEngine eng;
    public KeyboardControl(final CoreEngine engine) {
        this.eng = engine;
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(KeyboardControl.class);

    public void handle(KeyEvent event) {
        LOGGER.debug("Pressed: " + event.getCode());
        switch (event.getCode()) {
        case X:
            //cube.move(IMovable.MOVE.FORWARD, 10);
            break;
        case LEFT:
            //cube.play(IMovable.MOVE.LEFT, 10);
            break;
        case RIGHT:
            this.eng.getCommand(CommandPlayRight.class).Execute();
            break;
        case UP:
            //cube.play(IMovable.MOVE.UP, 10);
            break;
        case DOWN:
            //cube.play(IMovable.MOVE.DOWN, 10);
            break;
        case S:
            //cube.setPlayable(!cube.isPlayable());
            //cube.setColor(Color.YELLOW);
            break;
        default:
            break;
        }
    }
}
