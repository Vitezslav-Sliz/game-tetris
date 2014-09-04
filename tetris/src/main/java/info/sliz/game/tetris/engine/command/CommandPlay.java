package info.sliz.game.tetris.engine.command;

import info.sliz.game.tetris.engine.ICollidable;
import info.sliz.game.tetris.engine.elements.playcube.IPlayable;
import info.sliz.game.tetris.engine.elements.playcube.IMovable.MOVE;

import java.util.Set;

public abstract class CommandPlay extends CommandMove {
    
    protected IPlayable element;
    public CommandPlay(final IPlayable element,final double step,final Set<ICollidable> colidate) {
        super(element, step, colidate);
        this.element = element;
    }
    
    protected final boolean canPlay(final MOVE direction){
    	if (this.element.isPlayable()){
    		for (ICollidable col : this.colidate) {
    			if (!this.element.canPlay(direction, moveStep, col)) {
    				return false;
    			}
    		}
    		return true;
    	}else{
    		return false;
    	}
    }
    public abstract void execute() throws CommandException ;
    
    public class CommandPlayException extends CommandException{
        
        private static final long serialVersionUID = 518827425380469331L;
        
        public CommandPlayException(String message) {
            super(message);
        }
    }
}
