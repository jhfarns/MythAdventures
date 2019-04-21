package View_Controller;

import java.io.IOException;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class TypesReportController {
   @FXML
    private AnchorPane rootPane;

    @FXML
    private TableView<?> tableViewType;

    @FXML
    private TableColumn<?, ?> tableColumnMonth;

    @FXML
    private TableColumn<?, ?> tableColumnTypes;

    @FXML
    private Button buttonHome;

    @FXML
    private Button buttonReports;
// USE OF LAMBDA JUSTIFICATION - When compared with all of the rest of the code
//it is much cleaner and more concise, there also is not something that is require
//in the FXML to tie the action to a method.
    public void initialize() {
        buttonHome.setOnAction((event)-> {
	try {
	FXMLLoader homeScreen = new FXMLLoader(getClass().getResource("HomeScreen.fxml"));
        AnchorPane homeScreenPane = homeScreen.load();
        rootPane.getChildren().setAll(homeScreenPane);
	}catch(Exception ex){ex.getMessage();}});

	buttonReports.setOnAction((event)-> {
	try {
	FXMLLoader homeScreen = new FXMLLoader(getClass().getResource("Reports.fxml"));
        AnchorPane homeScreenPane = homeScreen.load();
        rootPane.getChildren().setAll(homeScreenPane);
	}catch(Exception ex){ex.getMessage();}});
    }
}
