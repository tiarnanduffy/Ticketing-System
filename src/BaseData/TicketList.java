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
public class TicketList {

    ArrayList<Ticket> tickets = new ArrayList<Ticket>();

    private ArrayList<AvailableTicket> availableTickets = new ArrayList<AvailableTicket>();
    private ArrayList<AvailableTicketByLevel> availableLevelTickets = new ArrayList<AvailableTicketByLevel>();
    private ArrayList<AvailableTicketBySection> availableSectionTickets = new ArrayList<AvailableTicketBySection>();
    private ArrayList<AvailableTicketByRow> availableRowTickets = new ArrayList<AvailableTicketByRow>();
    private ArrayList<AvailableTicketBySeat> availableSeatTickets = new ArrayList<AvailableTicketBySeat>();

    public final static int NUMBER_OF_LEVELS = 2;
    public final static int NUMBER_OF_SECTIONS = 20;
    public final static int NUMBER_OF_ROWS = 10;
    public final static int NUMBER_OF_SEATS = 25;
    

    public final static int GAME_CAPACITY = 10000;
    public final static int LEVEL_CAPACITY = 5000;
    public final static int SECTION_CAPACITY = 250;
    public final static int ROW_CAPACITY = 25;
    public final static int SEAT_CAPACITY = 1;

    public class AvailableTicket {

        Integer GameID;
        Integer ticketsLeft;

        public AvailableTicket(int GameID, int ticketsLeft) {
            this.GameID = GameID;
            this.ticketsLeft = ticketsLeft;
        }

        public Integer getGameID() {
            return GameID;
        }

        public void setGameID(Integer GameID) {
            this.GameID = GameID;
        }

        public Integer getTicketsLeft() {
            return ticketsLeft;
        }

        public void setTicketsLeft(Integer ticketsLeft) {
            this.ticketsLeft = ticketsLeft;
        }

        @Override
        public String toString() {
            return "AvailableTicket{" + "GameID=" + GameID + ", ticketsLeft=" + ticketsLeft + '}';
        }

    }

    public class AvailableTicketByLevel {

        Integer levelID;
        Integer ticketsLeft;

        public AvailableTicketByLevel(int levelID, int ticketsLeft) {
            this.levelID = levelID;
            this.ticketsLeft = ticketsLeft;
        }

        public void setTicketsLeft(Integer ticketsLeft) {
            this.ticketsLeft = ticketsLeft;
        }

        public Integer getLevelID() {
            return levelID;
        }

        public Integer getTicketsLeft() {
            return ticketsLeft;
        }

        @Override
        public String toString() {
            return "AvailableTicketByLevel{" + "levelID=" + levelID + ", ticketsLeft=" + ticketsLeft + '}';
        }
        
    }

    public class AvailableTicketBySection {

        Integer sectionID;
        Integer ticketsLeft;

        public AvailableTicketBySection(int sectionID, int ticketsLeft) {
            this.sectionID = sectionID;
            this.ticketsLeft = ticketsLeft;
        }

        public void setTicketsLeft(Integer ticketsLeft) {
            this.ticketsLeft = ticketsLeft;
        }
        
        public Integer getSectionID() {
            return sectionID;
        }

        public Integer getTicketsLeft() {
            return ticketsLeft;
        }

    }
    
    public class AvailableTicketByRow {

        Integer rowID;
        Integer ticketsLeft;

        public AvailableTicketByRow(int rowID, int ticketsLeft) {
            this.rowID = rowID;
            this.ticketsLeft = ticketsLeft;
        }

        public void setTicketsLeft(Integer ticketsLeft) {
            this.ticketsLeft = ticketsLeft;
        }
        
        public Integer getRowID() {
            return rowID;
        }

        public Integer getTicketsLeft() {
            return ticketsLeft;
        }

    }

    public class AvailableTicketBySeat {

        Integer seatID;
        Integer ticketsLeft;

        public AvailableTicketBySeat(int seatID, int ticketsLeft) {
            this.seatID = seatID;
            this.ticketsLeft = ticketsLeft;
        }

        public void setTicketsLeft(Integer ticketsLeft) {
            this.ticketsLeft = ticketsLeft;
        }
        
        public Integer getSeatID() {
            return seatID;
        }

