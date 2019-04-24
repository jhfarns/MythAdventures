package View_Controller;

import Model.MythConnections;
import Model.Query;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
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
    private ListView<String> tableViewCustomer;

    @FXML
    private ListView<String> tableViewUsers;

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
       
	//Check start and end month to ensure they are in the appropriate range and throw an error if they are not
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
	
	//Stop overlapping appts
	PreparedStatement overlapping = MythConnections.preparedStatement("SELECT * FROM appointment "
		+ "WHERE userId = ?");
	overlapping.setInt(1, Query.currentId());
	overlapping.executeQuery();
	ResultSet overlappingResult = overlapping.getResultSet();
	overlappingResult.beforeFirst();
	
	if(startHourOfDay < 8 || startHourOfDay > 17) {
		Alert error = new Alert(Alert.AlertType.ERROR, "The hour of the day that appointment is being scheduled is outsides of the local business operating hours, please reschedule apointment for a time that is within 8am to 5pm", ButtonType.CLOSE);
                    error.showAndWait();
	}else{
		//set all values to those in the text box for start and end time
		try {
		calendarStart.set(startYear, startMonth, startDate, startHourOfDay, startMinute);
		calendarEnd.set(endYear, endMonth, endDate, endHourOfDay, endMinute);
		startTime = new java.sql.Timestamp(calendarStart.getTimeInMillis());
		endTime = new java.sql.Timestamp(calendarEnd.getTimeInMillis());

		//if this does not suffice, will have to increment startTime by minute until it reaches endtime and use the between check logic
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
		} catch(Exception ex){
		    Alert error = new Alert(Alert.AlertType.ERROR, "The User or Customer ID is incorrect, please review the lists of applicable IDs", ButtonType.CLOSE);
                    error.showAndWait();
		}
		//then verify that it updates it to UTC
	}
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
	    Integer temp1 = currentTime.get(currentTime.MONTH) + 1;
	    textFieldStartMonth.setText(Integer.toString(temp1));
	    textFieldStartDay.setText(Integer.toString(currentTime.get(currentTime.DATE)));
	    textFieldStartHour.setText(Integer.toString(currentTime.get(currentTime.HOUR_OF_DAY)));
	    textFieldStartMin.setText(Integer.toString(currentTime.get(currentTime.MINUTE)));

	    textFieldEndYear.setText(Integer.toString(currentTime.get(currentTime.YEAR)));
	    Integer temp = currentTime.get(currentTime.MONTH) + 1;
	    textFieldEndMonth.setText(Integer.toString(temp));
	    textFieldEndDay.setText(Integer.toString(currentTime.get(currentTime.DATE)));
	    textFieldEndHour.setText(Integer.toString(currentTime.get(currentTime.HOUR_OF_DAY)));
	    textFieldEndMin.setText(Integer.toString(currentTime.get(currentTime.MINUTE)));

	    ObservableList<String> customerList = FXCollections.observableArrayList();
	    ObservableList<String> userList = FXCollections.observableArrayList();
	    
	    PreparedStatement customerStatement = MythConnections.preparedStatement("SELECT customerId, customerName FROM customer");
	    customerStatement.executeQuery();

	    PreparedStatement userStatement = MythConnections.preparedStatement("SELECT userName, userId FROM user");
	    userStatement.executeQuery();

	    ResultSet customerResult = customerStatement.getResultSet();
	    ResultSet userResult = userStatement.getResultSet();
	    
	    customerResult.beforeFirst();
	    userResult.beforeFirst();
	    
	    while(customerResult.next()) {
		    customerList.add(customerResult.getString("customerId") + " - " + customerResult.getString("customerName"));

	    }

	    while(userResult.next()) {
		    userList.add(userResult.getString("userId") + " - " + userResult.getString("userName"));
	    }
	    
	   tableViewCustomer.setItems(customerList);
	   tableViewUsers.setItems(userList);
    }
}