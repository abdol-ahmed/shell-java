package commands.builtin;

import commands.Command;
import commands.CommandType;
import dto.InputDto;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Catenate implements Command {

    @Override
    public CommandType getType() {
        return CommandType.BUILTIN;
    }

    @Override
    public String name() {
        return "cat";
    }

    @Override
    public void execute(InputDto input) {
        StringBuilder data = new StringBuilder();

        for (String arg : input.args()) {
            data.append(readFile(arg)).append(" ");
        }
        data.deleteCharAt(data.length() - 1);
        System.out.println(data.toString());
    }

    public StringBuilder readFile(String path) {
        StringBuilder data = new StringBuilder();
                File file = new File(path);
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                data.append(sc.nextLine());
                if (sc.hasNextLine()) {
                    data.append("\n"); // Or System.lineSeparator()
                }
            }
        } catch (FileNotFoundException e) {
            System.out.printf("cat: %s: No such file or directory%n", path);
        }

        return data;
    }
}

//cat "/Volumes/HD1/Users/abdullah.ahmed/f\\n22" "/Volumes/HD1/Users/abdullah.ahmed/f\\57" "/Volumes/HD1/Users/abdullah.ahmed/f'\\'83"