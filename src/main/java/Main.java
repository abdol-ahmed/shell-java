import commands.CommandFactory;

public class Main {
     public static void main(String[] args) {
        var commandFactory = new CommandFactory();
        new Shell(commandFactory).startShell();
    }
}