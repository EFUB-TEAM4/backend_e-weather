package efub.team4.backend_eweather.global.util;

import java.util.UUID;

public class FileUtil {
    public static String generateFileName(final String fileName) throws NullPointerException {
        return UUID.randomUUID() + "-" + fileName.replace(" ", "_");
    }
}
