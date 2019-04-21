package View_Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

public class AuditReportController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private ListView<String> listView;

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

    public void initialize() throws FileNotFoundException {

	String filename = "src/view_controller/audit/audit.txt";
	File file = new File(filename);
	Scanner inputFile = new Scanner(file);
	
	ObservableList<String> logLines = FXCollections.observableArrayList();
	
	while(inputFile.hasNext()){
		logLines.add(inputFile.nextLine());
	}
	inputFile.close();
	listView.setItems(logLines);
    }
}
