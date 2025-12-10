import java.util.HashMap;
import java.util.Map;

public class Commands {
    private static final Map<String, Command> commands = new HashMap<>();
    static {
        commands.put("echo", new Echo());
        commands.put("type", new Type());
        commands.put("exit", new Exit());
        commands.put("pwd", new PrintWorkingDirectory());
        commands.put("cd", new ChangeDirectory());
        commands.put("./", new Execute());
    }

    public static Command getCommand(String command) {
        return commands.get(command);
    }

    public static boolean isKeyExist(String key) {
        return commands.containsKey(key);
    }
}
