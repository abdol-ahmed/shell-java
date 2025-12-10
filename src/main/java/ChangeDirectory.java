import java.io.File;

public class    ChangeDirectory implements Command {
    @Override
    public boolean execute(String[] args) {
        String arg = args.length > 1 ? args[1] : "";
        if (arg.isEmpty()) {
            System.out.println(System.getProperty("user.home"));
            System.setProperty("user.dir", System.getProperty("user.home"));
            return true;
        }

        File file = new File(arg);
        if (file.exists() && file.isDirectory()) {
            System.setProperty("user.dir", arg);
            return true;
        } else {
            System.out.println("cd: " + arg + ": No such file or directory");
        }
        return true;
    }
}
