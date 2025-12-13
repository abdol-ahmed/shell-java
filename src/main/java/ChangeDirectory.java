import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class    ChangeDirectory implements Command {
    @Override
    public boolean execute(String[] args) {

        String arg = args.length > 1 ? args[1] : "";
        if (arg.isEmpty()) {
            System.setProperty("user.dir", System.getenv("HOME"));
            return true;
        }

        if (arg.equals("~")) {
            System.setProperty("user.dir", System.getenv("HOME"));
            return true;
        }

        Path inputPath = Paths.get(arg);
        Path cwd = Paths.get(System.getProperty("user.dir"));
        String absolutePath = cwd.resolve(inputPath).normalize().toString();

        File file = new File(absolutePath);
        if (file.exists() && file.isDirectory()) {
            System.setProperty("user.dir", absolutePath);
            return true;
        } else {
            System.out.println("cd: " + absolutePath + ": No such file or directory");
        }
        return true;
    }
}