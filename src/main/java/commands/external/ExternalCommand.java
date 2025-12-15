package commands.external;

import commands.Command;
import commands.CommandType;
import dto.InputDto;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public record ExternalCommand(CommandType commandType, String commandName, Path filePath) implements Command {

    @Override
    public void execute(InputDto input) {
        List<String> cmd = new ArrayList<>();
        cmd.add(commandName);
        cmd.addAll(input.args());

        var pb = new ProcessBuilder(cmd);

        String cwd = System.getProperty("user.dir");
        if (cwd != null && !cwd.isBlank()) {
            pb.directory(new File(cwd));
        }

        pb.redirectErrorStream(true);

        try {
            // Start the process
            Process process = pb.start();
            // Read the output
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }
            process.waitFor();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
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