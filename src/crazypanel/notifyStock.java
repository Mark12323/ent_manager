
package crazypanel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class notifyStock implements Runnable {
    
    
    private static int executeQuery(Connection connection, String query) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt("total_quantity");
            }
        }
        return 0; // Return 0 if no result or error occurs
    }
     InventoryPage page = new InventoryPage();

     String url = "jdbc:MySQL://localhost:3306/java_users_off";
    String user = "root";
    String pass = "";
    Connection con;
    @Override
    public void run() {
   
        
        
        /////
        while (true) {
            int empties = Integer.parseInt(page.estock.getText());
            int nonempties = Integer.parseInt(page.nestock.getText());

            // Check if 'wer' is within the range 2-7
            if ((empties >= 0 && empties <= 100) && (nonempties >= 0 && empties <= 100)) {
                // Display reminder message
                JOptionPane.showMessageDialog(null, "Attention: Stock is running low!");
            }

            // Sleep for three hours (in milliseconds)
            try {
                Thread.sleep(3 * 60 * 60 * 1000); // 3 hours in milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}