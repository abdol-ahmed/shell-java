package commands.builtin;

import dto.InputDto;
import commands.Command;
import commands.CommandType;

public class Echo implements Command {
    private final CommandType type;

    public Echo() {
        this.type = CommandType.BUILTIN;
    }

    @Override
    public CommandType getType() {
        return type;
    }

    @Override
    public String name() {
        return "echo";
    }

    @Override
    public void execute(InputDto input) {
        System.out.println(String.join(" ", input.args()));
    }
}
