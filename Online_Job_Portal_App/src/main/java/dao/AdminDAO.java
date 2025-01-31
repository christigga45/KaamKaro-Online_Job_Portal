package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Admin;

public class AdminDAO {
     public String VALIDATE_ADMIN_SQL = 
        "SELECT admin_id FROM administrator WHERE username = ? AND password = ?";

    // Method to validate admin credentials
    public Admin validateAdmin(String username, String password) {
        Admin admin = null;
        try {
        	Connection con=DBConnect.getCon();
        	PreparedStatement stmt = con.prepareStatement(VALIDATE_ADMIN_SQL);
        	
            // Set query parameters
            stmt.setString(1, username);
            stmt.setString(2, password);

            // Execute query
           ResultSet rs = stmt.executeQuery() ;
                if (rs.next()) {
                    // If credentials are valid, create an Admin object
                    admin = new Admin();
                    admin.setAdminId(rs.getInt("Admin_id"));
                    admin.setUsername(username);
                 
                }
            
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        
        return admin;
    }
}
