/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Model.MythConnections.conn;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Jhfar
 */
public class Query {
    private static String query;
    private static Statement stmt;
    private static ResultSet result;
    
    public static void makeQuery(String q){
        query = q;
        
        try{
            stmt = conn.createStatement();
            
            if(query.toLowerCase().startsWith("select"))
                result = stmt.executeQuery(query);
           
            if (query.toLowerCase().startsWith("delete") || query.toLowerCase().startsWith("insert") || query.toLowerCase().startsWith("update"))
                stmt.executeUpdate(query);
            
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public static ResultSet getResult() {
        return result;
    }

    static void makeQuery() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
