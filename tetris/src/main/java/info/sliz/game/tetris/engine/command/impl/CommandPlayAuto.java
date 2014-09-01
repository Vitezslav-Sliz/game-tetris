package info.sliz.game.tetris.engine.command.impl;

import javafx.geometry.Point3D;
import info.sliz.game.tetris.engine.command.CommandMove;
import info.sliz.game.tetris.engine.elements.playcube.FxElement;
import info.sliz.game.tetris.engine.elements.playcube.IMovable.MOVE;

public class CommandPlayAuto extends CommandMove {

    public CommandPlayAuto(FxElement el, double step, double boundary) {
        super(el, step, boundary);
    }
    
    @Override
    public void execute() throws CommandException {
        for (Point3D p : el.getControlPoints()) {
            if ((p.getZ()+moveStep) > moveBoundary){
                el.setPlayable(false);
                super.execute();
                return;
            }
        }
        el.play(MOVE.FORWARD, moveStep);
        super.execute();
        
    }
}
