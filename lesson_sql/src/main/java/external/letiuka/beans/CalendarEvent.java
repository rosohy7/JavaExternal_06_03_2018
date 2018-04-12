package external.letiuka.beans;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class CalendarEvent implements Serializable {
    private int id;
    private String description;
    private Time startTime;
    private Time length;
    private Date date;
    private CalendarDate calendarDate;

    public CalendarDate getCalendarDate() {
        return calendarDate;
    }

    public void setCalendarDate(CalendarDate calendarDate) {
        this.calendarDate = calendarDate;
    }

    public CalendarEvent(String description, Time startTime, Time length) {
        this.description = description;
        this.startTime = startTime;
        this.length = length;
    }

    public CalendarEvent() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getLength() {
        return length;
    }

    public void setLength(Time length) {
        this.length = length;
    }

    public Date getDate() {
        if(calendarDate!=null && calendarDate.getDate()!=null)
            return calendarDate.getDate();
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
