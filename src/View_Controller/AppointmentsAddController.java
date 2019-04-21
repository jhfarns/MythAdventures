package View_Controller;

import Model.MythConnections;
import Model.Query;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AppointmentsAddController {

    @FXML
    private TextField textFieldStartMonth;

    @FXML
    private TextField textFieldStartYear;

    @FXML
    private TextField textFieldStartDay;

    @FXML
    private TextField textFieldStartMin;

    @FXML
    private TextField textFieldStartHour;

    @FXML
    private TextField textFieldEndMonth;

    @FXML
    private TextField textFieldEndDay;

    @FXML
    private TextField textFieldEndYear;

    @FXML
    private TextField textFieldEndHour;

    @FXML
    private TextField textFieldEndMin;

    @FXML
    private TextField textFieldCustomerId;

    @FXML
    private TextField textFieldUserId;

    @FXML
    private TextField textFieldTitle;

    @FXML
    private TextField textFieldDescription;

    @FXML
    private TextField textFieldLocation;

    @FXML
    private TextField textFieldContact;

    @FXML
    private TextField textFieldType;

    @FXML
    private TextField textFieldUrl;

    @FXML
    private Button buttonSave;

    @FXML
    private Button buttonCancel;
    
    @FXML
    private AnchorPane rootPane;
    
    TimeZone timeZone = TimeZone.getDefault();
    Locale locale = Locale.getDefault();
    
    private TimeZone GMT = TimeZone.getTimeZone("GMT");
    

    @FXML
    void saveFunction(ActionEvent event) throws SQLException, IOException {
        
        Calendar calendarStart = Calendar.getInstance();
        Calendar calendarEnd = Calendar.getInstance();

        Integer startYear = Integer.parseInt(textFieldStartYear.getText());
        Integer startMonth = Integer.parseInt(textFieldStartMonth.getText());
        Integer startDate = Integer.parseInt(textFieldStartDay.getText());
        Integer startHourOfDay = Integer.parseInt(textFieldStartHour.getText());
        Integer startMinute = Integer.parseInt(textFieldStartMin.getText());

        Integer endYear = Integer.parseInt(textFieldEndYear.getText());
        Integer endMonth = Integer.parseInt(textFieldEndMonth.getText());
        Integer endDate = Integer.parseInt(textFieldEndDay.getText());
        Integer endHourOfDay = Integer.parseInt(textFieldEndHour.getText());
        Integer endMinute = Integer.parseInt(textFieldEndMin.getText());

        String customerId = textFieldCustomerId.getText();
        String userId = textFieldUserId.getText();
        String title = textFieldTitle.getText();
        String description = textFieldDescription.getText();
        String location = textFieldLocation.getText();
        String contact = textFieldContact.getText();
        String type = textFieldType.getText();
        String url = textFieldUrl.getText();
        Timestamp startTime;
        Timestamp endTime;
        
        //set all values to those in the text box for start and end time
        calendarStart.set(startYear, startMonth, startDate, startHourOfDay, startMinute);
        calendarEnd.set(endYear, endMonth, endDate, endHourOfDay, endMinute);
        startTime = new java.sql.Timestamp(calendarStart.getTimeInMillis());
        endTime = new java.sql.Timestamp(calendarEnd.getTimeInMillis());
        //insert all fields into appointment table
        PreparedStatement ps = MythConnections.preparedStatement("INSERT INTO appointment "
                + "(customerId, userId, title, description, location, contact, type, url, start,"
                + " end, createDate, createdBy, lastUpdate, lastUpdateBy) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,NOW(),?,NOW(),?)");
        
        ps.setString(1, customerId);
        ps.setString(2, userId);
        ps.setString(3, title);
        ps.setString(4, description);
        ps.setString(5, location);
        ps.setString(6, contact);
        ps.setString(7, type);
        ps.setString(8, url);
        ps.setTimestamp(9, startTime);
        ps.setTimestamp(10,endTime);
        ps.setString(11, Query.currentUser());
        ps.setString(12, Query.currentUser());
        
        ps.executeUpdate();
        
        FXMLLoader homeScreen = new FXMLLoader(getClass().getResource("Appointments.fxml"));
        AnchorPane homeScreenPane = homeScreen.load();
        rootPane.getChildren().setAll(homeScreenPane);
        
        //verify that it inputs all fields
        //then verify that it updates it to UTC
    }
    
    @FXML
    void cancelFunction(ActionEvent event) throws IOException {
        FXMLLoader homeScreen = new FXMLLoader(getClass().getResource("Appointments.fxml"));
        AnchorPane homeScreenPane = homeScreen.load();
        rootPane.getChildren().setAll(homeScreenPane);
    }
    public void initialize() throws SQLException {
	    Calendar currentTime = Calendar.getInstance();

	    textFieldUserId.setText(Query.currentId().toString());

	    textFieldStartYear.setText(Integer.toString(currentTime.get(currentTime.YEAR)));
	    textFieldStartMonth.setText(Integer.toString(currentTime.get(currentTime.MONTH)));
	    textFieldStartDay.setText(Integer.toString(currentTime.get(currentTime.DATE)));
	    textFieldStartHour.setText(Integer.toString(currentTime.get(currentTime.HOUR_OF_DAY)));
	    textFieldStartMin.setText(Integer.toString(currentTime.get(currentTime.MINUTE)));

	    textFieldEndYear.setText(Integer.toString(currentTime.get(currentTime.YEAR)));
	    textFieldEndMonth.setText(Integer.toString(currentTime.get(currentTime.MONTH)));
	    textFieldEndDay.setText(Integer.toString(currentTime.get(currentTime.DATE)));
	    textFieldEndHour.setText(Integer.toString(currentTime.get(currentTime.HOUR_OF_DAY)));
	    textFieldEndMin.setText(Integer.toString(currentTime.get(currentTime.MINUTE)));
    }
}