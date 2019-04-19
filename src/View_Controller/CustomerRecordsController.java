package View_Controller;

import Model.Customer;
import Model.MythConnections;
import Model.Query;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    @FXML void deleteFunction() throws IOException, SQLException{
        
        Customer selectedCustomer = tableColumnCustomerRecords.getSelectionModel().getSelectedItem();
        Integer custId = selectedCustomer.getCustId();
        Integer addressId;
        Integer cityId;
        Integer countryId;
        
        //Drop customer out of observable list
        allCustomers.remove(selectedCustomer);
        
        //Customer - update time/who changed
        System.out.println("Customer Id: " + custId);
        Query.makeQuery("SELECT * FROM customer WHERE customerId = " + custId);
        ResultSet result = Query.getResult();
        result.first();
        addressId = result.getInt("addressId");
        PreparedStatement ps4 = MythConnections.preparedStatement("DELETE FROM customer WHERE customerId = ?");
        ps4.setInt(1, custId);
        ps4.executeUpdate();
        
        
        //Address - update time/who changed
        System.out.println("Address Id: " + addressId);
        Query.makeQuery("SELECT * FROM address WHERE addressId = " + addressId);
        result = Query.getResult();
        result.first();
        cityId = result.getInt("cityId");
        PreparedStatement ps3 = MythConnections.preparedStatement("DELETE FROM address WHERE addressId = ?");
        ps3.setInt(1, addressId);
        ps3.executeUpdate();
        
        
        //City
        System.out.println("City Id: " + cityId);
        Query.makeQuery("SELECT * FROM city WHERE cityId = " + cityId);
        result = Query.getResult();
        result.first();
        countryId = result.getInt("countryId");
        PreparedStatement ps2 = MythConnections.preparedStatement("DELETE FROM city WHERE cityId = ?");
        ps2.setInt(1, cityId);
        ps2.executeUpdate();
        
        
        //Country
        System.out.println("Country Id: " + countryId);
        PreparedStatement ps1 = MythConnections.preparedStatement("DELETE FROM country WHERE countryId = ?");
        ps1.setInt(1, countryId);
        ps1.executeUpdate();

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
