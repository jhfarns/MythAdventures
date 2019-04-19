package View_Controller;

import Model.Appointment;
import Model.Query;
import java.io.IOException;
import java.sql.ResultSet;
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
    void updateFunction() throws IOException {
	//add a loader for the selected customer
        FXMLLoader homeScreen = new FXMLLoader(getClass().getResource("AppointmentsUpdate.fxml"));
        AnchorPane homeScreenPane = homeScreen.load();
        rootPane.getChildren().setAll(homeScreenPane);
    }
    
    @FXML
    void deteleFunction(){
        return;
    }

    private ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    private ResultSet result;

    public void initialize () {
	
	try {
		Query.makeQuery("SELECT * FROM appointment");
		result = Query.getResult();
		result.beforeFirst();
		while(result.next()){
			allAppointments.add(new Appointment(result.getInt("appointmentId")));
		}

	} catch (Exception ex){
		System.out.println(ex.getMessage());
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
}
