import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.print("$ ");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String[] arguments = scanner.nextLine().trim().split(" ", 2);
            String command = arguments[0];
            String arg = arguments.length > 1 ? arguments[1] : "";
            switch (command) {
                case "echo":
                    System.out.println(arg);
                    break;
                case "exit":
                    System.exit(0);
                default:
                    System.out.println(command + ": command not found");
            }
            System.out.print("$ ");
        }
    }
}
