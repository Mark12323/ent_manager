
package crazypanel;

import com.formdev.flatlaf.FlatDarkLaf;
import com.github.anastaciocintra.escpos.EscPos;
import com.github.anastaciocintra.output.PrinterOutputStream;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javaswingdev.drawer.Drawer;
import javaswingdev.drawer.DrawerController;
import javaswingdev.drawer.DrawerItem;
import javaswingdev.drawer.EventDrawer;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class RecordsPage extends javax.swing.JFrame {

    private Connection con;
    private DrawerController drawer;
    String printerName;
    public RecordsPage() {
        
        String urlink = "jdbc:MySQL://localhost:3306/java_users_off";
        String u = "root";
        String p = "";
        try {
            con = DriverManager.getConnection(urlink , u , p);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        
        initComponents();
        drawer = Drawer.newDrawer(this)
                .header(new Header())
                .background(new Color(250, 250, 250))
                //.backgroundTransparent(0.3f)
                .drawerBackground(new Color(87, 87, 87))
                .addChild(new DrawerItem("Sales").icon(new ImageIcon(getClass().getResource(""))).build())
                .addChild(new DrawerItem("Financial Report").icon(new ImageIcon(getClass().getResource(""))).build())
                .addChild(new DrawerItem("Personnel").icon(new ImageIcon(getClass().getResource(""))).build())
                .addChild(new DrawerItem("Records").icon(new ImageIcon(getClass().getResource(""))).build())
                .addFooter(new Footer())
                .event(new EventDrawer () {
                @Override
                public void selected(int i, DrawerItem di) {
                    if (i == 0) {
                        Close();
                        MainPage firstpage = new MainPage();
                        firstpage.setVisible(true);
                        firstpage.pack();
                    }
                    else if (i == 1) {
                        Close();
                        RecordsPage secondpage = new RecordsPage();
                        secondpage.setVisible(true);
                        secondpage.pack();
                    }
                    else if (i == 2) {
                        Close();
                        ManagerOverride popup = new ManagerOverride();
                        popup.setVisible(true);
                        popup.pack();
                    }
                    else if (i == 3) {
                        Close();
                        InventoryPage popdown = new InventoryPage();
                        popdown.setVisible(true);
                        popdown.pack();
                    }
                    
                }                    
                })
                .build(); 
        
         String q1 = "SELECT reciept_id AS ID , date AS Date , data AS Data\n"
                 + "FROM print_data\n"
                 + "ORDER BY Date";
         try {
             PreparedStatement st = con.prepareStatement(q1);
             ResultSet rt = st.executeQuery();
             DefaultTableModel model = (DefaultTableModel) RecieptTable.getModel();
             model.setRowCount(0);
             
             while (rt.next()) {
                 model.addRow(new Object[] {
                 rt.getString("ID"),
                 rt.getString("Date"),
                 rt.getString("Data")
                 });
             }
         } catch(Exception x) {
             x.printStackTrace();
         }
         
         
         String query = "SELECT name AS Name, 'purchase' AS Transaction_Type, quantity, price, Date_and_time AS Date \n" +
                        "FROM product_data \n" +
                        "UNION ALL \n" +
                        "SELECT item_name AS Name, 'sale' AS Transaction_Type, quantity, total, date AS Date \n" +
                        "FROM sales_record\n" +
                        "ORDER BY Date;" ;
         
         try {
             PreparedStatement st = con.prepareStatement(query);
             ResultSet rt = st.executeQuery();
             
             DefaultTableModel model = (DefaultTableModel) Display.getModel();
             model.setRowCount(0);
             int serialNumber = 1;
             
             while (rt.next()) {
                // Add row to the table model
                model.addRow(new Object[]{
                        serialNumber,
                        rt.getString("Name"),
                        rt.getString("Transaction_Type"),
                        rt.getInt("Quantity"),
                        rt.getInt("price"),
                        rt.getString("Date")
                });
                
                // Increment serial number
                serialNumber++;
            }
         }
         catch (Exception e) {
             e.printStackTrace();
         }
         ///fill in performance numbers
         //////////////
          String q = "SELECT SUM(total) AS total_amount FROM sales_record";
        try {
            PreparedStatement st = con.prepareStatement(q);
            ResultSet rs = st.executeQuery();

            // Check if there is a result
            if (rs.next()) {
                int totalAmount = rs.getInt("total_amount");
                String tot = Integer.toString(totalAmount);
                totincome.setText(tot);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ///////
        String qu = "SELECT SUM(price) AS total_amount FROM product_data";
        try {
            PreparedStatement st = con.prepareStatement(qu);
            ResultSet rs = st.executeQuery();
            
            //Check if there is a result
            if (rs.next()) {
                int totalAmount = rs.getInt("total_amount");
                String tot = Integer.toString(totalAmount);
                expenses.setText(tot);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        int income;
        int total = Integer.parseInt(totincome.getText());
        int expense = Integer.parseInt(expenses.getText());
        income = total - expense;
        String net = Integer.toString(income);
        netincome.setText(net);
    }

    public void Close () {
        this.dispose();
    }
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        totincome = new javax.swing.JLabel();
        icon1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        netincome = new javax.swing.JLabel();
        icon2 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        expenses = new javax.swing.JLabel();
        icon3 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        Display = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        Print = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        RecieptTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jButton1.setText("|||");
        jButton1.setAutoscrolls(true);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setPreferredSize(new java.awt.Dimension(264, 80));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Total Income");

        totincome.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        totincome.setText("0.00");

        icon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/naira des.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(icon1, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                    .addComponent(totincome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totincome, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(icon1)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Transaction History");

        jPanel7.setBackground(new java.awt.Color(102, 102, 102));
        jPanel7.setPreferredSize(new java.awt.Dimension(264, 80));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Net Income");

        netincome.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        netincome.setText("0.00");

        icon2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/naira des.png"))); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(icon2, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                    .addComponent(netincome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 30, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(netincome, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(icon2)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(102, 102, 102));
        jPanel8.setPreferredSize(new java.awt.Dimension(264, 80));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Expenses");

        expenses.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        expenses.setText("0.00");

        icon3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/naira des.png"))); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(icon3, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                    .addComponent(expenses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 30, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(expenses, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(icon3)
        );

        Display.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "S/N", "Name", "Transaction type", "Price", "Quantity", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(Display);

        jTabbedPane1.addTab("Transactions", jScrollPane1);

        Print.setText("Print");
        Print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintActionPerformed(evt);
            }
        });

        RecieptTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Name/ID", "Date", "Details"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(RecieptTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(868, Short.MAX_VALUE)
                .addComponent(Print))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(Print)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Reciept", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(61, 61, 61))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(348, 348, 348)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(16, 16, 16)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

  
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (drawer.isShow()) {
            drawer.hide();
        }        
        else {
            drawer.show();
        }
    }//GEN-LAST:event_jButton1ActionPerformed
     public String setPrinterName(String printerName) {
        this.printerName = printerName;
        return printerName;
    }
    
    private void PrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintActionPerformed
        // TODO add your handling code here:                 
         DefaultTableModel model = (DefaultTableModel) RecieptTable.getModel();
         if (RecieptTable.getSelectedRowCount() == 1) {
             int selectedRow = RecieptTable.getSelectedRow();
             String rowData = RecieptTable.getValueAt(selectedRow, 2).toString(); // Assuming "data" column is at index 2
              try {
        PrintService defaultPrinter = PrintServiceLookup.lookupDefaultPrintService();
        String pn = setPrinterName(defaultPrinter.getName());        PrintService printService = PrinterOutputStream.getPrintServiceByName(pn);
                System.out.println(pn);

        if(printService == null) {
            System.out.println("Printer '" + printerName + "' not found.");
            System.exit(0);
        }
        PrinterOutputStream printerOutputStream = new PrinterOutputStream(printService);
        try (EscPos escpos = new EscPos(printerOutputStream)) {
            escpos.writeLF(rowData);
                    escpos.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }}
       catch(Exception e) {
           e.printStackTrace();
       }
         }
        else if (RecieptTable.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Empty table");
        }
        else {
            JOptionPane.showMessageDialog(this, "Select Row");
        }
    }//GEN-LAST:event_PrintActionPerformed

   
    public static void main(String args[]) { 
        FlatDarkLaf.registerCustomDefaultsSource("crazypanel");
        FlatDarkLaf.setup();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Display;
    private javax.swing.JButton Print;
    private javax.swing.JTable RecieptTable;
    public javax.swing.JLabel expenses;
    private javax.swing.JLabel icon1;
    private javax.swing.JLabel icon2;
    private javax.swing.JLabel icon3;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    public javax.swing.JLabel netincome;
    public javax.swing.JLabel totincome;
    // End of variables declaration//GEN-END:variables

   
}
