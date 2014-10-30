package info.sliz.game.tetris.engine.game.impl;

import info.sliz.game.tetris.engine.ICollidable;
import info.sliz.game.tetris.engine.IGameStrategy;
import info.sliz.game.tetris.engine.command.CommandPlay;
import info.sliz.game.tetris.engine.command.ICommand;
import info.sliz.game.tetris.engine.command.impl.CommandPlayAuto;
import info.sliz.game.tetris.engine.command.impl.CommandPlayDown;
import info.sliz.game.tetris.engine.command.impl.CommandPlayLeft;
import info.sliz.game.tetris.engine.command.impl.CommandPlayRight;
import info.sliz.game.tetris.engine.command.impl.CommandPlayRotateX;
import info.sliz.game.tetris.engine.command.impl.CommandPlayRotateY;
import info.sliz.game.tetris.engine.command.impl.CommandPlayRotateZ;
import info.sliz.game.tetris.engine.command.impl.CommandPlaySpace;
import info.sliz.game.tetris.engine.command.impl.CommandPlayUp;
import info.sliz.game.tetris.engine.elements.IElements;
import info.sliz.game.tetris.engine.elements.IPlaybleElementGenerator;
import info.sliz.game.tetris.engine.elements.event.ElementListener;
import info.sliz.game.tetris.engine.elements.event.impl.ElementEvent;
import info.sliz.game.tetris.engine.elements.impl.Elements;
import info.sliz.game.tetris.engine.elements.impl.LevelColorManager;
import info.sliz.game.tetris.engine.elements.impl.AllElementGeneratorStrategy;
import info.sliz.game.tetris.engine.elements.playcube.FxPlayableElement;
import info.sliz.game.tetris.engine.game.AbstractGameStrategy;
import info.sliz.game.tetris.engine.game.command.GameOperationException;
import info.sliz.game.tetris.engine.game.command.IGameOperation;
import info.sliz.game.tetris.engine.game.command.impl.DefaultUpdateGameElementsOperation;
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

public class DefaultGameStrategy extends AbstractGameStrategy implements ElementListener, IGameStrategy {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultGameStrategy.class);
    private static final double ANGLE = 90;

    private final Set<CommandPlay> commands = new HashSet<CommandPlay>();
    final IGameOperation updateElements;
    

    private final int size;
    private final int height;
    private final int blockSize;

    private final FxGameSpace space;
    final IElements elements;
    FxPlayableElement element;
    private final IPlaybleElementGenerator generator;

    public DefaultGameStrategy(final int size, final int height, final int blockSize, final int initialSpeed, final Color... colors) {
        super(initialSpeed);
        this.size = size;
        this.height = height;
        this.blockSize = blockSize;
        this.space = new FxGameSpace(size, height, blockSize, this.blockSize / 100.0);
        this.generator = new AllElementGeneratorStrategy(new Point3D(0, 0, -(this.height * this.blockSize) + this.blockSize / 2), this.blockSize);
        this.elements = new Elements(this.blockSize, new LevelColorManager(this.blockSize, this.blockSize, colors));
        this.updateElements = new DefaultUpdateGameElementsOperation(elements, this.space, this.size * this.size, this.blockSize);

        updateToNewElement();
    }

    final void updateToNewElement() {
        this.element = this.generator.generateElement();
        this.commands.clear();

        Set<ICollidable> col = new HashSet<ICollidable>();
        col.add(space);
        col.addAll(this.elements.getColidable());

        commands.add(new CommandPlayLeft(this.element, this.blockSize, col));
        commands.add(new CommandPlayRight(this.element, this.blockSize, col));
        commands.add(new CommandPlayUp(this.element, this.blockSize, col));
        commands.add(new CommandPlayDown(this.element, this.blockSize, col));
        commands.add(new CommandPlaySpace(this.element, this.blockSize, col));
        commands.add(new CommandPlayRotateX(this.element, ANGLE, col));
        commands.add(new CommandPlayRotateY(this.element, ANGLE, col));
        commands.add(new CommandPlayRotateZ(this.element, ANGLE, col));
        CommandPlay m = new CommandPlayAuto(this.element, this.blockSize, col);
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

    public void elementChanged(ElementEvent e) {
        LOGGER.debug("Game changed - reflect changes");
        if (!this.element.isPlayable()) {
            LOGGER.debug("Elements not movable create");
            this.elements.createAddInGameElement(this.element);
            try {
                this.updateElements.execute();
            } catch (GameOperationException e1) {
                LOGGER.error("Error with calculate results and manipulate", e1);
            }
            updateToNewElement();
        }
        LOGGER.debug("Elements in game is: " + (this.getElements().size() - 1));
        gameChanged();
    }
}