        public Integer getTicketsLeft() {
            return ticketsLeft;
        }

    }

    public TicketList() {
        loadTickets();
        populateAvailableLevel();
        populateAvailableSection();
        populateAvailableRow();
        populateAvailableSeat();
    }

    private void populateAvailableLevel() {
        for (int i = 1; i <= NUMBER_OF_LEVELS; i++) {
            AvailableTicketByLevel ticketsLeft = new AvailableTicketByLevel(i, LEVEL_CAPACITY);
            availableLevelTickets.add(ticketsLeft);
        }
    }
    
    private void populateAvailableSection() {
        for (int i = 1; i <= NUMBER_OF_SECTIONS; i++) {
            AvailableTicketBySection ticketsLeft = new AvailableTicketBySection(i, SECTION_CAPACITY);
            availableSectionTickets.add(ticketsLeft);
        }
    }
    
    private void populateAvailableRow() {
        for (int i = 1; i <= NUMBER_OF_ROWS; i++) {
            AvailableTicketByRow ticketsLeft = new AvailableTicketByRow(i, ROW_CAPACITY);
            availableRowTickets.add(ticketsLeft);
        }
    }
    
    private void populateAvailableSeat() {
        for (int i = 1; i <= NUMBER_OF_SEATS; i++) {
            AvailableTicketBySeat ticketsLeft = new AvailableTicketBySeat(i, SEAT_CAPACITY);
            availableSeatTickets.add(ticketsLeft);
        }
    }

