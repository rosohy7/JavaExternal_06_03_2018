package external.letiuka;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SqlDateTimeLib {
    private SqlDateTimeLib() {
    }

    ;

    public static Date getDate(String simpleDate) throws ParseException {
        DateFormat format = new SimpleDateFormat("dd-mm-yyyy");
        return new Date(format.parse(simpleDate).getTime());
    }

    public static Time getTime(int hours, int minutes, int seconds) {
        return new Time(1000 * (seconds + 60 * (minutes + 60 * (hours - 2))));
    }

}
