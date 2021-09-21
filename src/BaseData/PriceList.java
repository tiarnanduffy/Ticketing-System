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
public class PriceList {

    ArrayList<Price> prices = new ArrayList<Price>();
    ArrayList<PricesBySection> pricesBySectionArray = new ArrayList<PricesBySection>();
    ArrayList<Double> defaultPrices = new ArrayList<Double>();

    public PriceList() {
        loadPrices();
    }

    public int size() {
        return prices.size();
    }

    public ArrayList<Price> getPrices() {
        return prices;
    }

    public void setPrices(ArrayList<Price> prices) {
        this.prices = prices;
    }

    public class PricesBySection {

        Integer sectionTicket;
        double priceBySection;

        public PricesBySection(Integer sectionTicket, double priceBySection) {
            this.sectionTicket = sectionTicket;
            this.priceBySection = priceBySection;
        }

        public Integer getSectionTicket() {
            return sectionTicket;
        }

        public void setSectionTicket(Integer sectionTicket) {
            this.sectionTicket = sectionTicket;
        }

        public double getPriceBySection() {
            return priceBySection;
        }

        public void setPriceBySection(double priceBySection) {
            this.priceBySection = priceBySection;
        }

        @Override
        public String toString() {
            return "PricesBySection{" + "sectionTicket=" + sectionTicket + ", priceBySection=" + priceBySection + '}';
        }

        

    }

    private void loadPrices() {
        prices = new ArrayList<Price>();
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection = DriverManager.getConnection(DBURL);
            //Using SQL SELECT Query
            PreparedStatement preparedStatement = connection.prepareStatement("select * from price");
            //Creaing Java ResultSet object
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Price thisPrice = new Price(
                        resultSet.getInt("PriceID"),
                        resultSet.getInt("GameID"),
                        resultSet.getInt("LevelTicket"),
                        resultSet.getInt("SectionTicket"),
                        resultSet.getDouble("Price"));
                prices.add(thisPrice);
            }

        } catch (Exception e) {
            System.out.println("Error in connection" + e);
        }

    }

    
    public void addPrice(Price p) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection = DriverManager.getConnection(DBURL);
            PreparedStatement preparedStatement = connection.prepareStatement("insert into price (GameID, LevelTicket, SectionTicket, Price)values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, p.getGameID());
            preparedStatement.setInt(2, p.getLevel());
            preparedStatement.setInt(3, p.getSection());
            preparedStatement.setDouble(4, p.getPrice());

            //Creaing Java ResultSet object
            int r = preparedStatement.executeUpdate();

            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                if (keys.next()) {
                    p.setPriceID((int) keys.getLong(1));
                }
            }
            loadPrices();
        } catch (Exception e) {
            System.out.println("Error in connection" + e);
        }
    }
    
    public void updatePrice(Price p) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection = DriverManager.getConnection(DBURL);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE price set price =" + p.getPrice() + "\n"
                    + "WHERE gameID =" + p.getGameID() + " AND levelticket =" + p.getLevel() + " AND sectionticket =" + p.getSection() 
                    );


            //Creaing Java ResultSet object
            int r = preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error in connection" + e);
        }
    }
    
    public void getPricesFromAccess(int thisGameID, int thisLevel) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection = DriverManager.getConnection(DBURL);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT SectionTicket, price.Price\n"
                    + "FROM price\n"
                    + "WHERE (GameID =" + thisGameID + ") AND (LevelTicket =" + thisLevel + ")\n"
                    + "GROUP BY SectionTicket, price.Price;");
            //Creaing Java ResultSet object
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PricesBySection sectionPrice = new PricesBySection(
                        resultSet.getInt("sectionticket"),
                        resultSet.getDouble("price"));
                pricesBySectionArray.add(sectionPrice);
            }
        } catch (Exception e) {
            System.out.println("Error in connection" + e);
        }
    }

    
    public ArrayList<PricesBySection> getPricesBySectionArray() {
        return pricesBySectionArray;
    }

}
