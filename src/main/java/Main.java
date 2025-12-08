import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.print("$ ");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        switch (command) {
            case "exit":
                System.out.println("Goodbye!");
                System.exit(0);
            default:
                System.out.printf(command + ": command not found");
                System.exit(0);
        }
    }
}
