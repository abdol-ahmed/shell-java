import java.util.List;

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
    public boolean execute(String[] args) {
        String arg = args.length > 1 ? args[1] : "";
        Tokenizer tokenizer = new Tokenizer(arg);
        List<Token> tokens = tokenizer.tokenize();
        for (Token token : tokens) {
            System.out.print(token.getValue());
        }
        System.out.println();
        return true;
    }
}
