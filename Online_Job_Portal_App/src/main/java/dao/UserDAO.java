package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class UserDAO {
    public boolean registerUser(User user) {
        String query = "INSERT INTO applicant (APPLICANT_ID,USERNAME, EMAIL, PASSWORD, CREATED_AT) VALUES (APPLICANT_SEQ.NEXTVAL, ?, ?, ?, ?)";
        try (Connection connection = DBConnect.getCon();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword()); // Hash the password if necessary
            preparedStatement.setTimestamp(4, user.getCreatedAt());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    public User authenticateUser(String username, String password) {
        User user = null;

        try (Connection conn = DBConnect.getCon();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM applicant WHERE username = ? AND password = ?")) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setApplicantId(rs.getInt("applicant_id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setCreatedAt(rs.getTimestamp("created_at"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
    
    public String getApplicantNameById(int applicantId) {
        String applicantName = null;
        String sql = "SELECT username FROM applicant WHERE applicant_id = ?"; // Assuming the table is `applicants`

        try (Connection con = DBConnect.getCon();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, applicantId);  // Set applicantId in the query

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                applicantName = rs.getString("username"); // Retrieve the applicant name
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return applicantName;  // Return the applicant name, or null if not found
    }
}

