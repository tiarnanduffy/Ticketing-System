/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseData;

import java.io.Serializable;

/**
 *
 * @author DevUser
 */
public class Ticket implements Serializable {
    private int ticketID;
    private int customerID;
    private int gameID;
    private int level;
    private int section;
    private int row;
    private int seat;
    private double price;
    private boolean available;

    public Ticket(int customerID) {
        this.ticketID = 0;
        this.customerID = customerID;
        this.gameID = 0;
        this.level = 0;
        this.section = 0;
        this.row = 0;
        this.seat = 0;
        this.price = 0.00;
        this.available = true;
    }
    
    public Ticket(int ticketID, int customerID, int gameID, int level, int section, int row, int seat, double price, boolean available) {
        this.ticketID = ticketID;
        this.customerID = customerID;
        this.gameID = gameID;
        this.level = level;
        this.section = section;
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.available = available;
    }
    

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    
        public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public double getPrice() {
        return price;
    }

    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Ticket{" + "ticketID=" + ticketID + ", customerID=" + customerID + ", gameID=" + gameID + ", level=" + level + ", section=" + section + ", row=" + row + ", seat=" + seat + ", price=" + price + ", available=" + available + '}';
    }
    
    
}
