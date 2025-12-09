
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    static void main(String[] args) {
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
                    running = Commands.getCommand("./").execute(arguments);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            scanner.close();
        }
    }
}
