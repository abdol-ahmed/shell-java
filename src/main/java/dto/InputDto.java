package dto;

import java.util.List;

public record InputDto (
    String command,
    List<String> args,
    List<String> argv) {
}
