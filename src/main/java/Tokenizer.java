import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    private final String input;
    private int pos;

    public Tokenizer(String input) {
        this.input = input;
        this.pos = 0;
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();
        StringBuilder buf = new StringBuilder();
        boolean inSingleQuote = false;

        while (!isEnd()) {
            if (inSingleQuote) {
                if (isSingleQuote(currentChar())) {
                    tokens.add(new Token(TokenType.STRING, buf.toString()));
                    inSingleQuote = false;
                    buf.setLength(0);
                } else {
                    buf.append(currentChar());
                }
            }else {
                // start quote
                if (isSingleQuote(currentChar())) {
                    if (!buf.isEmpty()) {
                        tokens.add(new Token(TokenType.WORD, buf.toString()));
                        buf.setLength(0);
                    }
                    inSingleQuote = true;
                } else if (isWhitespace(currentChar())) {
                    if (!buf.isEmpty()) {
                        tokens.add(new Token(TokenType.WORD, buf.toString()));
                        buf.setLength(0);
                    }
                    buf.append(currentChar());
                    skipWhitespace();
                    continue;
                } else {
                    buf.append(currentChar());
                }
            }
            this.pos++;
        }

        if (!buf.isEmpty()) {
            tokens.add(new Token(TokenType.WORD, buf.toString()));
        }

        if (inSingleQuote) {
            throw new RuntimeException("Unclosed single quote");
        }

        return tokens;
    }

    private boolean isWhitespace(char c) {
        return c == ' ' || c == '\t' || c == '\n' || c == '\r';
    }

    private boolean isSingleQuote(char c) {
        return c == '\'';
    }

    private boolean isDoubleQuote(char c) {
        return c == '"';
    }

    private boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isSpecial(char c) {
        return c == '(' || c == ')' || c == '[' || c == ']' || c == '{' || c == '}' || c == '<' || c == '>' || c == ',' || c == '.' || c == ':' || c == ';';
    }

    private boolean isAlphaNumeric(char c) {
        return isLetter(c) || isDigit(c);
    }

    private boolean isEnd() {
        return pos >= input.length();
    }

    private char currentChar() {
        return input.charAt(pos);
    }

    private void skipWhitespace() {
        while (!isEnd() && isWhitespace(currentChar())) {
            pos++;
        }
    }

    private void skipComment() {
        while (!isEnd() && currentChar() != '\n') {
            pos++;
        }
    }

    private void skipQuote() {
        while (!isEnd() && currentChar() != '\"') {
            pos++;
        }
    }

    private void skipString() {
        while (!isEnd() && currentChar() != '\"') {
            pos++;
        }
    }

    private void skipNumber() {
        while (!isEnd() && isDigit(currentChar())) {
            pos++;
        }
    }

    private void skipIdentifier() {
        while (!isEnd() && isAlphaNumeric(currentChar())) {
            pos++;
        }
    }

    private void skipSpecial() {
        while (!isEnd() && isSpecial(currentChar())) {
            pos++;
        }
    }
}
