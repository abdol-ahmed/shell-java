package commands.builtin;

import dto.InputDto;
import commands.Command;
import commands.CommandType;

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
        String dir = input.args().getFirst();

        if (dir.startsWith("/")) {
            changeDir(dir);
        } else if (dir.startsWith("~")) {
            String usersDir = System.getenv("HOME");
            Path path = Path.of(usersDir);
            changeDir(path.toString());
        }else {
            Path cwd = Paths.get(System.getProperty("user.dir"));
            String absolutePath = cwd.resolve(dir).normalize().toString();
            changeDir(absolutePath);
        }
    }

    private void changeDir(String dir) {
        if (Files.isDirectory(Path.of(dir)))
            System.setProperty("user.dir", dir);
        else
            System.out.printf("cd: %s: No such file or directory%n", dir);
    }
}