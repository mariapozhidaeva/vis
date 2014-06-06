package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NameGenerator {

    public static String getUniqName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss_S_");
        return dateFormat.format(date);
    }


    public static String buildFileName(String[] array) {
        StringBuilder sb = new StringBuilder();
        for (String str : array) {
            sb.append(str);
        }
        return sb.toString();
    }
}
