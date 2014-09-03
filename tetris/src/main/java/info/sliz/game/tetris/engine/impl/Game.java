package info.sliz.game.tetris.engine.impl;

import info.sliz.game.tetris.engine.ICollidable;
import info.sliz.game.tetris.engine.IGameStrategy;
import info.sliz.game.tetris.engine.command.CommandPlay;
import info.sliz.game.tetris.engine.command.ICommand;
import info.sliz.game.tetris.engine.command.impl.CommandPlayAuto;
import info.sliz.game.tetris.engine.command.impl.CommandPlayDown;
import info.sliz.game.tetris.engine.command.impl.CommandPlayLeft;
import info.sliz.game.tetris.engine.command.impl.CommandPlayRight;
import info.sliz.game.tetris.engine.command.impl.CommandPlaySpace;
import info.sliz.game.tetris.engine.command.impl.CommandPlayUp;
import info.sliz.game.tetris.engine.elements.IPlaybleElementGenerator;
import info.sliz.game.tetris.engine.elements.event.ElementListener;
import info.sliz.game.tetris.engine.elements.event.impl.ElementEvent;
import info.sliz.game.tetris.engine.elements.impl.RandomFxPlayableElementGenerator;
import info.sliz.game.tetris.engine.elements.playcube.FxPlayableElement;
import info.sliz.game.tetris.engine.event.GameListener;
import info.sliz.game.tetris.engine.event.impl.GameChangedEvent;
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
    private final IGameStrategy strategy;
    private final Set<ICollidable> col = new HashSet<ICollidable>();
    private final Set<CommandPlay> commands = new HashSet<CommandPlay>();
    
    private final FxGameSpace space;  
    private final List<FxInplaceElement> elements = new ArrayList<FxInplaceElement>();
    private FxPlayableElement element;

    private final IPlaybleElementGenerator generator;
    private GameRunner runner;
    
    public Game() {
        this.e = new GameChangedEvent(this);
        this.space = new FxGameSpace(5, 10, 10, 0.15, Color.YELLOW,Color.BLUE,Color.RED, Color.GREEN, Color.INDIGO, Color.CYAN, Color.MAGENTA, Color.VIOLET, Color.BEIGE);
        this.generator = new RandomFxPlayableElementGenerator(new Point3D(0, 0, -85), 10);
        this.strategy = new DefaultGameStrategy(this.elements,25);
        this.element = this.generator.generateElement();
        
        col.add(space);
        
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
       this.element = this.generator.generateElement();
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

    public void elementChanged(ElementEvent e) {
        LOGGER.debug("Game changed - reflect changes");
        if (!this.element.isPlayable()){
            LOGGER.debug("Elements not movable create");
            for (Point3D point : this.element.getBoundaries()) {
                FxInplaceElement el = new FxInplaceElement(point, 10);
                col.add(el);
                el.setColor(this.space.getColor(point));
                this.elements.add(el);
                
            }
            this.strategy.update();
            updateToNewElement();
        }
        LOGGER.debug("Elements in game is: " + (this.getElements().size()-1));
        if(this.listener != null){
            LOGGER.debug("Listener is exist - running event trigger");
            this.listener.gameChanged(this.e);
        }
    }
}

