package de.ardoid.files;

import de.ardoid.vars.StringHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Vector;

public class Callee {
    /**
     * use Runtime.exec or ProcessBuilder alternatively
     */
    private static final boolean USE_EXEC = false;

    /** run a external program
     *
     * @param path path to cmd
     * @param cmd commandline command name
     * @param args multiple arguments for call
     * @return a Process representing the external execution
     */
    public static Process call(String path, String cmd, String... args) {
        final Vector<String> processParameters = new Vector<String>();

        String pathAndCmd = StringHelper.assureTrailingSlash(path) + cmd;
        processParameters.add(pathAndCmd);

        processParameters.addAll(Arrays.asList(args));

        String callLine = String.join(" ", processParameters.toArray(new String[0]));
        System.out.println("Callee.call():" + callLine);

        // run the command
        Process process;
        if (USE_EXEC) {
            Runtime runtime = Runtime.getRuntime();
            try {
                process = runtime.exec(processParameters.toArray(new String[0]));
//            process = runtime.exec(callLine);
            } catch (Exception e) {
                System.out.println("ERROR: process could not be started");
                throw new RuntimeException(e);
            }

        } else {
            ProcessBuilder builder = new ProcessBuilder(processParameters);
            try {
                process = builder.start();
            } catch (IOException e) {
                System.out.println("ERROR: process could not be started");
                throw new RuntimeException(e);
            }
        }
        return process;
    }
    public static void printProcessOutput(Process process) {
        StringBuilder out = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                out.append(line);
                out.append("\n");
            }
            System.out.println(out);
        } catch (Exception e) {
            System.out.println("ERROR: Exception at program execution");
        }
    }

}
