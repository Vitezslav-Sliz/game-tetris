package info.sliz.game.tetris.engine.command;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CommandManager {

    private final Map<Class<? extends ICommand>, ICommand> commands = new HashMap<Class<? extends ICommand>, ICommand>();
    
    public void put(final ICommand command){
        this.commands.put(command.getClass(),command);
    }
    public ICommand get(Class<? extends ICommand> command){
        return this.commands.get(command);
    }
    
    public void putAll(Set<ICommand> commands) {
        for (ICommand iCommand : commands) {
            this.put(iCommand);
        }
    }
    
    public int size() {
        return this.commands.size();
    }
}
