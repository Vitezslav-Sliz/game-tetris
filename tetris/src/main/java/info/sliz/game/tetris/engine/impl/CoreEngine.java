package info.sliz.game.tetris.engine.impl;

import info.sliz.game.tetris.engine.command.ICommand;
import info.sliz.game.tetris.engine.command.impl.CommandPlayRight;
import info.sliz.game.tetris.ui.elements.playcube.FxElement;
import info.sliz.game.tetris.ui.elements.playcube.impl.FxDotElement;
import info.sliz.game.tetris.ui.gamespace.FxGameSpace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.Node;

public class CoreEngine {

    private final Map<Class<? extends ICommand>, ICommand> commands = new HashMap<Class<? extends ICommand>, ICommand>();
    private final List<Node> elements = new ArrayList<Node>();
    
    private FxElement active;
    
    public CoreEngine() {
        
        FxGameSpace s = new FxGameSpace(5, 10, 10, 0.15);
        this.elements.add(s);
        
        active = new FxDotElement(0,0,-85,10,true);
        this.elements.add(active); 
        this.commands.put(CommandPlayRight.class, new CommandPlayRight(this.active,10));
    }
    
    public ICommand getCommand(Class<? extends ICommand> command){
        return this.commands.get(command);
    }
    public List<Node> getElements(){
        return this.elements;
    }
}
