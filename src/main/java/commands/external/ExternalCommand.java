package commands.external;

import dto.InputDto;
import commands.Command;
import commands.CommandType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;

public record ExternalCommand(CommandType commandType, String commandName, Path filePath) implements Command {

    @Override
    public void execute(InputDto input) {
        var pb = new ProcessBuilder();
        pb.command(input.keywords());
        try {
            // Start the process
            Process process = pb.start();
            // Read the output
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public CommandType getType() {
        return CommandType.EXTERNAL;
    }

    @Override
    public String name() {
        return this.commandName;
    }
}
