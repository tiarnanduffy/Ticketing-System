/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseData;

import java.io.*;
import java.util.Date;
/**
 *
 * @author DevUser
 */
public class Customer implements Serializable
{
    
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private java.util.Date dateOfBirth;
    private String email;
    private String phoneNumber;
    private String securityQuestion;
    private String securityAnswer;
    private boolean seasonTicket;
    private int customerID;

    public Customer(int customerID, String username, String password, String firstname, String lastname, Date dateOfBirth, String email, String phoneNumber, String securityQuestion, String securityAnswer, boolean seasonTicket) {
        
        this.customerID = customerID;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.seasonTicket = seasonTicket;
        
    }

    @Override
    public String toString() {
        return "Customer{" + "username=" + username + ", password=" + password + ", firstname=" + firstname + ", lastname=" + lastname + ", dateOfBirth=" + dateOfBirth + ", email=" + email + ", phoneNumber=" + phoneNumber + ", securityQuestion=" + securityQuestion + ", securityAnswer=" + securityAnswer + ", seasonTicket=" + seasonTicket + ", customerID=" + customerID + '}';
    }
    
    private java.util.Date convertSqlToUtilDate(java.sql.Date sqlDate) {
        return new java.util.Date(sqlDate.getTime());
    }
    
    private java.sql.Date convertUtilToSqlDateTime(java.util.Date utilDate) {
        return new java.sql.Date(utilDate.getTime());
    }
    
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public void setSeasonTicket(boolean seasonTicket) {
        this.seasonTicket = seasonTicket;
    }


    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public boolean isSeasonTicket() {
        return seasonTicket;
    }

    public int getCustomerID() {
        return customerID;
    }
    
}
