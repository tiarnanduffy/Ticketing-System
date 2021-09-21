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
public class Price implements Serializable{
    private int priceID;
    private int gameID;
    private int level;
    private int section;
    private double price;

    public Price(int priceID, int gameID, int level, int section, double price) {
        this.priceID = priceID;
        this.gameID = gameID;
        this.level = level;
        this.section = section;
        this.price = price;
    }

    

    public int getPriceID() {
        return priceID;
    }

    public void setPriceID(int priceID) {
        this.priceID = priceID;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Price{" + "priceID=" + priceID + ", gameID=" + gameID + ", level=" + level + ", section=" + section + ", price=" + price + '}';
    }
    
    

}
