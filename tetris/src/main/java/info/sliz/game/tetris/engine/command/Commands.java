package info.sliz.game.tetris.engine.command;

import java.util.HashMap;
import java.util.Map;

public class Commands {

    private final Map<Class<? extends ICommand>, ICommand> commands = new HashMap<Class<? extends ICommand>, ICommand>();
    
    public void put(final ICommand command){
        this.commands.put(command.getClass(),command);
    }
    public ICommand get(Class<? extends ICommand> command){
        return this.commands.get(command);
    }
}