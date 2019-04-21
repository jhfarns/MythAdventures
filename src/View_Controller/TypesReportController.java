package View_Controller;

import Model.Appointment;
import Model.TypesReport;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class TypesReportController {
   @FXML
    private AnchorPane rootPane;

    @FXML
    private TableView<TypesReport> tableViewType;

    @FXML
    private TableColumn<TypesReport, Integer> tableColumnJan;

    @FXML
    private TableColumn<TypesReport, Integer> tableColumnFeb;

    @FXML
    private TableColumn<TypesReport, Integer> tableColumnMar;

    @FXML
    private TableColumn<TypesReport, Integer> tableColumnApr;

    @FXML
    private TableColumn<TypesReport, Integer> tableColumnMay;

    @FXML
    private TableColumn<TypesReport, Integer> tableColumnJune;

    @FXML
    private TableColumn<TypesReport, Integer> tableColumnJuly;

    @FXML
    private TableColumn<TypesReport, Integer> tableColumnAug;

    @FXML
    private TableColumn<TypesReport, Integer> tableColumnSept;

    @FXML
    private TableColumn<TypesReport, Integer> tableColumnOct;

    @FXML
    private TableColumn<TypesReport, Integer> tableColumnNov;

    @FXML
    private TableColumn<TypesReport, Integer> tableColumnDec;

    @FXML
    private Button buttonHome;

    @FXML
    private Button buttonReports;
// USE OF LAMBDA JUSTIFICATION - When compared with all of the rest of the code
//it is much cleaner and more concise, there also is not something that is require
//in the FXML to tie the action to a method.
    public void initialize() throws SQLException {
	    
	TypesReport typesObject = new TypesReport();
	ObservableList<TypesReport> typeList = FXCollections.observableArrayList();
	typeList.add(typesObject);
	tableViewType.setItems(typeList);

	
	tableColumnJan.setCellValueFactory(new PropertyValueFactory("january"));
	tableColumnFeb.setCellValueFactory(new PropertyValueFactory("february"));
	tableColumnMar.setCellValueFactory(new PropertyValueFactory("march"));
	tableColumnApr.setCellValueFactory(new PropertyValueFactory("april"));
	tableColumnMay.setCellValueFactory(new PropertyValueFactory("may"));
	tableColumnJune.setCellValueFactory(new PropertyValueFactory("june"));
	tableColumnJuly.setCellValueFactory(new PropertyValueFactory("july"));
	tableColumnAug.setCellValueFactory(new PropertyValueFactory("august"));
	tableColumnSept.setCellValueFactory(new PropertyValueFactory("september"));
	tableColumnOct.setCellValueFactory(new PropertyValueFactory("october"));
	tableColumnNov.setCellValueFactory(new PropertyValueFactory("november"));
	tableColumnDec.setCellValueFactory(new PropertyValueFactory("december"));


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
