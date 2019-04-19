/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    Calendar startTime = Calendar.getInstance();
    Calendar endTime = Calendar.getInstance();

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

         startYear.set(startTime.get(Calendar.YEAR)); 
         startMonth.set(startTime.get(Calendar.MONTH)); 
         startDate.set(startTime.get(Calendar.DAY_OF_MONTH));
         startHour.set(startTime.get(Calendar.HOUR_OF_DAY)); 
         startMinute.set(startTime.get(Calendar.MINUTE));

         endYear.set(endTime.get(Calendar.YEAR));
         endMonth.set(endTime.get(Calendar.MONTH));
         endDate.set(endTime.get(Calendar.DAY_OF_MONTH));
         endHour.set(endTime.get(Calendar.HOUR_OF_DAY));
         endMinute.set(endTime.get(Calendar.MINUTE));

         title.set(result.getString("title"));
	 start.set(startTime.getTime().toString());
	 end.set(endTime.getTime().toString());
         description.set(result.getString("description"));
         location.set(result.getString("location"));
         contact.set(result.getString("contact"));
         type.set(result.getString("type"));
         url.set(result.getString("url"));
    }
    
    
    
}
