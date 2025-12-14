package commands.builtin;

import dto.InputDto;
import commands.Command;
import commands.CommandFactory;
import commands.CommandType;
import commands.external.ExternalCommand;

public class Type implements Command {
    private final CommandType type;
    private final CommandFactory factory;


    public Type(CommandFactory factory) {
        this.type = CommandType.BUILTIN;
        this.factory = factory;
    }

    @Override
    public CommandType getType() {
        return type;
    }

    @Override
    public String name() {
        return "type";
    }

    @Override
    public void execute(InputDto input) {
        String arg = input.args().getFirst();
        factory.getCommand(arg).ifPresentOrElse(
                this::printCommandType,
                () -> System.out.printf("%s: not found%n", arg)
        );
    }

    private void printCommandType(Command command) {
        if (command.getType() == CommandType.BUILTIN)
            System.out.printf("%s is a shell builtin%n", command.name());
        else if (command.getType() == CommandType.EXTERNAL)
            System.out.printf("%s is %s%n", command.name(), ((ExternalCommand) command).filePath().toString());
    }
}
