package info.sliz.game.tetris.engine.impl;

import info.sliz.game.tetris.engine.ICommands;
import info.sliz.game.tetris.engine.IEngine;
import info.sliz.game.tetris.engine.IGameStrategy;
import info.sliz.game.tetris.engine.command.CommandManager;
import info.sliz.game.tetris.engine.command.ICommand.CommandException;
import info.sliz.game.tetris.engine.command.impl.CommandStartGame;
import info.sliz.game.tetris.engine.command.impl.CommandStopGame;
import info.sliz.game.tetris.engine.event.GameListener;
import info.sliz.game.tetris.engine.event.impl.GameChangedEvent;
import info.sliz.game.tetris.engine.game.Games;

import java.util.List;
import java.util.Observable;

import javafx.scene.Node;

public class CoreEngine extends Observable implements IEngine, GameListener {
    private IGameStrategy game;
    private final CommandManager commands = new CommandManager();

    public CoreEngine(final Games game) {
        this.game = game.getGame();
        this.commands.putAll(this.game.getCommands());
        this.commands.put(new CommandStartGame(this.game));
        this.commands.put(new CommandStopGame(this.game));

        this.game.setGameListener(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see info.sliz.game.tetris.engine.impl.IEngine#getElements()
     */
    public List<Node> getElements() {
        return this.game.getElements();
    }

    public void gameChanged(GameChangedEvent e) {
        this.commands.putAll(this.game.getCommands());
        setChanged();
        notifyObservers();
    }

    public void callCommand(ICommands command) throws CommandException {
        this.commands.get(command.getCommandClassName()).execute();
    }
}
