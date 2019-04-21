package View_Controller;

import Model.Appointment;
import Model.MythConnections;
import Model.Query;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AppointmentsUpdateController {

    @FXML
    private AnchorPane rootPane;

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
    
    Appointment selectedAppointment;
    
    void getAppointment(Integer apptId) throws SQLException {
	    this.selectedAppointment = new Appointment(apptId);

	    textFieldCustomerId.setText(selectedAppointment.getCustomerId().toString());
	    textFieldUserId.setText(selectedAppointment.getUserId().toString());
	    textFieldTitle.setText(selectedAppointment.getTitle());
	    textFieldDescription.setText(selectedAppointment.getDescription());
	    textFieldLocation.setText(selectedAppointment.getLocation());
	    textFieldContact.setText(selectedAppointment.getContact());
	    textFieldType.setText(selectedAppointment.getType());
	    textFieldUrl.setText(selectedAppointment.getUrl());
	    textFieldStartYear.setText(selectedAppointment.getStartYear().toString());
	    textFieldStartMonth.setText(selectedAppointment.getStartMonth().toString());
	    textFieldStartDay.setText(selectedAppointment.getStartDate().toString());
	    textFieldStartHour.setText(selectedAppointment.getStartHour().toString());
	    textFieldStartMin.setText(selectedAppointment.getStartMinute().toString());
	    textFieldEndYear.setText(selectedAppointment.getEndYear().toString());
	    textFieldEndMonth.setText(selectedAppointment.getEndMonth().toString());
	    textFieldEndDay.setText(selectedAppointment.getEndDate().toString());
	    textFieldEndHour.setText(selectedAppointment.getEndHour().toString());
	    textFieldEndMin.setText(selectedAppointment.getEndMinute().toString());
    }

    @FXML
    void cancelFunction(ActionEvent event) throws IOException {
        FXMLLoader homeScreen = new FXMLLoader(getClass().getResource("Appointments.fxml"));
        AnchorPane homeScreenPane = homeScreen.load();
        rootPane.getChildren().setAll(homeScreenPane);
    }

    @FXML
    void saveFunction(ActionEvent event) throws SQLException, IOException {

	    String customerId;
	    String userId;
	    String title;
	    String description;
	    String location; 
	    String contact;
	    String type;
	    String url;
	    String startYear;
	    String startMonth;
	    String startDate;
	    String startHour;
	    String startMinute;
	    String endYear;
	    String endMonth;
	    String endDate;
	    String endHour;
	    String endMinute;
	    Timestamp startTime;
	    Timestamp endTime;
	    Calendar calendarStart = Calendar.getInstance();
	    Calendar calendarEnd = Calendar.getInstance();

	    customerId = textFieldCustomerId.getText();
	    userId = textFieldUserId.getText();
	    title = textFieldTitle.getText();
	    description = textFieldDescription.getText();
	    location = textFieldLocation.getText();
	    contact = textFieldContact.getText();
	    type = textFieldType.getText();
	    url = textFieldUrl.getText();
	    startYear = textFieldStartYear.getText();
	    startMonth = textFieldStartMonth.getText();
	    startDate = textFieldStartDay.getText();
	    startHour = textFieldStartHour.getText();
	    startMinute = textFieldStartMin.getText();
	    endYear = textFieldEndYear.getText();
	    endMonth = textFieldEndMonth.getText();
	    endDate = textFieldEndDay.getText();
	    endHour = textFieldEndHour.getText();
	    endMinute = textFieldEndMin.getText();
	    
	    calendarStart.set(Integer.parseInt(startYear), Integer.parseInt(startMonth), Integer.parseInt(startDate),Integer.parseInt(startHour),Integer.parseInt(startMinute));
	    calendarEnd.set(Integer.parseInt(endYear), Integer.parseInt(endMonth), Integer.parseInt(endDate),Integer.parseInt(endHour),Integer.parseInt(endMinute));
	    startTime = new java.sql.Timestamp(calendarStart.getTimeInMillis());
	    endTime = new java.sql.Timestamp(calendarEnd.getTimeInMillis());

	    PreparedStatement ps = MythConnections.preparedStatement("UPDATE "
		    + "appointment SET customerId = ?, userId = ?, title = ?,"
		    + "description = ?, location = ?, contact = ?, type =?, "
		    + "url = ?, start = ?, end = ?, lastUpdate = NOW(), "
		    + "lastUpdateBy = ? WHERE appointmentId = ?");
	    ps.setInt(1, Integer.parseInt(customerId));
	    ps.setInt(2, Integer.parseInt(userId));
	    ps.setString(3, title);
	    ps.setString(4, description);
	    ps.setString(5, location);
	    ps.setString(6, contact);
	    ps.setString(7, type);
	    ps.setString(8, url);
	    ps.setTimestamp(9, startTime);
	    ps.setTimestamp(10, endTime);
	    ps.setString(11, Query.currentUser());
	    ps.setInt(12, selectedAppointment.getAppointmentId());

	    ps.executeUpdate();

	    FXMLLoader homeScreen = new FXMLLoader(getClass().getResource("Appointments.fxml"));
            AnchorPane homeScreenPane = homeScreen.load();
            rootPane.getChildren().setAll(homeScreenPane);
    }

   public void initialize() {
  	 
    }
}
