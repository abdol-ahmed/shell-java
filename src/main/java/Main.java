
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    static void main(String[] args) throws Exception {
        boolean running = true;
        try {
            while (running) {
                System.out.print("$ ");

                String input = scanner.nextLine().trim();
                if (input.isEmpty()) continue;

                String[] arguments = input.split("\\s+", 2);
                String command = arguments[0];
                Command cmd = Commands.getCommand(command);
                if (cmd != null) {
                    running = cmd.execute(arguments);
                } else {
                    System.out.println(command + ": command not found");
                }

            }
        } catch (Exception e) {
            running = false;
            throw new RuntimeException(e);
        } finally {
            running = false;
            scanner.close();
        }
    }
}
