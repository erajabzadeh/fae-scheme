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

    private static final JVMOptionParser instance = new JVMOptionParser();
}
