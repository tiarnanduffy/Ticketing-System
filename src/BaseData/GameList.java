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
public class GameList {

    ArrayList<Game> games = new ArrayList<Game>();

    public GameList() {
        loadGames();
    }

    public int size() {
        return games.size();
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    private void loadGames() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection = DriverManager.getConnection(DBURL);
            //Using SQL SELECT Query
            PreparedStatement preparedStatement = connection.prepareStatement("select * from game");
            //Creaing Java ResultSet object
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Game thisG = new Game(
                        resultSet.getInt("GameID"),
                        resultSet.getString("Opponent"),
                        convertSqlToUtilDate(resultSet.getDate("GameDate")),
                        convertSqlToUtilTime(resultSet.getTime("GameTime")),
                        resultSet.getString("Description"),
                        resultSet.getDouble("DefaultPrice"));
                games.add(thisG);
            }
        } catch (Exception e) {
            System.out.println("Error in connection" + e);
        }
    }

    private java.sql.Date convertUtilToSqlDateTime(java.util.Date utilDate) {
        return new java.sql.Date(utilDate.getTime());
    }

    private java.util.Date convertSqlToUtilDate(java.sql.Date sqlDate) {
        return new java.util.Date(sqlDate.getTime());
    }

    private java.util.Date convertSqlToUtilTime(java.sql.Time sqlDate) {
        return new java.util.Date(sqlDate.getTime());
    }

    public int addGame(Game g) {
        
        
        int gameID = -1;
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection = DriverManager.getConnection(DBURL);

            PreparedStatement preparedStatement = connection.prepareStatement("insert into Game (Opponent,GameDate,GameTime,Description, DefaultPrice)values(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, g.getOpponent());
            preparedStatement.setObject(2, convertUtilToSqlDateTime(g.getStartDate()));
            preparedStatement.setObject(3, convertUtilToSqlDateTime(g.getStartTime()));
            preparedStatement.setString(4, g.getDescription());
            preparedStatement.setDouble(5, g.getDefaultPrice());

            //Creaing Java ResultSet object
            int r = preparedStatement.executeUpdate();

            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                if (keys.next()) {
                    gameID = (int) keys.getLong(1);
                    g.setGameID(gameID);
                    games.add(g);
                }
            }
     
        } catch (Exception e) {
            System.out.println("Error in connection" + e);
        }
        return gameID;
    }

    

}
