package util;

import java.util.List;

public class StringUtils {

    private StringUtils() {}

    public static String join (List<String> ss, final String dovetail) {
        if (ss == null || ss.isEmpty())
            return "";

        if (ss.size() < 2)
            return ss.get(0);

        StringBuilder sb = new StringBuilder();
    
        for (String s : ss.subList(0, ss.size()-2)) {
            sb.append(s)
              .append(dovetail);
        }
        sb.append(ss.get(ss.size()-1));

        return sb.toString();
    }
}
