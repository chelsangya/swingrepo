package sageapp.DAO;

import sageapp.database.MySqlConnection;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import sageapp.controller.SMTPSMailSender;
import sageapp.model.AuthData;
import sageapp.model.LoginModel;
import sageapp.model.RegisterModel;
import sageapp.model.UpdatePasswordModel;
import sageapp.model.UpdateProfileModel;
import sageapp.model.UpdateProfilePictureModel;

public class AuthDAO extends MySqlConnection {
    
   public boolean register(RegisterModel user) {
    String createTableSQL = "CREATE TABLE IF NOT EXISTS users ("
            + "id INT AUTO_INCREMENT PRIMARY KEY, "               
            + "fname VARCHAR(50) NOT NULL, "
            + "lname VARCHAR(50) NOT NULL, "
            + "email VARCHAR(100) UNIQUE NOT NULL, "
            + "password VARCHAR(255) NOT NULL, "
            + "image BLOB NOT NULL"
            + ")";

    String sql = "INSERT INTO users(fname, lname, email, password, image) VALUES(?,?,?,?,?)";
    
    try (Connection conn = openConnection();
         PreparedStatement createTableStmt = conn.prepareStatement(createTableSQL);
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        // Create table if not exists
        createTableStmt.executeUpdate();
        
        
        ps.setString(1, user.getFname());
        ps.setString(2, user.getLname());
        ps.setString(3, user.getEmail());
        ps.setString(4, user.getPassword());
        ps.setBytes(5, user.getImage()); // Directly use byte[] image

        int result = ps.executeUpdate();
        return result > 0;
        
    } catch (SQLException e) {
        System.out.println("Error in register: " + e.getMessage());
        return false;
    }
}

    
public AuthData login(LoginModel login) {
        try {
            Connection conn = openConnection();
            String sql = "SELECT * FROM users WHERE email = ? and password = ?";
            
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, login.getEmail());
                ps.setString(2, login.getPassword());

                ResultSet result = ps.executeQuery();
                
                if (result != null && result.next()) {
                    int uid = result.getInt("id");  // Fixing uid reference
                    String email = result.getString("email");
                    String password = result.getString("password");
                    String fname = result.getString("fname");
                    String lname = result.getString("lname");
                    byte[] image = result.getBytes("image");

                    return new AuthData(uid, fname, lname, email, password, image);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error in login: " + e.getMessage());
        }
        return null;
    }

public AuthData getUser(int uid) {
        try {
            Connection conn = openConnection();
            String sql = "SELECT * FROM users WHERE id = ?";  // Fixing incorrect uid reference
            
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, uid);
                ResultSet result = ps.executeQuery();

                if (result != null && result.next()) {
                    String email = result.getString("email");
                    String password = result.getString("password");
                    String fname = result.getString("fname");
                    String lname = result.getString("lname");
                    byte[] image = result.getBytes("image");

                    return new AuthData(uid, fname, lname, email, password, image);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error in getUser: " + e.getMessage());
        }
        return null;
    }

    public AuthData updateProfile(UpdateProfileModel profile, int uid) throws SQLException {
        String sql = "UPDATE users SET email = ?, fname = ?, lname = ? WHERE id = ?";  // Fixing column names
        
        try (Connection conn = openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, profile.getEmail());
            ps.setString(2, profile.getFirstName());
            ps.setString(3, profile.getLastName());
            ps.setInt(4, uid);
            
            int rowsUpdated = ps.executeUpdate();
            
            if (rowsUpdated > 0) {
                return getUser(uid);
            } else {
                throw new SQLException("No rows updated. User may not exist.");
            }
            
        } catch (SQLException e) {
            System.err.println("Failed to update profile: " + e.getMessage());
            throw e;
        }
    }

    public AuthData updateProfilePicture(UpdateProfilePictureModel profile, int uid) throws SQLException {
        String sql = "UPDATE users SET image = ? WHERE id = ?";  // Fixing column names
        
        try (Connection conn = openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setBytes(1, profile.getImage());
            ps.setInt(2, uid);
            
            int rowsUpdated = ps.executeUpdate();
            
            if (rowsUpdated > 0) {
                return getUser(uid);
            } else {
                throw new SQLException("No rows updated. User may not exist.");
            }
            
        } catch (SQLException e) {
            System.err.println("Failed to update profile: " + e.getMessage());
            throw e;
        }
    }
    
    public AuthData updatePassword(UpdatePasswordModel profile, int uid) throws SQLException{
         String sql = "UPDATE users SET password = ? WHERE id = ?";  // Fixing column names
        
        try (Connection conn = openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            System.out.println("New Password: "+profile.getPassword());
            System.out.println("UID: "+uid);
            ps.setString(1, profile.getPassword());
            ps.setInt(2, uid);
            
            int rowsUpdated = ps.executeUpdate();
            
            if (rowsUpdated > 0) {
                return getUser(uid);
            } else {
                throw new SQLException("No rows updated. User may not exist.");
            }
            
        } catch (SQLException e) {
            System.err.println("Failed to update password: " + e.getMessage());
            throw e;
        }
    }
    public int getUserIdByEmail(String email){
       int id;
     try {
            Connection conn = openConnection();
         
            String sql = "SELECT id FROM users WHERE email = ?";  // Fixing incorrect uid reference
            
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, email);
                ResultSet result = ps.executeQuery();

                if (result != null && result.next()) {   
                  id=result.getInt("id");
                  return id;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error in getUser: " + e.getMessage());
       
         
        }
    return -1;
}
    
   // Method to generate a random OTP
    public String generateOTP() {
        Random rand = new Random();
        int otp = 100000 + rand.nextInt(900000);  // Generate a 6-digit OTP
        return String.valueOf(otp);
    }

   // Method to send OTP via email
    public boolean sendOTPEmail(String email, String otp) {
    String subject = "Password Reset Code";
    String messageBody = "Your OTP code is: " + otp;

    System.out.println("Sending OTP to: " + email);

    boolean isSent;
    isSent = SMTPSMailSender.sendMail(email, subject, messageBody);
//isSent=false;
    if (isSent) {
        System.out.println("OTP sent successfully to: " + email);
    } else {
        System.err.println("Failed to send OTP to: " + email);
    }

    return isSent;
}
    // Method to generate OTP and send it via email
    public String generateAndSendOTP(String email) {
        // Generate the OTP
        String otp = generateOTP();

        // Send the OTP via email
        boolean emailSent= sendOTPEmail(email, otp);
        if (emailSent){
            return otp;
        } else {
            return null;
        }
    }

}
