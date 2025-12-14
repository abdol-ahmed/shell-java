
import commands.CommandFactory;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    static void main(String[] args) {
        var commandFactory = new CommandFactory();
        new Shell(commandFactory).startShell();
    }
}