package View_Controller;

import java.io.IOException;
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

    @FXML
    void cancelFunction(ActionEvent event) throws IOException {
        FXMLLoader homeScreen = new FXMLLoader(getClass().getResource("Appointments.fxml"));
        AnchorPane homeScreenPane = homeScreen.load();
        rootPane.getChildren().setAll(homeScreenPane);
    }

    @FXML
    void saveFunction(ActionEvent event) {

    }

   public void initialize() {
   
    }
}
