package info.sliz.game.tetris.engine;

import info.sliz.game.tetris.engine.command.ICommand;
import info.sliz.game.tetris.engine.command.impl.CommandPlayDown;
import info.sliz.game.tetris.engine.command.impl.CommandPlayLeft;
import info.sliz.game.tetris.engine.command.impl.CommandPlayRight;
import info.sliz.game.tetris.engine.command.impl.CommandPlayRotateX;
import info.sliz.game.tetris.engine.command.impl.CommandPlayRotateY;
import info.sliz.game.tetris.engine.command.impl.CommandPlayRotateZ;
import info.sliz.game.tetris.engine.command.impl.CommandPlaySpace;
import info.sliz.game.tetris.engine.command.impl.CommandPlayUp;
import info.sliz.game.tetris.engine.command.impl.CommandStartGame;
import info.sliz.game.tetris.engine.command.impl.CommandStopGame;

public enum Commands {

    PLAY(CommandStartGame.class), STOP(CommandStopGame.class), PLAY_LEFT(
            CommandPlayLeft.class), PLAY_RIGHT(CommandPlayRight.class), PLAY_UP(
            CommandPlayUp.class), PLAY_DOWN(CommandPlayDown.class), PLAY_SPACE(
            CommandPlaySpace.class), PLAY_ROTATE_X(CommandPlayRotateX.class), PLAY_ROTATE_Y(
            CommandPlayRotateY.class), PLAY_ROTATE_Z(CommandPlayRotateZ.class);

    private final Class<? extends ICommand> command;

    private Commands(Class<? extends ICommand> command) {
        this.command = command;
    }

    public Class<? extends ICommand> getCommandClass() {
        return this.command;
    }
}
