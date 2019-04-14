package View_Controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

public class AppointmentsController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label labelAppointments;

    @FXML
    private TableView<?> tableViewAppointments;

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

}
