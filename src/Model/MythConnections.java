/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Jhfar
 */
public class MythConnections {
    
    private static final String databaseName = "U05ceS";
    private static final String DB_URL = "jdbc:mysql://52.206.157.109/" + databaseName;
    private static final String username = "U05ceS";
    private static final String password = "53688464622";
    private static final String driver = "com.mysql.jdbc.Driver";
    static Connection conn;
    
    public static void makeConnection() throws ClassNotFoundException, SQLException, Exception{
        Class.forName(driver);
        conn = DriverManager.getConnection(DB_URL, username, password);
        System.out.println("Connection Successful, Welcome to Deva!");
    }
    
    public static void closeConnection() throws ClassNotFoundException, SQLException, Exception{
        conn.close();
        System.out.println("Connection Successful, Welcome back to Claw");
        
        
    //May consider adding a get current user function to return the current user
    
    }
}
