/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseData;

import java.io.*;
import java.util.Date;
//import java.sql.*;

/**
 *
 * @author DevUser
 */
public class Game implements Serializable{
    private int gameID;
    private String opponent;
    private java.util.Date startDate;
    private java.util.Date startTime;
    private String description;
    private double defaultPrice;

    
    

    public Game(int gameID, String opponent, Date startDate, Date startTime, String description, double defaultPrice) {
        this.gameID = gameID;
        this.opponent = opponent;
        this.startDate = startDate;
        this.startTime = startTime;
        this.opponent = opponent;
        this.defaultPrice = defaultPrice;
        
    }

    @Override
    public String toString() {
        return "Game{" + "gameID=" + gameID + ", opponent=" + opponent + ", startDate=" + startDate + ", startTime=" + startTime + ", description=" + description + '}';
    }
    
    
    
    

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public Date getStartDate() {
        
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String Opponent) {
        this.opponent = Opponent;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(double defaultPrice) {
        this.defaultPrice = defaultPrice;
    }
    
    
}


