package commands.external;

import commands.Command;
import commands.CommandFactory;
import commands.CommandType;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExternalCommandPathFinder {

    public Optional<Command> findExternalCommand(String commandName) {
        String path = System.getenv("PATH");
        String[] paths = path.split(File.pathSeparator);
        for (String folderPath : paths) {
            var commandPath = Path.of(folderPath, commandName);
            boolean fileExists = Files.exists(commandPath);
            boolean fileExecutable = Files.isExecutable(commandPath);
            if (fileExists && fileExecutable) {
                return Optional.of(new ExternalCommand(CommandType.EXTERNAL, commandName, commandPath));
            }
        }

        return Optional.empty();
    }
}
