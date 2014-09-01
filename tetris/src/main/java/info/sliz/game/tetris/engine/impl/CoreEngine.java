package info.sliz.game.tetris.engine.impl;

import info.sliz.game.tetris.engine.command.Commands;
import info.sliz.game.tetris.engine.command.ICommand;
import info.sliz.game.tetris.engine.command.impl.CommandStartGame;
import info.sliz.game.tetris.engine.command.impl.CommandStopGame;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.Node;

public class CoreEngine extends Observable implements Observer{
    private Game game;
    private final Commands commands = new Commands();
    public CoreEngine() {
        this.game = new Game();
        this.game.addObserver(this);
        this.commands.putAll(this.game.getCommands());
        
        this.commands.put(new CommandStartGame(game));
        this.commands.put(new CommandStopGame(game));
    }
    
    public ICommand getCommand(Class<? extends ICommand> command){
        return this.commands.get(command);
    }
    public List<Node> getElements(){
        return this.game.getElements();
    }

    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }

}
