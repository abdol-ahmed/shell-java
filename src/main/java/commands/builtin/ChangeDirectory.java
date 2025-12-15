package commands.builtin;

import commands.Command;
import commands.CommandType;
import dto.InputDto;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ChangeDirectory implements Command {
    private final CommandType type;

    public ChangeDirectory() {
        this.type = CommandType.BUILTIN;
    }

    public CommandType getType() {
        return type;
    }

    @Override
    public String name() {
        return "cd";
    }

    @Override
    public void execute(InputDto input) {
        String dir;

        if (input.args() == null || input.args().isEmpty()) {
            dir = System.getenv("HOME");
            if (dir == null || dir.isBlank()) {
                System.out.println("cd: HOME not set");
                return;
            }
        } else {
            dir = input.args().getFirst();
        }

        if (dir.startsWith("~")) {
            String home = System.getenv("HOME");
            if (home == null || home.isBlank()) {
                System.out.println("cd: HOME not set");
                return;
            }
            dir = home + dir.substring(1); // keeps suffix, handles "~" and "~/x"
        }

        Path target;
        if (Path.of(dir).isAbsolute()) {
            target = Path.of(dir);
        } else {
            Path cwd = Paths.get(System.getProperty("user.dir"));
            target = cwd.resolve(dir);
        }

        changeDir(target.normalize().toString());
    }

    private void changeDir(String dir) {
        Path normalized = Path.of(dir).toAbsolutePath().normalize();
        if (Files.isDirectory(normalized))
            System.setProperty("user.dir", normalized.toString());
        else
            System.out.printf("cd: %s: No such file or directory%n", dir);
    }
}