/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package northdownnets2;

import BaseData.Game;
import BaseData.GameList;
import BaseData.Price;
import BaseData.PriceList;
import BaseData.Ticket;
import BaseData.TicketList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author tiarn
 */
public class ChangePriceTable extends javax.swing.JFrame {

    public ArrayList<Game> games;
    public GameList gList;

    public ArrayList<Price> prices;
    public PriceList pList;
    public Price thisPrice;
    public ArrayList<Double> defaultPrices;

    public TicketList tList;
    public Ticket thisTicket;

    public int gameID = 0;
    public int level = 0;
    public int section = 0;
    double price = 40.0;

    /**
     * Creates new form ChangePriceTable
     */
    public ChangePriceTable(GameList gList) {
        initComponents();

        pList = new PriceList();

        //The ticketID is set to 0 because Microsoft Access will automatically assign an ID to it, not java.
        int ticketID = 0;

        games = gList.getGames();
        prices = pList.getPrices();

        //An array of Strings is made with its size being the amount of games in the database.
        String[] gamesArray = new String[games.size()];
        //The date is formatted for ease-of-access; java automatically sets the date to format MM-dd-yyyy
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        
        //This for loop gets the name of each opponent and date of this match with the opponent and adds it to the gamesArray
        for (int i = 0; i < games.size(); i++) {
            String ddlText;

            ddlText = games.get(i).getOpponent() + " | " + dateFormat.format(games.get(i).getStartDate());

            gamesArray[i] = ddlText;
        }

        //The combo box model is set to the gamesArray
        cbxFixtures.setModel(new javax.swing.DefaultComboBoxModel<>(gamesArray));

    }

    public void updateDefaultValue() {
        
        //The text in txtDefaultPrice is the new value of each section
        for (int i = 0; i < 20; i++) {
            tblPrices.setValueAt(Double.parseDouble(txtDefaultPrice.getText().trim()), i, 1);
        }
    }

    public void setTableValues() {
        
    //String game is the user selection in cbxFixtures
        String game = (String) cbxFixtures.getSelectedItem();
        //The user selection in cbxFixtures is seperated between the opponent name and the opponent date.
        //The split operation allows the opponent name to be added to gameElements array.
        String[] gameElements = game.split(" \\| ", 0);

        //This for loop iterates through the games from the database        
        for (int i = 0; i < games.size(); i++) {
            //if the opponent name in the database is equal to the opponent name selected in cbxFixtures...
            //than the gameID can be found using a getter method on this record where the opponent names equal eachother. 
            if (games.get(i).getOpponent().equals(gameElements[0])) {
                gameID = games.get(i).getGameID();
            }
        }

        //The price List is populated with the prices from access through PriceList class
        pList.getPricesFromAccess(gameID, level);

        //Each rows value is set to the price of its corresponding section using the recently populated priceList 
        for (int j = 0; j < 20; j++) {
            tblPrices.setValueAt(pList.getPricesBySectionArray().get(j).getPriceBySection(), j, 1);
        }
    }

