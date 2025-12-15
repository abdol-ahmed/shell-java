package commands;

import commands.builtin.*;
import commands.external.ExternalCommandPathFinder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommandFactory {
    private final Map<String, Command> builtinCommands = new HashMap<>();
    private final ExternalCommandPathFinder externalCommandPathFinder;

    public CommandFactory() {
        var exitCommand = new Exit();
        builtinCommands.put(exitCommand.name(), exitCommand);
        var echoCommand = new Echo();
        builtinCommands.put(echoCommand.name(), echoCommand);
        var typeCommand = new Type(this);
        builtinCommands.put(typeCommand.name(), typeCommand);
        var pwdCommand = new PrintWorkingDirectory();
        builtinCommands.put(pwdCommand.name(), pwdCommand);
        var cdCommand = new ChangeDirectory();
        builtinCommands.put(cdCommand.name(), cdCommand);
        this.externalCommandPathFinder = new ExternalCommandPathFinder();
    }

    public Optional<Command> getCommand(String commandName) {
        Command command = builtinCommands.get(commandName);
        return command != null ? Optional.of(command) : externalCommandPathFinder.findExternalCommand(commandName);
    }
}
