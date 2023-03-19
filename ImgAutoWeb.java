import java.io.IOException;
import java.util.TreeMap;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;

public class ImgAutoWeb {
    /**
     * call ImageMagick with some appreviation for easiness
     * @param args
     */
    final static int defSize = 987;
    public static void main(String[] args) {
        System.out.println("img-auto");
        TreeMap<String, String> parameters = new TreeMap();

        // handle args seperately
        for (int i = 0; i < args.length; i++) {
//            System.out.println(args[i]);
            String arg = args[i].strip().toLowerCase(); // choose the actual arg and strip whitespace for unknown reasons and lowercase it for easiness
            if (arg.startsWith("-")) {
                arg = arg.substring(1); // cut minus for easy handling
                String[] argParts = arg.split(":");
                if (argParts.length > 2) {
                    System.out.println("ERROR: in argument nr:" + (i + 1) + " detect more than 1 seperator ':'");
                    continue;
                }
                // a valid seeming argument consisting of a single seperator ':'
                if (argParts.length == 2) {
                    parameters.put(argParts[0], argParts[1]);
                }
                // a single argument without seperator ':', just give a key
                if (argParts.length == 1) {
                    parameters.put(argParts[0], "");
                }
            }
        }
        // print parameters
        Iterator<String> it = parameters.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            String val = parameters.get(key);
            System.out.println(key + ":" + val);
        }
        // call ImageMagick must be available on PATH environment-variable

        // add command and parameters for system call
        final Vector<String> processParameters = new Vector<String>();
        processParameters.add("magick"); // extern program name

        // set input file, as very first
        if (!parameters.containsKey("i")) {
            System.out.println("ERROR: input file is missing");
            return;
        }
        processParameters.add(parameters.get("i"));

        // set size
        if (parameters.containsKey("s")) {
            String paraSize = parameters.get("s");
            if (paraSize.startsWith("w")) {
                String paraSizeVal = paraSize.substring(1);
                try {
                    int size = Integer.parseInt(paraSizeVal);
                    processParameters.add("-resize " + size + "x");
                } catch (NumberFormatException e) {
                    System.out.println("ERROR: size is not a valid int val=" + paraSizeVal);
                }
            }
        } else if (parameters.containsKey("auto")) {
            processParameters.add("-resize " + defSize + "x");
        }

        // set quality or use auto


        // set output file, as very last
        if (!parameters.containsKey("o")) {
            System.out.println("ERROR: output file is missing");
            return;
        }
        processParameters.add(parameters.get("o"));

        ProcessBuilder builder = new ProcessBuilder(processParameters);
        Process process;
        try {
            process = builder.start();
        } catch (IOException e) {
            System.out.println("ERROR: process could not be started");
            throw new RuntimeException(e);
        }

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
