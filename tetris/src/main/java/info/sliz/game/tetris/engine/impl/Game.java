package info.sliz.game.tetris.engine.impl;

import info.sliz.game.tetris.engine.command.CommandPlay;
import info.sliz.game.tetris.engine.command.ICommand;
import info.sliz.game.tetris.engine.command.impl.CommandPlayAuto;
import info.sliz.game.tetris.engine.command.impl.CommandPlayDown;
import info.sliz.game.tetris.engine.command.impl.CommandPlayLeft;
import info.sliz.game.tetris.engine.command.impl.CommandPlayRight;
import info.sliz.game.tetris.engine.command.impl.CommandPlaySpace;
import info.sliz.game.tetris.engine.command.impl.CommandPlayUp;
import info.sliz.game.tetris.engine.elements.basic.impl.FxInplaceElement;
import info.sliz.game.tetris.engine.elements.playcube.FxPlayableElement;
import info.sliz.game.tetris.engine.elements.playcube.impl.FxDotElement;
import info.sliz.game.tetris.engine.elements.playcube.impl.FxTripleElement;
import info.sliz.game.tetris.engine.elements.playcube.impl.FxDoubleElement;
import info.sliz.game.tetris.engine.gamespace.impl.FxGameSpace;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.paint.Color;

public class Game extends Observable implements Observer{
    private static final Logger LOGGER = LoggerFactory.getLogger(Game.class);
    
    private final FxGameSpace space;
    private final Set<CommandPlay> commands = new HashSet<CommandPlay>();
    private final List<FxInplaceElement> elements = new ArrayList<FxInplaceElement>();
    private FxPlayableElement element;
    private GameRunner runner;
    
    public Game() {
        space = new FxGameSpace(5, 10, 10, 0.15);
        
        this.element = new FxDotElement(0,0,-85,10);
        commands.add(new CommandPlayLeft(this.element,10,-25));
        commands.add(new CommandPlayRight(this.element,10,25));
        commands.add(new CommandPlayUp(this.element,10,-25));
        commands.add(new CommandPlayDown(this.element,10,25));
        commands.add(new CommandPlaySpace(this.element,10,-5));
        CommandPlay c = new CommandPlayAuto(this.element,10,-5);
        commands.add(c);

        for (CommandPlay cs : commands) {
            cs.addObserver(this);
        }
        runner = new GameRunner(500,c);
    }

    public Set<ICommand> getCommands() {
        return new HashSet<ICommand>(commands);
    }

    public List<Node> getElements() {
        List<Node> ret = new ArrayList<Node>();
        ret.add(space);
        ret.addAll(elements);
        ret.add(element);
        return ret;
    }

    public void update(Observable o, Object arg) {
        LOGGER.debug("Game changed - reflect changes");
        if (!element.isPlayable()){
            
            LOGGER.debug("Elements not movable create");
            for (Point3D point : element.getControlPoints()) {
                FxInplaceElement el = new FxInplaceElement(point.getX(), point.getY(), point.getZ(), 10);             
                el.setColor(Color.YELLOW);
                this.elements.add(el);               
            }
            updateToNewElement();
        }
        LOGGER.debug("Elements in game is: " + (this.getElements().size()-1));
        setChanged();
        notifyObservers();
    }
    private void updateToNewElement(){
       double r = Math.random();
       if (r < 0.33){
           this.element = new FxDotElement(0,0,-85,10);
       }
       if (r >= 0.33 && r < 0.66){
           this.element = new FxTripleElement(0,0,-85,10);          
       }
       if (r >= 0.66 ){
           this.element = new FxDoubleElement(0,0,-85,10);          
       }
       
       for (CommandPlay commandMove : commands) {
           commandMove.setElement(this.element);
       }
    }
    public void stopGame() {
        this.runner.Stop();
    }
    public void startGame() {
        this.runner.start();
    }
    public boolean isRunning(){
        return this.runner.isAlive();
    }
}

