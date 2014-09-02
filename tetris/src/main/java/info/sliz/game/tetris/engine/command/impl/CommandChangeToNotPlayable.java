package info.sliz.game.tetris.engine.command.impl;

import info.sliz.game.tetris.engine.command.CommandPlay;
import info.sliz.game.tetris.engine.command.ICommand;
import info.sliz.game.tetris.engine.elements.playcube.FxPlayableElement;

public class CommandChangeToNotPlayable extends CommandPlay implements ICommand {

    public CommandChangeToNotPlayable(FxPlayableElement el) {
        super(el,0,null);
    }
    
    public void execute() throws CommandException {
        this.el.setPlayable(false);
    }   
}
