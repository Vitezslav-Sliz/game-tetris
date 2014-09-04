package info.sliz.game.tetris.engine.command.impl;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.sliz.game.tetris.engine.ICollidable;
import info.sliz.game.tetris.engine.command.CommandPlay;
import info.sliz.game.tetris.engine.elements.playcube.IPlayable;
import info.sliz.game.tetris.engine.elements.playcube.IRotable.ROTATE;

public class CommandPlayRotateZ extends CommandPlay {
	private static final Logger LOGGER = LoggerFactory.getLogger(CommandPlayRotateZ.class);
	
	public CommandPlayRotateZ(IPlayable element, double angle,Set<ICollidable> colidate) {
		super(element, 0,angle, colidate);
	}

	@Override
	public void execute() throws CommandPlayException {
		LOGGER.debug("Execute command");
        if (!this.canPlay(ROTATE.Z)){
            throw new CommandPlayException("Can't play: colide with another elements, or is not playable");
        }
        element.rotate(ROTATE.Z, angleStep);
		
	}

}
