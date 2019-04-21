package View_Controller;

import Model.Appointment;
import Model.MythConnections;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class SchedulesReportController {

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
    private Button buttonHome;

    @FXML
    private Button buttonReports;

    @FXML
    void homeFunction(ActionEvent event) throws IOException {
	FXMLLoader homeScreen = new FXMLLoader(getClass().getResource("HomeScreen.fxml"));
        AnchorPane homeScreenPane = homeScreen.load();
        rootPane.getChildren().setAll(homeScreenPane);
    }

    @FXML
    void reportsFunction(ActionEvent event) throws IOException {
	FXMLLoader homeScreen = new FXMLLoader(getClass().getResource("Reports.fxml"));
        AnchorPane homeScreenPane = homeScreen.load();
        rootPane.getChildren().setAll(homeScreenPane);
    }

    public void initialize() throws SQLException {
	ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
	PreparedStatement ps = MythConnections.preparedStatement("SELECT *"
		+ "FROM appointment");
	ps.executeQuery();

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

	tableViewAppointments.getSortOrder().add(tableColumnUserId);
    }
}
