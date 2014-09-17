package info.sliz.game.tetris.engine.command;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CommandManager {

    private final Map<String, ICommand> commands = new HashMap<String, ICommand>();

    public void put(final ICommand command) {
        this.commands.put(command.getClass().getName(), command);
    }

    public ICommand get(String string) {
        return this.commands.get(string);
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
