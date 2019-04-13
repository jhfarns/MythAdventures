/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.MythConnections;
import Model.Query;
import java.io.IOException;
import java.sql.ResultSet;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Jhfar
 */
public class MythAdventures extends Application {
    

    
    @Override
    public void start(Stage primaryStage) throws IOException {
       
       
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LogonPage.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root, 1000, 800);
        
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
    }
        
    public static void main(String[] args) {
        
        try {
            MythConnections.makeConnection();
            launch(args);
            //Before connection close you need to set the user that was active
            //to inactive.
            String query1 = "SELECT * FROM user WHERE active = 1";
            Query.makeQuery(query1);
            ResultSet result1 = Query.getResult();
            result1.first();
            String query2 = "UPDATE user SET active = 0 WHERE userId = " + result1.getInt("userId");
            Query.makeQuery(query2);
            MythConnections.closeConnection();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
    
}
