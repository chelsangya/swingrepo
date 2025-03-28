

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sageapp.DAO;
import sageapp.database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import sageapp.model.CustomerModel;

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
public class CustomerDAO extends MySqlConnection {
  public boolean addCustomer(CustomerModel customer, Integer uid) {
      String createTableSQL = "CREATE TABLE IF NOT EXISTS customers ("
        + "phone VARCHAR(15) NOT NULL, "
        + "uid INT NOT NULL, "
        + "name VARCHAR(255) NOT NULL, "
        + "PRIMARY KEY (phone), "
        +"FOREIGN KEY (uid) REFERENCES users(id) ON DELETE CASCADE"
        + ")";

    String sql = "INSERT INTO customers(uid,phone,name) VALUES(?,?,?)";

    try (Connection conn = openConnection();
         PreparedStatement createTableStmt = conn.prepareStatement(createTableSQL);
         PreparedStatement ps = conn.prepareStatement(sql)) {

        // Create table if not exists
        createTableStmt.executeUpdate();
        System.out.println("TABLE CREATED");

        // Insert product into table
        ps.setInt(1, uid);
        ps.setString(2, customer.getPhone());
        ps.setString(3, customer.getName());

        int result = ps.executeUpdate();
        return result > 0;
        
    } catch (Exception e) {
        System.out.println("Error in adding customer: " + e.getMessage());
        return false;
    }
}

  public String findCustomer(String phone, int uid) {
    String name;
    String sql = "SELECT name FROM customers WHERE phone = ? AND uid=?";
 
    try (Connection conn = openConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, phone);
        ps.setInt(2, uid);

        try (ResultSet rs = ps.executeQuery()) {
          
            if (rs.next()) { // Check if a record exists
                name = rs.getString("name"); // Retrieve the name from the result set
                return name;
            }
        
        }
    } catch (Exception e) {
        System.out.println("Error in fetching Customer: " + e.getMessage());
    }
    return null;
   
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
