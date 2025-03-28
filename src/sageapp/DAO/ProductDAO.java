

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sageapp.DAO;
import sageapp.database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sageapp.model.ProductData;
import sageapp.model.ProductModel;
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
public class ProductDAO extends MySqlConnection {
  public boolean addProduct(ProductModel product, Integer uid) {
    String createTableSQL = "CREATE TABLE IF NOT EXISTS products ("
            + "id INT AUTO_INCREMENT PRIMARY KEY, "
            + "uid INT NOT NULL, "
            + "name VARCHAR(100) NOT NULL, "
            + "description TEXT, "
            + "price INT NOT NULL, "
            + "stock INT NOT NULL,"
            +" FOREIGN KEY (uid) REFERENCES users(id) ON DELETE CASCADE"
            + ")";

    String sql = "INSERT INTO products(uid, name, description, price, stock) VALUES(?,?,?,?,?)";

    try (Connection conn = openConnection();
         PreparedStatement createTableStmt = conn.prepareStatement(createTableSQL);
         PreparedStatement ps = conn.prepareStatement(sql)) {

        // Create table if not exists
        createTableStmt.executeUpdate();
        System.out.println("TABLE CREATED");

        // Insert product into table
        ps.setInt(1, uid);
        ps.setString(2, product.getName());
        ps.setString(3, product.getDescription());
        ps.setInt(4, product.getPrice());
        ps.setInt(5, product.getStock());

        int result = ps.executeUpdate();
        return result > 0;
        
    } catch (Exception e) {
        System.out.println("Error in addProduct: " + e.getMessage());
        return false;
    }
}

  public List<ProductData> viewAllProducts(int uid) {
      System.out.println("VIEW PRODUCTS");
      List<ProductData> products = new ArrayList<>();

      String sql = "SELECT * FROM products WHERE uid = ?";

    try (Connection conn = openConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        // Set the `uid` parameter before executing the query
        ps.setInt(1, uid);
        System.out.println("INT SET");
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ProductData product = new ProductData();

                // Get values from ResultSet
                int id = rs.getInt("id");
                product.setId(rs.getInt("id"));
                if (!rs.wasNull()) {
                    product.setUid(uid);
                }

                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getInt("price"));
                product.setStock(rs.getInt("stock"));

                products.add(product);
            }
        }
    } catch (Exception e) {
        System.out.println("Error in viewAllProducts: " + e.getMessage());
    }

    return products;
}
  
  public int countProductsByUid(int uid) {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM products WHERE uid = ?";

        try (Connection conn = openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, uid);
            System.out.println("Counting products for uid: " + uid);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1); // Retrieve the count value
                }
            }
        } catch (Exception e) {
            System.out.println("Error in counting products: " + e.getMessage());
        }

        return count;
    }
  
  public List<String> findProductNamesByUid(int uid){
       List<String> names= new ArrayList<>();
      String sql= "SELECT name from products WHERE uid=?";
      try{
          Connection conn= openConnection();
          PreparedStatement ps = conn.prepareStatement(sql );
          ps.setInt(1, uid);
          try {
              ResultSet rs= ps.executeQuery();
              while(rs.next()){
                  names.add(rs.getString("name"));
              }
          } catch(SQLException e){}
      }catch(SQLException e){}
      return names;
  }
  
   public int findProduct(String name) {
    int id = 000;
    String sql = "SELECT id FROM products WHERE name = ?";

    try (Connection conn = openConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, name);
        System.out.println("Executing query...");

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) { 
                id = rs.getInt("id"); 
            }
        }
    } catch (Exception e) {
        System.out.println("Error in fetching Customer: " + e.getMessage());
    }

    return id;
}
     
 public ProductData findProductByName(String name) {
    int id = 000;
    int uid;
    ProductData product= new ProductData();
    String sql = "SELECT * FROM products WHERE name = ?";

    try (Connection conn = openConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, name);
        System.out.println("Executing query...");

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {  
                product.setId(rs.getInt("id"));
                product.setUid(rs.getInt("uid"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getInt("price"));
                product.setStock(rs.getInt("stock"));

                
            }
        }
    } catch (Exception e) {
        System.out.println("Error in fetching Customer: " + e.getMessage());
    }
 System.out.println("PRODUCT FOUND"+product);
 return product;
}

}
