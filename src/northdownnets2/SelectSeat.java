/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package northdownnets2;

import BaseData.Customer;
import BaseData.Game;
import BaseData.GameList;
import BaseData.Price;
import BaseData.PriceList;
import BaseData.Ticket;
import BaseData.TicketList;
import java.awt.Color;
import java.awt.Component;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javax.swing.JButton;

/**
 *
 * @author DevUser
 */
public class SelectSeat extends javax.swing.JFrame {

    public ArrayList<Game> games;
    public GameList gList;

    public Customer thisCustomer;

    public ArrayList<Ticket> tickets;
    public TicketList tList;
    public Ticket thisTicket;

    public PriceList pList;
    public ArrayList<Price> prices;

    
    //Public Variables to be amended and used throughout the class
    public int level = 0;
    public int section = 0;
    public int row = 0;
    public int seat = 0;
    double price = 40.0;

    int basket = 0;

    int count = 0;

    public SelectSeat(GameList gList, Customer c) {
        initComponents();

        tList = new TicketList();
        tickets = tList.getTickets();
        thisTicket = new Ticket(c.getCustomerID());

        games = gList.getGames();

        pList = new PriceList();
        prices = pList.getPrices();

        
        //Gets the ticketList from the MS Access database
        tList.getAvailTicketsByGame();
        //Array of Strings with opponent names
        String[] gamesArray = new String[games.size()];
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        for (int i = 0; i < games.size(); i++) {
            //String ddlText is used to be added to the cbxFixtures
            String ddlText;

            //opponent name and date is added to ddlText
            ddlText = games.get(i).getOpponent() + " | " + dateFormat.format(games.get(i).getStartDate()) + " | ";
            
            boolean found = false;
            //boolean variable is used to see if any tickets have been bought for this game so that the amount of tickets left for each game will be shown in cbxFixtures
            
            for (int j = 0; j < tList.getAvailableTickets().size(); j++) {
                //If tickets for this game have been bought then the tickets bought minus 10000 is added to ddlText
                if (games.get(i).getGameID() == tList.getAvailableTickets().get(j).getGameID()) {
                    ddlText += tList.getAvailableTickets().get(j).getTicketsLeft().toString();
                    found = true;
                    break;
                }
            }
            //If no tickets have been bought for the game then the tickets left is set to 10000
            if (found == false) {
                ddlText += TicketList.GAME_CAPACITY;
            }
            gamesArray[i] = ddlText;
        }

        cbxFixtures.setModel(new javax.swing.DefaultComboBoxModel<>(gamesArray));
        thisCustomer = c;

    }

    public void getAvailableTicketsByLevel() {
        tList.getAvailTicketsByLevel(thisTicket.getGameID());

        //The buttons for each level show the amount of seats left for that level
        btnLevel1.setText("Level 1" + " | " + tList.getAvailableLevelTickets().get(0).getTicketsLeft().toString());
        btnLevel2.setText("Level 2" + " | " + tList.getAvailableLevelTickets().get(1).getTicketsLeft().toString());
    }

    public void getAvailableTicketsBySection() {
        tList.getAvailTicketsBySection(thisTicket.getGameID(), level);
        pList.getPricesFromAccess(thisTicket.getGameID(), level);

        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        //The buttons for each level show the amount of seats left for that section and they also show the price of that section
        btnSection1.setText("1" + " | " + tList.getAvailableSectionTickets().get(0).getTicketsLeft().toString() + " | " + formatter.format(pList.getPricesBySectionArray().get(0).getPriceBySection()));
        btnSection2.setText("2" + " | " + tList.getAvailableSectionTickets().get(1).getTicketsLeft().toString() + " | " + formatter.format(pList.getPricesBySectionArray().get(1).getPriceBySection()));
        btnSection3.setText("3" + " | " + tList.getAvailableSectionTickets().get(2).getTicketsLeft().toString() + " | " + formatter.format(pList.getPricesBySectionArray().get(2).getPriceBySection()));
        btnSection4.setText("4" + " | " + tList.getAvailableSectionTickets().get(3).getTicketsLeft().toString() + " | " + formatter.format(pList.getPricesBySectionArray().get(3).getPriceBySection()));
        btnSection5.setText("5" + " | " + tList.getAvailableSectionTickets().get(4).getTicketsLeft().toString() + " | " + formatter.format(pList.getPricesBySectionArray().get(4).getPriceBySection()));
        btnSection6.setText("6" + " | " + tList.getAvailableSectionTickets().get(5).getTicketsLeft().toString() + " | " + formatter.format(pList.getPricesBySectionArray().get(5).getPriceBySection()));
        btnSection7.setText("7" + " | " + tList.getAvailableSectionTickets().get(6).getTicketsLeft().toString() + " | " + formatter.format(pList.getPricesBySectionArray().get(6).getPriceBySection()));
        btnSection8.setText("8" + " | " + tList.getAvailableSectionTickets().get(7).getTicketsLeft().toString() + " | " + formatter.format(pList.getPricesBySectionArray().get(7).getPriceBySection()));
        btnSection9.setText("9" + " | " + tList.getAvailableSectionTickets().get(8).getTicketsLeft().toString() + " | " + formatter.format(pList.getPricesBySectionArray().get(8).getPriceBySection()));
        btnSection10.setText("10" + " | " + tList.getAvailableSectionTickets().get(9).getTicketsLeft().toString() + " | " + formatter.format(pList.getPricesBySectionArray().get(9).getPriceBySection()));
        btnSection11.setText("11" + " | " + tList.getAvailableSectionTickets().get(10).getTicketsLeft().toString() + " | " + formatter.format(pList.getPricesBySectionArray().get(10).getPriceBySection()));
        btnSection12.setText("12" + " | " + tList.getAvailableSectionTickets().get(11).getTicketsLeft().toString() + " | " + formatter.format(pList.getPricesBySectionArray().get(11).getPriceBySection()));
        btnSection13.setText("13" + " | " + tList.getAvailableSectionTickets().get(12).getTicketsLeft().toString() + " | " + formatter.format(pList.getPricesBySectionArray().get(12).getPriceBySection()));
        btnSection14.setText("14" + " | " + tList.getAvailableSectionTickets().get(13).getTicketsLeft().toString() + " | " + formatter.format(pList.getPricesBySectionArray().get(13).getPriceBySection()));
        btnSection15.setText("15" + " | " + tList.getAvailableSectionTickets().get(14).getTicketsLeft().toString() + " | " + formatter.format(pList.getPricesBySectionArray().get(14).getPriceBySection()));
        btnSection16.setText("16" + " | " + tList.getAvailableSectionTickets().get(15).getTicketsLeft().toString() + " | " + formatter.format(pList.getPricesBySectionArray().get(15).getPriceBySection()));
        btnSection17.setText("17" + " | " + tList.getAvailableSectionTickets().get(16).getTicketsLeft().toString() + " | " + formatter.format(pList.getPricesBySectionArray().get(16).getPriceBySection()));
        btnSection18.setText("18" + " | " + tList.getAvailableSectionTickets().get(17).getTicketsLeft().toString() + " | " + formatter.format(pList.getPricesBySectionArray().get(17).getPriceBySection()));
        btnSection19.setText("19" + " | " + tList.getAvailableSectionTickets().get(18).getTicketsLeft().toString() + " | " + formatter.format(pList.getPricesBySectionArray().get(18).getPriceBySection()));
        btnSection20.setText("20" + " | " + tList.getAvailableSectionTickets().get(19).getTicketsLeft().toString() + " | " + formatter.format(pList.getPricesBySectionArray().get(19).getPriceBySection()));
    }

