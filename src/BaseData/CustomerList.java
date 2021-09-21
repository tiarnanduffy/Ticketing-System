/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseData;

import static BaseData.MyConnection.DBURL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


/**
 *
 * @author DevUser
 */
public class CustomerList {

    ArrayList<Customer> customers = new ArrayList<Customer>();

    public CustomerList() {
        loadCustomers();
    }

    public int size() {
        return customers.size();
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }
    
    private java.sql.Date convertUtilToSqlDateTime(java.util.Date utilDate) {
        return new java.sql.Date(utilDate.getTime());
    }

    private java.util.Date convertSqlToUtilDate(java.sql.Date sqlDate) {
        return new java.util.Date(sqlDate.getTime());
    }


    private void loadCustomers() {
        customers = new ArrayList<Customer>();
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection = DriverManager.getConnection(DBURL);
            //Using SQL SELECT Query
            PreparedStatement preparedStatement = connection.prepareStatement("select * from customer");
            //Creaing Java ResultSet object
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Customer thisCust = new Customer(
                        resultSet.getInt("CustomerID"),
                        resultSet.getString("Username"),
                        resultSet.getString("Password"),
                        resultSet.getString("Firstname"),
                        resultSet.getString("Lastname"),
                        convertSqlToUtilDate(resultSet.getDate("DateOfBirth")),
                        resultSet.getString("Email"),
                        resultSet.getString("PhoneNumber"),
                        resultSet.getString("SecurityQuestion"),
                        resultSet.getString("SecurityAnswer"),
                        resultSet.getBoolean("SeasonTicket"));
                customers.add(thisCust);
            }
            

        } catch (Exception e) {
            System.out.println("Error in connection" + e);
        }
    }
    

    public void addCustomer(Customer c) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection = DriverManager.getConnection(DBURL);

            PreparedStatement preparedStatement = connection.prepareStatement("insert into Customer (Username,Password,Firstname,Lastname,DateOfBirth,Email,PhoneNumber,SecurityQuestion,SecurityAnswer,SeasonTicket)values(?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, c.getUsername());
            preparedStatement.setString(2, c.getPassword());
            preparedStatement.setString(3, c.getFirstname());
            preparedStatement.setString(4, c.getLastname());
            preparedStatement.setDate(5, convertUtilToSqlDateTime(c.getDateOfBirth()));
            preparedStatement.setString(6, c.getEmail());
            preparedStatement.setString(7, c.getPhoneNumber());
            preparedStatement.setString(8, c.getSecurityQuestion());
            preparedStatement.setString(9, c.getSecurityAnswer());
            preparedStatement.setBoolean(10, false);

            //Creaing Java ResultSet object
            int r = preparedStatement.executeUpdate();

            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                if (keys.next()) {
                    c.setCustomerID((int) keys.getLong(1));
                }
            }
            loadCustomers();
        } catch (Exception e) {
            System.out.println("Error in connection" + e);
        }
    }

    public void updateCustomer(Customer c) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection = DriverManager.getConnection(DBURL);

            PreparedStatement preparedStatement = connection.prepareStatement("update Customer set Username = ?,  Password = ?,  Firstname = ?,  Lastname = ?,  DateOfBirth = ?,  Email = ?,  PhoneNumber = ?,  SecurityQuestion = ?,  SecurityAnswer = ? where CustomerID = ?");

            preparedStatement.setString(1, c.getUsername());            
            preparedStatement.setString(2, c.getPassword());
            preparedStatement.setString(3, c.getFirstname());
            preparedStatement.setString(4, c.getLastname());
            preparedStatement.setDate(5, convertUtilToSqlDateTime(c.getDateOfBirth()));
            preparedStatement.setString(6, c.getEmail());
            preparedStatement.setString(7, c.getPhoneNumber());
            preparedStatement.setString(8, c.getSecurityQuestion());
            preparedStatement.setString(9, c.getSecurityAnswer());
            preparedStatement.setInt(10, c.getCustomerID());

            //Creaing Java ResultSet object
            int r = preparedStatement.executeUpdate();
            loadCustomers();
        } catch (Exception e) {
            System.out.println("Error in connection" + e);
        }
    }

}

/*
 while (resultSet.next()) {
 Customer thisCust = new Customer(
 resultSet.getInt("CustomerID"),
 resultSet.getString("Username"),
 resultSet.getString("Password"),
 resultSet.getString("Firstname"),
 resultSet.getString("Lastname"),
 resultSet.getDate("DateOfBirth"),
 resultSet.getString("Email"),
 resultSet.getString("PhoneNumber"),
 resultSet.getString("SecurityQuestion"),
 resultSet.getString("SecurityAnswer"),
 resultSet.getBoolean("SeasonTicket"));
 customers.add(thisCust);
 }
 */
