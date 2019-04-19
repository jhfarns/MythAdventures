package View_Controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class HomeScreenController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label lableHomePage;

    @FXML
    private Button buttonCustomerRecords;

    @FXML
    private Button buttonReports;

    @FXML
    private Button buttonAppointments;
    
    
    
    @FXML
    void customerRecordsFunction(ActionEvent event) throws IOException {
        FXMLLoader homeScreen = new FXMLLoader(getClass().getResource("CustomerRecords.fxml"));
        AnchorPane homeScreenPane = homeScreen.load();
        rootPane.getChildren().setAll(homeScreenPane);
    }
    
    @FXML 
    void appointmentsFunction(ActionEvent event) throws IOException {
        FXMLLoader appts = new FXMLLoader(getClass().getResource("Appointments.fxml"));
        AnchorPane apptsPane = appts.load();
        rootPane.getChildren().setAll(apptsPane);
    }
    
    @FXML
    void reportsFunction(ActionEvent event) throws IOException {
        FXMLLoader homeScreen = new FXMLLoader(getClass().getResource("Reports.fxml"));
        AnchorPane homeScreenPane = homeScreen.load();
        rootPane.getChildren().setAll(homeScreenPane);
    }

}
