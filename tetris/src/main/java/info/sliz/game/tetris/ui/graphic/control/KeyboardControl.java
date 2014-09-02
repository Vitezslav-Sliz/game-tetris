package info.sliz.game.tetris.ui.graphic.control;

import info.sliz.game.tetris.engine.IEngine;
import info.sliz.game.tetris.engine.command.ICommand.CommandException;
import info.sliz.game.tetris.engine.command.impl.CommandPlayDown;
import info.sliz.game.tetris.engine.command.impl.CommandPlayLeft;
import info.sliz.game.tetris.engine.command.impl.CommandPlayRight;
import info.sliz.game.tetris.engine.command.impl.CommandPlaySpace;
import info.sliz.game.tetris.engine.command.impl.CommandPlayUp;
import info.sliz.game.tetris.engine.command.impl.CommandStartGame;

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
                this.eng.getCommand(CommandPlayLeft.class).execute();
                break;
            case RIGHT:
                this.eng.getCommand(CommandPlayRight.class).execute();
                break;
            case UP:
                this.eng.getCommand(CommandPlayUp.class).execute();
                break;
            case DOWN:
                this.eng.getCommand(CommandPlayDown.class).execute();
                break;
            case SPACE:
                this.eng.getCommand(CommandPlaySpace.class).execute();
                break;
            case S:
                this.eng.getCommand(CommandStartGame.class).execute();
                break;
            default:
                break;
            }
        } catch (CommandException e) {
            LOGGER.info(String.format("Problem with Key '%s'. Cause: %s",event.getCode().toString(),e.getMessage()));
        }
    }
}
