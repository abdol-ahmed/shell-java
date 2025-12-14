import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Catenate implements Command {
    private final CommandType type;

    public Catenate() {
        this.type = CommandType.EXTERNAL;
    }

    @Override
    public CommandType getType() {
        return type;
    }

    @Override
    public boolean execute(String[] args) {
        StringBuffer data = new StringBuffer();
        String arg = args.length > 1 ? args[1] : "";

        Tokenizer tokenizer = new Tokenizer(arg);
        List<Token> tokens = tokenizer.tokenize();
        for (Token token : tokens) {
            if (!token.getValue().isBlank()) {
                data.append(readFile(token.getValue()));
            }
        }
        System.out.println(data.toString());
        return true;
    }

    public StringBuffer readFile(String path) {
        StringBuffer data = new StringBuffer();
                File file = new File(path);
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                data.append(sc.nextLine());
                if (sc.hasNextLine()) {
                    data.append("\n"); // Or System.lineSeparator()
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        return data;
    }

}


// cat 'f   57' 'f   87' 'f   97'