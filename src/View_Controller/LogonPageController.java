package View_Controller;

import Model.Query;
import java.sql.ResultSet;
import java.util.Locale;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LogonPageController {

    @FXML
    private Label labelUsername;

    @FXML
    private Label labelPassword;

    @FXML
    private TextField textFieldUsername;

    @FXML
    private TextField textFieldPassword;

    @FXML
    private Button buttonLogin;
    
    @FXML
    private AnchorPane rootPane;
    
    @FXML Label labelLocation;

    @FXML
    void loginFunction(){
    //Take input from the text fields
    String username;
    String password;
    String query;
    ResultSet result;
    String dbUsername;
    String dbPassword;
    
    
    
    
    username = textFieldUsername.getText().toLowerCase();
    password = textFieldPassword.getText();
    
    System.out.println("This is the UserName " + username);
    System.out.println("This is the Password " + password);
    
    //Check the database to see if that username is in the database
    query = "SELECT * FROM user WHERE username = '" + username +"'";
    Query.makeQuery(query);
    result = Query.getResult();
    
    if(username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()){
        Alert error = new Alert(AlertType.ERROR, "Username or password field is empty/El campo de usuario o contraseña está vacío", ButtonType.CLOSE);
        error.showAndWait();
    } else{
        try{
        if(result.first()){
            //Check for username in database
            dbUsername = result.getString("username").toLowerCase();
            if(username.equals(dbUsername)){
                dbPassword = result.getString("password").toLowerCase();
                
                if(password.equals(dbPassword)){
                    String query2 = "UPDATE user SET active = 1 WHERE userId = '" + result.getInt("userId") + "'";
                    //take to the home page, set user to active and get timezone :)
                    
                    //set user to active
                    Query.makeQuery(query2);
                    
                    FXMLLoader homeScreen = new FXMLLoader(getClass().getResource("HomeScreen.fxml"));
                    AnchorPane homeScreenPane = homeScreen.load();
                    rootPane.getChildren().setAll(homeScreenPane);
                    
                   
                }else {
                    Alert error = new Alert(AlertType.ERROR, "Username or password was incorrect/Nombre de usuario o contraseña incorrecta.", ButtonType.CLOSE);
                    error.showAndWait();
                    
                }
            } else {
                //Pop error message here
                Alert error = new Alert(AlertType.ERROR, "Username or password was incorrect/Nombre de usuario o contraseña incorrecta.", ButtonType.CLOSE);
                error.showAndWait();
            }
            
        } else {
            //Pop up error message.
            Alert error = new Alert(AlertType.ERROR, "Username or password was incorrect/Nombre de usuario o contraseña incorrecta.", ButtonType.CLOSE);
            error.showAndWait();
        }
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    
    //If username is in the database check to see if the password matches.
    //If there is no username return error, needs to be in two languages.
    //If the password matches send to the main screen.
    //If the password does not match send return an error message, needs to be in two languages. 
    //Somewhere in here I need to use the Timezone class to find the user's location.
    }
    
    public void initialize() {
        Locale location = Locale.getDefault();
        labelLocation.setText("User's Location: " + location.getCountry());
	System.out.println(location.getDisplayLanguage());
	if(!location.getDisplayLanguage().equals("English")){
	labelUsername.setText("nombre de usuario");
	labelPassword.setText("contraseña");
	}
    }
}
