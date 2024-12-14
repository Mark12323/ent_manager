
package crazypanel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.awt.Color;
import java.util.List;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import javaswingdev.drawer.Drawer;
import javaswingdev.drawer.DrawerController;
import javaswingdev.drawer.DrawerItem;
import javaswingdev.drawer.EventDrawer;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class MainPage extends javax.swing.JFrame {

    private Connection con;
    private DrawerController drawer;
    public String[] totalarray;
    public String[] quantityarray ;
    public String type;
    String[] pnamearray;
    String[] snarray;
    String urlink = "jdbc:MySQL://localhost:3306/java_users_off";
    String u = "root";
    String p = "";
    int emptie= 0;
    int nonemptie = 0;
    public List<Object> nameData;
    public List<Object> quanData;
    public List<Object> costData;
    public List<Object> totData;
    public MainPage() {
        
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
        
        notifyStock notify = new notifyStock();
        Thread thread = new Thread(notify);
        thread.start();
        InvGenScheduler gen = new InvGenScheduler();
        Thread t1 = new Thread(gen);
        t1.start();
    }

    private void Close() {
        this.dispose();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        SalesTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        Product = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Staff = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        quantity = new javax.swing.JTextField();
        Add = new javax.swing.JButton();
        Remove = new javax.swing.JButton();
        Confirm = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        Total = new javax.swing.JLabel();
        PType = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jButton1.setText("|||");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 21, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton1)
                .addGap(0, 24, Short.MAX_VALUE))
        );

        SalesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S/N", "Item Name", "type", "Quantity", "Price", "total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        SalesTable.setRowHeight(80);
        SalesTable.setRowMargin(5);
        jScrollPane1.setViewportView(SalesTable);

        jLabel1.setText("Add by Name");

        Product.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ProductKeyPressed(evt);
            }
        });

        jLabel2.setText("Product Type");

        jLabel5.setText("Staff Name");

        jLabel6.setText("Quantity");

        quantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantityActionPerformed(evt);
            }
        });

        Add.setText("Add ");
        Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddActionPerformed(evt);
            }
        });

        Remove.setText("Remove");
        Remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveActionPerformed(evt);
            }
        });

        Confirm.setText("Confirm");
        Confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Total");

        Total.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Total)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Non-Empties", "Empties" }));
        PType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PTypeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(Staff, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Product, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(Confirm, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Add, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(Remove, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(PType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Product, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(PType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Staff, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Add, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Remove, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Confirm, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(974, 974, 974))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(132, 132, 132))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
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

    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed
        // TODO add your handling code here:
        String searchquery = Product.getText();
        String price = "";
        try (Connection con = DriverManager.getConnection(urlink , u , p)) {
          String sql = "SELECT price FROM product_data WHERE name = ?";
          try (PreparedStatement statement = con.prepareStatement(sql)) {
              statement.setString(1, searchquery);
              try (ResultSet resultSet = statement.executeQuery()) {
                  if(resultSet.next()) {
                      price = resultSet.getString("price");
                  }
                  else {
                    JOptionPane.showMessageDialog(this , "Product not found");
                  }
              }
          }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if(Product.getText().equals("") || quantity.getText().equals("")) {
            JOptionPane.showMessageDialog(this , "Product Name and Quantity required");
        }
        else {
            if(price.equals("")) JOptionPane.showMessageDialog(this , "Fatal error occured");
            DefaultTableModel model = (DefaultTableModel) SalesTable.getModel();
            int quant = Integer.parseInt(quantity.getText());
            int pr = Integer.parseInt(price);
            int tot = pr * quant;
            String total = Integer.toString(tot);
            
            int serial = model.getRowCount() + 1;
            String sdata = Integer.toString(serial);
            String [] Data = {sdata, Product.getText(), PType.getSelectedItem().toString() , quantity.getText(), price, total};
            model.addRow(Data);
            Product.setText("");
            quantity.setText("");
            //final lap
            int columnind = 5;
            int rowCount = model.getRowCount();
            int sum = 0;
            for (int row = 0; row < rowCount; row++) {
           Object cellValue = model.getValueAt(row, columnind);
            if (cellValue instanceof Integer) {
                sum += (Integer) cellValue;
            } else if (cellValue instanceof String) {
                try {
                    sum += Integer.parseInt((String) cellValue);
                } catch (NumberFormatException e) {
                    // Handle parsing error
                }
            }
            }
            String fin = Integer.toString(sum);
            Total.setText(fin);     
        }
    }//GEN-LAST:event_AddActionPerformed

    private void RemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) SalesTable.getModel();
        
        if (SalesTable.getSelectedRowCount() == 1) {
            model.removeRow(SalesTable.getSelectedRow());
        }
        else if (SalesTable.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Empty table");
        }
        else {
            JOptionPane.showMessageDialog(this, "Select Row");
        }
    }//GEN-LAST:event_RemoveActionPerformed

    private void ConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmActionPerformed
        int es = 0;
        int nes = 0;
        DefaultTableModel model = (DefaultTableModel) SalesTable.getModel();     
        for (int row = 0; row < model.getRowCount(); row++) {
            String type = (String) model.getValueAt(row, 2);
            String mm = (String) model.getValueAt(row, 3);
            int quantity = Integer.parseInt(mm);
            
            if (type.equals("empties")) {
                es += quantity;
            } else if (type.equals("non-empties")) {
                nes += quantity;
            }
        }
        
        
         String queryA = "SELECT SUM(quantity) AS total_quantity FROM product_data WHERE type = 'Empties'";
         String queryB = "SELECT SUM(quantity) AS total_quantity FROM product_data WHERE type = 'Non-Empties'";
        
        //pass the values of the table into an arraylist
        nameData = new ArrayList<>();
        quanData = new ArrayList<>();
        costData = new ArrayList<>();
        totData = new ArrayList<>();
        
         for (int row = 0; row < model.getRowCount(); row++) {
            Object value = model.getValueAt(row, 1);
            nameData.add(value);
        }
         for (int row = 0; row < model.getRowCount(); row++) {
            Object value = model.getValueAt(row, 3);
            quanData.add(value);
        }
         for (int row = 0; row < model.getRowCount(); row++) {
            Object value = model.getValueAt(row, 4);
            costData.add(value);
        }
         for (int row = 0; row < model.getRowCount(); row++) {
            Object value = model.getValueAt(row, 5);
            totData.add(value);
        }
             
        int option = JOptionPane.showConfirmDialog(null, "Do you want to proceed?(Ensure data is correct)", "Confirmation", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            Printform form = new Printform();
            form.tval.setText(Total.getText());
            StringBuilder textBuilder = new StringBuilder();
            for (int i = 0 ; i < nameData.size() ; i++) {
                textBuilder.append(nameData.get(i)+ "  " + quanData.get(i) + "  " + costData.get(i) + "   " + totData.get(i) + "  \n");
            }
            
            form.Reciept.setText("******************************\n");
            form.Reciept.setText(form.Reciept.getText() + " Jezicob Distributions Limited\n");
            form.Reciept.setText(form.Reciept.getText() + "    Enugu, Nigeria\n");
            form.Reciept.setText(form.Reciept.getText() + "      Reciept Invoice\n");
            form.Reciept.setText(form.Reciept.getText() + "\n");
            form.Reciept.setText(form.Reciept.getText() + "\n");
            Random rand = new Random();
            long randomNum = (long) (rand.nextDouble() * 9_000_000_000L) + 1_000_000_000L;
            String ra = String.valueOf(randomNum);
            form.Reciept.setText(form.Reciept.getText() + "Reciept code:"+ ra + "\n");
            form.Reciept.setText(form.Reciept.getText() + "***************************\n");
            form.Reciept.setText(form.Reciept.getText() + "\n");
            form.Reciept.setText(form.Reciept.getText() + "\n");
            form.Reciept.setText(form.Reciept.getText() + textBuilder.toString());
            form.Reciept.setText(form.Reciept.getText() + "\n");
            form.Reciept.setText(form.Reciept.getText() + "----------------------------\n");
            form.Reciept.setText(form.Reciept.getText()+ "Total:                 " + Total.getText() + "\n");
            form.Reciept.setText(form.Reciept.getText() + "\n");
            form.Reciept.setText(form.Reciept.getText() + "------------------------------\n");
            //form.Reciept.setText(form.Reciept.getText() + "                           " + Total.toString());
            ////////////////////////////////////////////////////////////////////////
            Date d = new Date();
            String date = d.toString();
            form.Reciept.setText(form.Reciept.getText() +date+ "\n");
            form.Reciept.setText(form.Reciept.getText() + "*************************\n");
            form.Reciept.setText(form.Reciept.getText() + "\n");
            form.Reciept.setText(form.Reciept.getText() + "\n");
            form.Reciept.setText(form.Reciept.getText() + "\n");
            form.Reciept.setText(form.Reciept.getText() + "      Thanks for Patronizing Us\n");
            form.Reciept.setText(form.Reciept.getText() + "\n");
            form.Reciept.setText(form.Reciept.getText() + "\n");
            form.Reciept.setText(form.Reciept.getText() + "\n");
            form.Reciept.setText(form.Reciept.getText() + "\n");
            form.setVisible(true);
            form.pack();
                
            int columnCount = 5; 
            int rowCount = model.getRowCount();
            try {    
            con = DriverManager.getConnection(urlink , u , p);
            Statement st = con.createStatement();
            
            String sql = "INSERT INTO sales_record (item_name, quantity, total, type) VALUES (?, ?, ?, ?)";
            //first variable assignment
            for (int row = 0; row < model.getRowCount(); row++) {
                // Get data from specific columns in the row
                String name = (String) model.getValueAt(row, 1);
                String quan= (String) model.getValueAt(row, 3);
                String total = (String) model.getValueAt(row, 5);
                String type = (String) model.getValueAt(row, 2);
                
                PreparedStatement statement = con.prepareStatement(sql);
                // Set parameters for the SQL statement
                statement.setString(1, name);
                statement.setString(2, quan);
                statement.setString(3, total);
                statement.setString(4, type);
                                
                // Execute the SQL statement to insert the row into the database
                statement.executeUpdate();
            }
            
            
            String query = "SELECT empties, nonempties FROM stock_data LIMIT 1";
try (Statement statement = con.createStatement();
     ResultSet resultSet = statement.executeQuery(query)) {
    if (resultSet.next()) {
        emptie = resultSet.getInt("empties");
        nonemptie = resultSet.getInt("nonempties");
        // Now you have the values in the empties and nonempties variables
    } else {
        // Handle case where no rows were returned
    }
} catch (SQLException ex) {
    // Handle any SQL exceptions
    ex.printStackTrace();
}
int stotal = emptie + nonemptie;

            
            int emptiesQuantity = 0;
int nonEmptiesQuantity = 0;
for (int row = 0; row < model.getRowCount(); row++) {
    String type = (String) model.getValueAt(row, 2); // Assuming typeColumnIndex is the index of the "type" column
    String q = (String) model.getValueAt(row, 3);
    int quantity = Integer.parseInt(q); // Assuming quantityColumnIndex is the index of the "quantity" column
    
    if ("empties".equals(type)) {
        emptiesQuantity += quantity;
    } else if ("non-empties".equals(type)) {
        nonEmptiesQuantity += quantity;
    }
}

    if (emptie == 0 || nonemptie == 0) {
        JOptionPane.showMessageDialog(this , "Stock is Empty");
    } else {
        emptie = emptie - emptiesQuantity;
        nonemptie  = nonemptie - nonEmptiesQuantity;
        stotal = emptie - nonemptie;
    }
            String a = Integer.toString(emptie);
            String b = Integer.toString(nonemptie);
            //
            String updateQuery = "UPDATE stock_data SET Empties = ?, NonEmpties = ?";
    
    // Create a PreparedStatement
    try (PreparedStatement preparedStatement = con.prepareStatement(updateQuery)) {
        // Set the values for the parameters
        preparedStatement.setString(1, a);
        preparedStatement.setString(2, b);
        
        // Execute the update statement
        int rowsAffected = preparedStatement.executeUpdate();
    } catch (Exception e) {
        
    }
            
            //
            
            
            InventoryPage page = new InventoryPage();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this , "Ensure sales data is correct");
        }
        
    }//GEN-LAST:event_ConfirmActionPerformed

    private void quantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantityActionPerformed

    private void PTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PTypeActionPerformed

    private void ProductKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProductKeyPressed

        String Barcode = Product.getText();
// Check if the barcode string is not empty
if (!Barcode.isEmpty() && evt.getKeyCode() == KeyEvent.VK_ENTER) {
    try (Connection con = DriverManager.getConnection(urlink, u, p)) {
        String sql = "SELECT name FROM product_data WHERE code = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, Barcode);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    Product.setText(name);
                } else {
                    JOptionPane.showMessageDialog(this, "Product not found");
                    Product.setText("");
                }
            }
        }
    } catch (SQLException | NumberFormatException e) {
        e.printStackTrace();
    }
}
    }//GEN-LAST:event_ProductKeyPressed

    private static int executeQuery(Connection connection, String query) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt("total_quantity");
            }
        }
        return 0; // Return 0 if no result or error occurs
    }
    
    public static void main(String args[]) {
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add;
    private javax.swing.JButton Confirm;
    private javax.swing.JComboBox<String> PType;
    private javax.swing.JTextField Product;
    private javax.swing.JButton Remove;
    private javax.swing.JTable SalesTable;
    private javax.swing.JTextField Staff;
    private javax.swing.JLabel Total;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField quantity;
    // End of variables declaration//GEN-END:variables
}
