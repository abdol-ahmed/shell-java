package tokenizer;

import dto.InputDto;

import java.util.List;

public class Translator {
    private final List<Token> keywords;

    public Translator(List<Token> keywords) {
        this.keywords = keywords;
    }

    public InputDto translate() {
        Token command = keywords.getFirst();
        return new InputDto(
                command.getValue(),
                keywords.subList(1, keywords.size()).stream().map(Token::getValue).toList(),
                keywords.stream().map(Token::getValue).toList()
        );
    }
}
