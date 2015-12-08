package fishing.sunshine.util;

/**
 * Created by sunshine on 12/8/15.
 */
public class SystemTeller {
    public static String tellPath(String path) {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.indexOf("windows") >= 0) {
            return path.replaceAll("/", "\\\\");
        }
        return path;
    }
}
