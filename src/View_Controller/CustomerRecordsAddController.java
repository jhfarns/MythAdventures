package View_Controller;

import Model.MythConnections;
import Model.Query;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class CustomerRecordsAddController {

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
    private TextField textFieldAddress2;
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
    
    Integer currentUser;
    Integer countryId;
    Integer cityId;
    Integer addressId;
    Integer custId;
    
    @FXML
    void cancelFunction() throws IOException {
        FXMLLoader homeScreen = new FXMLLoader(getClass().getResource("CustomerRecords.fxml"));
        AnchorPane homeScreenPane = homeScreen.load();
        rootPane.getChildren().setAll(homeScreenPane);
    }
    
    @FXML void saveFunction() throws SQLException, IOException{
        //Country
        PreparedStatement ps = MythConnections.preparedStatement("INSERT INTO country (country, createDate, createdby, lastUpdate, lastUpdateBy) VALUES (?,NOW(),?,NOW(),?)");
        ps.setString(1, textFieldCountry.getText());
        ps.setString(2, Query.currentUser());
        ps.setString(3, Query.currentUser());
        ps.executeUpdate();
        countryId = Query.generatedKeys(ps);

        //City
        PreparedStatement ps2 = MythConnections.preparedStatement("INSERT INTO city (city, countryId, createDate, createdBy, lastUpdate, lastUpdateBy) VALUES (?,?, NOW(), ?, NOW(), ?)");
        ps2.setString(1, textFieldCity.getText());
        ps2.setInt(2, countryId);
        ps2.setString(3, Query.currentUser());
        ps2.setString(4, Query.currentUser()); 
        ps2.executeUpdate();
        cityId = Query.generatedKeys(ps2);
       
        //Address
        PreparedStatement ps3 = MythConnections.preparedStatement("INSERT INTO address (address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, LastUpdateBy) VALUES (?,?,?,?,?, NOW(), ?, NOW(), ?)");
        ps3.setString(1, textFieldAddress.getText());
        ps3.setString(2, textFieldAddress2.getText());
        ps3.setInt(3, cityId);
        ps3.setString(4, textFieldPostal.getText());
        ps3.setString(5, textFieldPhone.getText());
        ps3.setString(6, Query.currentUser());
        ps3.setString(7, Query.currentUser()); 
        ps3.executeUpdate();
        addressId = Query.generatedKeys(ps3);
        
        //Customer
        PreparedStatement ps4 = MythConnections.preparedStatement("INSERT INTO customer (customerName, addressId, active, createDate, createdBy, lastUpdate,lastUpdateBy) VALUES (?,?,?, NOW(), ?, NOW(), ?)");
        ps4.setString(1, textFieldName.getText());
        ps4.setInt(2, addressId);
        ps4.setInt(3, 1);
        ps4.setString(4, Query.currentUser());
        ps4.setString(5, Query.currentUser());
        ps4.executeUpdate();
        custId = Query.generatedKeys(ps4);
        
        FXMLLoader homeScreen = new FXMLLoader(getClass().getResource("CustomerRecords.fxml"));
        AnchorPane homeScreenPane = homeScreen.load();
        rootPane.getChildren().setAll(homeScreenPane);
        
    }
    
    public void initialize() {
        //Add new entry into the customer, have to update dependent tables.
    }

}