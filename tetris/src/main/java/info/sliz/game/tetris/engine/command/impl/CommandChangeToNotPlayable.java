package info.sliz.game.tetris.engine.command.impl;

import java.util.HashSet;

import info.sliz.game.tetris.engine.command.CommandPlay;
import info.sliz.game.tetris.engine.command.ICommand;
import info.sliz.game.tetris.engine.elements.ICollision;
import info.sliz.game.tetris.engine.elements.playcube.FxPlayableElement;

public class CommandChangeToNotPlayable extends CommandPlay implements ICommand {

    public CommandChangeToNotPlayable(FxPlayableElement el) {
        super(el,0,new HashSet<ICollision>());
    }
    
    public void execute() throws CommandException {
        this.element.setPlayable(false);
    }   
}
