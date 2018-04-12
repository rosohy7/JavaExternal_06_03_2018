package external.letiuka;

import external.letiuka.beans.CalendarDate;
import external.letiuka.beans.CalendarEvent;
import external.letiuka.controller.SqlController;

import java.sql.SQLException;
import java.text.ParseException;


public class Main {
    public static void main(String[] args) {
        SqlController controller = null;
        try {
            controller = new SqlController(
                    "jdbc:mysql://localhost/calendarevents?useSSL=false",
                    "innovation", "innovation");
            CalendarDate today = new CalendarDate(
                    SqlDateTimeLib.getDate("12-04-2018"), true);
            CalendarDate yesterday = new CalendarDate(
                    SqlDateTimeLib.getDate("11-04-2018"), true);
            CalendarEvent javaLesson=new CalendarEvent(
                    "Learning Log4j at external java classes.",
                    SqlDateTimeLib.getTime(14,30,0),
                    SqlDateTimeLib.getTime(3,0,0));
            javaLesson.setCalendarDate(today);

            //controller.addDate(today);
            //controller.addDate(yesterday);
            //controller.removeDate(yesterday.getDate());

            controller.updateAddEvent(javaLesson);
            controller.updateAddEvent(javaLesson);
            controller.updateAddEvent(javaLesson);

            /*
            controller.addEvent(javaLesson);
            javaLesson.setDescription("new description");
            controller.updateEvent(javaLesson);
            controller.addEvent(javaLesson);
            */
            controller.updateAddDate(yesterday);
            yesterday.setBusinessDay(false);
            controller.updateDate(yesterday);
            controller.printDates();
            controller.printEvents(today.getDate());
            controller.findEvent(22);
            controller.findDate(today.getDate());



        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            if(controller!=null) controller.close();
        }

    }
}
