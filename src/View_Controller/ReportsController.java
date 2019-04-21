package View_Controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class ReportsController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label labelReports;

    @FXML
    private Button buttonApptTypes;

    @FXML
    private Button buttonAudit;

    @FXML
    private Button buttonSchedules;

    @FXML
    private Button buttonHome;

    @FXML
    void homeFunction() throws IOException {
        FXMLLoader homeScreen = new FXMLLoader(getClass().getResource("HomeScreen.fxml"));
        AnchorPane homeScreenPane = homeScreen.load();
        rootPane.getChildren().setAll(homeScreenPane);
    }

    @FXML
    void typesFunction() throws IOException{
	FXMLLoader homeScreen = new FXMLLoader(getClass().getResource("typesReport.fxml"));
        AnchorPane homeScreenPane = homeScreen.load();
        rootPane.getChildren().setAll(homeScreenPane);
    }

    @FXML
    void schedulesFunction() throws IOException {
	FXMLLoader homeScreen = new FXMLLoader(getClass().getResource("schedules.fxml"));
        AnchorPane homeScreenPane = homeScreen.load();
        rootPane.getChildren().setAll(homeScreenPane);;
    }

    @FXML
    void auditFunction(){
	    return;
    }
}
