/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Model.MythConnections.conn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    public static Statement returnStmt() {
        return stmt;
    }
    
    public static String currentUser() throws SQLException{
        Query.makeQuery("SELECT * FROM user WHERE active = 1");
        ResultSet result = Query.getResult();
        result.first();
        String currentUser = result.getString("userName");
        return currentUser;
    }
    
        public static Integer currentId() throws SQLException{
        Query.makeQuery("SELECT * FROM user WHERE active = 1");
        ResultSet result = Query.getResult();
        result.first();
        Integer currentUser = result.getInt("userId");
        return currentUser;
    }
    
    public static Integer generatedKeys(PreparedStatement ps) throws SQLException {
        ResultSet result = ps.getGeneratedKeys();
        result.first();
        Integer generatedKey = result.getInt(1);
        return generatedKey;
    }

    public static boolean isAlpha(String string){
	  char[] chars = string.toCharArray();

	  for (char c : chars){
		if(!Character.isLetter(c)){
			return false;
		}
	  }
	return true;  
    }

    public static boolean isNumeric(String string){

	    try {
		    Double num = Double.parseDouble(string);
	    } catch(NumberFormatException e){
		    return false;
	    }
	    return true;
    }
}
