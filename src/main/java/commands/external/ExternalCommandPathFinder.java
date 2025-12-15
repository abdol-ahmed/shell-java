package commands.external;

import commands.Command;
import commands.CommandType;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class ExternalCommandPathFinder {

    public Optional<Command> findExternalCommand(String commandName) {
        if (commandName == null || commandName.isBlank()) {
            return Optional.empty();
        }
        String path = System.getenv("PATH");
        if (path == null || path.isBlank()) {
            return Optional.empty();
        }
        String[] paths = path.split(File.pathSeparator);
        for (String folderPath : paths) {
            if (folderPath == null || folderPath.isBlank()) continue;
            var commandPath = Path.of(folderPath, commandName);
            if (Files.exists(commandPath) && Files.isExecutable(commandPath)) {
                return Optional.of(new ExternalCommand(CommandType.EXTERNAL, commandName, commandPath));
            }
        }

        return Optional.empty();
    }
}
