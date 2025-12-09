import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Execute implements Command {
    @Override
    public boolean execute(String[] args) {
        String programName = args[0];
        List<String> programArgs = new ArrayList<>();
        programArgs.add(programName);  // First arg is the program name
        // If there are more arguments, add them individually
        if (args.length > 1) {
            // Split the rest of the args string by spaces
            String[] additionalArgs = args[1].split("\\s+");
//            System.arraycopy(additionalArgs, 0, programArgs, 1, additionalArgs.length);
            programArgs.addAll(List.of(additionalArgs));
        }

        if (!Commands.isKeyExist(programName)) {
            String path = System.getenv("PATH");
            String[] paths = path.split(File.pathSeparator);
            for (String p : paths) {
                File dir = new File(p);
                File commandFile = new File(dir, programName);
                if (commandFile.exists() && commandFile.canExecute()) {

                    ProcessBuilder pb = new ProcessBuilder().directory(dir);
                    pb.command(programArgs);
                    try {
                        // Start the process
                        Process process = pb.start();
                        // Read the output
                        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                        String line;
                        while ((line = reader.readLine()) != null) {
                            System.out.println(line);
                        }
                        int exitCode = process.waitFor();
                        return true;
                    } catch (IOException | InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                    return true;
                }
            }
            System.out.println(programName + ": not found");
        }
        return true;
    }
}
