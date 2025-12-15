package tokenizer;

import dto.InputDto;

import java.util.List;

public class Translator {
    private final List<Token> tokens;

    public Translator(List<Token> tokens) {
        this.tokens = tokens;
    }

    public InputDto translate() {
        if (tokens.isEmpty()) {
            throw new IllegalArgumentException("Empty input");
        }
        Token command = tokens.getFirst();
        return new InputDto(
                command.getValue(),
                tokens.subList(1, tokens.size()).stream().map(Token::getValue).toList(),
                tokens.stream().map(Token::getValue).toList()
        );
    }
}