    public int size() {
        return tickets.size();
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    private void loadTickets() {
        tickets = new ArrayList<Ticket>();
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection = DriverManager.getConnection(DBURL);
            //Using SQL SELECT Query
            PreparedStatement preparedStatement = connection.prepareStatement("select * from ticket");
            //Creaing Java ResultSet object
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Ticket thisTicket = new Ticket(
                        resultSet.getInt("TicketID"),
                        resultSet.getInt("CustomerID"),
                        resultSet.getInt("GameID"),
                        resultSet.getInt("LevelNo"),
                        resultSet.getInt("SectionNo"),
                        resultSet.getInt("Row"),
                        resultSet.getInt("Seat"),
                        resultSet.getDouble("Price"),
                        resultSet.getBoolean("Available"));
                tickets.add(thisTicket);
            }

        } catch (Exception e) {
            System.out.println("Error in connection" + e);
        }
    }

    public void getAvailTicketsByGame() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection = DriverManager.getConnection(DBURL);
            //Using SQL SELECT Query
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT gameid, Count(ticket.ticketid) AS CountOfticketid\n"
                    + "FROM ticket\n"
                    + "WHERE (((ticket.[available])=False))\n"
                    + "GROUP BY ticket.gameid;");
            //Creaing Java ResultSet object
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                AvailableTicket ticketsLeft = new AvailableTicket(
                        resultSet.getInt("GameID"),
                        GAME_CAPACITY - resultSet.getInt("CountOfticketid"));
                availableTickets.add(ticketsLeft);
            }

        } catch (Exception e) {
            System.out.println("Error in connection" + e);
        }
    }

    public void getAvailTicketsByLevel(int thisGameID) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection = DriverManager.getConnection(DBURL);
            //Using SQL SELECT Query
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT levelno, Count(ticket.ticketid) AS CountOfticketid\n"
                    + "FROM ticket\n"
                    + "WHERE (((ticket.available)=False) AND ((ticket.[gameid]) =" + thisGameID + "))" + "\n"
                    + "GROUP BY ticket.levelno");
            //Creaing Java ResultSet object
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int levelID = resultSet.getInt("levelno");
                for (int i = 0; i < availableLevelTickets.size(); i++) {
                    if (levelID == availableLevelTickets.get(i).getLevelID()) {
                        availableLevelTickets.get(i).setTicketsLeft(LEVEL_CAPACITY - resultSet.getInt("CountOfticketid"));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error in connection" + e);
        }

    }

    public void getAvailTicketsBySection(int thisGameID, int thisLevelID) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection = DriverManager.getConnection(DBURL);
            //Using SQL SELECT Query
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT sectionno, Count(ticket.ticketid) AS CountOfticketid\n"
                    + "FROM ticket\n"
                    + "WHERE (((ticket.available)=False) AND ((ticket.[gameid]) =" + thisGameID + ")" + "AND (levelno =" + thisLevelID + "))" + "\n"
                    + "GROUP BY ticket.sectionno");

            //Creaing Java ResultSet object
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int sectionID = resultSet.getInt("sectionno");
                for (int i = 0; i < availableSectionTickets.size(); i++) {
                    if (sectionID == availableSectionTickets.get(i).getSectionID()) {
                        availableSectionTickets.get(i).setTicketsLeft(SECTION_CAPACITY - resultSet.getInt("CountOfticketid"));
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Error in connection" + e);
        }

    }
    
    
    public void getAvailTicketsByRow(int thisGameID, int thisLevelID, int thisSectionID) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection = DriverManager.getConnection(DBURL);
            //Using SQL SELECT Query
            
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT ticket.row, Count(ticket.ticketid) AS CountOfticketid\n"
                    + "FROM ticket\n"
                    + "WHERE available=False AND gameid =" + thisGameID + " AND levelno =" + thisLevelID + " AND sectionno =" + thisSectionID + "\n"
                    + "GROUP BY ticket.row");

            //Creaing Java ResultSet object
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int sectionID = resultSet.getInt("row");
                for (int i = 0; i < availableRowTickets.size(); i++) {
                    if (sectionID == availableRowTickets.get(i).getRowID()) {
                        availableRowTickets.get(i).setTicketsLeft(ROW_CAPACITY - resultSet.getInt("CountOfticketid"));
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Error in connection" + e);
        }

    }
    
    public void getAvailTicketsBySeat(int thisGameID, int thisLevelID, int thisSectionID, int thisRowID) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection = DriverManager.getConnection(DBURL);
            //Using SQL SELECT Query
            
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT ticket.seat, Count(ticket.ticketid) AS CountOfticketid\n"
                    + "FROM ticket\n"
                    + "WHERE available=False AND gameid =" + thisGameID + " AND levelno =" + thisLevelID + " AND sectionno =" + thisSectionID + " AND ticket.row =" + thisRowID + "\n"
                    + "GROUP BY ticket.seat");

            //Creaing Java ResultSet object
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int sectionID = resultSet.getInt("seat");
                for (int i = 0; i < availableSeatTickets.size(); i++) {
                    if (sectionID == availableSeatTickets.get(i).getSeatID()) {
                        availableSeatTickets.get(i).setTicketsLeft(SEAT_CAPACITY - resultSet.getInt("CountOfticketid"));
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Error in connection" + e);
        }

    }

    public void addTicket(Ticket t) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection = DriverManager.getConnection(DBURL);

            PreparedStatement preparedStatement = connection.prepareStatement("insert into Ticket (CustomerID, GameID, LevelNo, SectionNo, Row, Seat, Price, Available)values(?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, t.getCustomerID());
            preparedStatement.setInt(2, t.getGameID());
            preparedStatement.setInt(3, t.getLevel());
            preparedStatement.setInt(4, t.getSection());
            preparedStatement.setInt(5, t.getRow());
            preparedStatement.setInt(6, t.getSeat());
            preparedStatement.setDouble(7, t.getPrice());
            preparedStatement.setBoolean(8, t.getAvailable());

            //Creaing Java ResultSet object
            int r = preparedStatement.executeUpdate();

            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                if (keys.next()) {
                    t.setTicketID((int) keys.getLong(1));
                }
            }
            loadTickets();
        } catch (Exception e) {
            System.out.println("Error in connection" + e);
        }
    }

    public ArrayList<AvailableTicket> getAvailableTickets() {
        return availableTickets;
    }

    public ArrayList<AvailableTicketByLevel> getAvailableLevelTickets() {
        return availableLevelTickets;
    }
    

    public ArrayList<AvailableTicketBySection> getAvailableSectionTickets() {
        return availableSectionTickets;
    }
    
    public ArrayList<AvailableTicketByRow> getAvailableRowTickets() {
        return availableRowTickets;
    }
    
    public ArrayList<AvailableTicketBySeat> getAvailableSeatTickets() {
        return availableSeatTickets;
    }
}
