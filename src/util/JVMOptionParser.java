package util;

import java.util.Map;

public class JVMOptionParser {

    private JVMOptionParser() {
    }

    public static JVMOptionParser instance() {
        return instance;
    }

    public Map<String, String> getAllOptions() {
        return null;
    }

    public boolean isDefined(final String s) {
        return System.getProperty(s) != null;
    }

    public String value(final String s) {
        return System.getProperty(s);
    }

    private static final JVMOptionParser instance = new JVMOptionParser();
}
