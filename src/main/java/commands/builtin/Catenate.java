package commands.builtin;

import dto.InputDto;
import commands.Command;
import commands.CommandType;

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
        return "catenate";
    }

    @Override
    public void execute(InputDto input) {
        StringBuffer data = new StringBuffer();

        for (String arg : input.args()) {
            data.append(readFile(arg));
        }
        System.out.println(data.toString());
    }

    public StringBuffer readFile(String path) {
        StringBuffer data = new StringBuffer();
                File file = new File(path);
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                data.append(sc.nextLine());
                if (sc.hasNextLine()) {
                    data.append("\n"); // Or System.lineSeparator()
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        return data;
    }

}


//cat "file name" "'file name' with spaces"