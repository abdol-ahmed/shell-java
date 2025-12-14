package commands.builtin;

import dto.InputDto;
import commands.Command;
import commands.CommandType;

public class PrintWorkingDirectory implements Command {
    private final CommandType type;

    public PrintWorkingDirectory() {
        this.type = CommandType.BUILTIN;
    }

    @Override
    public CommandType getType() {
        return type;
    }

    @Override
    public String name() {
        return "pwd";
    }

    @Override
    public void execute(InputDto input) {
        System.out.println(System.getProperty("user.dir"));
    }
}
