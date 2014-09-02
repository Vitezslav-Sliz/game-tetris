package info.sliz.game.tetris.engine.impl;

import info.sliz.game.tetris.engine.GameChangedEvent;
import info.sliz.game.tetris.engine.GameListener;
import info.sliz.game.tetris.engine.IEngine;
import info.sliz.game.tetris.engine.command.Commands;
import info.sliz.game.tetris.engine.command.ICommand;
import info.sliz.game.tetris.engine.command.impl.CommandStartGame;
import info.sliz.game.tetris.engine.command.impl.CommandStopGame;

import java.util.List;
import java.util.Observable;

import javafx.scene.Node;

public class CoreEngine extends Observable implements IEngine, GameListener{
    private Game game;
    private final Commands commands = new Commands();
    
    public CoreEngine() {
        this.game = new Game();
        this.commands.putAll(this.game.getCommands());

        this.commands.put(new CommandStartGame(game));
        this.commands.put(new CommandStopGame(game));
        
        this.game.setGameListener(this);
    }
    
    /* (non-Javadoc)
     * @see info.sliz.game.tetris.engine.impl.IEngine#getCommand(java.lang.Class)
     */
    public ICommand getCommand(Class<? extends ICommand> command){
        return this.commands.get(command);
    }
    /* (non-Javadoc)
     * @see info.sliz.game.tetris.engine.impl.IEngine#getElements()
     */
    public List<Node> getElements(){
        return this.game.getElements();
    }
    public void gameChanged(GameChangedEvent e) {
        setChanged();
        notifyObservers();        
    }
}
