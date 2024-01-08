package utils;

public class TimestampUtil {
    private static final TimestampUtil UTIL = new TimestampUtil();

    private int ff, sec, mm, hh;
    private StringBuilder timestamp;

    private TimestampUtil() {
    }

    public static StringBuilder getTimestamp(StringBuilder timestamp) {
        UTIL.timestamp = timestamp;
        UTIL.convert();
        UTIL.createTimestamp();
        return UTIL.timestamp;
    }

    private void convert() {
        hh = Integer.parseInt(timestamp.substring(0, 2));
        mm = Integer.parseInt(timestamp.substring(3, 5));
        sec = Integer.parseInt(timestamp.substring(6, 8));
        ff = Integer.parseInt(timestamp.substring(9, 11));

        if (ff > 12)
            sec++;
        if (sec == 60)
            mm++;
        if (mm == 60)
            hh++;
    }

    private void createTimestamp() {
        timestamp = new StringBuilder();

        if (hh > 0)
            insertUnit(hh);
        insertUnit(mm);
        insertUnit(sec);
        timestamp.deleteCharAt(timestamp.length() - 1);
    }

    private void insertUnit(int unit) {
        if (unit < 10)
            timestamp.append('0').append(unit);
        else timestamp.append(unit);
        timestamp.append(':');
    }
}
