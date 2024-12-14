
package crazypanel;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.BorderFactory;
import javaswingdev.drawer.Drawer;
import javaswingdev.drawer.DrawerController;
import javaswingdev.drawer.DrawerItem;
import javaswingdev.drawer.EventDrawer;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
//import javax.swing.table.DefaultTableModel;

public class InventoryPage extends javax.swing.JFrame {
    
    String url = "jdbc:MySQL://localhost:3306/java_users_off";
    String user = "root";
    String pass = "";
    Connection con;
    private String type;
    private DrawerController drawer;
    String Barcode;
    int empties= 0;
    int nonempties = 0;
    public InventoryPage() {
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
        //Database Connection
   
        try {
            con = DriverManager.getConnection(url , user , pass);
            int count = getRowCountFromDatabase();
            String sql = "SELECT * FROM product_data";
            while (count > 0) {
            
            try {
                PreparedStatement st = con.prepareStatement(sql);
                ResultSet rt = st.executeQuery();
                DefaultTableModel model = (DefaultTableModel)DataTable.getModel();
                model.setRowCount(0);
                while (rt.next()) {
                    model.addRow(new String [] {rt.getString(1) , rt.getString(2) , rt.getString(4) , rt.getString(3) , rt.getString(5) , rt.getString(6) , rt.getString(7)});
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this , "An Unexpected Fatal Error occured. Code:" + ex.toString());
            }
            count--;
            }
            /////
            
                String queryA = "SELECT SUM(quantity) AS total_quantity FROM product_data WHERE type = 'Empties'";
            String queryB = "SELECT SUM(quantity) AS total_quantity FROM product_data WHERE type = 'Non-Empties'";

            try{
            // Execute query for type A
            int totalQuantityA = executeQuery(con, queryA);
            // Execute query for type B
            int totalQuantityB = executeQuery(con, queryB);
            String emp = Integer.toString(totalQuantityA);
            String nemp = Integer.toString(totalQuantityB);
            int gtotal = totalQuantityA + totalQuantityB;
            String total = Integer.toString(gtotal);
            
            String queryC = "INSERT INTO stock_data (Empties, NonEmpties) VALUES ('"+emp+"', '"+nemp+"')";
                        
                        Connection con = DriverManager.getConnection( url , user , pass);
            Statement st = con.createStatement();
            st.executeUpdate(queryC);
            
            String query = "SELECT empties, nonempties FROM stock_data LIMIT 1";
try (Statement statement = con.createStatement();
     ResultSet resultSet = statement.executeQuery(query)) {
    if (resultSet.next()) {
        empties = resultSet.getInt("empties");
        nonempties = resultSet.getInt("nonempties");
        // Now you have the values in the empties and nonempties variables
    } else {
        // Handle case where no rows were returned
    }
} catch (SQLException ex) {
    // Handle any SQL exceptions
    ex.printStackTrace();
}
            int stotal = empties + nonempties;
            String a = Integer.toString(empties);
            String b = Integer.toString(nonempties);
            String c = Integer.toString(stotal);
            nestock.setText(b);
            estock.setText(a);
            totstock.setText(c);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
            
            ////
            
            
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    
    public void Close() {
        this.dispose();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        totstock = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        estock = new javax.swing.JLabel();
        nestock = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        Remove = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        Pname = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        Ptype = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        Quantity = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        Cost = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        Price = new javax.swing.JTextField();
        Add = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        Code = new javax.swing.JLabel();
        bscan = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        DataTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jButton1.setText("|||");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        jLabel1.setText("Total Stock");

        totstock.setText("jLabel2");

        jLabel3.setText("Empties");

        jLabel4.setText("Non-Empties");

        estock.setText("jLabel10");

        nestock.setText("jLabel11");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(totstock, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(estock, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nestock, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(133, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(totstock))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(estock))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(nestock))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jPanel2.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setText("Add New Stock");

        Remove.setText("Remove");

        jLabel6.setText("Product Name");

        Pname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PnameActionPerformed(evt);
            }
        });

        jLabel7.setText("Product Type");

        Ptype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Non-Empties", "Empties"}));
        Ptype.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                PtypeItemStateChanged(evt);
            }
        });

        jLabel8.setText("Quantity");

        jLabel9.setText("Cost");

        jLabel12.setText("Price");

        Add.setText("Add");
        Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddActionPerformed(evt);
            }
        });

        jLabel2.setText("Input");

        jLabel10.setText("Code");

        bscan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bscanKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Add)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Ptype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Pname, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Code, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(68, 68, 68))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Price, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Cost, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(90, 90, 90)
                                        .addComponent(Remove))
                                    .addComponent(Quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bscan))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bscan))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Code)
                    .addComponent(jLabel10))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Pname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(Ptype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(Quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(Cost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(Price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Remove)
                    .addComponent(Add))
                .addGap(26, 26, 26))
        );

        DataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "S/N", "Product Name", "Quantity", "Product Type", "Cost", "Price", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        DataTable.setRowHeight(40);
        jScrollPane1.setViewportView(DataTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 711, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         if (drawer.isShow()) {
            drawer.hide();
        }        
        else {
            drawer.show();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void PnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PnameActionPerformed
/**/
    private void PtypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_PtypeItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_PtypeItemStateChanged

    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed
        // TODO add your handling code here:
        if (Pname.getText().equals("") || Ptype.getSelectedItem().toString().equals("") || Quantity.getText().equals("") || Price.getText().equals("") || Cost.getText().equals("")) {
           JOptionPane.showMessageDialog(this , "Empty fields not allowed");
        }
        else{
            try {
            String query;
            int count = getRowCountFromDatabase() +1;
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            
            Connection con = DriverManager.getConnection( url , user , pass);
            Statement st = con.createStatement();
            
            query = "INSERT INTO product_data (id, name, type, quantity, cost, price, code) VALUES ('"+count+"' , '"+Pname.getText()+"' , "
                    + " '"+Ptype.getSelectedItem().toString()+"' , '"+Quantity.getText()+"' , '"+Price.getText()+"' , '"+Cost.getText()+"' , '"+Code.getText()+"')";
            
            st.executeUpdate(query);
            con.close();
            refresh();
            }
            catch(Exception ex) {
                JOptionPane.showMessageDialog(this , "An Unexpected Fatal Error occured. Code:" + ex.toString());
            }
        }
    }//GEN-LAST:event_AddActionPerformed

    private void bscanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bscanKeyPressed
        // TODO add your handling code here:
        Barcode = bscan.getText();
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Code.setText(Barcode);
            bscan.setText("");
        }
    }//GEN-LAST:event_bscanKeyPressed
    
    private int getRowCountFromDatabase() throws SQLException {
    int rowCount = 0;

    try (Connection con = DriverManager.getConnection(url, user, pass);
         PreparedStatement statement = con.prepareStatement("SELECT COUNT(*) FROM product_data");
         ResultSet resultSet = statement.executeQuery()) {
        if (resultSet.next()) {
            rowCount = resultSet.getInt(1);
        }
    }
    return rowCount;
    }
    
    private static int executeQuery(Connection connection, String query) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt("total_quantity");
            }
        }
        return 0; // Return 0 if no result or error occurs
    }
    
    private void refresh() {
        this.dispose();
        InventoryPage newpage = new InventoryPage();
        newpage.setVisible(true);
        newpage.pack();
    }
    
    public static void main(String args[]) {
      
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add;
    private javax.swing.JLabel Code;
    private javax.swing.JTextField Cost;
    private javax.swing.JTable DataTable;
    private javax.swing.JTextField Pname;
    private javax.swing.JTextField Price;
    private javax.swing.JComboBox<String> Ptype;
    private javax.swing.JTextField Quantity;
    private javax.swing.JButton Remove;
    private javax.swing.JTextField bscan;
    public javax.swing.JLabel estock;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel nestock;
    public javax.swing.JLabel totstock;
    // End of variables declaration//GEN-END:variables
}