    public void getAvailableTicketsByRow() {
        tList.getAvailTicketsByRow(thisTicket.getGameID(), level, section);

        //The buttons for each level show the amount of seats left for that level
        btnRow1.setText("1" + " | " + tList.getAvailableRowTickets().get(0).getTicketsLeft().toString());
        btnRow2.setText("2" + " | " + tList.getAvailableRowTickets().get(1).getTicketsLeft().toString());
        btnRow3.setText("3" + " | " + tList.getAvailableRowTickets().get(2).getTicketsLeft().toString());
        btnRow4.setText("4" + " | " + tList.getAvailableRowTickets().get(3).getTicketsLeft().toString());
        btnRow5.setText("5" + " | " + tList.getAvailableRowTickets().get(4).getTicketsLeft().toString());
        btnRow6.setText("6" + " | " + tList.getAvailableRowTickets().get(5).getTicketsLeft().toString());
        btnRow7.setText("7" + " | " + tList.getAvailableRowTickets().get(6).getTicketsLeft().toString());
        btnRow8.setText("8" + " | " + tList.getAvailableRowTickets().get(7).getTicketsLeft().toString());
        btnRow9.setText("9" + " | " + tList.getAvailableRowTickets().get(8).getTicketsLeft().toString());
        btnRow10.setText("10" + " | " + tList.getAvailableRowTickets().get(9).getTicketsLeft().toString());

    }

    public void disableNonavailableSeats() {
        tList.getAvailTicketsBySeat(thisTicket.getGameID(), level, section, row);
        Component[] comp = jPanel6.getComponents();
        for (int i = 0; i < comp.length; i++) {

            //If the seat is taken, the button that represents it is disabled and can not be chosen
            if (comp[i] instanceof JButton) {
                JButton jb;
                jb = (JButton) comp[i];
                int j = Integer.parseInt(jb.getText());
                if (tList.getAvailableSeatTickets().get(j - 1).getTicketsLeft() == 0) {
                    jb.setEnabled(false);
                }
            }
        }

    }
  
    public void insertTicket() {

        int ticketID = 0;

        thisTicket.setLevel(level);
        thisTicket.setSection(section);
        thisTicket.setRow(row);
        thisTicket.setSeat(seat);
        thisTicket.setPrice(price);
        thisTicket.setAvailable(false);

        tList.addTicket(thisTicket);

    }

    public void updateTicketLabel() {
        //Shows the current seat selection, i.e. what game you selected, what level, etc.
        String game = (String) cbxFixtures.getSelectedItem();
        String[] gameElements = game.split(" \\| ", 0);

        lblCurrentGame.setText("Game: " + gameElements[0] + ", Level: " + level + ", Section: " + section + ", Row: " + row + ", Seat: " + seat);
        lblCurrentLevel.setText("Game: " + gameElements[0] + ", Level: " + level + ", Section: " + section + ", Row: " + row + ", Seat: " + seat);
        lblCurrentSection.setText("Game: " + gameElements[0] + ", Level: " + level + ", Section: " + section + ", Row: " + row + ", Seat: " + seat);
        lblCurrentRow.setText("Game: " + gameElements[0] + ", Level: " + level + ", Section: " + section + ", Row: " + row + ", Seat: " + seat);
        lblCurrentSeat.setText("Game: " + gameElements[0] + ", Level: " + level + ", Section: " + section + ", Row: " + row + ", Seat: " + seat);
        
        updateFinalTicket();
    }

