package commands.builtin;

import commands.Command;
import commands.CommandType;
import dto.InputDto;

public class Exit implements Command {
    private final CommandType type;

    public Exit() {
        this.type = CommandType.BUILTIN;
    }

    @Override
    public CommandType getType() {
        return type;
    }

    @Override
    public String name() {
        return "exit";
    }

    @Override
    public void execute(InputDto input) {
        System.exit(0);
    }
}
