
package crazypanel;

import java.awt.Color;
import javaswingdev.drawer.Drawer;
import javaswingdev.drawer.DrawerController;
import javaswingdev.drawer.DrawerItem;
import javaswingdev.drawer.EventDrawer;
import javax.swing.ImageIcon;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement; 
import javax.swing.table.DefaultTableModel;

public class PersonnelPage extends javax.swing.JFrame {

    private int count = Count();
    private Connection con;
    private DrawerController drawer;
    public PersonnelPage() {
        initComponents();
        
        
        String urlink = "jdbc:MySQL://localhost:3306/java_users_off";
        String u = "root";
        String p = "";
        try {
            con = DriverManager.getConnection(urlink , u , p);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        
        //
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
        while (count > 0) {
            String sql = "SELECT * FROM staff";
            try {
                PreparedStatement st = con.prepareStatement(sql);
                ResultSet rt = st.executeQuery();
                DefaultTableModel model = (DefaultTableModel)datatable.getModel();
                model.setRowCount(0);
                while (rt.next()) {
                    model.addRow(new String [] {rt.getString(1) , rt.getString(2) , rt.getString(3) , rt.getString(7) , rt.getString(6) , rt.getString(11)});
                }
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
            count--;
        }
        //notifyStock notify = new notifyStock();
        //notify.checkStock();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Drawerbutton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        datatable = new javax.swing.JTable();
        Add = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        Refresh = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        Drawerbutton.setText("|||");
        Drawerbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DrawerbuttonActionPerformed(evt);
            }
        });

        datatable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "S/N", "Surname", "Last Name", "Contact", "Gender", "Role"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        datatable.setRowHeight(30);
        jScrollPane1.setViewportView(datatable);
        if (datatable.getColumnModel().getColumnCount() > 0) {
            datatable.getColumnModel().getColumn(0).setResizable(false);
            datatable.getColumnModel().getColumn(0).setPreferredWidth(20);
        }

        Add.setText("Add");
        Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Staff List");

        Refresh.setText("Refresh");
        Refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshActionPerformed(evt);
            }
        });

        jButton1.setText("Access");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Drawerbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 945, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Add, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(Refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(Drawerbutton)
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(Refresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Add, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)))
                .addGap(23, 23, 23))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void DrawerbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DrawerbuttonActionPerformed
        if (drawer.isShow()) {
            drawer.hide();
        }        
        else {
            drawer.show();
        }
    }//GEN-LAST:event_DrawerbuttonActionPerformed

    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed
        // TODO add your handling code here:
        AddEmployee Emp = new AddEmployee();
        Emp.setVisible(true);
        Emp.pack();
    }//GEN-LAST:event_AddActionPerformed

    private void RefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshActionPerformed
        // TODO add your handling code here:
        refresh();
    }//GEN-LAST:event_RefreshActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        AccessPage page = new AccessPage();
        page.setVisible(true);
        page.pack();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void refresh() {
        this.dispose();
        PersonnelPage newpage = new PersonnelPage();
        newpage.setVisible(true);
        newpage.pack();
    }
    private void Close() {
        this.dispose(); 
    }
    
    private int Count() {
        int rowCount = 0;
        String sql = "SELECT COUNT(*) AS row_count FROM staff";

        try {
            String urlink = "jdbc:MySQL://localhost:3306/java_users_off";
            String u = "root";
            String p = "";
            try {
                con = DriverManager.getConnection(urlink , u , p);
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
            
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rt = st.executeQuery();
            if (rt.next()) {
                rowCount = rt.getInt("row_count");
            } else {
                System.out.println("Error in retrieving row count.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return rowCount;
    }
    
    public static void main(String args[]) {   
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add;
    private javax.swing.JButton Drawerbutton;
    private javax.swing.JButton Refresh;
    private javax.swing.JTable datatable;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
