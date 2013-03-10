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
        int n = ss.size();
        for (String s : ss) {
            sb.append(s);

            if (--n > 0)
              sb.append(dovetail);
        }

        return sb.toString();
    }
}
