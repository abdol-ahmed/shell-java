package tokenizer;

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
        this.pos = 0;
        State state = State.NORMAL;
        boolean escapeNext = false;
        boolean quoted = false;

        while (!isEnd()) {
            char c = currentChar();

            switch (state) {
                case NORMAL -> {
                    if (escapeNext) {
                        buf.append(c);
                        escapeNext = false;
                        pos++;
                        continue;
                    }

                    if (c == '\\') {
                        escapeNext = true;
                        pos++;
                        continue;
                    }

                    if (Character.isWhitespace(c)) {
                        if (!buf.isEmpty()) {
                            tokens.add(new Token(quoted ? TokenType.STRING : TokenType.WORD, buf.toString()));
                            buf.setLength(0);
                            quoted = false;
                        }
                        pos++;
                    } else if (c == '\'') {
                        state = State.IN_SINGLE;
                        quoted = true;
                        pos++;
                    } else if (c == '"') {
                        state = State.IN_DOUBLE;
                        quoted = true;
                        pos++;
                    } else {
                        buf.append(c);
                        pos++;
                    }
                }
                case IN_SINGLE -> {
                    if (c == '\'') {
                        state = State.NORMAL;
                        pos++;
                    } else {
                        buf.append(c);
                        pos++;
                    }
                }
                case IN_DOUBLE -> {
                    if (c == '"') {
                        state = State.NORMAL;
                        pos++;
                    } else {
                        buf.append(c);
                        pos++;
                    }
                }
            }
        }

        if (escapeNext) {
            throw new IllegalArgumentException("Dangling escape (\\) at end of input");
        }

        if (state != State.NORMAL) {
            throw new IllegalArgumentException("Unterminated quote in input");
        }

        if (!buf.isEmpty()) {
            tokens.add(new Token(quoted ? TokenType.STRING : TokenType.WORD, buf.toString()));
        }

        return tokens;
    }

    private boolean isEnd() {
        return pos >= input.length();
    }

    private char currentChar() {
        return input.charAt(pos);
    }

}