    public void updatePrice() {
        
        //For loop iterates through each row in table
        for (int i = 1; i <= 20; i++) {
            
            //Price is the value in each cell
            price = (double) tblPrices.getValueAt(i-1, 1);
            //Section is the amount of times the loop has been iterated through
            section = i;
            
            
            //If price is less than £1 the price for that section in set back to the default value that was declared in AddGame
            if(price < 1.00) {
                JOptionPane.showMessageDialog(null,"Error at section " + i + ", Price must be at least £1.00. Price has been set to original default");
                for(int j=0; j<games.size(); j++) {
                    if(games.get(j).getGameID() == gameID) {
                        price = games.get(j).getDefaultPrice();
                        tblPrices.setValueAt(price, section - 1, 1); 
                    }
                }
            }
            
            //If price is greater than or equal to £100 the price for that section in set back to the default value that was declared in AddGame
            if(price >= 100.00) {
                JOptionPane.showMessageDialog(null,"Error at section " + i + ", Price must be less than £100.00. Price has been set to original default");
                for(int j=0; j<games.size(); j++) {
                    if(games.get(j).getGameID() == gameID) {
                        price = games.get(j).getDefaultPrice();
                        tblPrices.setValueAt(price, section - 1, 1);
                    }
                }
            }
            
            //Once validation checks are performed the new price will be added to priceList which will then be added to the MS Access database using sql
            Price thisPrice = new Price(0, gameID, level, section, price);
            pList.updatePrice(thisPrice);

        }

        tblPrices.setValueAt(price, section - 1, 1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPrices = new javax.swing.JTable();
        cbxLevel = new javax.swing.JComboBox();
        cbxFixtures = new javax.swing.JComboBox();
        btnUpdatePrices = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblEmptyCellError = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnSetDefaultPrice = new javax.swing.JButton();
        txtDefaultPrice = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblPrices.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Integer(1), null},
                { new Integer(2), null},
                { new Integer(3), null},
                { new Integer(4), null},
                { new Integer(5), null},
                { new Integer(6), null},
                { new Integer(7), null},
                { new Integer(8), null},
                { new Integer(9), null},
                { new Integer(10), null},
                { new Integer(11), null},
                { new Integer(12), null},
                { new Integer(13), null},
                { new Integer(14), null},
                { new Integer(15), null},
                { new Integer(16), null},
                { new Integer(17), null},
                { new Integer(18), null},
                { new Integer(19), null},
                { new Integer(20), null}
            },
            new String [] {
                "Section", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblPrices);
        if (tblPrices.getColumnModel().getColumnCount() > 0) {
            tblPrices.getColumnModel().getColumn(0).setResizable(false);
            tblPrices.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        cbxLevel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cbxLevel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2" }));
        cbxLevel.setSelectedIndex(-1);
        cbxLevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxLevelActionPerformed(evt);
            }
        });

        cbxFixtures.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cbxFixtures.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxFixtures.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxFixturesActionPerformed(evt);
            }
        });

        btnUpdatePrices.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnUpdatePrices.setText("Update");
        btnUpdatePrices.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdatePricesActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Level:");

        lblEmptyCellError.setText("...");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Update Default Price:");

        btnSetDefaultPrice.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSetDefaultPrice.setText("Set Default");
        btnSetDefaultPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetDefaultPriceActionPerformed(evt);
            }
        });

        txtDefaultPrice.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblEmptyCellError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(273, 273, 273)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbxLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtDefaultPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnSetDefaultPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                        .addComponent(btnUpdatePrices, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(cbxFixtures, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxLevel))
                .addGap(27, 27, 27)
                .addComponent(lblEmptyCellError, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSetDefaultPrice)
                            .addComponent(txtDefaultPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUpdatePrices)
                        .addContainerGap())))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(23, 23, 23)
                    .addComponent(cbxFixtures, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(556, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxFixturesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxFixturesActionPerformed
        //String game is set to the user selection in cbxFixtures
        String game = (String) cbxFixtures.getSelectedItem();
        //The selection in cbxFixtures is split between the opponent name and the game date
        String[] gameElements = game.split(" \\| ", 0);
        
        //This for loop iterates through the games from the database
        for (int i = 0; i < games.size(); i++) {
            //if the opponent name in the database is equal to the opponent name selected in cbxFixtures...
            //than the gameID can be found using a getter method on this record where the opponent names equal eachother. 
            if (games.get(i).getOpponent().equals(gameElements[0])) {
                gameID = games.get(i).getGameID();
                
            }
        }

    }//GEN-LAST:event_cbxFixturesActionPerformed

    private void btnUpdatePricesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdatePricesActionPerformed
        //This stops the cell from editing as currently edited cells will have a value set and thus will not be updated
        if (tblPrices.isEditing()) {
            tblPrices.getCellEditor().stopCellEditing();
        }
        
        //The updatePrice method is called once btnUpdatePrices is pressed
        updatePrice();
    }//GEN-LAST:event_btnUpdatePricesActionPerformed

    private void btnSetDefaultPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetDefaultPriceActionPerformed
        //The updateDefaultValue method is called once btnUpdateDefaultPrices is pressed
        updateDefaultValue();
    }//GEN-LAST:event_btnSetDefaultPriceActionPerformed

    private void cbxLevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxLevelActionPerformed
        //The level int is set to the user selection in cbxLevel
        level = Integer.parseInt((String) cbxLevel.getSelectedItem());
        setTableValues();
    }//GEN-LAST:event_cbxLevelActionPerformed

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
            java.util.logging.Logger.getLogger(ChangePriceTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChangePriceTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChangePriceTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChangePriceTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new ChangePriceTable().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSetDefaultPrice;
    private javax.swing.JButton btnUpdatePrices;
    private javax.swing.JComboBox cbxFixtures;
    private javax.swing.JComboBox cbxLevel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEmptyCellError;
    private javax.swing.JTable tblPrices;
    private javax.swing.JTextField txtDefaultPrice;
    // End of variables declaration//GEN-END:variables
}
