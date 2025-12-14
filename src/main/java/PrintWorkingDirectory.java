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
    public boolean execute(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        return true;
    }
}
