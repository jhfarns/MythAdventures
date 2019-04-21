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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class AppointmentsController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label labelAppointments;

    @FXML
    private TableView<Appointment> tableViewAppointments;

    @FXML
    private TableColumn<Appointment, Integer> tableColumnCustomerId;

    @FXML
    private TableColumn<Appointment, Integer> tableColumnUserId;

    @FXML
    private TableColumn<Appointment, String> tableColumnTitle;

    @FXML
    private TableColumn<Appointment, String> tableColumnType;

    @FXML
    private TableColumn<Appointment, String> tableColumnStart;

    @FXML
    private TableColumn<Appointment, String> tableColumnEnd;

    @FXML
    private TableColumn<Appointment, String> tableColumnUrl;

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonHome;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonUpdate;

    @FXML
    private RadioButton radioButtonWeek;

    @FXML
    private ToggleGroup ToggleGroup1;

    @FXML
    private RadioButton radioButtonMonth;

    ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

    @FXML
    void homeFunction() throws IOException {
        FXMLLoader homeScreen = new FXMLLoader(getClass().getResource("HomeScreen.fxml"));
        AnchorPane homeScreenPane = homeScreen.load();
        rootPane.getChildren().setAll(homeScreenPane);
    }
    
    @FXML
    void addFunction() throws IOException {
        FXMLLoader homeScreen = new FXMLLoader(getClass().getResource("AppointmentsAdd.fxml"));
        AnchorPane homeScreenPane = homeScreen.load();
        rootPane.getChildren().setAll(homeScreenPane);
    }
    
    @FXML 
    void updateFunction() throws IOException, SQLException {
	//add a loader for the selected customer
	
	Appointment selectedAppointment = tableViewAppointments.getSelectionModel().getSelectedItem();
	
	if(selectedAppointment != null){
		FXMLLoader homeScreen = new FXMLLoader(getClass().getResource("AppointmentsUpdate.fxml"));
		AnchorPane homeScreenPane = homeScreen.load();
		rootPane.getChildren().setAll(homeScreenPane);

		AppointmentsUpdateController controller = homeScreen.getController();
		controller.getAppointment(selectedAppointment.getAppointmentId());
	}
    }
    
    @FXML
    void deteleFunction() throws SQLException{
	    Appointment selectedAppointment = tableViewAppointments.getSelectionModel().getSelectedItem();
	    allAppointments.remove(selectedAppointment);

	    PreparedStatement ps = MythConnections.preparedStatement("DELETE "
		    + "FROM appointment WHERE appointmentId = ?");
	    ps.setInt(1, selectedAppointment.getAppointmentId());
	    ps.executeUpdate();
    }

    @FXML
    void weekFunction() throws SQLException {
	//Create two calendar instances, one for the miniumum and one for th 
	//maxium
	Calendar minimumCalendar = Calendar.getInstance();
	Calendar maximumCalendar = Calendar.getInstance();
	
	//set all of the time fields and the day_of_week_in_month fields
	//to miniumum and maximum values depending on the canedar
	Integer minYear = minimumCalendar.get(minimumCalendar.YEAR);
	Integer minDay = minimumCalendar.get(minimumCalendar.DAY_OF_MONTH) - (minimumCalendar.get(minimumCalendar.DAY_OF_WEEK) - 1);
	Integer minHour = minimumCalendar.getActualMinimum(minimumCalendar.HOUR_OF_DAY);
	Integer minMinute = minimumCalendar.getActualMinimum(minimumCalendar.MINUTE);
	Integer minSecond = minimumCalendar.getActualMinimum(minimumCalendar.SECOND);
	Integer minCurrentMonth = minimumCalendar.get(minimumCalendar.MONTH);

	Integer maxYear = maximumCalendar.get(maximumCalendar.YEAR);
	Integer maxDay = maximumCalendar.get(maximumCalendar.DAY_OF_MONTH) + (7 - (maximumCalendar.get(maximumCalendar.DAY_OF_WEEK)));
	Integer maxHour = maximumCalendar.getActualMaximum(maximumCalendar.HOUR_OF_DAY);
	Integer maxMinute = maximumCalendar.getActualMaximum(maximumCalendar.MINUTE);
	Integer maxSecond = maximumCalendar.getActualMaximum(maximumCalendar.SECOND);
	Integer maxCurrentMonth = maximumCalendar.get(maximumCalendar.MONTH);

	minimumCalendar.set(minYear, minCurrentMonth, minDay, minHour, minMinute, minSecond);
	maximumCalendar.set(maxYear, maxCurrentMonth, maxDay, maxHour, maxMinute, maxSecond);
	
	//change the calendars into sql timestamps
	Timestamp minTimestamp = new java.sql.Timestamp(minimumCalendar.getTimeInMillis());
	Timestamp maxTimestamp = new java.sql.Timestamp(maximumCalendar.getTimeInMillis());

	System.out.println("Day of Week min and max: ");
	System.out.println(minTimestamp);
	System.out.println(maxTimestamp);
	System.out.println();

	//create the PreparedStatement to return values between the minimum
	//and maximum values that also match the id of the currently logged on 
	//user

	PreparedStatement ps = MythConnections.preparedStatement("SELECT *"
		+ "FROM appointment WHERE start BETWEEN ? AND ? AND userId = ? ");
	ps.setTimestamp(1, minTimestamp);
	ps.setTimestamp(2, maxTimestamp);
	ps.setInt(3, Query.currentId());
	ps.executeQuery();

	allAppointments.clear();
	ResultSet appointmentResults = ps.getResultSet();
	appointmentResults.beforeFirst();
	while(appointmentResults.next()) {
		allAppointments.add(new Appointment(appointmentResults.getInt("appointmentId")));
	}

	tableViewAppointments.setItems(allAppointments);

	tableColumnCustomerId.setCellValueFactory(new PropertyValueFactory("customerId"));
	tableColumnUserId.setCellValueFactory(new PropertyValueFactory("userId"));
	tableColumnTitle.setCellValueFactory(new PropertyValueFactory("title"));
	tableColumnType.setCellValueFactory(new PropertyValueFactory("type"));
	tableColumnStart.setCellValueFactory(new PropertyValueFactory("start"));
	tableColumnEnd.setCellValueFactory(new PropertyValueFactory("end"));
	tableColumnUrl.setCellValueFactory(new PropertyValueFactory("url"));

    }

    @FXML
    void monthFunction() throws SQLException {
	//Create two calendar instances, one for the miniumum and one for th 
	//maxium
	Calendar minimumCalendar = Calendar.getInstance();
	Calendar maximumCalendar = Calendar.getInstance();
	
	//set all of the time fields and the day of the month fields
	//to miniumum and maximum values depending on the canedar

	Integer minYear = minimumCalendar.get(minimumCalendar.YEAR);
	Integer minDay = minimumCalendar.getActualMinimum(minimumCalendar.DAY_OF_MONTH);
	Integer minHour = minimumCalendar.getActualMinimum(minimumCalendar.HOUR_OF_DAY);
	Integer minMinute = minimumCalendar.getActualMinimum(minimumCalendar.MINUTE);
	Integer minSecond = minimumCalendar.getActualMinimum(minimumCalendar.SECOND);
	Integer minCurrentMonth = minimumCalendar.get(minimumCalendar.MONTH);

	Integer maxYear = maximumCalendar.get(maximumCalendar.YEAR);
	Integer maxDay = maximumCalendar.getActualMaximum(maximumCalendar.DAY_OF_MONTH);
	Integer maxHour = maximumCalendar.getActualMaximum(maximumCalendar.HOUR_OF_DAY);
	Integer maxMinute = maximumCalendar.getActualMaximum(maximumCalendar.MINUTE);
	Integer maxSecond = maximumCalendar.getActualMaximum(maximumCalendar.SECOND);
	Integer maxCurrentMonth = maximumCalendar.get(maximumCalendar.MONTH);

	minimumCalendar.set(minYear, minCurrentMonth, minDay, minHour, minMinute, minSecond);
	maximumCalendar.set(maxYear, maxCurrentMonth, maxDay, maxHour, maxMinute, maxSecond);
	
	//change the calendars into sql timestamps
	Timestamp minTimestamp = new java.sql.Timestamp(minimumCalendar.getTimeInMillis());
	Timestamp maxTimestamp = new java.sql.Timestamp(maximumCalendar.getTimeInMillis());

	System.out.println("Day of Month min and max: ");
	System.out.println(minTimestamp);
	System.out.println(maxTimestamp);
	System.out.println();

	//create the PreparedStatement to return values between the minimum
	//and maximum values that also match the id of the currently logged on 
	//user

	PreparedStatement ps = MythConnections.preparedStatement("SELECT *"
		+ "FROM appointment WHERE start BETWEEN ? AND ? AND userId = ? ");
	ps.setTimestamp(1, minTimestamp);
	ps.setTimestamp(2, maxTimestamp);
	ps.setInt(3, Query.currentId());
	ps.executeQuery();

	allAppointments.clear();
	ResultSet appointmentResults = ps.getResultSet();
	appointmentResults.beforeFirst();
	while(appointmentResults.next()) {
		allAppointments.add(new Appointment(appointmentResults.getInt("appointmentId")));
	}

	tableViewAppointments.setItems(allAppointments);

	tableColumnCustomerId.setCellValueFactory(new PropertyValueFactory("customerId"));
	tableColumnUserId.setCellValueFactory(new PropertyValueFactory("userId"));
	tableColumnTitle.setCellValueFactory(new PropertyValueFactory("title"));
	tableColumnType.setCellValueFactory(new PropertyValueFactory("type"));
	tableColumnStart.setCellValueFactory(new PropertyValueFactory("start"));
	tableColumnEnd.setCellValueFactory(new PropertyValueFactory("end"));
	tableColumnUrl.setCellValueFactory(new PropertyValueFactory("url"));
    }
    //Radio button when week is selected find the minium value for the day of the
    //week and then find the maximum value of the day of the week as related to the month
    //when then have the only thing that changes in the data base query with the radio
    //be the date leave the minimal date to 00:00:00.0 and the max to 23:59:59.9

    //Do the same thing for the day of the month where the query that is update
    // is determined by todays date and the min maxium of the current month.

    public void initialize () {

	//build the radio buttons' methods and then have a check performed for 
	//which button is performed then have it load that method and populate the 
	//tables

	if(radioButtonWeek.isSelected()){
		try {
			weekFunction();

		} catch (Exception ex){
			System.out.println(ex.getMessage());
		}
	} else {
		try {
			monthFunction();
		} catch (Exception ex){
			System.out.println(ex.getMessage());
		}	
	} 
    }
}
