package View_Controller;

import Model.Customer;
import Model.Query;
import java.io.IOException;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class CustomerRecordsController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label labelCustomerRecords;

    @FXML
    private TableView<Customer> tableColumnCustomerRecords;

    @FXML
    private TableColumn<Customer, String> tableColumnName;

    @FXML
    private TableColumn<Customer, String> tableColumnAddress;

    @FXML
    private TableColumn<Customer, String> tableColumnPhone;

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonHome;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonUpdate;
    
    @FXML
    void homeFunction() throws IOException {
        FXMLLoader homeScreen = new FXMLLoader(getClass().getResource("HomeScreen.fxml"));
        AnchorPane homeScreenPane = homeScreen.load();
        rootPane.getChildren().setAll(homeScreenPane);
    }

    @FXML void addFunction() throws IOException{
        FXMLLoader homeScreen = new FXMLLoader(getClass().getResource("CustomerRecordsAdd.fxml"));
        AnchorPane homeScreenPane = homeScreen.load();
        rootPane.getChildren().setAll(homeScreenPane);
        
    }
    
    @FXML void updateFunction() throws IOException {
        //select the row to be updated.
        
        Customer selectedCustomer = tableColumnCustomerRecords.getSelectionModel().getSelectedItem();
        
        if(selectedCustomer != null){
            
            FXMLLoader homeScreen = new FXMLLoader(getClass().getResource("CustomerRecordsUpdate.fxml"));
            AnchorPane homeScreenPane = homeScreen.load();
            rootPane.getChildren().setAll(homeScreenPane);
            
            CustomerRecordsUpdateController addingCustomer = homeScreen.getController();
            addingCustomer.addCustomer(selectedCustomer.getCustId());
        }
        
        
    }
    
    @FXML void deleteFunction(){
        return;
    }
    
    private ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    private ResultSet customerResultSet;
    
    public void initialize() {
        //create customer class      (done)  
        //make an observable list containing all information for all customers in the database.
        //pull all customers from database
        try{
            Query.makeQuery("SELECT * FROM customer");
            customerResultSet = Query.getResult();
            System.out.println("Loop Started.");
            customerResultSet.beforeFirst();
            while(customerResultSet.next()){
            System.out.println(customerResultSet.getString("customerName"));
            allCustomers.add(new Customer(customerResultSet.getInt("customerId")));
            }
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        //set the tableview to have all of the observable containing the customer objects.
        tableColumnCustomerRecords.setItems(allCustomers);
        
        //set the value of each tableColumn to the customer object's member variable.
        tableColumnName.setCellValueFactory(new PropertyValueFactory<Customer,String>("name"));
        tableColumnAddress.setCellValueFactory(new PropertyValueFactory<Customer,String>("fullAddress"));
        tableColumnPhone.setCellValueFactory(new PropertyValueFactory<Customer,String>("phone"));
    }
}
