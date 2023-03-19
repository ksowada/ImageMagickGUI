import java.util.TreeMap;
import java.util.Iterator;
public class ImgAutoWeb {
    public static void main(String[] args) {
        System.out.println("img-auto");
        TreeMap<String,String> parameters = new TreeMap();

        // handle args seperately
        for (int i = 0; i < args.length; i++) {
//            System.out.println(args[i]);
            String arg = args[i].strip(); // choose the actual arg and strip whitespace for unknown reasons
            if (arg.startsWith("-")) {
                arg = arg.substring(1); // cut minus for easy handling
                String[] argParts = arg.split(":");
                if (argParts.length>2) {
                    System.out.println("ERROR: in argument nr:"+(i+1)+" detect more than 1 seperator ':'");
                    continue;
                }
                // a valid seeming argument consisting of a single seperator ':'
                if (argParts.length==2) {
                    parameters.put(argParts[0], argParts[1]);
                }
            }
        }
        // debug parametrs
        Iterator<String> it = parameters.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            String val = parameters.get(key);
            System.out.println(key+":"+val);
        }
    }
}

