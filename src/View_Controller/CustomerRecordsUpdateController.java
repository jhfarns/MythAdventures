package View_Controller;

import Model.Customer;
import Model.MythConnections;
import Model.Query;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class CustomerRecordsUpdateController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label labedTitle;

    @FXML
    private Label labelName;

    @FXML
    private Label labelAddress;

    @FXML
    private Label labelPhoneNumber;

    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldPhone;

    @FXML
    private TextField textFieldAddress;

    @FXML
    private Button buttonSave;

    @FXML
    private Button buttonCancel;

    @FXML
    private Label labelCity;

    @FXML
    private Label labelPostal;

    @FXML
    private Label labelCountry;

    @FXML
    private TextField textFieldCity;

    @FXML
    private TextField textFieldCountry;

    @FXML
    private TextField textFieldPostal;

    @FXML
    private Label labelAddress2;

    @FXML
    private TextField textFieldAddress2;

    
    private Customer customer;
    private Integer custId;
    private Integer countryId;
    private Integer cityId;
    private Integer addressId;
    
    
    public void addCustomer(Integer custId) {
        this.customer = new Customer(custId);
        textFieldName.setText(customer.getName());
        textFieldAddress.setText(customer.getAddress());
        textFieldAddress2.setText(customer.getAddress2());
        textFieldCity.setText(customer.getCity());
        textFieldCountry.setText(customer.getCountry());
        textFieldPostal.setText(customer.getPostal());
        textFieldPhone.setText(customer.getPhone());
        
        this.custId = custId;
    }
    
    @FXML
    void cancelFunction(ActionEvent event) throws IOException {
        FXMLLoader homeScreen = new FXMLLoader(getClass().getResource("CustomerRecords.fxml"));
        AnchorPane homeScreenPane = homeScreen.load();
        rootPane.getChildren().setAll(homeScreenPane);
    }

    @FXML
    void saveFunction(ActionEvent event) throws SQLException, IOException {
        //Customer - update time/who changed
        PreparedStatement ps4 = MythConnections.preparedStatement("UPDATE customer SET customerName = ? WHERE customerId = ?");
        ps4.setString(1, textFieldName.getText());
        ps4.setInt(2, custId);
        ps4.executeUpdate();
        Query.makeQuery("SELECT * FROM customer WHERE customerId = " + custId);
        ResultSet result = Query.getResult();
        result.first();
        addressId = result.getInt("addressId");
        
        //Address - update time/who changed
        PreparedStatement ps3 = MythConnections.preparedStatement("UPDATE address SET address = ?, address2 = ?, postalCode = ?, phone = ? WHERE addressId = ?");
        ps3.setString(1, textFieldAddress.getText());
        ps3.setString(2, textFieldAddress2.getText());
        ps3.setString(3, textFieldPostal.getText());
        ps3.setString(4, textFieldPhone.getText());
        ps3.setInt(5, addressId);
        ps3.executeUpdate();
        Query.makeQuery("SELECT * FROM address WHERE addressId = " + addressId);
        result = Query.getResult();
        result.first();
        cityId = result.getInt("cityId");
        
        //City
        PreparedStatement ps2 = MythConnections.preparedStatement("UPDATE city SET city = ?, lastUpdate = NOW(), lastUpdateBy =?  WHERE cityId = ?");
        ps2.setString(1, textFieldCity.getText());
        ps2.setString(2, Query.currentUser());
        ps2.setInt(3, cityId);
        ps2.executeUpdate();
        Query.makeQuery("SELECT * FROM city WHERE cityId = " + cityId);
        result = Query.getResult();
        result.first();
        countryId = result.getInt("countryId");
        
        //Country
        PreparedStatement ps1 = MythConnections.preparedStatement("UPDATE country SET country = ?, lastUpdate = NOW(), lastUpdateby = ? WHERE countryId = ?");
        ps1.setString(1, textFieldCountry.getText());
        ps1.setString(2, Query.currentUser());
        ps1.setInt(3, countryId);
        ps1.executeUpdate();
        

        
        FXMLLoader homeScreen = new FXMLLoader(getClass().getResource("CustomerRecords.fxml"));
        AnchorPane homeScreenPane = homeScreen.load();
        rootPane.getChildren().setAll(homeScreenPane);
    }
    

}
