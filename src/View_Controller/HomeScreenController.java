package View_Controller;

import java.io.IOException;
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
    void customerRecordsFunction() throws IOException {
        FXMLLoader homeScreen = new FXMLLoader(getClass().getResource("CustomerRecords.fxml"));
        AnchorPane homeScreenPane = homeScreen.load();
        rootPane.getChildren().setAll(homeScreenPane);
    }
    
    @FXML 
    void appointmentsFucntion() throws IOException {
        FXMLLoader homeScreen = new FXMLLoader(getClass().getResource("Appointments.fxml"));
        AnchorPane homeScreenPane = homeScreen.load();
        rootPane.getChildren().setAll(homeScreenPane);
    }
    
    @FXML
    void reportsFucntion() throws IOException {
        FXMLLoader homeScreen = new FXMLLoader(getClass().getResource("Reports.fxml"));
        AnchorPane homeScreenPane = homeScreen.load();
        rootPane.getChildren().setAll(homeScreenPane);
    }

}
