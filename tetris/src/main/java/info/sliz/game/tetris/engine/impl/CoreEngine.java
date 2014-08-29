package info.sliz.game.tetris.engine.impl;

import info.sliz.game.tetris.engine.command.Commands;
import info.sliz.game.tetris.engine.command.ICommand;
import info.sliz.game.tetris.engine.command.impl.CommandPlayDown;
import info.sliz.game.tetris.engine.command.impl.CommandPlayLeft;
import info.sliz.game.tetris.engine.command.impl.CommandPlayRight;
import info.sliz.game.tetris.engine.command.impl.CommandPlayUp;
import info.sliz.game.tetris.engine.elements.playcube.FxElement;
import info.sliz.game.tetris.engine.elements.playcube.impl.FxDotElement;
import info.sliz.game.tetris.engine.gamespace.impl.FxGameSpace;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;

public class CoreEngine {

    private final Commands commands = new Commands();
    private final List<Node> elements = new ArrayList<Node>();
    
    private FxElement active;
    
    public CoreEngine() {
        
        FxGameSpace s = new FxGameSpace(5, 10, 10, 0.15);
        this.elements.add(s);
        
        active = new FxDotElement(0,0,-85,10,true);
        this.elements.add(active); 
        this.commands.put(new CommandPlayLeft(this.active,10));
        this.commands.put(new CommandPlayRight(this.active,10));
        this.commands.put(new CommandPlayUp(this.active,10));
        this.commands.put(new CommandPlayDown(this.active,10));
        
    }
    
    public ICommand getCommand(Class<? extends ICommand> command){
        return this.commands.get(command);
    }
    public List<Node> getElements(){
        return this.elements;
    }
}
