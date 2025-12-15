import commands.CommandFactory;
import dto.InputDto;
import tokenizer.Token;
import tokenizer.Tokenizer;
import tokenizer.Translator;

import java.util.List;
import java.util.Scanner;

public class Shell {
    private final CommandFactory commandFactory;

    public Shell(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }
    public void startShell() {
        try (var scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("$ ");
                if (!scanner.hasNextLine()) {
                    break;
                }

                String input = scanner.nextLine();
                if (input.isEmpty()) continue;

                try {
                    List<Token> tokens = new Tokenizer(input).tokenize();
                    InputDto inputDto = new Translator(tokens).translate();
                    commandFactory.getCommand(inputDto.command()).ifPresentOrElse(
                            cmd -> cmd.execute(inputDto),
                            () -> System.out.println(inputDto.command() + ": command not found")
                    );
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
