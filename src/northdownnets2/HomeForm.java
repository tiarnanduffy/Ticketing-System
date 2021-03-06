/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package northdownnets2;

import BaseData.Customer;
import BaseData.Game;
import BaseData.GameList;
import BaseData.Ticket;
import BaseData.TicketList;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class HomeForm extends javax.swing.JFrame {

    public ArrayList<Game> games;
    public GameList gList;
    public Game thisGame;
    
    public Customer thisCustomer;
    
    public ArrayList<Ticket> tickets;
    public TicketList tList;
    public Ticket thisTicket;
    
    public String userUsername = "";

    
    public HomeForm(Customer c) {
        initComponents();
       
        thisCustomer = c;

        
        gList = new GameList();
        games = gList.getGames();
        
        userUsername = c.getUsername();
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        lblWelcome = new javax.swing.JLabel();
        lblNetsWelcome = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        mniExit = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        mniAmendDetails = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        mniSelectSeat = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        jMenuItem1.setText("jMenuItem1");

        jMenu2.setText("jMenu2");

        jMenu3.setText("jMenu3");

        jMenu4.setText("jMenu4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Home Form\n");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        lblWelcome.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblWelcome.setForeground(new java.awt.Color(255, 255, 255));
        lblWelcome.setText(" ");

        lblNetsWelcome.setBackground(new java.awt.Color(204, 204, 204));
        lblNetsWelcome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/northdownnets2/images/netslogo.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addComponent(lblNetsWelcome)
                .addGap(255, 255, 255)
                .addComponent(lblWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblWelcome)
                .addGap(568, 568, 568))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblNetsWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jMenuBar1.setBackground(new java.awt.Color(51, 51, 51));

        jMenu5.setText("File");

        mniExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/northdownnets2/images/exitIcon.png"))); // NOI18N
        mniExit.setText("Exit");
        mniExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniExitActionPerformed(evt);
            }
        });
        jMenu5.add(mniExit);

        jMenuBar1.add(jMenu5);

        jMenu8.setText("Amend Information");

        mniAmendDetails.setIcon(new javax.swing.ImageIcon(getClass().getResource("/northdownnets2/images/infoIcon.png"))); // NOI18N
        mniAmendDetails.setText("View/Amend Details");
        mniAmendDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniAmendDetailsActionPerformed(evt);
            }
        });
        jMenu8.add(mniAmendDetails);

        jMenuBar1.add(jMenu8);

        jMenu7.setBackground(new java.awt.Color(0, 0, 0));
        jMenu7.setText("Select Seat");

        mniSelectSeat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/northdownnets2/images/seatIcon.png"))); // NOI18N
        mniSelectSeat.setText("Select Seat");
        mniSelectSeat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniSelectSeatActionPerformed(evt);
            }
        });
        jMenu7.add(mniSelectSeat);

        jMenuBar1.add(jMenu7);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 808, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mniAmendDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniAmendDetailsActionPerformed
        //If menu item 'View/Amend details' is selected, DetailsForm is opened
        DetailsForm detailsform = new DetailsForm(thisCustomer);
        detailsform.setVisible(true);
        detailsform.pack();
        detailsform.setLocationRelativeTo(null);
        
        
    }//GEN-LAST:event_mniAmendDetailsActionPerformed

    private void mniExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniExitActionPerformed
        //If menu item 'Exit' is selected the program is exited
        System.exit(0);
    }//GEN-LAST:event_mniExitActionPerformed

    private void mniSelectSeatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniSelectSeatActionPerformed
        //If menu item 'Select Seat' is selected, selectSeat form is opened
        SelectSeat selectseat = new SelectSeat(gList, thisCustomer);
        selectseat.setVisible(true);
        selectseat.pack();
        selectseat.setLocationRelativeTo(null);

    }//GEN-LAST:event_mniSelectSeatActionPerformed

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
            java.util.logging.Logger.getLogger(HomeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
 

        /* Create and display the form */
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeForm(thisCustomer).setVisible(true);
            }
        });*/
    }

    
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblNetsWelcome;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JMenuItem mniAmendDetails;
    private javax.swing.JMenuItem mniExit;
    private javax.swing.JMenuItem mniSelectSeat;
    // End of variables declaration//GEN-END:variables
}
