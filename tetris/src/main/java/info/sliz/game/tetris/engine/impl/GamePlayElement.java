package info.sliz.game.tetris.engine.impl;

import info.sliz.game.tetris.engine.command.CommandPlay;
import info.sliz.game.tetris.engine.command.impl.CommandPlayDown;
import info.sliz.game.tetris.engine.command.impl.CommandPlayLeft;
import info.sliz.game.tetris.engine.command.impl.CommandPlayRight;
import info.sliz.game.tetris.engine.command.impl.CommandPlaySpace;
import info.sliz.game.tetris.engine.command.impl.CommandPlayUp;
import info.sliz.game.tetris.engine.elements.playcube.FxPlayableElement;
import info.sliz.game.tetris.engine.elements.playcube.impl.FxDotElement;

import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

import javafx.scene.Node;

public class GamePlayElement extends Observable {
    private FxPlayableElement active;
    private final Set<CommandPlay> commands = new HashSet<CommandPlay>();
    
    public GamePlayElement(Game game) {
        active = new FxDotElement(0,0,-85,10);
        this.commands.add(new CommandPlayLeft(this.active,10,-25));
        this.commands.add(new CommandPlayRight(this.active,10,25));
        this.commands.add(new CommandPlayUp(this.active,10,-25));
        this.commands.add(new CommandPlayDown(this.active,10,25));
        this.commands.add(new CommandPlaySpace(this.active,10,-5));
        
        for (CommandPlay c : this.commands) {
            c.addObserver(game);
        }       
    }
    
    public Node getNode(){
        return this.active;
    }
    public Set<CommandPlay> getAvalaibleCommands(){
        return this.commands;
    }
    public boolean isPlayable(){
        return this.active.isPlayable();
    }
}