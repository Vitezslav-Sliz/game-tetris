package info.sliz.game.tetris.engine.impl;

import info.sliz.game.tetris.engine.ICollidable;
import info.sliz.game.tetris.engine.IStatisticsStrategy;
import info.sliz.game.tetris.engine.command.CommandPlay;
import info.sliz.game.tetris.engine.command.ICommand;
import info.sliz.game.tetris.engine.command.ICommand.CommandException;
import info.sliz.game.tetris.engine.command.impl.CommandPlayAuto;
import info.sliz.game.tetris.engine.command.impl.CommandPlayDown;
import info.sliz.game.tetris.engine.command.impl.CommandPlayLeft;
import info.sliz.game.tetris.engine.command.impl.CommandPlayRight;
import info.sliz.game.tetris.engine.command.impl.CommandPlayRotateX;
import info.sliz.game.tetris.engine.command.impl.CommandPlayRotateY;
import info.sliz.game.tetris.engine.command.impl.CommandPlayRotateZ;
import info.sliz.game.tetris.engine.command.impl.CommandPlaySpace;
import info.sliz.game.tetris.engine.command.impl.CommandPlayUp;
import info.sliz.game.tetris.engine.command.impl.CommandUpdateGameElements;
import info.sliz.game.tetris.engine.elements.IElements;
import info.sliz.game.tetris.engine.elements.IPlaybleElementGenerator;
import info.sliz.game.tetris.engine.elements.event.ElementListener;
import info.sliz.game.tetris.engine.elements.event.impl.ElementEvent;
import info.sliz.game.tetris.engine.elements.impl.Elements;
import info.sliz.game.tetris.engine.elements.impl.LevelColorManager;
import info.sliz.game.tetris.engine.elements.impl.AllElementGeneratorStrategy;
import info.sliz.game.tetris.engine.elements.playcube.FxPlayableElement;
import info.sliz.game.tetris.engine.event.GameListener;
import info.sliz.game.tetris.engine.event.impl.GameChangedEvent;
import info.sliz.game.tetris.engine.gamespace.impl.FxGameSpace;

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
    private final IStatisticsStrategy statistics;
    private final Set<CommandPlay> commands = new HashSet<CommandPlay>();
    private final ICommand calcElemnts;
    
    private final FxGameSpace space;
    private final IElements elements;
    private FxPlayableElement element;
    private final IPlaybleElementGenerator generator;
    private GameRunner runner;
    
    public Game() {
        this.e = new GameChangedEvent(this);
        this.space = new FxGameSpace(5, 10, 10, 0.15);
        this.runner = new GameRunner(1000);
        this.statistics = new DefaultGameStatistics();
        this.generator = new AllElementGeneratorStrategy(new Point3D(0, 0, -105), 10);
        this.elements = new Elements(10, new LevelColorManager(10,10, Color.YELLOW,Color.BLUE,Color.RED, Color.GREEN, Color.INDIGO, Color.CYAN, Color.MAGENTA, Color.VIOLET, Color.BEIGE)); 
        this.calcElemnts = new CommandUpdateGameElements(elements,this.space, 25,10);
        
        updateToNewElement();
    }
    private final void updateToNewElement(){
        this.element = this.generator.generateElement();
        this.commands.clear();
        
        Set<ICollidable> col = new HashSet<ICollidable>();
        col.add(space);
        col.addAll(this.elements.getColidable());
        
        commands.add(new CommandPlayLeft(this.element,10,col));
        commands.add(new CommandPlayRight(this.element,10,col));
        commands.add(new CommandPlayUp(this.element,10,col));
        commands.add(new CommandPlayDown(this.element,10,col));
        commands.add(new CommandPlaySpace(this.element,10,col));
        commands.add(new CommandPlayRotateX(this.element,90,col));
        commands.add(new CommandPlayRotateY(this.element,90,col));
        commands.add(new CommandPlayRotateZ(this.element,90,col));
        CommandPlay m = new CommandPlayAuto(this.element,10,col);
        commands.add(m);
        
        this.element.setEventListener(this);
        this.runner.setICommand(m);
        
        this.element.setEventListener(this);
    }

    public Set<ICommand> getCommands() {
        return new HashSet<ICommand>(commands);
    }

    public List<Node> getElements() {
        List<Node> ret = new ArrayList<Node>();
        ret.add(space);
        ret.addAll(this.elements.getNodes());
        ret.add(element);
        return ret;
    }

    public void setGameListener(final GameListener listener){
        this.listener = listener;
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
            this.elements.createAddInGameElement(this.element);
            try {
                this.calcElemnts.execute();
            } catch (CommandException e1) {
                LOGGER.error("Error with calculate results and manipulate",e);
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