    public void updateFinalTicket() {
        //Updates the ticket on the confirm purchase tabbed pane with final details
        String game = (String) cbxFixtures.getSelectedItem();
        String[] gameElements = game.split(" \\| ", 0);

        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        Date gameDate = null;
        Date gameTime = null;

        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getOpponent().equals(gameElements[0])) {
                gameDate = games.get(i).getStartDate();
                gameTime = games.get(i).getStartTime();
            }
        }

        if (tbpTicketTab.getSelectedIndex() >= 3) {
            lblTicketDetails.setOpaque(true);
            lblTicketDetails.setBackground(Color.WHITE);
            lblTicketDetails.setText("<html>" + "Opponent: " + gameElements[0] + "<br/>"
                    + "Level: " + level + "<br/>"
                    + "Section: " + section + "<br/>"
                    + "Row: " + row + "<br/>"
                    + "Seat: " + seat + "<br/>"
                    + "Price: " + formatter.format(pList.getPricesBySectionArray().get(section - 1).getPriceBySection()));
            txtGameDate.setValue(gameDate);
            txtGameTime.setValue(gameTime);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgSectionGroup = new javax.swing.ButtonGroup();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        tbpTicketTab = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        cbxFixtures = new javax.swing.JComboBox<String>();
        lblCurrentGame = new javax.swing.JLabel();
        lblTicketsLeft = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnLevel1 = new javax.swing.JButton();
        btnLevel2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lblCurrentLevel = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblCurrentSection = new javax.swing.JLabel();
        btnSection1 = new javax.swing.JButton();
        btnSection2 = new javax.swing.JButton();
        btnSection3 = new javax.swing.JButton();
        btnSection4 = new javax.swing.JButton();
        btnSection5 = new javax.swing.JButton();
        btnSection6 = new javax.swing.JButton();
        btnSection7 = new javax.swing.JButton();
        btnSection19 = new javax.swing.JButton();
        btnSection17 = new javax.swing.JButton();
        btnSection8 = new javax.swing.JButton();
        btnSection20 = new javax.swing.JButton();
        btnSection18 = new javax.swing.JButton();
        btnSection9 = new javax.swing.JButton();
        btnSection10 = new javax.swing.JButton();
        btnSection11 = new javax.swing.JButton();
        btnSection12 = new javax.swing.JButton();
        btnSection13 = new javax.swing.JButton();
        btnSection14 = new javax.swing.JButton();
        btnSection15 = new javax.swing.JButton();
        btnSection16 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnRow2 = new javax.swing.JButton();
        btnRow3 = new javax.swing.JButton();
        btnRow4 = new javax.swing.JButton();
        btnRow5 = new javax.swing.JButton();
        btnRow1 = new javax.swing.JButton();
        btnRow6 = new javax.swing.JButton();
        btnRow7 = new javax.swing.JButton();
        btnRow8 = new javax.swing.JButton();
        btnRow9 = new javax.swing.JButton();
        btnRow10 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lblCurrentRow = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        btnSeat1 = new javax.swing.JButton();
        btnSeat3 = new javax.swing.JButton();
        btnSeat2 = new javax.swing.JButton();
        btnSeat5 = new javax.swing.JButton();
        btnSeat4 = new javax.swing.JButton();
        btnSeat6 = new javax.swing.JButton();
        btnSeat7 = new javax.swing.JButton();
        btnSeat8 = new javax.swing.JButton();
        btnSeat9 = new javax.swing.JButton();
        btnSeat10 = new javax.swing.JButton();
        btnSeat11 = new javax.swing.JButton();
        btnSeat12 = new javax.swing.JButton();
        btnSeat13 = new javax.swing.JButton();
        btnSeat14 = new javax.swing.JButton();
        btnSeat15 = new javax.swing.JButton();
        btnSeat16 = new javax.swing.JButton();
        btnSeat17 = new javax.swing.JButton();
        btnSeat18 = new javax.swing.JButton();
        btnSeat19 = new javax.swing.JButton();
        btnSeat20 = new javax.swing.JButton();
        btnSeat21 = new javax.swing.JButton();
        btnSeat22 = new javax.swing.JButton();
        btnSeat23 = new javax.swing.JButton();
        btnSeat24 = new javax.swing.JButton();
        btnSeat25 = new javax.swing.JButton();
        lblCurrentSeat = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        btnProceedPayment = new javax.swing.JButton();
        lblTicketDetails = new javax.swing.JLabel();
        txtGameDate = new javax.swing.JFormattedTextField();
        txtGameTime = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/northdownnets2/images/SectionComposition2.png"))); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        tbpTicketTab.setBackground(new java.awt.Color(51, 51, 51));
        tbpTicketTab.setPreferredSize(new java.awt.Dimension(1000, 750));
        tbpTicketTab.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                tbpTicketTabComponentShown(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        cbxFixtures.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbxFixtures.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxFixtures.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cbxFixturesMouseEntered(evt);
            }
        });
        cbxFixtures.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxFixturesActionPerformed(evt);
            }
        });

        lblCurrentGame.setForeground(new java.awt.Color(255, 255, 255));
        lblCurrentGame.setText(" ");

        lblTicketsLeft.setForeground(new java.awt.Color(255, 255, 255));
        lblTicketsLeft.setText(" ");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("OPPONENT | DATE | AVAILABLE SEATS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTicketsLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbxFixtures, javax.swing.GroupLayout.Alignment.LEADING, 0, 1044, Short.MAX_VALUE)
                            .addComponent(lblCurrentGame, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lblCurrentGame, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addGap(15, 15, 15)
                .addComponent(cbxFixtures, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 210, Short.MAX_VALUE)
                .addComponent(lblTicketsLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tbpTicketTab.addTab("Select Game", jPanel2);

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));

        btnLevel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnLevel1.setText("Level 1");
        btnLevel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLevel1ActionPerformed(evt);
            }
        });

        btnLevel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnLevel2.setText("Level 2");
        btnLevel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLevel2ActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/northdownnets2/images/LevelCopmosition.png"))); // NOI18N

        lblCurrentLevel.setForeground(new java.awt.Color(255, 255, 255));
        lblCurrentLevel.setText(" ");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("LEVEL | AVAILABLE SEATS");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLevel1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLevel2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(421, 421, 421))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 1054, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCurrentLevel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblCurrentLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addComponent(btnLevel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLevel2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tbpTicketTab.addTab("Select Level", jPanel3);

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setPreferredSize(new java.awt.Dimension(1000, 750));

        lblCurrentSection.setForeground(new java.awt.Color(255, 255, 255));
        lblCurrentSection.setText("jLabel5");

        btnSection1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnSection1.setText("1");
        btnSection1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSection1ActionPerformed(evt);
            }
        });

        btnSection2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnSection2.setText("2");
        btnSection2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSection2ActionPerformed(evt);
            }
        });

        btnSection3.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnSection3.setText("3");
        btnSection3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSection3ActionPerformed(evt);
            }
        });

        btnSection4.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnSection4.setText("4");
        btnSection4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSection4ActionPerformed(evt);
            }
        });

        btnSection5.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnSection5.setText("5");
        btnSection5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSection5ActionPerformed(evt);
            }
        });

        btnSection6.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnSection6.setText("6");
        btnSection6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSection6ActionPerformed(evt);
            }
        });

        btnSection7.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnSection7.setText("7");
        btnSection7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSection7ActionPerformed(evt);
            }
        });

        btnSection19.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnSection19.setText("19");
        btnSection19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSection19ActionPerformed(evt);
            }
        });

        btnSection17.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnSection17.setText("17");
        btnSection17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSection17ActionPerformed(evt);
            }
        });

        btnSection8.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnSection8.setText("8");
        btnSection8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSection8ActionPerformed(evt);
            }
        });

        btnSection20.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnSection20.setText("20");
        btnSection20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSection20ActionPerformed(evt);
            }
        });

        btnSection18.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnSection18.setText("18");
        btnSection18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSection18ActionPerformed(evt);
            }
        });

        btnSection9.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnSection9.setText("9");
        btnSection9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSection9ActionPerformed(evt);
            }
        });

        btnSection10.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnSection10.setText("10");
        btnSection10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSection10ActionPerformed(evt);
            }
        });

        btnSection11.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnSection11.setText("11");
        btnSection11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSection11ActionPerformed(evt);
            }
        });

        btnSection12.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnSection12.setText("12");
        btnSection12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSection12ActionPerformed(evt);
            }
        });

        btnSection13.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnSection13.setText("13");
        btnSection13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSection13ActionPerformed(evt);
            }
        });

        btnSection14.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnSection14.setText("14");
        btnSection14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSection14ActionPerformed(evt);
            }
        });

        btnSection15.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnSection15.setText("15");
        btnSection15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSection15ActionPerformed(evt);
            }
        });

        btnSection16.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnSection16.setText("16");
        btnSection16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSection16ActionPerformed(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/northdownnets2/images/SectionComposition2.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SECTION | AVAILABLE SEATS | PRICE");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSection16, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnSection1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSection17, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(btnSection18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSection9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSection2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSection10, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSection3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSection11, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSection4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSection12, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSection13, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSection5, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSection14, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(btnSection6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSection7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSection15, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSection8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSection19, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSection20, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(26, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCurrentSection, javax.swing.GroupLayout.DEFAULT_SIZE, 1044, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCurrentSection, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(btnSection8)
                        .addGap(8, 8, 8)
                        .addComponent(btnSection19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSection16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSection20)
                            .addComponent(btnSection15)
                            .addComponent(btnSection14)
                            .addComponent(btnSection13)
                            .addComponent(btnSection12)
                            .addComponent(btnSection11)
                            .addComponent(btnSection10)
                            .addComponent(btnSection9)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSection7)
                            .addComponent(btnSection6)
                            .addComponent(btnSection5)
                            .addComponent(btnSection4)
                            .addComponent(btnSection3)
                            .addComponent(btnSection2)
                            .addComponent(btnSection1))
                        .addGap(8, 8, 8)
                        .addComponent(btnSection17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSection18)))
                .addGap(53, 53, 53)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(135, 135, 135))
        );

        tbpTicketTab.addTab("Select Section", jPanel4);

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));

        btnRow2.setText("Row 2");
        btnRow2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRow2ActionPerformed(evt);
            }
        });

        btnRow3.setText("Row 3");
        btnRow3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRow3ActionPerformed(evt);
            }
        });

        btnRow4.setText("Row 4");
        btnRow4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRow4ActionPerformed(evt);
            }
        });

        btnRow5.setText("Row 5");
        btnRow5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRow5ActionPerformed(evt);
            }
        });

        btnRow1.setText("Row 1");
        btnRow1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRow1ActionPerformed(evt);
            }
        });

        btnRow6.setText("Row 6");
        btnRow6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRow6ActionPerformed(evt);
            }
        });

        btnRow7.setText("Row 7");
        btnRow7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRow7ActionPerformed(evt);
            }
        });

        btnRow8.setText("Row 8");
        btnRow8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRow8ActionPerformed(evt);
            }
        });

        btnRow9.setText("Row 9");
        btnRow9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRow9ActionPerformed(evt);
            }
        });

        btnRow10.setText("Row 10");
        btnRow10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRow10ActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/northdownnets2/images/RowCompostion2.png"))); // NOI18N
        jLabel2.setText("jLabel2");

        lblCurrentRow.setForeground(new java.awt.Color(255, 255, 255));
        lblCurrentRow.setText("jLabel5");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("ROW | AVAILABLE SEATS");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCurrentRow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnRow6, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnRow10, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnRow9, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnRow8, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnRow7, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnRow1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnRow5, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnRow4, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnRow3, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnRow2, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(65, 65, 65)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 268, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lblCurrentRow, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addGap(118, 118, 118)
                        .addComponent(btnRow1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRow2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRow3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRow4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRow5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRow6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRow7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRow8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRow9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRow10))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(jLabel2)))
                .addContainerGap(89, Short.MAX_VALUE))
        );

        tbpTicketTab.addTab("Select Row", jPanel5);

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));

        btnSeat1.setText("1");
        btnSeat1.setMaximumSize(new java.awt.Dimension(100, 20));
        btnSeat1.setMinimumSize(new java.awt.Dimension(100, 20));
        btnSeat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeat1ActionPerformed(evt);
            }
        });

        btnSeat3.setText("3");
        btnSeat3.setMaximumSize(new java.awt.Dimension(100, 20));
        btnSeat3.setMinimumSize(new java.awt.Dimension(100, 20));
        btnSeat3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeat3ActionPerformed(evt);
            }
        });

        btnSeat2.setText("2");
        btnSeat2.setMaximumSize(new java.awt.Dimension(100, 20));
        btnSeat2.setMinimumSize(new java.awt.Dimension(100, 20));
        btnSeat2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeat2ActionPerformed(evt);
            }
        });

        btnSeat5.setText("5");
        btnSeat5.setMaximumSize(new java.awt.Dimension(100, 20));
        btnSeat5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeat5ActionPerformed(evt);
            }
        });

        btnSeat4.setText("4");
        btnSeat4.setMaximumSize(new java.awt.Dimension(100, 20));
        btnSeat4.setMinimumSize(new java.awt.Dimension(100, 20));
        btnSeat4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeat4ActionPerformed(evt);
            }
        });

        btnSeat6.setText("6");
        btnSeat6.setMaximumSize(new java.awt.Dimension(100, 20));
        btnSeat6.setMinimumSize(new java.awt.Dimension(100, 20));
        btnSeat6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeat6ActionPerformed(evt);
            }
        });

        btnSeat7.setText("7");
        btnSeat7.setMaximumSize(new java.awt.Dimension(100, 20));
        btnSeat7.setMinimumSize(new java.awt.Dimension(100, 20));
        btnSeat7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeat7ActionPerformed(evt);
            }
        });

        btnSeat8.setText("8");
        btnSeat8.setMaximumSize(new java.awt.Dimension(100, 20));
        btnSeat8.setMinimumSize(new java.awt.Dimension(100, 20));
        btnSeat8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeat8ActionPerformed(evt);
            }
        });

        btnSeat9.setText("9");
        btnSeat9.setMaximumSize(new java.awt.Dimension(100, 20));
        btnSeat9.setMinimumSize(new java.awt.Dimension(100, 20));
        btnSeat9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeat9ActionPerformed(evt);
            }
        });

        btnSeat10.setText("10");
        btnSeat10.setMaximumSize(new java.awt.Dimension(100, 20));
        btnSeat10.setMinimumSize(new java.awt.Dimension(100, 20));
        btnSeat10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeat10ActionPerformed(evt);
            }
        });

        btnSeat11.setText("11");
        btnSeat11.setMaximumSize(new java.awt.Dimension(100, 20));
        btnSeat11.setMinimumSize(new java.awt.Dimension(100, 20));
        btnSeat11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeat11ActionPerformed(evt);
            }
        });

        btnSeat12.setText("12");
        btnSeat12.setMaximumSize(new java.awt.Dimension(100, 20));
        btnSeat12.setMinimumSize(new java.awt.Dimension(100, 20));
        btnSeat12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeat12ActionPerformed(evt);
            }
        });

        btnSeat13.setText("13");
        btnSeat13.setMaximumSize(new java.awt.Dimension(100, 20));
        btnSeat13.setMinimumSize(new java.awt.Dimension(100, 20));
        btnSeat13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeat13ActionPerformed(evt);
            }
        });

        btnSeat14.setText("14");
        btnSeat14.setMaximumSize(new java.awt.Dimension(100, 20));
        btnSeat14.setMinimumSize(new java.awt.Dimension(100, 20));
        btnSeat14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeat14ActionPerformed(evt);
            }
        });

        btnSeat15.setText("15");
        btnSeat15.setMaximumSize(new java.awt.Dimension(100, 20));
        btnSeat15.setMinimumSize(new java.awt.Dimension(100, 20));
        btnSeat15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeat15ActionPerformed(evt);
            }
        });

        btnSeat16.setText("16");
        btnSeat16.setMaximumSize(new java.awt.Dimension(100, 20));
        btnSeat16.setMinimumSize(new java.awt.Dimension(100, 20));
        btnSeat16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeat16ActionPerformed(evt);
            }
        });

        btnSeat17.setText("17");
        btnSeat17.setMaximumSize(new java.awt.Dimension(100, 20));
        btnSeat17.setMinimumSize(new java.awt.Dimension(100, 20));
        btnSeat17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeat17ActionPerformed(evt);
            }
        });

        btnSeat18.setText("18");
        btnSeat18.setMaximumSize(new java.awt.Dimension(100, 20));
        btnSeat18.setMinimumSize(new java.awt.Dimension(100, 20));
        btnSeat18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeat18ActionPerformed(evt);
            }
        });

        btnSeat19.setText("19");
        btnSeat19.setMaximumSize(new java.awt.Dimension(100, 20));
        btnSeat19.setMinimumSize(new java.awt.Dimension(100, 20));
        btnSeat19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeat19ActionPerformed(evt);
            }
        });

        btnSeat20.setText("20");
        btnSeat20.setMaximumSize(new java.awt.Dimension(100, 20));
        btnSeat20.setMinimumSize(new java.awt.Dimension(100, 20));
        btnSeat20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeat20ActionPerformed(evt);
            }
        });

        btnSeat21.setText("21");
        btnSeat21.setMaximumSize(new java.awt.Dimension(100, 20));
        btnSeat21.setMinimumSize(new java.awt.Dimension(100, 20));
        btnSeat21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeat21ActionPerformed(evt);
            }
        });

        btnSeat22.setText("22");
        btnSeat22.setMaximumSize(new java.awt.Dimension(100, 20));
        btnSeat22.setMinimumSize(new java.awt.Dimension(100, 20));
        btnSeat22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeat22ActionPerformed(evt);
            }
        });

        btnSeat23.setText("23");
        btnSeat23.setMaximumSize(new java.awt.Dimension(100, 20));
        btnSeat23.setMinimumSize(new java.awt.Dimension(100, 20));
        btnSeat23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeat23ActionPerformed(evt);
            }
        });

        btnSeat24.setText("24");
        btnSeat24.setMaximumSize(new java.awt.Dimension(100, 20));
        btnSeat24.setMinimumSize(new java.awt.Dimension(100, 20));
        btnSeat24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeat24ActionPerformed(evt);
            }
        });

        btnSeat25.setText("25");
        btnSeat25.setMaximumSize(new java.awt.Dimension(100, 20));
        btnSeat25.setMinimumSize(new java.awt.Dimension(100, 20));
        btnSeat25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeat25ActionPerformed(evt);
            }
        });

        lblCurrentSeat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCurrentSeat.setForeground(new java.awt.Color(255, 255, 255));
        lblCurrentSeat.setText("jLabel8");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/northdownnets2/images/SeatComposition2.png"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnSeat6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnSeat1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnSeat11, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnSeat16, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnSeat21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(63, 63, 63)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSeat17, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSeat22, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSeat2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSeat7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSeat12, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(70, 70, 70)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnSeat13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnSeat8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnSeat3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnSeat18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSeat23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(65, 65, 65)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSeat4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSeat24, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSeat19, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSeat14, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSeat9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(73, 73, 73)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSeat15, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSeat5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSeat20, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSeat25, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSeat10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(201, 201, 201))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lblCurrentSeat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCurrentSeat, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(btnSeat2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnSeat7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnSeat12, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnSeat17, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnSeat22, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(btnSeat5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnSeat10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnSeat15, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnSeat20, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnSeat25, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btnSeat1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btnSeat6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSeat11, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSeat16, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSeat21, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btnSeat3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSeat8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSeat13, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSeat18, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSeat23, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btnSeat4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSeat9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSeat14, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSeat19, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSeat24, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(45, 45, 45)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(108, Short.MAX_VALUE))
        );

        tbpTicketTab.addTab("Select Seat", jPanel6);

        jPanel7.setBackground(new java.awt.Color(51, 51, 51));

        btnProceedPayment.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnProceedPayment.setText("Buy Ticket");
        btnProceedPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProceedPaymentActionPerformed(evt);
            }
        });

        lblTicketDetails.setBackground(new java.awt.Color(255, 255, 255));
        lblTicketDetails.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTicketDetails.setText("jLabel6");
        lblTicketDetails.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 0), 3));

        txtGameDate.setEditable(false);
        txtGameDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        txtGameDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGameDateActionPerformed(evt);
            }
        });

        txtGameTime.setEditable(false);
        txtGameTime.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT))));

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("DATE:");

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("TIME:");

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/northdownnets2/images/qrCodeIcon.png"))); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTicketDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnProceedPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addGap(70, 70, 70)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtGameTime, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtGameDate, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 797, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel12)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnProceedPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(lblTicketDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGameDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGameTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addContainerGap())
        );

        tbpTicketTab.addTab("Confirm Purchase", jPanel7);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(tbpTicketTab, javax.swing.GroupLayout.PREFERRED_SIZE, 1069, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(tbpTicketTab, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSeat16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeat16ActionPerformed
        seat = 16;
        updateTicketLabel();
        tbpTicketTab.setSelectedIndex(5);
    }//GEN-LAST:event_btnSeat16ActionPerformed

    private void btnLevel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLevel1ActionPerformed
        level = 1;
        updateTicketLabel();
        getAvailableTicketsBySection();
        tbpTicketTab.setSelectedIndex(2);
    }//GEN-LAST:event_btnLevel1ActionPerformed

    private void btnLevel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLevel2ActionPerformed
        level = 2;
        updateTicketLabel();
        getAvailableTicketsBySection();
        tbpTicketTab.setSelectedIndex(2);
    }//GEN-LAST:event_btnLevel2ActionPerformed

    private void btnRow1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRow1ActionPerformed
        row = 1;
        updateTicketLabel();
        disableNonavailableSeats();
        tbpTicketTab.setSelectedIndex(4);
    }//GEN-LAST:event_btnRow1ActionPerformed

    private void btnRow2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRow2ActionPerformed
        row = 2;
        updateTicketLabel();
        disableNonavailableSeats();
        tbpTicketTab.setSelectedIndex(4);
    }//GEN-LAST:event_btnRow2ActionPerformed

    private void btnRow3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRow3ActionPerformed
        row = 3;
        updateTicketLabel();
        disableNonavailableSeats();
        tbpTicketTab.setSelectedIndex(4);
    }//GEN-LAST:event_btnRow3ActionPerformed

    private void btnRow4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRow4ActionPerformed
        row = 4;
        updateTicketLabel();
        disableNonavailableSeats();
        tbpTicketTab.setSelectedIndex(4);
    }//GEN-LAST:event_btnRow4ActionPerformed

    private void btnRow5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRow5ActionPerformed
        row = 5;
        updateTicketLabel();
        disableNonavailableSeats();
        tbpTicketTab.setSelectedIndex(4);
    }//GEN-LAST:event_btnRow5ActionPerformed

    private void btnRow6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRow6ActionPerformed
        row = 6;
        updateTicketLabel();
        disableNonavailableSeats();
        tbpTicketTab.setSelectedIndex(4);
    }//GEN-LAST:event_btnRow6ActionPerformed

    private void btnRow7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRow7ActionPerformed
        row = 7;
        updateTicketLabel();
        disableNonavailableSeats();
        tbpTicketTab.setSelectedIndex(4);
    }//GEN-LAST:event_btnRow7ActionPerformed

    private void btnRow8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRow8ActionPerformed
        row = 8;
        updateTicketLabel();
        disableNonavailableSeats();
        tbpTicketTab.setSelectedIndex(4);
    }//GEN-LAST:event_btnRow8ActionPerformed

    private void btnRow9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRow9ActionPerformed
        row = 9;
        updateTicketLabel();
        disableNonavailableSeats();
        tbpTicketTab.setSelectedIndex(4);
    }//GEN-LAST:event_btnRow9ActionPerformed

    private void btnSeat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeat1ActionPerformed
        seat = 1;
        updateTicketLabel();
        tbpTicketTab.setSelectedIndex(5);
    }//GEN-LAST:event_btnSeat1ActionPerformed

    private void btnSeat2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeat2ActionPerformed
        seat = 2;
        updateTicketLabel();
        tbpTicketTab.setSelectedIndex(5);
    }//GEN-LAST:event_btnSeat2ActionPerformed

    private void btnSeat3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeat3ActionPerformed
        seat = 3;
        updateTicketLabel();
        tbpTicketTab.setSelectedIndex(5);
    }//GEN-LAST:event_btnSeat3ActionPerformed

    private void btnSeat4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeat4ActionPerformed
        seat = 4;
        updateTicketLabel();
        tbpTicketTab.setSelectedIndex(5);
    }//GEN-LAST:event_btnSeat4ActionPerformed

    private void btnSeat5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeat5ActionPerformed
        seat = 5;
        updateTicketLabel();
        tbpTicketTab.setSelectedIndex(5);
    }//GEN-LAST:event_btnSeat5ActionPerformed

    private void btnSeat6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeat6ActionPerformed
        seat = 6;
        updateTicketLabel();
        tbpTicketTab.setSelectedIndex(5);
    }//GEN-LAST:event_btnSeat6ActionPerformed

    private void btnSeat7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeat7ActionPerformed
        seat = 7;
        updateTicketLabel();
        tbpTicketTab.setSelectedIndex(5);
    }//GEN-LAST:event_btnSeat7ActionPerformed

    private void btnSeat8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeat8ActionPerformed
        seat = 8;
        tbpTicketTab.setSelectedIndex(5);
    }//GEN-LAST:event_btnSeat8ActionPerformed

    private void btnSeat9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeat9ActionPerformed
        seat = 9;
        updateTicketLabel();
        tbpTicketTab.setSelectedIndex(5);
    }//GEN-LAST:event_btnSeat9ActionPerformed

    private void btnSeat10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeat10ActionPerformed
        seat = 10;
        updateTicketLabel();
        tbpTicketTab.setSelectedIndex(5);
    }//GEN-LAST:event_btnSeat10ActionPerformed

    private void btnSeat11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeat11ActionPerformed
        seat = 11;
        updateTicketLabel();
        tbpTicketTab.setSelectedIndex(5);
    }//GEN-LAST:event_btnSeat11ActionPerformed

    private void btnSeat12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeat12ActionPerformed
        seat = 12;
        updateTicketLabel();
        tbpTicketTab.setSelectedIndex(5);
    }//GEN-LAST:event_btnSeat12ActionPerformed

    private void btnSeat13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeat13ActionPerformed
        seat = 13;
        updateTicketLabel();
        tbpTicketTab.setSelectedIndex(5);
    }//GEN-LAST:event_btnSeat13ActionPerformed

    private void btnSeat14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeat14ActionPerformed
        seat = 14;
        tbpTicketTab.setSelectedIndex(5);
    }//GEN-LAST:event_btnSeat14ActionPerformed

    private void btnSeat15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeat15ActionPerformed
        seat = 15;
        updateTicketLabel();
        tbpTicketTab.setSelectedIndex(5);
    }//GEN-LAST:event_btnSeat15ActionPerformed

    private void btnSeat17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeat17ActionPerformed
        seat = 17;
        updateTicketLabel();
        tbpTicketTab.setSelectedIndex(5);
    }//GEN-LAST:event_btnSeat17ActionPerformed

    private void btnSeat18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeat18ActionPerformed
        seat = 18;
        updateTicketLabel();
        tbpTicketTab.setSelectedIndex(5);
    }//GEN-LAST:event_btnSeat18ActionPerformed

    private void btnSeat19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeat19ActionPerformed
        seat = 19;
        updateTicketLabel();
        tbpTicketTab.setSelectedIndex(5);
    }//GEN-LAST:event_btnSeat19ActionPerformed

    private void btnSeat20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeat20ActionPerformed
        seat = 20;
        updateTicketLabel();
        tbpTicketTab.setSelectedIndex(5);
    }//GEN-LAST:event_btnSeat20ActionPerformed

    private void btnSeat21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeat21ActionPerformed
        seat = 21;
        updateTicketLabel();
        tbpTicketTab.setSelectedIndex(5);
    }//GEN-LAST:event_btnSeat21ActionPerformed

    private void btnSeat22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeat22ActionPerformed
        seat = 22;
        updateTicketLabel();
        tbpTicketTab.setSelectedIndex(5);
    }//GEN-LAST:event_btnSeat22ActionPerformed

    private void btnSeat23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeat23ActionPerformed
        seat = 23;
        updateTicketLabel();
        tbpTicketTab.setSelectedIndex(5);
    }//GEN-LAST:event_btnSeat23ActionPerformed

    private void btnSeat24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeat24ActionPerformed
        seat = 24;
        updateTicketLabel();
        tbpTicketTab.setSelectedIndex(5);
    }//GEN-LAST:event_btnSeat24ActionPerformed

    private void btnSeat25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeat25ActionPerformed
        seat = 25;
        updateTicketLabel();
        tbpTicketTab.setSelectedIndex(5);
    }//GEN-LAST:event_btnSeat25ActionPerformed

    private void btnProceedPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProceedPaymentActionPerformed
        insertTicket();
        this.dispose();
    }//GEN-LAST:event_btnProceedPaymentActionPerformed

    private void cbxFixturesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxFixturesActionPerformed
        String game = (String) cbxFixtures.getSelectedItem();
        String[] gameElements = game.split(" \\| ", 0);
        int gameID = 0;

        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getOpponent().equals(gameElements[0])) {
                gameID = games.get(i).getGameID();
            }
        }

        thisTicket.setGameID(gameID);

        lblCurrentGame.setText("Game: " + gameElements[0] + ", Level: " + row + ", Section: " + section + ", Row: " + row + ", Seat: " + seat);
        lblCurrentLevel.setText("Game: " + gameElements[0] + ", Level: " + row + ", Section: " + section + ", Row: " + row + ", Seat: " + seat);
        lblCurrentSection.setText("Game: " + gameElements[0] + ", Level: " + row + ", Section: " + section + ", Row: " + row + ", Seat: " + seat);
        lblCurrentRow.setText("Game: " + gameElements[0] + ", Level: " + row + ", Section: " + section + ", Row: " + row + ", Seat: " + seat);
        lblCurrentGame.setText("Game: " + gameElements[0] + ", Level: " + row + ", Section: " + section + ", Row: " + row + ", Seat: " + seat);

        getAvailableTicketsByLevel();

        tbpTicketTab.setSelectedIndex(1);
    }//GEN-LAST:event_cbxFixturesActionPerformed

    private void btnRow10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRow10ActionPerformed
        row = 10;
        updateTicketLabel();
        disableNonavailableSeats();
        tbpTicketTab.setSelectedIndex(4);
    }//GEN-LAST:event_btnRow10ActionPerformed

    private void tbpTicketTabComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_tbpTicketTabComponentShown

    }//GEN-LAST:event_tbpTicketTabComponentShown

    private void btnSection1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSection1ActionPerformed
        section = 1;
        updateTicketLabel();
        getAvailableTicketsByRow();
        tbpTicketTab.setSelectedIndex(3);
    }//GEN-LAST:event_btnSection1ActionPerformed

    private void btnSection2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSection2ActionPerformed
        section = 2;
        updateTicketLabel();
        getAvailableTicketsByRow();
        tbpTicketTab.setSelectedIndex(3);
    }//GEN-LAST:event_btnSection2ActionPerformed

    private void btnSection3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSection3ActionPerformed
        section = 3;
        updateTicketLabel();
        getAvailableTicketsByRow();
        tbpTicketTab.setSelectedIndex(3);
    }//GEN-LAST:event_btnSection3ActionPerformed

    private void btnSection4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSection4ActionPerformed
        section = 4;
        updateTicketLabel();
        getAvailableTicketsByRow();
        tbpTicketTab.setSelectedIndex(3);
    }//GEN-LAST:event_btnSection4ActionPerformed

    private void btnSection5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSection5ActionPerformed
        section = 5;
        updateTicketLabel();
        getAvailableTicketsByRow();
        tbpTicketTab.setSelectedIndex(3);
    }//GEN-LAST:event_btnSection5ActionPerformed

    private void btnSection6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSection6ActionPerformed
        section = 6;
        updateTicketLabel();
        getAvailableTicketsByRow();
        tbpTicketTab.setSelectedIndex(3);
    }//GEN-LAST:event_btnSection6ActionPerformed

    private void btnSection7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSection7ActionPerformed
        section = 7;
        updateTicketLabel();
        getAvailableTicketsByRow();
        tbpTicketTab.setSelectedIndex(3);
    }//GEN-LAST:event_btnSection7ActionPerformed

    private void btnSection8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSection8ActionPerformed
        section = 8;
        updateTicketLabel();
        getAvailableTicketsByRow();
        tbpTicketTab.setSelectedIndex(3);
    }//GEN-LAST:event_btnSection8ActionPerformed

    private void btnSection9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSection9ActionPerformed
        section = 9;
        updateTicketLabel();
        getAvailableTicketsByRow();
        tbpTicketTab.setSelectedIndex(3);
    }//GEN-LAST:event_btnSection9ActionPerformed

    private void btnSection10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSection10ActionPerformed
        section = 10;
        updateTicketLabel();
        getAvailableTicketsByRow();
        tbpTicketTab.setSelectedIndex(3);
    }//GEN-LAST:event_btnSection10ActionPerformed

    private void btnSection11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSection11ActionPerformed
        section = 11;
        updateTicketLabel();
        getAvailableTicketsByRow();
        tbpTicketTab.setSelectedIndex(3);
    }//GEN-LAST:event_btnSection11ActionPerformed

    private void btnSection12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSection12ActionPerformed
        section = 12;
        updateTicketLabel();
        getAvailableTicketsByRow();
        tbpTicketTab.setSelectedIndex(3);
    }//GEN-LAST:event_btnSection12ActionPerformed

    private void btnSection13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSection13ActionPerformed
        section = 13;
        updateTicketLabel();
        getAvailableTicketsByRow();
        tbpTicketTab.setSelectedIndex(3);
    }//GEN-LAST:event_btnSection13ActionPerformed

    private void btnSection14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSection14ActionPerformed
        section = 14;
        updateTicketLabel();
        getAvailableTicketsByRow();
        tbpTicketTab.setSelectedIndex(3);
    }//GEN-LAST:event_btnSection14ActionPerformed

    private void btnSection15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSection15ActionPerformed
        section = 15;
        updateTicketLabel();
        getAvailableTicketsByRow();
        tbpTicketTab.setSelectedIndex(3);
    }//GEN-LAST:event_btnSection15ActionPerformed

    private void btnSection16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSection16ActionPerformed
        section = 16;
        updateTicketLabel();
        getAvailableTicketsByRow();
        tbpTicketTab.setSelectedIndex(3);
    }//GEN-LAST:event_btnSection16ActionPerformed

    private void btnSection17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSection17ActionPerformed
        section = 17;
        updateTicketLabel();
        getAvailableTicketsByRow();
        tbpTicketTab.setSelectedIndex(3);
    }//GEN-LAST:event_btnSection17ActionPerformed

    private void btnSection18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSection18ActionPerformed
        section = 18;
        updateTicketLabel();
        getAvailableTicketsByRow();
        tbpTicketTab.setSelectedIndex(3);
    }//GEN-LAST:event_btnSection18ActionPerformed

    private void btnSection19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSection19ActionPerformed
        section = 19;
        updateTicketLabel();
        getAvailableTicketsByRow();
        tbpTicketTab.setSelectedIndex(3);
    }//GEN-LAST:event_btnSection19ActionPerformed

    private void btnSection20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSection20ActionPerformed
        section = 20;
        updateTicketLabel();
        getAvailableTicketsByRow();
        tbpTicketTab.setSelectedIndex(3);
    }//GEN-LAST:event_btnSection20ActionPerformed

    private void cbxFixturesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxFixturesMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxFixturesMouseEntered

    private void txtGameDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGameDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGameDateActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SelectSeat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SelectSeat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SelectSeat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SelectSeat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgSectionGroup;
    private javax.swing.JButton btnLevel1;
    private javax.swing.JButton btnLevel2;
    private javax.swing.JButton btnProceedPayment;
    private javax.swing.JButton btnRow1;
    private javax.swing.JButton btnRow10;
    private javax.swing.JButton btnRow2;
    private javax.swing.JButton btnRow3;
    private javax.swing.JButton btnRow4;
    private javax.swing.JButton btnRow5;
    private javax.swing.JButton btnRow6;
    private javax.swing.JButton btnRow7;
    private javax.swing.JButton btnRow8;
    private javax.swing.JButton btnRow9;
    private javax.swing.JButton btnSeat1;
    private javax.swing.JButton btnSeat10;
    private javax.swing.JButton btnSeat11;
    private javax.swing.JButton btnSeat12;
    private javax.swing.JButton btnSeat13;
    private javax.swing.JButton btnSeat14;
    private javax.swing.JButton btnSeat15;
    private javax.swing.JButton btnSeat16;
    private javax.swing.JButton btnSeat17;
    private javax.swing.JButton btnSeat18;
    private javax.swing.JButton btnSeat19;
    private javax.swing.JButton btnSeat2;
    private javax.swing.JButton btnSeat20;
    private javax.swing.JButton btnSeat21;
    private javax.swing.JButton btnSeat22;
    private javax.swing.JButton btnSeat23;
    private javax.swing.JButton btnSeat24;
    private javax.swing.JButton btnSeat25;
    private javax.swing.JButton btnSeat3;
    private javax.swing.JButton btnSeat4;
    private javax.swing.JButton btnSeat5;
    private javax.swing.JButton btnSeat6;
    private javax.swing.JButton btnSeat7;
    private javax.swing.JButton btnSeat8;
    private javax.swing.JButton btnSeat9;
    private javax.swing.JButton btnSection1;
    private javax.swing.JButton btnSection10;
    private javax.swing.JButton btnSection11;
    private javax.swing.JButton btnSection12;
    private javax.swing.JButton btnSection13;
    private javax.swing.JButton btnSection14;
    private javax.swing.JButton btnSection15;
    private javax.swing.JButton btnSection16;
    private javax.swing.JButton btnSection17;
    private javax.swing.JButton btnSection18;
    private javax.swing.JButton btnSection19;
    private javax.swing.JButton btnSection2;
    private javax.swing.JButton btnSection20;
    private javax.swing.JButton btnSection3;
    private javax.swing.JButton btnSection4;
    private javax.swing.JButton btnSection5;
    private javax.swing.JButton btnSection6;
    private javax.swing.JButton btnSection7;
    private javax.swing.JButton btnSection8;
    private javax.swing.JButton btnSection9;
    private javax.swing.JComboBox<String> cbxFixtures;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel lblCurrentGame;
    private javax.swing.JLabel lblCurrentLevel;
    private javax.swing.JLabel lblCurrentRow;
    private javax.swing.JLabel lblCurrentSeat;
    private javax.swing.JLabel lblCurrentSection;
    private javax.swing.JLabel lblTicketDetails;
    private javax.swing.JLabel lblTicketsLeft;
    private javax.swing.JTabbedPane tbpTicketTab;
    private javax.swing.JFormattedTextField txtGameDate;
    private javax.swing.JFormattedTextField txtGameTime;
    // End of variables declaration//GEN-END:variables
}
