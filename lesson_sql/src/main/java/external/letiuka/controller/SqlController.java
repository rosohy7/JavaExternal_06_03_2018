package external.letiuka.controller;

import com.sun.org.apache.xpath.internal.SourceTree;
import external.letiuka.beans.CalendarDate;
import external.letiuka.beans.CalendarEvent;

import java.sql.*;

public class SqlController {
    Connection cn = null;
    String connectionString, username, password;

    public SqlController(String connectionString, String username, String password) throws SQLException {
        this.connectionString = connectionString;
        this.username = username;
        this.password = password;
        cn = DriverManager.getConnection(connectionString, username, password);
    }

    public void close() {
        try {
            if (cn != null) cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addDate(CalendarDate date) throws SQLException {
        PreparedStatement st = cn.prepareStatement(
                "INSERT INTO dates(calendar_date, is_business_day)" +
                        "VALUES(?, ?)");
        st.setDate(1, date.getDate());
        st.setBoolean(2, date.isBusinessDay());
        if (st.executeUpdate() != 1)
            System.err.println("Failed to add " + date);
    }

    public void addEvent(CalendarEvent event) throws SQLException {
        PreparedStatement st = cn.prepareStatement(
                "INSERT INTO calendar_events(description, start_time, length, calendar_date)" +
                        "VALUES(?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
        st.setString(1, event.getDescription());
        st.setTime(2, event.getStartTime());
        st.setTime(3, event.getLength());
        st.setDate(4, event.getDate());
        if (st.executeUpdate() != 1)
            System.err.println("Failed to add " + event);
        ResultSet rs = st.getGeneratedKeys();
        if (rs.next()) {
            event.setId(rs.getInt(1));
        } else {
            System.err.println("Failed to extract an event id from the database on insert");
        }
    }

    public void removeDate(Date date) throws SQLException {
        PreparedStatement st = cn.prepareStatement(
                "DELETE FROM dates WHERE calendar_date = ?");
        st.setDate(1, date);
        int rows;
        if ((rows = st.executeUpdate()) != 1)
            System.err.println("Removed " + rows + " rows from dates");
    }

    public void removeEvent(int id) throws SQLException {
        PreparedStatement st = cn.prepareStatement(
                "DELETE FROM calendar_events WHERE id = ?");
        st.setInt(1, id);
        int rows;
        if ((rows = st.executeUpdate()) != 1)
            System.err.println("Removed " + rows + " rows from events");
    }

    public boolean updateDate(CalendarDate date) throws SQLException {
        if (date.getDate() == null) return false;
        PreparedStatement st = cn.prepareStatement(
                "UPDATE dates SET is_business_day = ? WHERE calendar_date = ?");
        st.setBoolean(1, date.isBusinessDay());
        st.setDate(2, date.getDate());
        int rows;
        if ((rows = st.executeUpdate()) != 1) {
            return false;
        } else return true;

    }

    public void updateAddDate(CalendarDate date) throws SQLException {
        if (!updateDate(date)) {
            addDate(date);
        }
    }

    public boolean updateEvent(CalendarEvent event) throws SQLException {
        if (event.getId() <= 0) return false;
        PreparedStatement st = cn.prepareStatement(
                "UPDATE calendar_events SET description = ?, " +
                        "start_time = ?, length = ?, calendar_date = ? WHERE id = ?");
        st.setString(1, event.getDescription());
        st.setTime(2, event.getStartTime());
        st.setTime(3, event.getLength());
        st.setDate(4, event.getDate());
        st.setInt(5, event.getId());
        int rows;
        if ((rows = st.executeUpdate()) != 1) {
            return false;
        } else return true;

    }

    public void updateAddEvent(CalendarEvent event) throws SQLException {
        if (!updateEvent(event)) {
            addEvent(event);
        }
    }

    public void printDates() throws SQLException {
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM dates");
        System.out.println("All dates");
        int i = 0;
        while (rs.next()) {
            i++;
            Date date = rs.getDate("calendar_date");
            boolean business = rs.getBoolean("is_business_day");
            System.out.println(i + ". " + date + " is" + (business ? "" : " not") + " a business day");
        }
    }

    public void printEvents(Date date) throws SQLException {
        PreparedStatement st = cn.prepareStatement("SELECT * FROM calendar_events WHERE calendar_date = ?");
        st.setDate(1, date);
        ResultSet rs = st.executeQuery();
        System.out.println("All events on " + date);
        int i = 0;
        while (rs.next()) {
            i++;
            int id = rs.getInt("id");
            String desc = rs.getString("description");
            Time start = rs.getTime("start_time");
            Time length = rs.getTime("length");
            System.out.println(i + ". #" + id + " " + desc);
            System.out.println("Starts at " + start + " and lasts " + length);
        }
    }

    public void findEvent(int id) throws SQLException {
        PreparedStatement st = cn.prepareStatement(
                "SELECT * FROM calendar_events WHERE id = ?");
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            String desc = rs.getString("description");
            Time start = rs.getTime("start_time");
            Time length = rs.getTime("length");
            System.out.println("Found event: #" + id + " " + desc);
            System.out.println("Starts at " + start + " and lasts " + length);
        }
        else{
            System.out.println("Have not found event #"+id);
        }
    }
    public void findDate(Date date) throws SQLException {
        PreparedStatement st = cn.prepareStatement(
                "SELECT * FROM dates WHERE calendar_date = ?");
        st.setDate(1, date);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            boolean business = rs.getBoolean("is_business_day");
            System.out.println("Found " + date + " which is" + (business ? "" : " not") + " a business day");
        }

        else{
            System.out.println("Have not found date "+date);
        }

    }


}
