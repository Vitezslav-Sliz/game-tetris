package info.sliz.game.tetris.engine.elements.playcube;

import java.util.Set;

import info.sliz.game.tetris.engine.ICollidable;

public interface IPlayable extends IMovable, IRotable {

    public boolean isPlayable();

    public void setPlayable(final boolean play);

    public void play(final MOVE direction, final double step);

    public void play(final ROTATE axis, final double angle);

    public boolean canPlay(final MOVE direction, final double step, final Set<ICollidable> elements);

    public boolean canPlay(final ROTATE axis, final double angle, final Set<ICollidable> elements);

    public class NotPlayableException extends RuntimeException {
        private static final long serialVersionUID = 7295332880366992209L;

        public NotPlayableException(String message) {
            super(message);
        }
    }
}
