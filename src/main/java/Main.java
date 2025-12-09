import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.print("$ ");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String command = scanner.nextLine();
            switch (command) {
                case "exit":
                    System.exit(0);
                default:
                    System.out.println(command + ": command not found");
            }
            System.out.print("$ ");
        }
    }
}
