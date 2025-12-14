import java.io.IOException;

public interface Command {
    CommandType getType();
    boolean execute(String[] args);
}
