package info.sliz.game.tetris.engine.event.impl;

import info.sliz.game.tetris.engine.impl.DefaultGameStrategy;

import java.util.EventObject;

public class GameChangedEvent extends EventObject{

    private static final long serialVersionUID = 1911117023835679493L;

    public GameChangedEvent(DefaultGameStrategy game) {
        super(game);
    }
}
