/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.TimeZone;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Jhfar
 */
public class Appointment {
    IntegerProperty appointmentId = new SimpleIntegerProperty();
    IntegerProperty customerId = new SimpleIntegerProperty();
    IntegerProperty userId = new SimpleIntegerProperty();
    
    IntegerProperty startYear = new SimpleIntegerProperty();
    IntegerProperty startMonth = new SimpleIntegerProperty();
    IntegerProperty startDate = new SimpleIntegerProperty();
    IntegerProperty startHour = new SimpleIntegerProperty();
    IntegerProperty startMinute = new SimpleIntegerProperty();

    IntegerProperty endYear = new SimpleIntegerProperty();
    IntegerProperty endMonth = new SimpleIntegerProperty();
    IntegerProperty endDate = new SimpleIntegerProperty();
    IntegerProperty endHour = new SimpleIntegerProperty();
    IntegerProperty endMinute = new SimpleIntegerProperty();
    
    StringProperty title = new SimpleStringProperty();
    StringProperty description = new SimpleStringProperty();
    StringProperty location = new SimpleStringProperty();
    StringProperty contact = new SimpleStringProperty();
    StringProperty type = new SimpleStringProperty();
    StringProperty url = new SimpleStringProperty();
    StringProperty start = new SimpleStringProperty();
    StringProperty end = new SimpleStringProperty();
    
    TimeZone GMT = TimeZone.getTimeZone("GMT");
    TimeZone myTimeZone = TimeZone.getDefault();
    Calendar startTime = Calendar.getInstance(GMT);
    Calendar endTime = Calendar.getInstance(GMT);

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId.set(appointmentId);
    }

    public void setCustomerId(Integer customerId) {
        this.customerId.set(customerId);
    }

    public void setUserId(Integer userId) {
        this.userId.set(userId);
    }

    public void setStartYear(Integer startYear) {
        this.startYear.set(startYear);
    }

    public void setStartMonth(Integer startMonth) {
        this.startMonth.set(startMonth);
    }

    public void setStartDate(Integer startDate) {
        this.startDate.set(startDate);
    }

    public void setStartHour(Integer startHour) {
        this.startHour.set(startHour);
    }

    public void setStartMinute(Integer startMinute) {
        this.startMinute.set(startMinute);
    }

    public void setEndYear(Integer endYear) {
        this.endYear.set(endYear);
    }

    public void setEndMonth(Integer endMonth) {
        this.endMonth.set(endMonth);
    }

    public void setEndDate(Integer endDate) {
        this.endDate.set(endDate);
    }

    public void setEndHour(Integer endHour) {
        this.endHour.set(endHour);
    }

    public void setEndMinute(Integer endMinute) {
        this.endMinute.set(endMinute);
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public void setStart(String start) {
        this.start.set(start);
    }

    public void setEnd(String end) {
        this.end.set(end);
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public void setLocation(String location) {
        this.location.set(location);
    }

    public void setContact(String contact) {
        this.contact.set(contact);
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public void setUrl(String url) {
        this.url.set(url);
    }

    public Integer getAppointmentId() {
        return appointmentId.get();
    }

    public Integer getCustomerId() {
        return customerId.get();
    }

    public Integer getUserId() {
        return userId.get();
    }

    public Integer getStartYear() {
        return startYear.get();
    }

    public Integer getStartMonth() {
        return startMonth.get();
    }

    public Integer getStartDate() {
        return startDate.get();
    }

    public Integer getStartHour() {
        return startHour.get();
    }

    public Integer getStartMinute() {
        return startMinute.get();
    }

    public Integer getEndYear() {
        return endYear.get();
    }

    public Integer getEndMonth() {
        return endMonth.get();
    }

    public Integer getEndDate() {
        return endDate.get();
    }

    public Integer getEndHour() {
        return endHour.get();
    }

    public Integer getEndMinute() {
        return endMinute.get();
    }

    public String getTitle() {
        return title.get();
    }

    public String getStart() {
        return start.get();
    }

    public String getEnd() {
        return end.get();
    }

    public String getDescription() {
        return description.get();
    }

    public String getLocation() {
        return location.get();
    }

    public String getContact() {
        return contact.get();
    }

    public String getType() {
        return type.get();
    }

    public String getUrl() {
        return url.get();
    }
    
    public Appointment(Integer appointmentId) throws SQLException {
        this.appointmentId.set(appointmentId);
        Query.makeQuery("SELECT * FROM appointment WHERE appointmentId = " + appointmentId);
        ResultSet result = Query.getResult();
        result.first();

         startTime.setTimeInMillis(result.getTimestamp("start").getTime());
         endTime.setTimeInMillis(result.getTimestamp("end").getTime());

         customerId.set(result.getInt("customerId")); 
         userId.set(result.getInt("userId")); 

         title.set(result.getString("title"));
         description.set(result.getString("description"));
         location.set(result.getString("location"));
         contact.set(result.getString("contact"));
         type.set(result.getString("type"));
         url.set(result.getString("url"));

	 DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss'.0'");
	 String startString = result.getString("start");
	 String endString = result.getString("end");
	 
	 LocalDateTime ldtStart = LocalDateTime.parse(startString, dt);
	 LocalDateTime ldtEnd = LocalDateTime.parse(endString, dt);
	 
	 ZoneId zid = ZoneId.systemDefault();
	 ZoneId GMT = ZoneId.of("UTC");
	 ZonedDateTime zdtStart = ldtStart.atZone(GMT); 
	 ZonedDateTime zdtEnd = ldtEnd.atZone(GMT);

	 startYear.set(zdtStart.getDayOfYear()); 
         startMonth.set(zdtStart.getMonthValue()); 
         startDate.set(zdtStart.getDayOfMonth());
         startHour.set(zdtStart.getHour()); 
         startMinute.set(zdtStart.getMinute());

         endYear.set(zdtEnd.getYear());
         endMonth.set(zdtEnd.getMonthValue());
         endDate.set(zdtEnd.getDayOfMonth());
         endHour.set(zdtEnd.getHour());
         endMinute.set(zdtEnd.getMinute());

	 Timestamp tsStart = Timestamp.valueOf(zdtStart.toLocalDateTime());
	 Timestamp tsEnd = Timestamp.valueOf(zdtEnd.toLocalDateTime());
	 Timestamp tstest = Timestamp.valueOf(result.getString("start"));

	 start.set(tsStart.toString());
	 end.set(tsEnd.toString());
	 
	 Instant instant = Instant.now();
	 ZoneOffset currentOffsetForMyZone = zid.getRules().getOffset(instant);
	
	 ZonedDateTime test = tstest.toInstant().atZone(GMT);
	 System.out.println(ldtStart.atOffset(currentOffsetForMyZone));

    }
    
    
    
}
