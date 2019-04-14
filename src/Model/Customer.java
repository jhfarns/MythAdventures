/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.ResultSet;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Jhfar
 */
public class Customer {
    StringProperty name = new SimpleStringProperty();
    StringProperty address = new SimpleStringProperty();
    StringProperty city = new SimpleStringProperty();
    StringProperty country = new SimpleStringProperty();
    StringProperty postalCode = new SimpleStringProperty();
    StringProperty phone = new SimpleStringProperty();
    StringProperty fullAddress = new SimpleStringProperty();
    IntegerProperty custId = new SimpleIntegerProperty();
    
    
    public Customer(Integer custId){
        this.custId.set(custId);
        String address = "";
        String postalCode = "";
        String phone = "";
        String country = "";
        String city = "";
        String name = "";
        ResultSet result;
        Integer addressId;
        Integer cityId;
        Integer countryId;
                
        try{
            
        Query.makeQuery("SELECT * FROM customer WHERE customerId = " + custId);
        result = Query.getResult();
        result.first();
        name = result.getString("customerName");
        addressId = result.getInt("addressId");
        
        Query.makeQuery("SELECT * FROM address WHERE addressId = " + addressId);
        result = Query.getResult();
        result.first();
        address = result.getString("address");
        postalCode = result.getString("postalCode");
        phone = result.getString("phone");
        cityId = result.getInt("cityId");
        
        Query.makeQuery("SELECT * FROM city WHERE cityId = " + cityId);
        result = Query.getResult();
        result.first();
        city = result.getString("city"); 
        countryId = result.getInt("countryId");
        
        Query.makeQuery("SELECT * FROM country WHERE countryId = " + countryId);
        result = Query.getResult();
        result.first();
        country = result.getString("country");
        
       
        
        } catch (Exception ex) {
            ex.getMessage();
        }
        
        this.address.set(address);
        this.country.set(country);
        this.city.set(city);
        this.postalCode.set(postalCode);
        this.phone.set(phone);
        this.name.set(name);
        this.setFullAddress(this.address.get(), this.city.get(), this.country.get(), this.postalCode.get());
        
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public void setCustId(Integer custId) {
        this.custId.set(custId);
    }
    
    public void setPostal(String postal) {
        this.postalCode.set(postal);
    }
    
    public void setCity (String city) {
        this.city.set(city);
    }
    
    public void setCountry (String country) {
        this.country.set(country);
    }
    
    public void setFullAddress (String address, String city, String country, String postal) {
        this.fullAddress.set(address + " " + city + " " + country + " " + postal);
    }
    
    public String getName() {
        return name.get();
    }

    public String getAddress() {
        return address.get();
    }

    public String getPhone() {
        return phone.get();
    }

    public Integer getCustId() {
        return custId.get();
    }
    
    public String getPostal() {
        return postalCode.get();
    }
    
    public String getCity () {
       return city.get();
    }
    
    public String getCountry () {
        return country.get();
    }    
    
    public String getFullAddress () {
        return fullAddress.get();
    }
       
}
