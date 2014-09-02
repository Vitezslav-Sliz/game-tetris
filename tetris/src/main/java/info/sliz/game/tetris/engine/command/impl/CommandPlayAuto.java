package info.sliz.game.tetris.engine.command.impl;

import javafx.geometry.Point3D;
import info.sliz.game.tetris.engine.command.CommandPlay;
import info.sliz.game.tetris.engine.elements.playcube.FxPlayableElement;
import info.sliz.game.tetris.engine.elements.playcube.IMovable.MOVE;

public class CommandPlayAuto extends CommandPlay {

    public CommandPlayAuto(FxPlayableElement el, double step, double boundary) {
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
