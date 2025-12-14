package commands;

import dto.InputDto;

public interface Command {
    CommandType getType();
    String name();
    void execute(InputDto input);
}
