package de.ardoid.vars;

public class StringHelper {
    public static String assureTrailingSlash(String in) {
        String str = in;
        if (!str.endsWith("/")) str = str + "/";
        return str;
    }

    public static String surroundWithQuotes(String in) {
        String str = "\""+in+"\"";
        return str;
    }
}
