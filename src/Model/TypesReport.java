/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Jhfar
 */
public class TypesReport {

	IntegerProperty january  = new SimpleIntegerProperty();
	IntegerProperty february  = new SimpleIntegerProperty();
	IntegerProperty march  = new SimpleIntegerProperty();
	IntegerProperty april  = new SimpleIntegerProperty();
	IntegerProperty may  = new SimpleIntegerProperty();
	IntegerProperty june  = new SimpleIntegerProperty();
	IntegerProperty july  = new SimpleIntegerProperty();
	IntegerProperty august  = new SimpleIntegerProperty();
	IntegerProperty september  = new SimpleIntegerProperty();
	IntegerProperty october  = new SimpleIntegerProperty();
	IntegerProperty november  = new SimpleIntegerProperty();
	IntegerProperty december  = new SimpleIntegerProperty();

	public Integer getJanuary() {
		return january.get();
	}

	public Integer getFebruary() {
		return february.get();
	}

	public Integer getMarch() {
		return march.get();
	}

	public Integer getApril() {
		return april.get();
	}

	public Integer getMay() {
		return may.get();
	}

	public Integer getJune() {
		return june.get();
	}

	public Integer getJuly() {
		return july.get();
	}

	public Integer getAugust() {
		return august.get();
	}

	public Integer getSeptember() {
		return september.get();
	}

	public Integer getOctober() {
		return october.get();
	}

	public Integer getNovember() {
		return november.get();
	}

	public Integer getDecember() {
		return december.get();
	}

	public ResultSet getResult() {
		return result;
	}

	ArrayList<IntegerProperty> listMonths = new ArrayList<IntegerProperty>();
	ArrayList<String> resultType = new ArrayList<String>();
	ResultSet result;
	Calendar resultMonth = Calendar.getInstance();
	//Calendar monthTracker = Calendar.getInstance();
	int monthTracker = 0;

	

	public TypesReport() throws SQLException{
		PreparedStatement ps = MythConnections.preparedStatement("SELECT"
			+ " type, start FROM appointment");
		ps.executeQuery();
		result = ps.getResultSet();
		result.beforeFirst();
		typesByMonth();
	}

	public void typesByMonth() throws SQLException {
		listMonths.add(january);
		listMonths.add(february);
		listMonths.add(march);
		listMonths.add(april);
		listMonths.add(may);
		listMonths.add(june);
		listMonths.add(july);
		listMonths.add(august);
		listMonths.add(september);
		listMonths.add(october);
		listMonths.add(november);
		listMonths.add(december);

		result.beforeFirst();
		//monthTracker.set(monthTracker.MONTH, monthTracker.getActualMaximum(monthTracker.MONTH));
		for(IntegerProperty month: listMonths){
			while(result.next()){
				resultMonth.setTimeInMillis(result.getTimestamp("start").getTime());
				if(resultMonth.get(resultMonth.MONTH) == monthTracker){
					if(!resultType.contains(result.getString("type"))){
						resultType.add(result.getString("type"));
					}	
				}
			}
		month.set(resultType.size());
		resultType.clear();
		monthTracker = monthTracker + 1;
		result.beforeFirst();
		}
		
	}
	
} 