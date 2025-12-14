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
    public boolean execute(String[] args) {
        return false;
    }
}
