import java.io.File;

public class Type implements Command {
    @Override
    public boolean execute(String[] args) {
        String arg = args.length > 1 ? args[1] : "";
        if (Commands.isKeyExist(arg)) {
            System.out.println(arg + " is a shell builtin");
        } else {
            String path = System.getenv("PATH");
            String[] paths = path.split(File.pathSeparator);
            for (String p : paths) {
                File file = new File(p + "/" + arg);
                if (file.exists() && file.canExecute()) {
                    System.out.println(arg + " is " + file.getAbsolutePath());
                    return true;
                }
            }
            System.out.println(arg + ": not found");
        }
        return true;
    }
}
