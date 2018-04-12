package external.letiuka.beans;

import java.io.Serializable;
import java.sql.Date;

public class CalendarDate implements Serializable {
    private Date date;
    private boolean businessDay;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isBusinessDay() {
        return businessDay;
    }

    public void setBusinessDay(boolean businessDay) {
        this.businessDay = businessDay;
    }

    public CalendarDate(Date date, boolean businessDay) {
        this.date = date;
        this.businessDay = businessDay;
    }

    public CalendarDate() {
    }
}
