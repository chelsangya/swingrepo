

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sageapp.DAO;
import java.sql.Statement;
import sageapp.database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import sageapp.model.BillModel;

//
//package sageapp.DAO;
//import sageapp.database.MySqlConnection;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import sageapp.model.AuthData;
//import sageapp.model.LoginModel;
//import sageapp.model.RegisterModel;

/**
 *
 * @author sangyakoirala
 */
public class BillDAO extends MySqlConnection {
public boolean addBill(BillModel bill) {
    String createBillsTableSQL = "CREATE TABLE IF NOT EXISTS bills ("
            + "bill_id INT AUTO_INCREMENT PRIMARY KEY, "
            + "uid INT NOT NULL, "
            + "phone VARCHAR(15) NOT NULL, "
            + "total_price INT NOT NULL, "
            + "date_of_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
            + "FOREIGN KEY (phone) REFERENCES customers(phone) ON DELETE CASCADE)";

    String createBillProductsTableSQL = "CREATE TABLE IF NOT EXISTS bill_products ("
            + "bill_id INT NOT NULL, "
            + "product_id INT NOT NULL, "
            + "quantity INT NOT NULL, "
            + "PRIMARY KEY (bill_id, product_id), "
            + "FOREIGN KEY (bill_id) REFERENCES bills(bill_id) ON DELETE CASCADE, "
            + "FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE)";

    String billInsertSQL = "INSERT INTO bills (uid, phone, total_price) VALUES (?, ?, ?)";
    String productInsertSQL = "INSERT INTO bill_products (bill_id, product_id, quantity) VALUES (?, ?, ?)";

    try (Connection conn = openConnection();
         PreparedStatement createTableStmt = conn.prepareStatement(createBillsTableSQL);
         PreparedStatement createBillProductsTableStmt = conn.prepareStatement(createBillProductsTableSQL);
         PreparedStatement ps = conn.prepareStatement(billInsertSQL, Statement.RETURN_GENERATED_KEYS)) {

        // Create tables if not exists
        createTableStmt.executeUpdate();
        createBillProductsTableStmt.executeUpdate();

        // Insert the bill
        ps.setInt(1, bill.getUid());
        ps.setString(2, bill.getPhone());
        ps.setInt(3, bill.getTotalPrice());

        int result = ps.executeUpdate();

        if (result > 0) {
            // Retrieve the generated bill_id
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int billId = generatedKeys.getInt(1); // Get the generated bill_id

                    // Now, insert into the bill_products table for each product
                    try (PreparedStatement productStmt = conn.prepareStatement(productInsertSQL)) {
                        for (Map.Entry<Integer, Integer> entry : bill.getProductMap().entrySet()) {
                            productStmt.setInt(1, billId);
                            productStmt.setInt(2, entry.getKey()); // Product ID
                            productStmt.setInt(3, entry.getValue()); // Quantity
                            productStmt.addBatch(); // Add to batch
                        }

                        // Execute the batch insert for bill_products
                        productStmt.executeBatch();
                    }

                    System.out.println("Generated Bill ID: " + billId);
                    return true;
                } else {
                    throw new SQLException("Creating bill failed, no ID obtained.");
                }
            }
        } else {
            return false;
        }

    } catch (SQLException e) {
        System.out.println("Error in adding bill: " + e.getMessage());
        return false;
    }
}




  public String findCustomer(String phone, int uid) {
    String name = null;
    String sql = "SELECT name FROM customers WHERE phone = ? AND uid=?";

    try (Connection conn = openConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, phone);
        ps.setInt(2, uid);
        System.out.println("Executing query...");

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) { // Check if a record exists
                name = rs.getString("name"); // Retrieve the name from the result set
            }
        }
    } catch (Exception e) {
        System.out.println("Error in fetching Customer: " + e.getMessage());
    }

    return name;
}
    public int countCustomersByUid(int uid) {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM customers WHERE uid = ?";

        try (Connection conn = openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, uid);
            System.out.println("Counting customers for uid: " + uid);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }
        } catch (Exception e) {
            System.out.println("Error in counting customers: " + e.getMessage());
        }

        return count;
    }

}
