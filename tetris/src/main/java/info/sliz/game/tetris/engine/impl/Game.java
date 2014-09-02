package info.sliz.game.tetris.engine.impl;

import info.sliz.game.tetris.engine.GameChangedEvent;
import info.sliz.game.tetris.engine.GameListener;
import info.sliz.game.tetris.engine.command.CommandPlay;
import info.sliz.game.tetris.engine.command.ICommand;
import info.sliz.game.tetris.engine.command.impl.CommandPlayAuto;
import info.sliz.game.tetris.engine.command.impl.CommandPlayDown;
import info.sliz.game.tetris.engine.command.impl.CommandPlayLeft;
import info.sliz.game.tetris.engine.command.impl.CommandPlayRight;
import info.sliz.game.tetris.engine.command.impl.CommandPlaySpace;
import info.sliz.game.tetris.engine.command.impl.CommandPlayUp;
import info.sliz.game.tetris.engine.elements.ICollision;
import info.sliz.game.tetris.engine.elements.playcube.ElementChanged;
import info.sliz.game.tetris.engine.elements.playcube.ElementListener;
import info.sliz.game.tetris.engine.elements.playcube.FxPlayableElement;
import info.sliz.game.tetris.engine.elements.playcube.impl.FxDotElement;
import info.sliz.game.tetris.engine.elements.playcube.impl.FxTripleElement;
import info.sliz.game.tetris.engine.elements.playcube.impl.FxDoubleElement;
import info.sliz.game.tetris.engine.gamespace.impl.FxGameSpace;
import info.sliz.game.tetris.engine.gamespace.impl.FxInplaceElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.paint.Color;

public class Game implements ElementListener{
    private static final Logger LOGGER = LoggerFactory.getLogger(Game.class);
    private final GameChangedEvent e;
    private GameListener listener;
    private final FxGameSpace space;
    private final Set<ICollision> col = new HashSet<ICollision>();
    private final Set<CommandPlay> commands = new HashSet<CommandPlay>();
    private final List<FxInplaceElement> elements = new ArrayList<FxInplaceElement>();
    private FxPlayableElement element;
    private GameRunner runner;
    
    public Game() {
        this.e = new GameChangedEvent(this);
        space = new FxGameSpace(5, 10, 10, 0.15);
        
        col.add(space);
        
        this.element = new FxDotElement(0,0,-85,10);
        commands.add(new CommandPlayLeft(this.element,10,col));
        commands.add(new CommandPlayRight(this.element,10,col));
        commands.add(new CommandPlayUp(this.element,10,col));
        commands.add(new CommandPlayDown(this.element,10,col));
        commands.add(new CommandPlaySpace(this.element,10,col));
        CommandPlay m = new CommandPlayAuto(this.element,10,col);
        commands.add(m);
        
        this.element.setEventListener(this);

        runner = new GameRunner(1000,m);
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

    public void setGameListener(final GameListener listener){
        this.listener = listener;
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
           commandMove.setPlayableElement(this.element);
       }
       this.element.setEventListener(this);
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

    public void elementChanged(ElementChanged e) {
        LOGGER.debug("Game changed - reflect changes");
        if (!this.element.isPlayable()){
            LOGGER.debug("Elements not movable create");
            for (Point3D point : this.element.getControlPoints()) {
                FxInplaceElement el = new FxInplaceElement(point.getX(), point.getY(), point.getZ(), 10);
                col.add(el);
                el.setColor(Color.YELLOW);
                this.elements.add(el);
            }
            updateToNewElement();
        }
        LOGGER.debug("Elements in game is: " + (this.getElements().size()-1));
        if(this.listener != null){
            LOGGER.debug("Listener is exist - running event trigger");
            this.listener.gameChanged(this.e);
        }
    }
}

