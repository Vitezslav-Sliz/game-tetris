package info.sliz.game.tetris.ui.graphic.control;

import info.sliz.game.tetris.engine.Commands;
import info.sliz.game.tetris.engine.IEngine;
import info.sliz.game.tetris.engine.command.ICommand.CommandException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyboardControl implements EventHandler<KeyEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(KeyboardControl.class);
    private IEngine eng;
    
    public KeyboardControl(final IEngine engine) {
        this.eng = engine;
    }

    public void handle(KeyEvent event) {
        LOGGER.debug("Pressed: " + event.getCode());
        try {
            switch (event.getCode()) {
            case LEFT:
                this.eng.callCommand(Commands.PLAY_LEFT);
                break;
            case RIGHT:
                this.eng.callCommand(Commands.PLAY_RIGHT);
                break;
            case UP:
                this.eng.callCommand(Commands.PLAY_UP);
                break;
            case DOWN:
                this.eng.callCommand(Commands.PLAY_DOWN);
                break;
            case SPACE:
                this.eng.callCommand(Commands.PLAY_SPACE);
                break;
            case A:
                this.eng.callCommand(Commands.PLAY_ROTATE_X);
                break;
            case S:
                this.eng.callCommand(Commands.PLAY_ROTATE_Y);
                break;
            case D:
                this.eng.callCommand(Commands.PLAY_ROTATE_Z);
                break;
            default:
                break;
            }
        } catch (CommandException e) {
            LOGGER.info(String.format("Problem with Key '%s'. Cause: %s",event.getCode().toString(),e.getMessage()));
        }
    }
}
