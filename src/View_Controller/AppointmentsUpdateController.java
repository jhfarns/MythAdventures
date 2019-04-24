package View_Controller;

import Model.Appointment;
import Model.MythConnections;
import Model.Query;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
	    Integer temp = selectedAppointment.getStartMonth() + 1;
	    textFieldStartMonth.setText(temp.toString());
	    textFieldStartDay.setText(selectedAppointment.getStartDate().toString());
	    textFieldStartHour.setText(selectedAppointment.getStartHour().toString());
	    textFieldStartMin.setText(selectedAppointment.getStartMinute().toString());
	    textFieldEndYear.setText(selectedAppointment.getEndYear().toString());
	    Integer temp2 = selectedAppointment.getEndMonth() + 1;
	    textFieldEndMonth.setText(temp2.toString());
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
	    Integer startYear = Integer.parseInt(textFieldStartYear.getText());
	    Integer startMonth = Integer.parseInt(textFieldStartMonth.getText());
	    Integer startDate = Integer.parseInt(textFieldStartDay.getText());
	    Integer startHour = Integer.parseInt(textFieldStartHour.getText());
	    Integer startMinute = Integer.parseInt(textFieldStartMin.getText());

	    Integer endYear = Integer.parseInt(textFieldEndYear.getText());
	    Integer endMonth = Integer.parseInt(textFieldEndMonth.getText());
	    Integer endDate = Integer.parseInt(textFieldEndDay.getText());
	    Integer endHour = Integer.parseInt(textFieldEndHour.getText());
	    Integer endMinute = Integer.parseInt(textFieldEndMin.getText());

	    	switch(startMonth){
		case 0:
		Alert zeroValue = new Alert(Alert.AlertType.ERROR, "Zero is not an accepted value. Please use 1-12", ButtonType.CLOSE);
                zeroValue.showAndWait();
		return;
		case 1:
		startMonth = startMonth - 1;
		break;
		case 2:
		startMonth = startMonth - 1;
		break;
		case 3:
		startMonth = startMonth - 1;
		break;
		case 4:
		startMonth = startMonth - 1;
		break;
		case 5:
		startMonth = startMonth - 1;
		break;
		case 6:
		startMonth = startMonth - 1;
		break;
		case 7:
		startMonth = startMonth - 1;
		break;
		case 8:
		startMonth = startMonth - 1;
		break;
		case 9:
		startMonth = startMonth - 1;
		break;
		case 10:
		startMonth = startMonth - 1;
		break;
		case 11:
		startMonth = startMonth - 1;
		break;
		case 12:
		startMonth = startMonth - 1;
		break;
		default:
		Alert greaterValue = new Alert(Alert.AlertType.ERROR, "You have entered a value that is outside of the approved range. Please select a value 1-12", ButtonType.CLOSE);
                greaterValue.showAndWait();	
		return;
	}
	
	switch(endMonth){
		case 0:
		Alert zeroValue = new Alert(Alert.AlertType.ERROR, "Zero is not an accepted value. Please use 1-12", ButtonType.CLOSE);
                zeroValue.showAndWait();
		return;
		case 1:
		endMonth = endMonth - 1;
		break;
		case 2:
		endMonth = endMonth - 1;
		break;
		case 3:
		endMonth = endMonth - 1;
		break;
		case 4:
		endMonth = endMonth - 1;
		break;
		case 5:
		endMonth = endMonth - 1;
		break;
		case 6:
		endMonth = endMonth - 1;
		break;
		case 7:
		endMonth = endMonth - 1;
		break;
		case 8:
		endMonth = endMonth - 1;
		break;
		case 9:
		endMonth = endMonth - 1;
		break;
		case 10:
		endMonth = endMonth - 1;
		break;
		case 11:
		endMonth = endMonth - 1;
		break;
		case 12:
		endMonth = endMonth - 1;
		break;
		default:
		Alert greaterValue = new Alert(Alert.AlertType.ERROR, "You have entered a value that is outside of the approved range. Please select a value 1-12", ButtonType.CLOSE);
                greaterValue.showAndWait();	
		return;
	}
	    
		if(startHour < 8 || startHour > 17) {
			Alert error = new Alert(Alert.AlertType.ERROR, "The hour of the day that appointment is being scheduled is outsides of the local business operating hours, please reschedule apointment for a time that is within 8am to 5pm", ButtonType.CLOSE);
			    error.showAndWait();
			    return;
		}
	    try{
		    calendarStart.set(startYear, startMonth, startDate,startHour,startMinute);
		    calendarEnd.set(endYear, endMonth, endDate,endHour,endMinute);
		    startTime = new java.sql.Timestamp(calendarStart.getTimeInMillis());
		    endTime = new java.sql.Timestamp(calendarEnd.getTimeInMillis());

		    PreparedStatement overlapping = MythConnections.preparedStatement("SELECT * FROM appointment "
		    + "WHERE userId = ?");
		    overlapping.setInt(1, Query.currentId());
		    overlapping.executeQuery();
		    ResultSet overlappingResult = overlapping.getResultSet();
		    overlappingResult.beforeFirst();
		    
		    while(overlappingResult.next()){
			Timestamp resultStart = new java.sql.Timestamp(overlappingResult.getTimestamp("start").getTime());
			Timestamp resultEnd = new java.sql.Timestamp(overlappingResult.getTimestamp("end").getTime());
			
			if(startTime.after(resultStart) && startTime.before(resultEnd)) {
				Alert error = new Alert(Alert.AlertType.ERROR, "Desired appointment is overlapping with appointment ID "
					+ overlappingResult.getInt("appointmentId"), ButtonType.CLOSE);
                    		error.showAndWait();	
				return;
			}
			if(endTime.after(resultStart) && endTime.before(resultEnd)) {
				Alert error = new Alert(Alert.AlertType.ERROR, "Desired appointment is overlapping with appointment ID "
					+ overlappingResult.getInt("appointmentId"), ButtonType.CLOSE);
                    		error.showAndWait();	
				return;
			}
		    }
		    if(startTime.after(endTime)){
			Alert error = new Alert(Alert.AlertType.ERROR, "Start Time is before End Time "
			, ButtonType.CLOSE);
                    	error.showAndWait();	
			return;
		}
		
		if (startTime.equals(endTime)){
			Alert error = new Alert(Alert.AlertType.ERROR, "Start and End time are the same "
			, ButtonType.CLOSE);
                    	error.showAndWait();	
			return;
		}

		Calendar curTime = Calendar.getInstance();
		Timestamp curTS = new java.sql.Timestamp(curTime.getTimeInMillis());

		if(startTime.before(curTS)){
			Alert error = new Alert(Alert.AlertType.ERROR, "Start Time has already happened. Please choose a time that is after the current time"
			, ButtonType.CLOSE);
                    	error.showAndWait();	
			return;
		}

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

	    } catch(Exception ex){
		    Alert error = new Alert(Alert.AlertType.ERROR, "The User or Customer ID is incorrect, please review the lists of applicable IDs", ButtonType.CLOSE);
                    error.showAndWait();
		}
    }

   public void initialize() {
  	 
    }
}
